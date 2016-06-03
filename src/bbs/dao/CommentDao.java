package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Comment;
import bbs.exception.SQLRuntimeException;

public class CommentDao
{
	public void insert (Connection connection, Comment comment)
	{
		PreparedStatement ps = null;
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("user_id");
			sql.append(", message_id");
			sql.append(", text");
			sql.append(", insert_date");
			sql.append(") VALUE (");
			sql.append("?");//user_id
			sql.append(", ?");
			sql.append(", ?");//comment
			sql.append(", CURRENT_TIMESTAMP");//insert
			sql.append(");");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getUserId());
			ps.setInt(2, comment.getMessageId());
			ps.setString(3, comment.getComment());

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
}
