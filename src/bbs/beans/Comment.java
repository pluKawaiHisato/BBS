package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable
{
	private static final long serialVersionUID =1L;

	private int id;
	private int userId;
	private int messageId;
	private String comment;
	private Date insertDate;


	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getMessageId()
	{
		return messageId;
	}
	public void setMessageId(int messageId)
	{
		this.messageId = messageId;
	}

	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public Date getInsertDate()
	{
		return insertDate;
	}
	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}
}