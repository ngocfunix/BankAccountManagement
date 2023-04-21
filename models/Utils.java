package vn.funix.FX18420.java.asm04.models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils implements Serializable {

    private static final long serialVersionUID = 3L;
    public Utils() {
    }

    //For printing the invoice
    public String getDivider(){
        return "+--------------+----------------------------+--------------+";
    }

    //Get date and time for withdrawal
    public String getDateTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }

    //format number to defined string
    public String formatBalance(double amount){
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(amount) + "đ";
    }

}
