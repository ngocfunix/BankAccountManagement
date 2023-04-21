package vn.funix.FX18420.java.asm04.dao;

import vn.funix.FX18420.java.asm04.models.SavingsAccount;
import vn.funix.FX18420.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccountDao {
    private static final String FILE_PATH_ACCOUNT = "store/accounts.dat";

    private final static int MAX_THREAD = 10;

    public static void save(List<SavingsAccount> accounts) throws IOException {
        BinaryFileService.writeFile(FILE_PATH_ACCOUNT, accounts);
    }

    public static List<SavingsAccount> list(){
        return BinaryFileService.readFile(FILE_PATH_ACCOUNT);
    }


    public static void update(SavingsAccount editAccount) throws IOException {
        List<SavingsAccount> accounts = list();
        boolean hasExit = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
        List<SavingsAccount> updateAccounts;
        if(!hasExit){
            updateAccounts = new ArrayList<>(accounts);
            updateAccounts.add(editAccount);
        }else {
            updateAccounts = new ArrayList<>();
            final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD);
            final List<Future<?>> futures = new ArrayList<>();
            for(SavingsAccount account: accounts){
                Future<?> future = executor.submit(() -> {
                    if(account.getAccountNumber().equals(editAccount.getAccountNumber())){
                        updateAccounts.add(editAccount);
                    }else {
                        updateAccounts.add(account);
                    }
                });
                futures.add(future);
            }
            try {
                for (Future<?> future : futures) {
                    future.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        save(updateAccounts);
    }}












