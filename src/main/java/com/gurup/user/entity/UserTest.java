package com.gurup.user.entity;

import com.gurup.user.manager.UserManager;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager um = new UserManager();
		User user = new User();
		user.setMailAddress("aaa@gmail.com");
		user.setUserName("TestName");
		user.setUserID(0);
		user.setUserPassword("TestPass");
		System.out.println(user.getMailAddress());
		try {
			um.create(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
