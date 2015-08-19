package com.agilet.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AdminUserEntity {
	// exam_admin_userid long
	// exam_admin_user_login_name string
	// exam_admin_user_login_password string
	// exam_admin_user_authority long
	// exam_admin_user_active boolean
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String userName;

	@Persistent
	private String passWord;

	@Persistent
	private long authority;

	@Persistent
	private String field1;

	@Persistent
	private String field2;

	@Persistent
	private String field3;

	@Persistent
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public long getAuthority() {
		return authority;
	}

	public void setAuthority(long authority) {
		this.authority = authority;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
