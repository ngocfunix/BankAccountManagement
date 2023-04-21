package vn.funix.FX18420.java.asm04.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public abstract class Bank {

    private String bankId;
    private String bankName;


    public Bank() {
        this.bankId = String.valueOf(UUID.randomUUID());
    }
//public boolean validateCustomerId(String canCuocCongDan){
//    if (canCuocCongDan.length() != 12) {
//        System.out.println("So CCCD khong hop le.");
//        return false;
//    }
//    for (int i = 0; i < canCuocCongDan.length(); i++) {
//        if (!Character.isDigit(canCuocCongDan.charAt(i))) {
//            System.out.println("So CCCD khong hop le.");
//            return false;
//        }
//    }
//    return true;
//}

//    public boolean addCustomer(Customer newCustomer) {
//        String checkId = newCustomer.getCustomerId();
//        if(validateCustomerId(checkId)){
//            if (isCustomerExisted(checkId) == null) {
//                this.customers.add(newCustomer);
//                return true;
//            } else {
//                System.out.println("Ma khach hang nay da ton tai!");
//                return false;
//            }
//        }
//        return false;
//    }

//    public boolean addAccount(String customerId, Account account){
//        Customer customer = isCustomerExisted(customerId);
//        if (isCustomerExisted(customerId) != null) {
//            return customer.addAccount(account);
//        } else {
//            System.out.println("Ma khach hang nay khong ton tai!");
//            return false;
//        }
//    }
//
//    public Customer isCustomerExisted(String customerId) {
//        if(this.customers != null) {
//
//            for (Customer customer : customers) {
//                if (customerId.equals(customer.getCustomerId())) {
//                    return customer;
//                }
//            }
//        }
//        return null;
//    }

    public String getBankId() {
        return bankId;
    }



//    public abstract boolean addAccount(Scanner scanner, String customerId);
//
//    public abstract Customer isCustomerExisted(List<Customer> customers, Customer newCustomer);
//
//    public abstract Customer isCustomerExisted(List<Customer> customers, String customerId);
}
