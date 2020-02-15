package com.nabesh;

public class BalanceInquiry extends Transaction {

    public BalanceInquiry(int userAccountNumber, Screen atmScreen, BankDataBase atmBankDatabase){
        super(userAccountNumber, atmScreen, atmBankDatabase);
    }

    @Override
    public void execute() {
        BankDataBase bankDataBase = getBankDataBase();
        Screen screen = getScreen();

        double availableBalance = bankDataBase.getAvailableBalance(getAccountNumber());
        double totalBalance = bankDataBase.getTotalBalance(getAccountNumber());

        //displaying the balance on the screen

        screen.displayMessageLine("Balance Information:");
        screen.displayMessage("-Available balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage(" -Total Balance: ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
