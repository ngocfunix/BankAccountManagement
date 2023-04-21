package vn.funix.FX18420.java.asm04.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    private SavingsAccount account;

    @BeforeEach
    public void setup(){
        account = new SavingsAccount("123654",9_000_000,"001215000001");
    }

    //withdraw() should be true
    @Test
    void withdraw() {
        assertTrue(account.withdraw(50_000));
    }

    //transfer() should be false as the amount < 50_000
    @Test
    void transfer() {
        SavingsAccount receivedAccount = new SavingsAccount("123666",8_000_000,"001215000002");
        assertFalse(account.transfer(receivedAccount, 49_000));
    }

    //isAccepted() should be false as the amount > 5_000_000 for not Premium account
    @Test
    void isAccepted() {
        assertFalse(account.isAccepted(8_000_000));
    }
}