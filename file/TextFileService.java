package vn.funix.FX18420.java.asm04.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";

    public static List<List<String>> readFile(String fileName){
        List<List<String>> customers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                //scanner.skip(scanner.delimiter());
                //String customerId = scanner.next();
                //String name = scanner.next();
                //System.out.println(scanner.next());
//                String input = scanner.nextLine();//entire line
//                String[] data = input.split(",");

                String[] data = scanner.nextLine().split(COMMA_DELIMITER);
                String customerId = data[0];
                String name = data[1];
                List<String> customer = new ArrayList<>();
                customer.add(customerId);
                customer.add(name);
                customers.add(customer);
            }
        } catch (IOException e) {
            System.out.println("Tệp không tồn tại");
        }
        //do smth

        return customers;
    }
}
