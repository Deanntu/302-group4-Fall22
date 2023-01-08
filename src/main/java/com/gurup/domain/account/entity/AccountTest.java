package com.gurup.domain.account.entity;

import com.gurup.domain.account.manager.AccountManager;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountManager um = new AccountManager();
		try {
			System.out.println(um.createAccount("TestName", "TestPass", "TestPass", "aaa@gmail.com"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
