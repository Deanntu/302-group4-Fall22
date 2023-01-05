package com.gurup.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gurup.domain.account.entity.AccountOperationResults;
import com.gurup.domain.account.manager.AccountManager;

public class TryLoginTest {
    
    @Test
    public void testLogin() {
        AccountManager Manager = new AccountManager();
        try {
            Manager.delete("esma");
            Manager.createAccount("esma", "12345", "12345", "me@eeray.com");
            assertEquals(AccountOperationResults.EMPTY_FIELD, Manager.loginAccount("", "", "", ""));
            assertEquals(AccountOperationResults.EMPTY_FIELD, Manager.loginAccount("", "123", "123", "me@eeray.com"));
            assertEquals(AccountOperationResults.EMPTY_FIELD, Manager.loginAccount("esma", "123", "123", ""));
            assertEquals(AccountOperationResults.WRONG_MAIL_FORMAT, Manager.loginAccount("esma", "123", "123", "a@acom"));
            assertEquals(AccountOperationResults.WRONG_CREDENTIAL, Manager.loginAccount("esmaa", "123", "123", "me@eeray.com"));
            assertEquals(AccountOperationResults.WRONG_CREDENTIAL, Manager.loginAccount("esma", "123", "123", "me@eeray.comm"));
            assertEquals(AccountOperationResults.SUCCESS, Manager.loginAccount("esma", "12345", "12345", "me@eeray.com"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}