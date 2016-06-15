package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String name;
	private String password;
	private String branchName;
	private String postName;
	private int branchId;
	private int postId ;
	private Date insertDate;
	private Date updateDate;
	private int status;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId ;
	}

	public String getBranchName()
	{
		return branchName;
	}
	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	public String getPostName()
	{
		return postName;
	}
	public void setPostName(String postName)
	{
		this.postName = postName;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getBranchId()
	{
		return branchId;
	}

	public void setBranchId(int branchId)
	{
		this.branchId = branchId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getPostId()
	{
		return postId;
	}

	public void setPostId(int postId)
	{
		this.postId = postId;
	}

	public Date getInsertDate()
	{
		return insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

}
