package vn.funix.FX18420.java.asm04.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.funix.FX18420.java.asm04.models.Customer;

import javax.swing.text.Utilities;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DigitalBankTest {

    private DigitalBank activeBank;

    @BeforeEach
    public void setup() throws IOException {
        activeBank = new DigitalBank();
    }


    //The customer received should have defined Name
    @Test
    void getCustomerById() {
        Customer customer = activeBank.getCustomerById(activeBank.getCustomers(),"001215000001");
        assertEquals("Nguyen Thi Phuong",customer.getName());
    }

    //isCustomerExisted() should return Null as no customer found
    @Test
    void isCustomerExisted() {
        assertNull(activeBank.getCustomerById(activeBank.getCustomers(),"001215000009"));
    }


}