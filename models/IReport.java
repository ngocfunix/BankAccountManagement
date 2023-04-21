package vn.funix.FX18420.java.asm04.models;

public interface IReport {
    //for printing the withdrawal invoice
    void log(double amount, String type, String accountNumber);
}
