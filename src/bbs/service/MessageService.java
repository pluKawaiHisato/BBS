package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
//import java.util.List;
import java.util.List;

import bbs.beans.Message;
import bbs.beans.UserMessage;
import bbs.dao.MessageDao;
import bbs.dao.UserMessageDao;

public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, message);

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

	public List<UserMessage> getCategory()
	{

		Connection connection = null;

		try
		{
			connection = getConnection();

			UserMessageDao userMessageDao = new UserMessageDao();
			List<UserMessage> ret = userMessageDao.getCategory(connection);

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

	public List<UserMessage> getNewestDate()
	{

		Connection connection = null;

		try
		{
			connection = getConnection();

			UserMessageDao userMessageDao = new UserMessageDao();
			List<UserMessage> ret = userMessageDao.getNewestDate(connection);

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

	public List<UserMessage> getOldestDate()
	{

		Connection connection = null;

		try
		{
			connection = getConnection();

			UserMessageDao userMessageDao = new UserMessageDao();
			List<UserMessage> ret = userMessageDao.getOldestDate(connection);

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

	public List<UserMessage> getSearchMessages(String category, String startDate, String endDate)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();


			UserMessageDao searchMessageDao = new UserMessageDao();
			List<UserMessage> ret = searchMessageDao.getSearchMessages(connection, category, startDate, endDate);

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

	private static final int LIMIT_NUM = 1000;


	public List<UserMessage> getMessage()
	{

		Connection connection = null;
		try
		{
			connection = getConnection();


			UserMessageDao allMessageDao = new UserMessageDao();
			List<UserMessage> ret = allMessageDao.getUserMessages(connection, LIMIT_NUM);

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

	public void deleteMessage(int messageId)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.deleteMessage(connection, messageId);


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
