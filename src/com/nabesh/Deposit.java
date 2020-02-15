package com.nabesh;

public class Deposit extends Transaction {
    private double amount;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private final static int CANCELED = 0;

    public Deposit(int userAccountNumber, Screen atmScreen, BankDataBase atmBankDatabase,
                   Keypad atmKeypad, DepositSlot atmDepositSlot){
        super(userAccountNumber, atmScreen, atmBankDatabase);


        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }

    @Override
    public void execute() {
        BankDataBase bankDataBase = getBankDataBase();
        Screen screen = getScreen();

        amount = promptForDepositAmount();

        if (amount != CANCELED){
            screen.displayMessage("\nPlease insert a deposit envelope containig ");
            screen.displayDollarAmount(amount);
            screen.displayMessageLine("");

            boolean envelopeReceived = depositSlot.isEnvelopedReceived();

            if (envelopeReceived){
                screen.displayMessageLine("\nYour envelope has been received.\nNOTE: " +
                        "The money just deposited will not be available until we verify the " +
                        "amount of any enclosed cash and your cheques clear. ");

                bankDataBase.credit(getAccountNumber(), amount);
            }else{
                screen.displayMessageLine("\nYou did not insert an envelope, so the ATM has cancelled your transaction.");
            }
        }else{
            screen.displayMessageLine("\nCancelling Transaction...");
        }
    }

    private double promptForDepositAmount(){
        Screen screen = getScreen();
        screen.displayMessage("\nPlease Enter a valid deposit amount or press 0 to cancel: ");
        int input = keypad.getInput();

        if (input == CANCELED){
            return CANCELED;
        }else{
            return (double) input;
        }
    }
}
