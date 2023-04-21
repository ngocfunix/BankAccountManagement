package vn.funix.FX18420.java.asm04.models;

public interface ITransfer {

    //To transfer money from account to account
    boolean transfer(SavingsAccount receiveAccount, double amount);

    //for checking if an amount is valid
    boolean isAccepted(double amount);
}
