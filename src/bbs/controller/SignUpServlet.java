package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bbs.beans.Branch;
import bbs.beans.Post;
import bbs.beans.User;
import bbs.service.BranchService;
import bbs.service.PostService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException
	{
		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Post> posts = new PostService().getPost();
		request.setAttribute("posts", posts);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User editUser = getEditUser(request);
		session.setAttribute("editUser", editUser);

		if (isValid(request, messages) == true)
		{
			getEditUser(request);

			new UserService().register(editUser);

			response.sendRedirect("manageUser");
		}
		else
		{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages)
	{
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");

		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (loginId.length() < 6)
		{
			messages.add("ログインIDは6文字以上20文字以下で設定してください");
		}
		if (!password.equals(checkPassword))
		{
			messages.add("確認用パスワードが違います");

		}
		if (messages.size() == 0)
		{
			return true;
		} else {
			return false;
		}
	}

	private User getEditUser(HttpServletRequest request) throws IOException, ServletException
	{
		User editUser = new User();

		editUser.setLoginId(request.getParameter("loginId"));
		editUser.setName(request.getParameter("name"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branch")));
		editUser.setPostId(Integer.parseInt(request.getParameter("post")));

		return editUser;
	}


}
