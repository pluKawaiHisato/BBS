package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

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
		//カテゴリー検索の選択肢
		List<UserMessage> categoryList = new MessageService().getCategory();
		request.setAttribute("categoryList", categoryList);

		//ユーザーが選択したカテゴリーの保持
		String searchCategory = request.getParameter("searchCategory");
		request.setAttribute("selectedCategory", searchCategory);

		//日時検索の初期値（一番古い投稿から最新の投稿）
		List<UserMessage> newestDate = new MessageService().getNewestDate();
		request.setAttribute("newestDate", newestDate.get(0).getInsertDate());

		List<UserMessage> oldestDate = new MessageService().getOldestDate();
		request.setAttribute("Date", oldestDate.get(0).getInsertDate());

		//ユーザーが選択した日時の保持
		String searchStartDate = request.getParameter("startDate");
		request.setAttribute("startDate", searchStartDate);

		String searchEndDate = request.getParameter("endDate");
		request.setAttribute("endDate", searchEndDate);


		//選択した日時に時間を追加して返す（1日検索）
		if (StringUtils.isNullOrEmpty(searchStartDate) != true)
		{
			searchStartDate = (searchStartDate + " 00:00:00");
		}




		if (StringUtils.isNullOrEmpty(searchEndDate) != true)
		{
			searchEndDate = (searchEndDate + " 23:59:59");
		}



		//全件表示
		List<UserMessage> messages = new MessageService().getMessage();
		request.setAttribute("messages", messages);

		List<String> errorMessages = new ArrayList<>();

		//検索の際、ユーザーが選択した値で検索したリストを返す
		List<UserMessage> searchMessages =new MessageService().getSearchMessages
			(searchCategory, searchStartDate , searchEndDate );



		if(isValid(request, errorMessages, searchMessages) == true)
		{
			request.setAttribute("articleErrorMessages", errorMessages);
		}
		request.setAttribute("messages", searchMessages);



		List<UserComment> comments = new CommentService().getComment();

		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request, response);


	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
//	{
//		HttpSession session = request.getSession();
//
//		List<String> messages = new ArrayList<String>();
//
//		if(isValid(request, messages) == true)
//		{
//			response.sendRedirect("home");
//		}
//		else
//		{
//			session.setAttribute("articleErrorMessages", messages);
//			response.sendRedirect("home");
//		}
//	}

	private boolean isValid(HttpServletRequest request, List<String> messages, List<UserMessage> searchMessages)
	{

		if (searchMessages.size() == 0)
		{
			messages.add("該当する記事がありません");
			return true;
		}else
		{
			return false;
		}
	}
}