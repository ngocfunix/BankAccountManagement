package vn.funix.FX18420.java.asm04.dao;

import vn.funix.FX18420.java.asm04.models.Customer;
import vn.funix.FX18420.java.asm04.models.SavingsAccount;
import vn.funix.FX18420.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CustomerDao {
    private final static String FILE_PATH = "store/customers.dat";

    private final static int MAX_THREAD = 10;

    public static void save(List<Customer> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Customer> list() throws IOException {
        return BinaryFileService.readFile(FILE_PATH);
    }

}
