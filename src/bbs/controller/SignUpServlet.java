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
		request.setAttribute("editUser", editUser);

		if (isValid(request, messages) == true)
		{
			getEditUser(request);
			new UserService().register(editUser);

			messages.add("新規ユーザーの登録が完了しました");
			session.setAttribute("completeMessages", messages);

			response.sendRedirect("manageUser");
		}
		else
		{
			List<Branch> branches = new BranchService().getBranch();
			request.setAttribute("branches", branches);

			List<Post> posts = new PostService().getPost();
			request.setAttribute("posts", posts);

			request.setAttribute("editUser", editUser);
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages)
	{
		String name = request.getParameter("name");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int postId = Integer.parseInt(request.getParameter("postId"));

		UserService userService = new UserService();
		User checkUser = userService.getNewCheckSameLoginId(loginId);

		if (StringUtils.isBlank(name) == true)
		{
			messages.add("名前を入力してください");
		}
		else if (name.length() > 10)
		{
			messages.add("名前は10文字以下で設定してください");
		}

		if (checkUser != null)
		{
			messages.add("このログインIDはすでに使用されています");
		}
		if (StringUtils.isBlank(loginId) == true)
		{
			messages.add("ログインIDを入力してください");
		}
		else if (loginId.length() < 6)
		{
			messages.add("ログインIDは6文字以上20文字以下で設定してください");
		}
		else if (loginId.length() > 20)
		{
			messages.add("ログインIDは6文字以上20文字以下で設定してください");
		}

		if (StringUtils.isEmpty(password) == true)
		{
			messages.add("パスワードを入力してください");
		}
		if (StringUtils.isEmpty(password) != true)
		{
			if ( password.length() < 6)
			{
				messages.add("パスワードは6文字以上255文字以下で設定してください");
			}
			if ( password.length() > 255)
			{
				messages.add("パスワードは6文字以上255文字以下で設定してください");
			}
		}
		if (StringUtils.isEmpty(checkPassword) == true) {
			messages.add("確認用パスワードを入力してください");
		}
		else if (!password.equals(checkPassword))
		{
			messages.add("パスワードが一致しません");
		}

		if (branchId == 0)
		{
			messages.add("所属を選択してください");
		}
		if (postId == 0)
		{
			messages.add("部署・役職を選択してください");
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
		editUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		editUser.setPostId(Integer.parseInt(request.getParameter("postId")));

		return editUser;
	}


}