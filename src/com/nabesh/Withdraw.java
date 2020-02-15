package com.nabesh;

public class Withdraw extends Transaction {
    private int amount;
    private Keypad keypad;
    private CashDispenser cashDispenser;

    private final static int CANCELED = 11;

    public Withdraw(int userAccountNumber, Screen atmScreen, BankDataBase atmBankDatabase,
                    Keypad atmKeypad, CashDispenser atmCashDispenser) {
        //initialise super class variables
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        double availableBalance;

        BankDataBase bankDataBase = getBankDataBase();
        Screen screen = getScreen();

        do {
            amount = displayMenuOfAmounts();
            if (amount != CANCELED) {
                availableBalance = bankDataBase.getAvailableBalance(getAccountNumber());

                if (amount <= availableBalance) {
                    if (cashDispenser.isCashSufficient(amount)) {
                        bankDataBase.debit(getAccountNumber(), amount);
                        cashDispenser.dispenseCash(amount);
                        cashDispensed = true;
                        screen.displayMessageLine("Your cash has been dispensed. Please take your cash now.");
                    } else {
                        screen.displayMessageLine("Insufficient cash available in ATM. Please choose a smaller amount");
                    }
                } else {
                    screen.displayMessageLine("Insufficient funds in your account. Please choose a smaller amount");
                }

            }else{
                screen.displayMessageLine("Cancelling transaction...");
                return;
            }
        }while(!cashDispensed);
    }

    private int displayMenuOfAmounts() {
        int userChoice = 0;
        Screen screen = getScreen();
        int [] amounts = {1, 5, 10, 20, 40, 50, 100, 200, 500, 1000};

        while (userChoice == 0){
            //display withdraw menu
            screen.displayMessageLine("Withdraw Menu");
            screen.displayMessageLine("1 - Ksh 1");
            screen.displayMessageLine("2 - Ksh 5");
            screen.displayMessageLine("3 - Ksh 10");
            screen.displayMessageLine("4 - Ksh 20");
            screen.displayMessageLine("5 - Ksh 40");
            screen.displayMessageLine("6 - Ksh 50");
            screen.displayMessageLine("7 - Ksh 100");
            screen.displayMessageLine("8 - Ksh 200");
            screen.displayMessageLine("9 - Ksh 500");
            screen.displayMessageLine("10 - Ksh 1000");
            screen.displayMessageLine("11 - Cancel Transaction");
            screen.displayMessage("\nChoose a withdraw amount: ");

            int input = keypad.getInput();

            switch (input){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input];
                    break;
                case CANCELED:
                    userChoice = CANCELED;
                    break;
                default:
                    screen.displayMessageLine("\n Invalid selection. Try again. ");
            }
        }
        return userChoice;
    }
}