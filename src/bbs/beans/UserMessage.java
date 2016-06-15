package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String loginId;
	private String name;
	private int id;
	private int userId;
	private int branchId;
	private int postId;
	private int messageId;
	private String category;
	private String title;
	private String text;
	private Date insertDate;

	public String getLoginId()
	{
		return loginId;
	}
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

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

	public int getBranchId()
	{
		return branchId;
	}
	public void setBranchId(int branchId)
	{
		this.branchId = branchId;
	}

	public int getPostId()
	{
		return postId;
	}
	public void setPostId(int postId)
	{
		this.postId = postId;
	}

	public int getMessageId()
	{
		return messageId;
	}
	public void setMessageId(int messageId)
	{
		this.messageId = messageId;
	}

	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
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
