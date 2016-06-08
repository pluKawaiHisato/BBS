package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.UserComment;
import bbs.beans.UserMessage;
import bbs.service.CommentService;
import bbs.service.MessageService;


@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{

//		if(submit.equals("停止する")){
//
//		}else{
//			new UserService().release(editUserId);
//		}

		List<UserMessage> messages = new MessageService().getMessage();
		List<UserMessage> csMessages = new MessageService().getCategorySearchMessages();
		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("messages", messages);
		request.setAttribute("csMessages", csMessages);
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}