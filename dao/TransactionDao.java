package vn.funix.FX18420.java.asm04.dao;

import vn.funix.FX18420.java.asm04.models.Transaction;
import vn.funix.FX18420.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "store/transactions.dat";

    public static void save(List<Transaction> transactions) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }
}
