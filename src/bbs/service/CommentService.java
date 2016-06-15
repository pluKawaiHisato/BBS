package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Comment;
import bbs.beans.UserComment;
import bbs.dao.CommentDao;
import bbs.dao.UserCommentDao;

public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



	public List<UserComment> getComment()
	{

		Connection connection = null;
		try
		{
			connection = getConnection();

			UserCommentDao messageDao = new UserCommentDao();
			List<UserComment> ret = messageDao.getUserComments(connection);

			commit(connection);

			return ret;
		}
		catch (RuntimeException e)
		{
			rollback(connection);
			throw e;
		}
		catch (Error e)
		{
			rollback(connection);
			throw e;
		}
		finally
		{
			close(connection);
		}
	}

	public void deleteComment(int commentId)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.deleteComment(connection, commentId);


			commit(connection);
		}
		catch (RuntimeException e)
		{
			rollback(connection);
			throw e;
		}
		catch (Error e)
		{
			rollback(connection);
			throw e;
		}
		finally
		{
			close(connection);
		}
	}
}