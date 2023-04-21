package vn.funix.FX18420.java.asm04.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vn.funix.FX18420.java.asm04.dao.CustomerDao;
import vn.funix.FX18420.java.asm04.models.Customer;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryFileServiceTest {

    //Hàm này không throw Exception do đã có try-catch block, dù đường dẫn file bị sai
    @Test
    void readFile() {
        assertDoesNotThrow(() -> BinaryFileService.readFile("store/filex.txt"), "store\\filex.txt (The system cannot find the file specified)");
    }

    //Hàm này không throw Exception do đã có try-catch block, dù list đối tượng customer là null
    @Test
    void writeFile() {
        List<Customer> customers = null;
        assertDoesNotThrow(() -> BinaryFileService.writeFile("store/ForTest.txt", customers), "NullPointerException");
    }
}
