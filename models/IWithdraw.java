package vn.funix.FX18420.java.asm04.models;

public interface IWithdraw {
    //for withdrawal
    boolean withdraw(double amount);

    //for checking if an amount is valid
    boolean isAccepted(double amount);
}
