package vn.funix.FX18420.java.asm04.models;

import vn.funix.FX18420.java.asm04.dao.TransactionDao;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Account implements Serializable{
    private String accountNumber;
    private double balance;
    private String customerId;
    private List<Transaction> transactions = TransactionDao.list();

    private static final long serialVersionUID = 2L;

    public Account(String accountNumber, double balance, String customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public Account() {
    }

    //Lọc list giao dịch trong tài khoản thông qua số accountNumber
    public List<Transaction> getTransactions() {
        if(transactions.size() != 0)
            return transactions
                .stream()
                .filter(transaction -> transaction.getAccountNumber().equals(this.getAccountNumber()))
                .collect(Collectors.toList());
        return new ArrayList<>();
    }

    //Gán customerId cho tài khoản
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    //Lấy customerId của tài khoản
    public String getCustomerId() {
        return customerId;
    }

    //Thêm giao dịch cho tài khoản
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        try{
            TransactionDao.save(transactions);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Lấy balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    //Kiểm tra xem tài khoản có phải là Premium không
    public boolean isPremium(){
        return this.balance >= 10_000_000;
    }

    //Tạo chuỗi thông tin tài khoản
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,##0.00#");
        return String.format("%s%22s", this.getAccountNumber() + "   |  SAVINGS                              |              " , df.format(this.getBalance()) + "đ");
    }

    //In danh sách giao dịch của tài khoản
    public void displayTransactionsList(){
        for (Transaction transaction : this.getTransactions()){
            System.out.println(transaction.toString());
        }
    }

    //Tạo 1 giao dịch mới khi khách hàng rút, chuyển tiền
    public Transaction createTransaction(double amount, boolean status, String type){
        Transaction transaction = new Transaction(this.accountNumber, amount);
        transaction.setType(type);
        transaction.setStatus(status);
        return transaction;
    }

    //Kiểm tra số accountNumber hợp lệ
    public static boolean accountNumberValidation(String accountNumber){
        if (accountNumber.length() != 6) {
            return false;
        }
        for (int i = 0; i < accountNumber.length(); i++) {
            if (!Character.isDigit(accountNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //Kiểm tra và gán thông tin cho tài khoản mới
    public void input(Scanner scanner){
        System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
        String accountNumber = scanner.next();
        while (!accountNumberValidation(accountNumber)) {
            System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
            accountNumber = scanner.next();
            System.out.println();
        }
        System.out.println("Nhập số dư tài khoản >= 50000đ: ");
        double balanceInput = scanner.nextDouble();
        while (balanceInput < 50_000) {
            System.out.println("Nhập số dư tài khoản >= 50000đ: ");
            balanceInput = scanner.nextDouble();
        }

        this.accountNumber = accountNumber;
        this.balance = balanceInput;
        }

}



