package com.nabesh;

public class BankDataBase {
    private Account[] accounts;

    public BankDataBase(){
        accounts = new Account[2];
        accounts [0] = new Account(1240795025, 0000, 150000, 170000);
        accounts [1] = new Account(/*7770176863711*/ 1240795024, 7777,120000, 150000);
    }

    private Account getAccount(int accountNumber){
        for (Account currentAccount: accounts) {
            if (currentAccount.getAccountNumber() == accountNumber)
                return currentAccount;
        }
        return null;
    }

    public boolean authenticateUser(int userAccountNumber, int userPin){
        Account userAccount = getAccount(userAccountNumber);
         if (userAccount != null)
             return userAccount.validatePin(userPin);
         else
             return false;
    }

    public double getAvailableBalance(int userAccountNumber){
        return getAccount(userAccountNumber).getAvailableBalance();
    }

    public double getTotalBalance(int userAccountNumber){
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount){
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, double amount){
        getAccount(userAccountNumber).debit(amount);
    }
}
