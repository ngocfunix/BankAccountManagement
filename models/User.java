package vn.funix.FX18420.java.asm04.models;

import java.io.Serializable;

public abstract class User implements Serializable{
    private String name;
    private String customerId;

    private static final long serialVersionUID = 1L;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) throws IllegalArgumentException{

        if (customerId.length() != 12) {
            throw new IllegalArgumentException("Mã số khách hàng "+ customerId + " không đúng");
        }
        for (int i = 0; i < customerId.length(); i++) {
            if (!Character.isDigit(customerId.charAt(i))) {
                throw new IllegalArgumentException("Mã số khách hàng "+ customerId + " không đúng");
            }
        }
                this.customerId = customerId;
    }

}
