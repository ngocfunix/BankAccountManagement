package vn.funix.FX18420.java.asm04.common;

import org.junit.jupiter.api.Test;
import vn.funix.FX18420.java.asm04.exception.CustomerIdNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIdValidatorTest {

    //validateCustomerId() should return false as 12-digit customerId is not valid
    @Test
    void customerIdValidation() throws CustomerIdNotValidException {
        Exception exception = assertThrows(Exception.class, () -> CustomerIdValidator.customerIdValidation("00av555"));
        assertEquals("CustomerIdNotValidException: Mã số khách hàng 00av555 không đúng", exception.getMessage());
    }
}