package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.User;
import bbs.exception.NoRowsUpdatedRuntimeException;
import bbs.exception.SQLRuntimeException;

public class UserDao
{
	public User getUser(Connection connection, String loginId, String password)
	{
		PreparedStatement ps = null;
		try
		{
			String sql = "SELECT * FROM bbs.users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true)
			{
				return null;
			}
			else if (2 <= userList.size())
			{
				throw new IllegalStateException("2 <= userList.size()");
			}
			else
			{
				return userList.get(0);
			}
		}
		catch (SQLException e)
		{
			throw new SQLRuntimeException(e);
		}
		finally
		{
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException
	{
		List<User> ret = new ArrayList<User>();
		try
		{
			while (rs.next())
			{
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int branchId = rs.getInt("branch_id");
				int postId = rs.getInt("post_id");
				int status = rs.getInt("status");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setName(name);
				user.setPassword(password);
				user.setBranchId(branchId);
				user.setPostId(postId);
				user.setStatus(status);

				ret.add(user);
			}
			return ret;
		}
		finally
		{
			close(rs);
		}
	}

	public void insert(Connection connection, User user)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO bbs.users ( ");
			sql.append("login_id");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", password");
			sql.append(", post_id");
			sql.append(") VALUE (");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getBranchId());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getPostId());

			//System.out.println(ps);

			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new SQLRuntimeException(e);
		}
		finally
		{
			close(ps);
		}
	}

	public List<User> getUser(Connection connection)
	{

		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);

			return ret;
		}
		catch (SQLException e)
		{
			throw new SQLRuntimeException(e);
		}
		finally
		{
			close(ps);
		}
	}

	public void update(Connection connection, User user)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("INSERT INTO bbs.users ( ");
			sql.append("login_id");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", password");
			sql.append(", post_id");
			sql.append(", status");

			sql.append(" WHERE");
			sql.append(" id = ?");
			sql.append(" AND");
			sql.append(" update_date = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getBranchId());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getPostId());
			ps.setInt(6, user.getId());
			ps.setInt(7, user.getStatus());

			System.out.println(ps);
			int count = ps.executeUpdate();
			if (count == 0)
			{
				throw new NoRowsUpdatedRuntimeException();
			}
		}
		catch (SQLException e)
		{
			throw new SQLRuntimeException(e);
		}
		finally
		{
			close(ps);
		}
	}

	public void lock(Connection connection, int userId)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" status = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, 1);
			ps.setInt(2, userId);

			System.out.println(ps);
			int count = ps.executeUpdate();
			if (count == 0)
			{
				throw new NoRowsUpdatedRuntimeException();
			}
		}
		catch (SQLException e)
		{
			throw new SQLRuntimeException(e);
		}
		finally
		{
			close(ps);
		}
	}

	public void release(Connection connection, int userId)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" status = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, 0);
			ps.setInt(2, userId);

			System.out.println(ps);
			int count = ps.executeUpdate();
			if (count == 0)
			{
				throw new NoRowsUpdatedRuntimeException();
			}
		}
		catch (SQLException e)
		{
			throw new SQLRuntimeException(e);
		}
		finally
		{
			close(ps);
		}
	}

	public User getSelectUser(Connection connection, int userId)
	{
		PreparedStatement ps = null;
		try
		{
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true)
			{
				return null;
			}
			else if (2 <= userList.size())
			{
				throw new IllegalStateException("2 <= userList.size()");
			}
			else
			{
				return userList.get(0);
			}
		}
		catch (SQLException e)
		{
			throw new SQLRuntimeException(e);
		}
		finally
		{
			close(ps);
		}
	}
}
