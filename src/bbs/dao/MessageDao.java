package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Message;
import bbs.exception.NoRowsUpdatedRuntimeException;
import bbs.exception.SQLRuntimeException;

public class MessageDao
{
	public void insert (Connection connection, Message message)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append("user_id");
			sql.append(", text");
			sql.append(", insert_date");
			sql.append(", title");
			sql.append(", category");
			sql.append(") VALUE (");
			sql.append("?");//user_id
			sql.append(", ?");//text
			sql.append(", CURRENT_TIMESTAMP");//insert
			sql.append(", ?");//title
			sql.append(", ?");//category
			sql.append(");");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getUserId());
			ps.setString(2, message.getText());
			ps.setString(3, message.getTitle());
			ps.setString(4, message.getCategory());

			System.out.println(ps);

			ps.executeUpdate();
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

	public void deleteMessage(Connection connection, int messageId)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM messages");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, messageId);

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

}