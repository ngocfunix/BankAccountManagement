package vn.funix.FX18420.java.asm04.models;

import vn.funix.FX18420.java.asm04.dao.AccountDao;
import vn.funix.FX18420.java.asm04.dao.TransactionDao;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class SavingsAccount extends Account implements IReport, ITransfer,IWithdraw{
    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000;
    private final vn.funix.FX18420.java.asm04.models.Utils Utils = new Utils();

    public SavingsAccount(String accountNumber, double balance, String customerId) {
        super(accountNumber, balance, customerId);
    }

    public SavingsAccount() {
        super();
    }

    //For printing report
    public String getTitle(){
        return "BIEN LAI GIAO DICH SAVINGS";
    }


    //For printing the withdrawal invoice
    @Override
    public void log(double amount, String type, String accountNumber) {
        System.out.println(Utils.getDivider());
        System.out.printf("| %41s%17s%n", getTitle()," |");
        System.out.printf("| NGÀY GD: %49s%n", Utils.getDateTime() + " |");
        System.out.printf("| ATM ID: %50s%n", "DIGITAL-BANK-ATM 2022 |");
        System.out.printf("| SỐ TK: %51s%n", this.getAccountNumber() + " |");
        if(type.equals("WITHDRAW")){
            System.out.printf("| SỐ TIỀN: %49s%n", Utils.formatBalance(amount) + " |");
        }
        if(type.equals("TRANSFER")){
            System.out.printf("| SỐ TK NHẬN: %46s%n", accountNumber + " |");
            System.out.printf("| SỐ TIỀN CHUYỂN: %42s%n", Utils.formatBalance(amount) + " |");
        }
        System.out.printf("| SỐ DƯ TK: %48s%n", Utils.formatBalance(this.getBalance()) + " |");
        System.out.printf("| PHI + VAT: %47s%n", 0 + " |");
        System.out.println(Utils.getDivider());
    }

    //Withdraw money from account and print information
    public boolean withdraw(double amount) {
        if(isAccepted(amount)){
            double newBalance = this.getBalance() - amount;
            Transaction transaction = createTransaction(amount, true, "WITHDRAW");
            setBalance(newBalance);
            addTransaction(transaction);
            try{
                AccountDao.update(this);
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Rút tiền thành công, biên lai giao dịch: ");
            this.log(amount, "WITHDRAW",getAccountNumber());
            return true;
        }else {
            System.out.println("So tien can rut khong hop le");
            return false;
        }
    }


    //Transfer money from account to account
    @Override
    public boolean transfer(SavingsAccount receiveAccount, double amount) {
        if(isAccepted(amount)){
            Scanner scanner = new Scanner(System.in);
            double newBalance = this.getBalance() - amount;
            double newReceivedBalance = receiveAccount.getBalance() + amount;
            System.out.print("Xác nhận thực hiện chuyển " + amount + "đ từ tài khoản [" + getAccountNumber() + "] đến tài khoản [" + receiveAccount.getAccountNumber() +
                    "] (Y/N) (hoặc 'E' nếu muốn thoát): ");
            String confirm = scanner.next();
            while (!confirm.equals("Y") && !confirm.equals("N") && !confirm.equals("E")) {
                System.out.print("Xác nhận thực hiện chuyển " + amount + "đ từ tài khoản [" + getAccountNumber() + "] đến tài khoản [" + receiveAccount.getAccountNumber() +
                        "] (Y/N) (hoặc 'E' nếu muốn thoát): ");
                confirm = scanner.next();
            }
            if(confirm.equals("Y")){
                Transaction transaction = this.createTransaction(amount, true, "TRANSFER");
                this.setBalance(newBalance);
                this.addTransaction(transaction);
                try{
                    AccountDao.update(this);
                }catch (IOException e){
                    e.printStackTrace();
                }
                Transaction transaction1 = receiveAccount.createTransaction(amount, true, "DEPOSIT");
                receiveAccount.setBalance(newReceivedBalance);
                receiveAccount.addTransaction(transaction1);
                try{
                    AccountDao.update(receiveAccount);
                }catch (IOException e){
                    e.printStackTrace();
                }
                this.log(amount, "TRANSFER",receiveAccount.getAccountNumber());
                return true;
            } else if (confirm.equals("N")) {
                System.out.println("Giao dịch chuyển tiền đã bị hủy!");
                return false;
            } else {
                return false;
            }

        }else {
            System.out.println("So tien can chuyen " + amount + " khong hop le");
            return false;
        }
    }

    //check if the amount is valid
    @Override
    public boolean isAccepted(double amount) {
        if(amount < 50_000 || amount % 10_000 != 0){
            return false;
        }
        if(!this.isPremium() && amount > SAVINGS_ACCOUNT_MAX_WITHDRAW){
            return false;
        }
        if(this.getBalance() -amount < 50_000){
            return false;
        }
        return true;
    }


}
