package vn.funix.FX18420.java.asm04.common;

import vn.funix.FX18420.java.asm04.exception.AccountNumberNotValidException;

public class AccountNumberValidator {
    public static boolean validateAccount(String accountNumber)throws AccountNumberNotValidException {
        if (accountNumber.length() != 6) {
           throw new AccountNumberNotValidException("AccountNumberNotValidException: Số tài khoản "+ accountNumber + " không đúng");
        }
        for (int i = 0; i < accountNumber.length(); i++) {
            if (!Character.isDigit(accountNumber.charAt(i))) {
                throw new AccountNumberNotValidException("AccountNumberNotValidException: Số tài khoản "+ accountNumber + " không đúng");
            }
        }
        return true;
    }
}
