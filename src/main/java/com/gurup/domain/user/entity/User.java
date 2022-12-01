package com.gurup.domain.user.entity;

public class User {
	private long userID;
	private String userName;
	private String userPassword;
	private String mailAddress;

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public User(long userID, String userName, String userPassword, String mailAddress) {
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.mailAddress = mailAddress;
	}
	public User() {
		
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

}