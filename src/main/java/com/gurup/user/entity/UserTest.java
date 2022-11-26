package com.gurup.user.entity;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setMailAddress("aaa@gmail.com");
		user.setUserName("TestName");
		user.setUserID(0);
		user.setUserPassword("TestPass");
		System.out.println(user.getMailAddress());
	}

}
