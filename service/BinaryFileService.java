package vn.funix.FX18420.java.asm04.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService<T> {
    public static <T> List<T> readFile(String fileName){
        List<T> objects = new ArrayList<>();
        try(ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))){
             boolean eof = false;
             while(!eof){
                 try{
                     T object = (T) file.readObject();
                     objects.add(object);
                 }catch (EOFException e){
                     eof = true;
                 }
             }
        }catch (EOFException e){
            return new ArrayList<>();
        }catch (IOException io){
            //
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException :" + e.getMessage());
        }
        return objects;
    }

    public static <T> void writeFile(String fileName, List<T> objects){
        try(ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))){
            for(T object: objects){
                    file.writeObject(object);
            }
        }catch (IOException e){
            System.out.println("IOException");
        }catch (NullPointerException e){
            System.out.println("NullPointerException");
        }
    }






















}
