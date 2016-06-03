package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable
{
	private static final long serialVersionUID =1L;

	private int id;
	private int userId;
	private String text;
	private String title;
	private String category;
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

	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}

	public String getTitle()
		{
			return title;
		}
		public void setTitle(String title)
		{
			this.title = title;
		}

		public String getCategory()
		{
			return category;
		}
		public void setCategory(String category)
		{
			this.category = category;
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
