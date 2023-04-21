package vn.funix.FX18420.java.asm04.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.funix.FX18420.java.asm04.models.Account;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DigitalCustomerTest {

    private DigitalCustomer customer;

    @BeforeEach
    public void setup() throws IOException {
        customer = new DigitalCustomer("001215000001", "Nguyen Thi Phuong");
        customer.setAccounts();
    }

    //isPremium() should return false as there is no Premium account for this customer
    @Test
    void isPremium(){
        assertFalse(customer.isPremium());
    }

    //validateAccount() should return true as the account exist
    @Test
    void isAccountExisted(){
        assertTrue(customer.isAccountExisted(customer.getAccounts(), "123333"));
    }

    //the account's balance should be exact value get from database
    @Test
    void getAccountByAccountNumber() {
        assertEquals(75_000, customer.getAccountByAccountNumber(customer.getAccounts(), "123333").getBalance());
    }


    //total balance of the accounts should be 75_000
    @Test
    void getBalance(){
        assertEquals(75_000, customer.getBalance());
    }



}