package vn.funix.FX18420.java.asm04.models;

import vn.funix.FX18420.java.asm04.dao.AccountDao;
import vn.funix.FX18420.java.asm04.exception.CustomerIdNotValidException;

import java.io.IOException;
import java.util.List;

public class DigitalCustomer extends Customer {

    public DigitalCustomer(String customerId, String name) throws IOException {
        super(customerId, name);
    }

    public DigitalCustomer(List<String> values) throws CustomerIdNotValidException, IOException {
        super(values);
    }

    //print transaction history
//    public void displayTransaction(String customerId) {
//
//        for (int i = 0; i < this.getAccounts().size(); i++) {
//            Account account = this.getAccounts().get(i);
//            List<Transaction> transactions = account.getTransactions();
//
//            for (int j = 0; j < transactions.size(); j++){
//                System.out.println(transactions.get(j).toString());
//            }
//        }}

    }
