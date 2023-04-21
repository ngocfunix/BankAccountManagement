package vn.funix.FX18420.java.asm04.models;

import vn.funix.FX18420.java.asm04.common.CustomerIdValidator;
import vn.funix.FX18420.java.asm04.dao.AccountDao;
import vn.funix.FX18420.java.asm04.dao.CustomerDao;
import vn.funix.FX18420.java.asm04.exception.CustomerIdNotValidException;
import vn.funix.FX18420.java.asm04.file.TextFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DigitalBank extends Bank {
    private List<Customer> customers = null;

    //Khởi tạo instance và gán list customer đọc từ file
    public DigitalBank() throws IOException{
            this.customers = CustomerDao.list();
    }

    //In thông tin khách hàng của ngân hàng
    public void showCustomer(){
        if(this.customers.size() != 0){
            for (Customer customer: customers){
                customer.setAccounts();
                customer.displayInformation();
            }
        }else {
            System.out.println("Chưa có khách hàng nào trong danh sách!");
        }
    }

    //Kiểm tra xem khách hàng có tồn tại trong list không thông qua Id
    public boolean isCustomerExisted(List<Customer> customers, String customerId) {
        if(customers.size() != 0)
            return customers.stream().anyMatch(customer -> customer.getCustomerId().equals(customerId));
        return false;
    }

    //Lọc khách hàng trong list thông qua Id
    public DigitalCustomer getCustomerById(List<Customer> customerList, String customerId){

        for (Customer customer : customerList) {
            if (customerId.equals(customer.getCustomerId())) {
                return (DigitalCustomer) customer;
            }
        }
        return null;
    }


    //Thêm khách hàng vào list
    public void addCustomer(String fileName) throws IOException {
        List<List<String>> customerList = TextFileService.readFile(fileName);
        for (List<String> customerString : customerList){
            try{
                CustomerIdValidator.customerIdValidation(customerString.get(0));
            }catch (CustomerIdNotValidException e){
                System.out.println(e);
            }
            if (!isCustomerExisted(customers, customerString.get(0))) {
                DigitalCustomer customer = new DigitalCustomer(customerString.get(0), customerString.get(1));
                customers.add(customer);
                try{
                    CustomerDao.save(customers);
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("Đã thêm khách hàng " + customerString.get(0) + " vào danh sách khách hàng");
            }else {
                System.out.println("Khách hàng " + customerString.get(0) + " đã tồn tại, thêm khách hàng không thành công");
            }
        }
    }

    //Thêm tài khoản ATM
    public void addSavingAccount(Scanner scanner, String customerId) {
//        scanner = new Scanner(System.in);
        Customer customer = getCustomerById(customers,customerId);
        customer.setAccounts();
        if(customer.getAccounts().size() == 0) System.out.println("Khách hàng chưa có tài khoản nào!");
        if (customer != null) {
            customer.addAccount(scanner);
        } else {
            System.out.println("Không tìm thấy khách hàng " + customerId + ", tác vụ không thành công");
        }
    }

    //Rút tiền từ tài khoản nếu khách hàng và tài khoản có tồn tại
    public void withdraw(Scanner scanner, String customerId) {
        scanner = new Scanner(System.in);
        try{
            CustomerIdValidator.customerIdValidation(customerId);
        }catch (CustomerIdNotValidException e){
            System.out.println(e);
        }
        if(!isCustomerExisted(customers, customerId)){
            System.out.println("Mã số khách hàng "+ customerId + " không tồn tại");
        }else {
            Customer customer = getCustomerById(customers, customerId);
            customer.setAccounts();
            if(customer.getAccounts().size() == 0) System.out.println("Khách hàng chưa có tài khoản nào!");
            customer.displayInformation();
            customer.withdraw(scanner);
        }
    }

    //Chuyển tiền từ tài khoản nếu khách hàng và tài khoản có tồn tại
    public void transfer(Scanner scanner, String customerId){
//        scanner = new Scanner(System.in);
        try{
            CustomerIdValidator.customerIdValidation(customerId);
        }catch (CustomerIdNotValidException e){
            System.out.println(e);
        }
        if(!isCustomerExisted(customers, customerId)){
            System.out.println("Mã số khách hàng "+ customerId + " không tồn tại");
        }else {
            Customer customer = getCustomerById(customers, customerId);
            customer.setAccounts();
            if(customer.getAccounts().size() == 0) System.out.println("Khách hàng chưa có tài khoản nào!");
            customer.displayInformation();
            customer.transfer(scanner);
        }
    }

    //In thông tin khách hàng kèm theo giao dịch
    public void showTransactionHistory(Scanner scanner, String customerId){
        try{
            CustomerIdValidator.customerIdValidation(customerId);
        }catch (CustomerIdNotValidException e){
            System.out.println(e);
        }
        if(!isCustomerExisted(customers, customerId)){
            System.out.println("Mã số khách hàng "+ customerId + " không tồn tại");
        }else {
            Customer customer = getCustomerById(customers, customerId);
            customer.setAccounts();
            if(customer.getAccounts().size() == 0) System.out.println("Khách hàng chưa có tài khoản nào!");
            customer.displayInformation();
            customer.displayTransactions();
        }

    }

    //Lấy list khách hàng
    public List<Customer> getCustomers() {
        return customers;
    }
}
