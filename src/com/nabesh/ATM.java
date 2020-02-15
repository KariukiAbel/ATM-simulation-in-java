package com.nabesh;

public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDataBase bankDataBase;

    public static final int BALANCE_INQUIRY = 1;
    public static final int WITHDRAW = 2;
    public static final int DEPOSIT = 3;
    public static final int EXIT = 4;

    //Constructor and initializing all fields and variables
    public ATM(){
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen = new Screen();
            keypad = new Keypad();
            cashDispenser = new CashDispenser();
            depositSlot = new DepositSlot();
            bankDataBase = new BankDataBase();
    }
    public void run(){
        while (true){
            while (!userAuthenticated){
                screen.displayMessageLine("Welcome!");
                authenticateUser();
            }
            performTransactions();
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessageLine("Thank you and Goodbye!");
        }
    }

    private void authenticateUser() {
        screen.displayMessage("Please Enter your Account Number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("Enter Pin: ");
        int pin = keypad.getInput();

        userAuthenticated = bankDataBase.authenticateUser(accountNumber,pin);

        if (userAuthenticated){
            currentAccountNumber = accountNumber;
        }else{
            screen.displayMessageLine("Invalid Account number or Pin. Please Try again! ");
        }
    }

    private void performTransactions() {
        Transaction currentTransaction = null;
        boolean userExited = false;

        while (!userExited){
            int mainMenuSelection = displayMainMenu();
            switch (mainMenuSelection){
                case BALANCE_INQUIRY:
                case WITHDRAW:
                case DEPOSIT:
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute();
                    break;
                case EXIT:
                    screen.displayMessageLine("Exiting system...");
                    userExited = true;
                    break;
                default:
                    screen.displayMessageLine("You did not enter a valid selection. Try again..");
                    break;
            }
        }
    }

    private Transaction createTransaction(int type) {
        Transaction temp = null;
        switch (type){
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDataBase);
                break;
            case WITHDRAW:
                temp = new Withdraw(currentAccountNumber, screen, bankDataBase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccountNumber, screen, bankDataBase, keypad, depositSlot);
                break;
        }
        return temp;
    }


    //to display the main menu items and the selection made
    private int displayMainMenu() {
        screen.displayMessageLine("Main Menu");
        screen.displayMessageLine("1 - Check Balance");
        screen.displayMessageLine("2 - Withdraw Cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit");
        screen.displayMessage("Enter choice");
        return keypad.getInput();//returns user selection
    }

}
