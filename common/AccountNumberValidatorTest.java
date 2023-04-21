package vn.funix.FX18420.java.asm04.common;

import org.junit.jupiter.api.Test;
import vn.funix.FX18420.java.asm04.exception.AccountNumberNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class AccountNumberValidatorTest {

    //validateAccount() should return true as 6-digit customerId is valid
    @Test
    void validateAccount() throws AccountNumberNotValidException {
        assertTrue(AccountNumberValidator.validateAccount("006555"));
    }
}