package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.User;
import bbs.dao.UserDao;
import bbs.utils.CipherUtil;


public class UserService
{

	public void register(User user)
	{
		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

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
	public List<User> getUser()
	{

		Connection connection = null;

		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getUser(connection);

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


	public User getSelectUser(int userId)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getSelectUser(connection, userId);

			commit(connection);

			return user;
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

	public void lock(int userId)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.lock(connection, userId);

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

	public void release(int userId)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.release(connection, userId);

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

	public void update(User user)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

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
