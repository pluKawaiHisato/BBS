package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

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
		List<UserMessage> categoryList = new MessageService().getCategory();
		request.setAttribute("categoryList", categoryList);

		UserMessage message = new UserMessage();
		message.setCategory(request.getParameter("searchCategory"));

		String searchCategory = request.getParameter("searchCategory");
		String searchStartDate = request.getParameter("startDate");
		String searchEndDate = request.getParameter("endDate");
		//String newestDate = request.getParameter("newestDate");
		//String oldestDate = request.getParameter("oldestDate");

		List<UserMessage> newestDate = new MessageService().getNewestDate();
		request.setAttribute("newestDate", newestDate.get(0).getInsertDate());

		List<UserMessage> oldestDate = new MessageService().getOldestDate();
		request.setAttribute("newestDate", oldestDate.get(0).getInsertDate());


		request.setAttribute("selectCategory", searchCategory);

		if(StringUtils.isEmpty(searchCategory) == true){
			List<UserMessage> messages = new MessageService().getMessage();
			request.setAttribute("messages", messages);
		}else{
			List<UserMessage> searchMessages =
					new MessageService().getSearchMessages(searchCategory, searchStartDate, searchEndDate);
			request.setAttribute("messages", searchMessages);

		}



		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}