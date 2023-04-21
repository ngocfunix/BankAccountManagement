package vn.funix.FX18420.java.asm04.models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction implements Serializable {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;

    private static final long serialVersionUID = 4L;

    private String type;


    public Transaction(String accountNumber, double amount) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = getTime();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //get date and time of transaction
    public String getTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    //Print each transaction
    public String toString(){
        DecimalFormat df = new DecimalFormat("###,##0.00#");
        if(this.type.equals("WITHDRAW")){
            return String.format("[GD]  %s%-19s%s%22s", this.getAccountNumber() + "   |  WITHDRAW    |   " , df.format(- this.getAmount()),"đ |  ", this.getTime());
        } else if (this.type.equals("TRANSFER")){
            return String.format("[GD]  %s%-19s%s%22s", this.getAccountNumber() + "   |  TRANSFERS   |   " , df.format(- this.getAmount()),"đ |  ", this.getTime());
        }else {
            return String.format("[GD]  %s%-19s%s%22s", this.getAccountNumber() + "   |  DEPOSIT     |   " , df.format(this.getAmount()),"đ |  ", this.getTime());
        }
    }
}
