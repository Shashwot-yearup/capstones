package com.pluralsight;

import java.util.Scanner;

public class DisplayScreen {

    //Method to display the main screen.
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

    //Gets the user choice for the Main screen
    public String getUserChoice(Scanner scanner){
        return scanner.nextLine().trim().toUpperCase(); //returns user choice.
    }

    //Method to display the Ledger screen.
    public void displayLedger() {
        System.out.println("=================================");
        System.out.println("          MONEYTRACK");
        System.out.println("             LEDGER");
        System.out.println("=================================");
        System.out.println();
        System.out.println("A) All Transactions");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        System.out.println("H) Home");
        System.out.println();
        System.out.print("Please select an option: ");
    }

    //Gets the user choice of the Ledger screen.
    public String getLedgerChoice(Scanner scanner) {
        return scanner.nextLine().trim().toUpperCase();
    }

    //Method to display the Report screen.
    public void displayReports() {

        System.out.println("\n========== REPORTS ==========");
        System.out.println("M) Month To Date");
        System.out.println("P) Previous Month");
        System.out.println("Y) Year To Date");
        System.out.println("L) Previous Year");
        System.out.println("V) Search By Vendor");
        System.out.println("B) Return to Ledger");

        System.out.print("Choose an option: ");
    }

    //Gets the user choice of the Report screen.
    public String getReportChoice(Scanner scanner) {
        return scanner.nextLine().trim().toUpperCase();
    }
}