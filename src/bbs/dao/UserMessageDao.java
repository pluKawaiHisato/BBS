package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbs.beans.UserMessage;
import bbs.exception.SQLRuntimeException;

public class UserMessageDao
{

	public List<UserMessage> getUserMessages(Connection connection, int num)
	{

		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_messages ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

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

	public List<UserMessage> getSearchMessages
	(Connection connection, String category, String startDate, String endDate)
	{

		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_messages");
			sql.append(" WHERE 1");

			if (!StringUtils.isEmpty(category))
			{
				sql.append(" AND category = ?");
			}
			if (!StringUtils.isEmpty(startDate))
			{
				sql.append(" AND insert_date >= ?");
			}
			if (!StringUtils.isEmpty(endDate))
			{
				sql.append(" AND insert_date <= ?");
			}

			sql.append(" ORDER BY insert_date DESC ");

			ps = connection.prepareStatement(sql.toString());
			int i = 1;
			if (!StringUtils.isEmpty(category))
			{
				ps.setString(i++, category);
			}
			if (!StringUtils.isEmpty(startDate))
			{
				ps.setString(i++, startDate );
			}
			if (!StringUtils.isEmpty(endDate))
			{
				ps.setString(i++, endDate);
			}

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

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

	public List<UserMessage> getNewestDate(Connection connection)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM bbs.users_messages ORDER BY  insert_date DESC LIMIT 1 ");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

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

	public List<UserMessage> getOldestDate(Connection connection)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM bbs.users_messages ORDER BY insert_date LIMIT 1 ");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

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

	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int branchId = rs.getInt("branch_id");
				int postId = rs.getInt("post_id");
				int messageId = rs.getInt("message_id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserMessage message = new UserMessage();
				message.setLoginId(loginId);
				message.setName(name);
				message.setId(id);
				message.setUserId(userId);
				message.setBranchId(branchId);
				message.setPostId(postId);
				message.setMessageId(messageId);
				message.setCategory(category);
				message.setTitle(title);
				message.setText(text);
				message.setInsertDate(insertDate);
				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	private List<UserMessage> toCategoryList(ResultSet rs) throws SQLException
	{
		List<UserMessage> ret = new ArrayList<UserMessage>();
		try
		{
			while (rs.next())
			{
				String category = rs.getString("category");

				UserMessage categoryList = new UserMessage();

				categoryList.setCategory(category);

				ret.add(categoryList);
			}
			return ret;
		}
		finally
		{
			close(rs);
		}
	}
	public List<UserMessage> getCategory(Connection connection)
	{

		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT category FROM users_messages GROUP BY category");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toCategoryList(rs);

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


}

