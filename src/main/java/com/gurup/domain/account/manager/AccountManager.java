package com.gurup.domain.account.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.gurup.domain.account.entity.Account;
import com.gurup.domain.account.entity.AccountOperationResults;

public class AccountManager {
	// To be filled after deciding the database.
	private final String url = "jdbc:postgresql://localhost/gurup";
	private final String username = "postgres";
	private final String password = "123456";
	private final String driver = "org.postgresql.Driver";
	private final String emptyEntry = "";

	public AccountManager() {
		try {
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AccountOperationResults loginAccount(String username, String password1, String password2, String mail) throws Exception {
		// EFFECTS: Checks for the credentials and returns the login result to be processed by Game
		// REQUIRES: An account that matches the given credentials to exist in the database to log in
		// Also requires that password1 and password2 are equal as well as the mail to be in a valid email address format
		Account account = new Account();
		if(password1.length() < 8)
		    return AccountOperationResults.SHORT_PASSWORD;
		if (!password1.equals(password2))
			return AccountOperationResults.PASSWORD_MISMATCH;
		if (isEmptyField(username, password1, mail))
			return AccountOperationResults.EMPTY_FIELD;
		if (!checkProperMail(mail))
			return AccountOperationResults.WRONG_MAIL_FORMAT;
		account.setUserName(username);
		account.setUserPassword(password1);
		account.setMailAddress(mail);

		return tryLoginAccount(account);
	}
	public AccountOperationResults createAccount(String username, String password1, String password2, String mail)
			throws Exception {
		/*
		 *EFFECTS: Checks for the credentials and creates an account if they match the requirements
		 *REQUIRES: A set of credentials to be given that do not already exist in the database
		 *Also requires that password1 and password2 are equal as well as the mail to be in a valid email address format
		 *MODIFIES: The database
		 */

		Account account = new Account();
		if(password1.length() < 8)
            return AccountOperationResults.SHORT_PASSWORD;
		if (!password1.equals(password2))
			return AccountOperationResults.PASSWORD_MISMATCH;
		if (isEmptyField(username, password1, mail))
			return AccountOperationResults.EMPTY_FIELD;
		if (!checkProperMail(mail))
			return AccountOperationResults.WRONG_MAIL_FORMAT;
		account.setUserName(username);
		account.setUserPassword(password1);
		account.setMailAddress(mail);

		return tryCreateAccount(account);
	}

	private boolean isEmptyField(String username, String password, String mail) {
		return username.equals(emptyEntry) || password.equals(emptyEntry) || mail.equals(emptyEntry);
	}

	private boolean checkProperMail(String mail) {
		String regexPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{1,6}$";
		return patternMatches(mail, regexPattern);
	}

	private static boolean patternMatches(String emailAddress, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}

	private AccountOperationResults tryLoginAccount(Account account) throws Exception {

		if (findByMail(account.getMailAddress()) == null || findByUserName(account.getUserName()) == null)
			return AccountOperationResults.WRONG_CREDENTIAL;
		Account temp = findByUserName(account.getUserName());
		if (temp.getMailAddress().equals(account.getMailAddress())
				&& temp.getUserPassword().equals(account.getUserPassword())) {
			return AccountOperationResults.SUCCESS;
		}
		return AccountOperationResults.WRONG_CREDENTIAL;
	}

	private AccountOperationResults tryCreateAccount(Account account) throws Exception {
		if (findByMail(account.getMailAddress()) != null)
			return AccountOperationResults.MAIL_EXISTS;
		if (findByUserName(account.getUserName()) != null)
			return AccountOperationResults.USERNAME_EXISTS;
		if (create(account))
			return AccountOperationResults.SUCCESS;
		return AccountOperationResults.FAILED;

	}

	private boolean create(Account user) throws Exception {
		Connection connection = DriverManager.getConnection(url, username, password);

		String sql = "insert into useraccount(username, userpassword,mail) values (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getUserName());
		statement.setString(2, user.getUserPassword());
		statement.setString(3, user.getMailAddress());
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0;
	}

	private boolean update(Account user) throws Exception {
		Connection connection = DriverManager.getConnection(url, username, password);

		String sql = "update useraccount set userpassword=? where username=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(2, user.getUserName());
		statement.setString(1, user.getUserPassword());
		int affected = statement.executeUpdate();

		connection.close();
		return affected > 0;
	}

	public boolean delete(String username) throws Exception {
		Connection connection = DriverManager.getConnection(url, this.username, password);
		String sql = "delete from useraccount where username =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		int affected = statement.executeUpdate();
		connection.close();
		return affected > 0;
	}

	private Account findByUserName(String userUserName) throws Exception {
		Account user = null;

		Connection connection = DriverManager.getConnection(url, username, password);
		String sql = "select * from useraccount where username = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userUserName);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			user = parse(resultSet);
		}
		connection.close();
		return user;
	}

	private Account findByMail(String mail) throws Exception {
		Account user = null;

		Connection connection = DriverManager.getConnection(url, username, password);
		String sql = "select * from useraccount where mail = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, mail);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			user = parse(resultSet);
		}
		connection.close();
		return user;
	}

	private Account parse(ResultSet resultSet) throws Exception {
		String userName = resultSet.getString("username");
		String userPassword = resultSet.getString("userpassword");
		String address = resultSet.getString("mail");
		Account user = new Account(userName, userPassword, address);
		return user;
	}

	public List<Account> list() throws Exception {

		Connection connection = DriverManager.getConnection(url, username, password);

		String sql = "select * from category";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		List<Account> userList = parseList(resultSet);

		connection.close();

		return userList;
	}

	private List<Account> parseList(ResultSet resultSet) throws Exception {
		List<Account> userList = new ArrayList<>();
		while (resultSet.next()) {
			Account user = parse(resultSet);
			userList.add(user);
		}
		return userList;
	}
}