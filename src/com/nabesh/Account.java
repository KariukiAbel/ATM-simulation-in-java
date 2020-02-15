package com.nabesh;

public class Account {
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;

    //account constructor initializes attributes
    public Account(int theAccountNumber, int thepin, double theAvailableBalance,double theTotalBalance){
        accountNumber = theAccountNumber;
        pin = thepin;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
    }

    public boolean validatePin(int userPIN){
        if (userPIN == pin)
            return true;
        else
            return false;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void credit(double amount){
        totalBalance += amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void debit(double amount){
        availableBalance -= amount;
        totalBalance -= amount;

    }
}
