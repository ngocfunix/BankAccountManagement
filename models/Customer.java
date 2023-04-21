package vn.funix.FX18420.java.asm04.models;

import vn.funix.FX18420.java.asm04.common.AccountNumberValidator;
import vn.funix.FX18420.java.asm04.dao.AccountDao;
import vn.funix.FX18420.java.asm04.dao.CustomerDao;
import vn.funix.FX18420.java.asm04.exception.AccountNumberNotValidException;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends User{
    private List<SavingsAccount> accounts;

    public Customer(String customerId, String name) throws IOException {

        this.setName(name);
        try{
            this.setCustomerId(customerId);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }


    public Customer(List<String> values) throws IOException {
        this(values.get(0),values.get(1));
    }

    //Lấy account list cho Customer
    public void setAccounts() {
        this.accounts = AccountDao.list();
    }
    //===============================================

    //Lọc account thuộc về khách hàng qua số Id
    public List<SavingsAccount> getAccounts() {
        if(this.accounts == null)
            this.setAccounts();
        return accounts
                .stream()
                .filter(account -> account.getCustomerId().equals(this.getCustomerId()))
                .collect(Collectors.toList());
    }

    //Kiểm tra xem khách hàng có phải là Premium (tài khoản >= 10 triệu
    public boolean isPremium() {
        for (int i = 0; i < this.getAccounts().size(); i++) {
            Account checkedAccount = this.getAccounts().get(i);
            if (checkedAccount.isPremium()) {
                return true;
            }
        }
        return false;
    }

    //Thêm tài khoản
    public void addAccount(Scanner scanner) {
        SavingsAccount newAccount = new SavingsAccount();
        newAccount.input(scanner);
        newAccount.setCustomerId(this.getCustomerId());
        String checkedNumber = newAccount.getAccountNumber();
            if (!isAccountExisted(accounts,checkedNumber)) {
                accounts.add(newAccount);
                try{
                    AccountDao.save(accounts);
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("Tạo tài khoản thành công");
            } else {
                System.out.println("So tai khoan nay da ton tai!");
            }
    }

    //Kiểm tra xrem tài khoản có tồn tại trong list
    public boolean isAccountExisted(List<SavingsAccount> accountsList, String accountNumber) {
        if(accountsList!=null)
            return accountsList.stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber));
        return false;
    }

    //Lọc ra tài khoản theo số accountNumber
    public SavingsAccount getAccountByAccountNumber(List<SavingsAccount> accounts, String accountNumber){

        for (SavingsAccount account : accounts) {
            if (accountNumber.equals(account.getAccountNumber())) {
                return account;
            }
        }
        return null;
    }

    //Rút tiền từ 1 trong các tài khoản của khách hàng
    public void withdraw(Scanner scanner) {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.next();
        try {
            AccountNumberValidator.validateAccount(accountNumber);
        } catch (AccountNumberNotValidException e) {
            System.out.println(e);
        }

        if (this.isAccountExisted(this.getAccounts(), accountNumber)) {
            SavingsAccount account = getAccountByAccountNumber(accounts, accountNumber);
            if(account.getTransactions().size() == 0) System.out.println("Tài khoản này chưa có giao dịch nào!");
            System.out.print("Nhập số tiền rút: ");
            double amount = scanner.nextDouble();
            System.out.println();
            if(account != null){
                ((IWithdraw) account).withdraw(amount);
            }
        }else {
            System.out.println("So tai khoan " + accountNumber + " khong ton tai");
        }
    }

    //Chuyển tiền từ một trong các tài khoản của khách hàng
    public SavingsAccount transfer(Scanner scanner) {
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = scanner.next();
        try{
            AccountNumberValidator.validateAccount(accountNumber);
        }catch (AccountNumberNotValidException e){
            System.out.println(e);
        }
        if (this.isAccountExisted(this.getAccounts(),accountNumber)) {
            SavingsAccount account = getAccountByAccountNumber(accounts, accountNumber);
            if(account.getTransactions().size() == 0) System.out.println("Tài khoản này chưa có giao dịch nào!");
            System.out.print("Nhập số tài khoản nhận (hoặc 'exit' nếu muốn thoát): ");
            String receivedAccountNumber = scanner.next();
            if(receivedAccountNumber.equals("exit")){
                return null;
            } else if (receivedAccountNumber.equals(account.getAccountNumber())) {
                System.out.println("Lỗi: Số tài khoản trùng với tài khoản người gửi");
            }else {
                try{
                    AccountNumberValidator.validateAccount(receivedAccountNumber);
                }catch (AccountNumberNotValidException e){
                    System.out.println(e);
                }
                if (this.isAccountExisted(accounts,receivedAccountNumber)) {
                    SavingsAccount receivedAccount = getAccountByAccountNumber(accounts, receivedAccountNumber);
                    if(receivedAccount.getTransactions().size() == 0) System.out.println("Tài khoản này chưa có giao dịch nào!");
                    System.out.print("Nhập số tiền chuyển: ");
                    double amount = scanner.nextDouble();
                    System.out.println();
                    account.transfer(receivedAccount, amount);
                }else {
                    System.out.println("So tai khoan " + receivedAccountNumber + " khong ton tai");
                }
            }
        }else {
            System.out.println("So tai khoan " + accountNumber + " khong ton tai");
        }
        return null;
    }

    //Tính tổng balance các tài khoản của mỗi khách hàng
    public double getBalance() {
        double totalBalance = 0;
        for (Account account : this.getAccounts()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    //In thông tin khách hàng và các tài khoản
    public void displayInformation() {
        DecimalFormat df = new DecimalFormat("###,##0.00#");
        if (this.isPremium()) {
            System.out.printf("%s%-35s%s%22s\n",this.getCustomerId() + "   |  ", this.getName() , "  |  Premium  |  " , df.format(this.getBalance()) + "đ");
        } else {
            System.out.printf("%s%-35s%s%22s\n",this.getCustomerId() + "   |  " , this.getName() , "  |   Normal  |  " , df.format(this.getBalance()) + "đ");
        }

        if(this.getAccounts().size() != 0){
            for (int i = 0; i < this.getAccounts().size(); i++) {
                System.out.println((i + 1) + "     " + this.getAccounts().get(i).toString());
            }
        }

    }

    public void displayTransactions(){
        for (Account account:this.getAccounts()) {
            account.displayTransactionsList();
        }
    }


}
