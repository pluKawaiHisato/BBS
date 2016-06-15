package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbs.beans.User;
import bbs.dao.UbpDao;
import bbs.dao.UserDao;
import bbs.utils.CipherUtil;


public class UserService
{

	public void register(User user)
	{
		Connection connection = null;
		try {
			connection = getConnection();
System.out.println(user.getPassword());
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
	public List<User> getUbp()
	{

		Connection connection = null;

		try
		{
			connection = getConnection();

			UbpDao ubpDao = new UbpDao();
			List<User> ret = ubpDao.getUbp(connection);

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

	public User getNewCheckSameLoginId(String loginId)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getNewCheckSameLoginId(connection, loginId);

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


	public User getCheckSameLoginId(String loginId, int id)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getCheckSameLoginId(connection, loginId, id);

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

			if(StringUtils.isEmpty(user.getPassword()))
			{

				userDao.notPassUpdate(connection, user);
			}
			else
			{
				String encPassword = CipherUtil.encrypt(user.getPassword());
				user.setPassword(encPassword);
				userDao.update(connection, user);
			}

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

	public void deleteUser(int userId)
	{
		Connection connection = null;
		try
		{
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.deleteUser(connection, userId);


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
