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

import bbs.beans.User;
import bbs.service.LoginService;

@WebServlet (urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
//		int status =Integer.parseInt(request.getParameter("status"));

		LoginService loginService = new LoginService();
		User user =loginService.login(loginId,password);

		HttpSession session = request.getSession();


		User editUser = getEditUser(request);


		session.setAttribute("loginUser", user);

		if (user != null)
		{
			if ( user.getStatus() == 1)
			{
				List<String> messages = new ArrayList<String>();
				messages.add("このアカウントは現在使用できません");
				session.setAttribute("errorMessages", messages);
				request.setAttribute("editUser", editUser);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				response.sendRedirect("home");
			}

		}
		else
		{
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました");
			request.setAttribute("editUser", editUser);
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	private User getEditUser(HttpServletRequest request) throws IOException, ServletException
	{
		User editUser = new User();

		editUser.setLoginId(request.getParameter("loginId"));


		return editUser;
	}
}

