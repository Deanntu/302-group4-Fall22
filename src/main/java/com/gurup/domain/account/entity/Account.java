package com.gurup.domain.account.entity;

public class Account {
	private long accountID;
	private String userName;
	private String userPassword;
	private String mailAddress;

	public long getUserID() {
		return accountID;
	}

	public void setUserID(long userID) {
		this.accountID = userID;
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

	public Account(String userName, String userPassword, String mailAddress) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.mailAddress = mailAddress;
	}

	public Account() {

	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

}