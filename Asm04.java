package vn.funix.FX18420.java.asm04;

import vn.funix.FX18420.java.asm04.models.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Asm04 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
//

    static String SOFTWARE = "NGAN HANG SO";
    static String AUTHOR = "FX18420";
    static String VERSION = "v4.0.0";
    //Bien nhap tu ban phim
    static Scanner sc = new Scanner((System.in));

    //Ham dung hien thị giao dien
    public static void displayFunctionName() {
        System.out.println("+--------------+----------------------------+--------------+");
        System.out.println("| " + SOFTWARE + " | " + AUTHOR + "@" + VERSION + "                            |");
    }

    //Hien thi menu chuc nang
    public static void displayFunctionOptions() {
        System.out.println("+--------------+----------------------------+--------------+");
        System.out.println("| 1. Xem danh sach khach hang                              |");
        System.out.println("| 2. Nhap danh sach khach hang                             |");
        System.out.println("| 3. Them tai khoan ATM                                    |");
        System.out.println("| 4. Chuyen tien                                           |");
        System.out.println("| 5. Rut tien                                              |");
        System.out.println("| 6. Tra cuu Lich su giao dich                             |");
        System.out.println("| 0. Thoat                                                 |");
        System.out.println("+--------------+----------------------------+--------------+");
    }

    //Ham kiem tra nhap lieu nguoi dung khi chon chuc nang chinh
    public static int functionValidation(int chucNang) {
        while (chucNang != 6 && chucNang != 5 && chucNang != 4 && chucNang != 3 && chucNang != 2 && chucNang != 1 && chucNang != 0) {
            try {
                chucNang = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Nhap sai du lieu (number only!)");
            }
            if (chucNang != 6 && chucNang != 5 && chucNang != 4 && chucNang != 3 && chucNang != 2 && chucNang != 1 && chucNang != 0) {
                System.out.println("Vui long chon chuc nang bang cach go 1, 2, 3, 4, 5 hoac 0");
            }
        }
        return chucNang;
    }

    //Hien thi menu chuc nang sau khi nguoi dung hoan tat 1 chuc nang nao do
    public static int selectFunction() {
        displayFunctionOptions();
        System.out.print("Chọn chuc nang: ");
        int chucNang = 7;
        return functionValidation(chucNang);
    }

    public static void main(String[] args) throws IOException {
        DigitalBank activeBank = new DigitalBank();
        displayFunctionName();
        displayFunctionOptions();
        System.out.print("Chọn chuc nang: ");
        int chucNang = 7;
        //Kiem tra du lieu nhap cua nguoi dung khi chon chuc nang chinh
        chucNang = functionValidation(chucNang);
        //Khi vao menu chinh thi nguoi dung co the chon chuc nang tuy thich cho den luc thoat
        while (true) {
            //Chuc nang 1 - In ra thong tin khach hang
            if (chucNang == 1) {
                activeBank.showCustomer();
                chucNang = selectFunction();
            }
            //Chuc nang 2 - Nhập danh sách khách hàng
            if (chucNang == 2) {
                System.out.println("Nhập đường dẫn đến tệp:");
                String fileName = sc.next();
                activeBank.addCustomer(fileName);
                chucNang = selectFunction();
            }
            //Chuc nang 3 - Them tai khoan ATM (SavingsAccount)
            if (chucNang == 3) {
                System.out.println("Nhập mã số của khách hàng:");
                String customerId = sc.next();
                activeBank.addSavingAccount(sc,customerId);
                chucNang = selectFunction();
            }
            //Chuc nang 4 - Chuyển tiền
            if (chucNang == 4) {
                System.out.println("Nhập mã số của khách hàng:");
                String customerId = sc.next();
                activeBank.transfer(sc,customerId);
                chucNang = selectFunction();
            }
            //Chuc nang 5 - Rút tiền
            if (chucNang == 5) {
                System.out.println("Nhập mã số của khách hàng:");
                String customerId = sc.next();
                activeBank.withdraw(sc,customerId);
                chucNang = selectFunction();
            }
            //Chuc nang 5 - Tra cứu lịch sử giao dịch
            if (chucNang == 6) {
                System.out.println("Nhập mã số của khách hàng:");
                String customerId = sc.next();
                activeBank.showTransactionHistory(sc, customerId);
                chucNang = selectFunction();
            }
            if (chucNang == 0) {
                System.exit(EXIT_COMMAND_CODE);
            }
        }
    }
}
