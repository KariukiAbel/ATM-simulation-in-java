package com.nabesh;

public abstract class Transaction {
    private int accountNumber;
    private Screen screen;
    private BankDataBase bankDataBase;

    public Screen getScreen() {
        return screen;
    }

    public BankDataBase getBankDataBase() {
        return bankDataBase;
    }

    public int getAccountNumber() {

        return accountNumber;
    }

    public Transaction(int userAccountNumber, Screen atmScreen, BankDataBase atmBankDatabase){
        accountNumber = userAccountNumber;
        screen = atmScreen;
        bankDataBase = atmBankDatabase;

    }

    abstract public void execute();
}
