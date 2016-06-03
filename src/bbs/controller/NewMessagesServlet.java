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

import bbs.beans.Message;
import bbs.beans.User;
import bbs.service.MessageService;

@WebServlet (urlPatterns = { "/newMessages" })
public class NewMessagesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		request.getRequestDispatcher("newmessages.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages) == true)
		{
			User user = (User) session.getAttribute("loginUser");

			Message message = new Message();
			message.setText(request.getParameter("text"));
			message.setTitle(request.getParameter("title"));
			message.setCategory(request.getParameter("category"));
			message.setUserId(user.getId());

			new MessageService().register(message);

			response.sendRedirect("home");
		}
		else
		{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("newMessages");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages)
	{
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String message = request.getParameter("text");

		if (StringUtils.isEmpty(category) == true)
		{
			messages.add("カテゴリーを選択してください");
		}
		if (StringUtils.isEmpty(title) == true)
		{
			messages.add("件名を入力してください");
		}
		if (50 < title.length())
		{
			messages.add("件名は50文字以下で入力してください");
		}
		if (StringUtils.isEmpty(message) == true)
		{
			messages.add("メッセージを入力してください");
		}
		if (1000 < message.length())
		{
			messages.add("本文は1000文字以下で入力してください");
		}
		if (messages.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
