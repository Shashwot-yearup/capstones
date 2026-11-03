package com.pluralsight;

import java.util.Locale;
import java.util.Scanner;

public class DisplayScreen {

    //method to display the main screen.
    public void mainScreen(){
        System.out.println("=================================");
        System.out.println("        MONEYTRACK");
        System.out.println("   Personal Finance Tracker");
        System.out.println("=================================");
        System.out.println();
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println();
        System.out.print("Please select an option: ");
    }

    //gets the user choice
    public String getUserChoice(Scanner scanner){
        return scanner.nextLine().trim().toUpperCase(); //returns user choice.
    }
}
