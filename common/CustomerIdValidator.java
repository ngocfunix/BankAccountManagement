package vn.funix.FX18420.java.asm04.common;

import vn.funix.FX18420.java.asm04.exception.CustomerIdNotValidException;

public class CustomerIdValidator {
    public static void customerIdValidation(String customerId) throws CustomerIdNotValidException {

        if (customerId.length() != 12) {
            throw new CustomerIdNotValidException("CustomerIdNotValidException: Mã số khách hàng "+ customerId + " không đúng");
        }
        for (int i = 0; i < customerId.length(); i++) {
            if (!Character.isDigit(customerId.charAt(i))) {
                throw new CustomerIdNotValidException("CustomerIdNotValidException: Mã số khách hàng "+ customerId + " không đúng");
            }
        }
    }
}
