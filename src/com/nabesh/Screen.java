package com.nabesh;

public class Screen {
    public void displayMessageLine(String s) {
        System.out.println(s);
    }

    public void displayMessage(String s) {
        System.out.print(s);
    }
    public void displayDollarAmount(double amount){
        System.out.printf("$%,.2f", amount);
    }
}
