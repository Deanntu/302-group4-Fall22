package com.gurup.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gurup.domain.account.entity.AccountOperationResults;
import com.gurup.domain.account.manager.AccountManager;

public class TryRegisterTest {

    @Test
    public void testLogin() {
        AccountManager Manager = new AccountManager();
        try {
            Manager.delete("tugra");
            assertEquals(AccountOperationResults.EMPTY_FIELD, Manager.createAccount("", "", "", ""));
            assertEquals(AccountOperationResults.SUCCESS,
                    Manager.createAccount("tugra", "12345", "12345", "me@deanntu.com"));
            assertEquals(AccountOperationResults.MAIL_EXISTS,
                    Manager.createAccount("demirel", "123", "123", "me@deanntu.com"));
            assertEquals(AccountOperationResults.USERNAME_EXISTS,
                    Manager.createAccount("tugra", "12345", "12345", "admin@deanntu.com"));
            assertEquals(AccountOperationResults.WRONG_MAIL_FORMAT,
                    Manager.createAccount("deanntu", "123", "123", "a@acom"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
