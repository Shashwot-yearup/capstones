package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MoneyTrackApp {

    // Core components for managing data, user interface, and file I/O
    private ArrayList<AccountingLedger> transactions;
    private DisplayScreen displayScreen;
    private Ledger ledger;
    private FileManager fileManager;

    //constructor
    public MoneyTrackApp() {
        fileManager = new FileManager();
        transactions = fileManager.loadTransactions();
        displayScreen = new DisplayScreen();
        ledger = new Ledger(transactions);
    }

    //Method to keep the program running.
    public void run(Scanner scanner) {

        String choice = "";

        //While loop to keep running until the user hit x.
        while (!choice.equals("X")) {
            displayScreen.mainScreen();

            choice = displayScreen.getUserChoice(scanner); //saves the user choice

            //Switch Statement to get which option did the user choose.
            switch (choice) {
                case "D" -> addDeposit(scanner);
                case "P" -> makePayment(scanner);
                case "L" -> displayLedgerMenu(scanner);
                case "X" -> System.out.println("Thank you\n");
                default -> System.out.println("Invalid option. Please try again");
            }
        }
    }

    //Method to display the Ledger screen
    public void displayLedgerMenu(Scanner scanner) {
        String userChoice = "";

        while (!userChoice.equals("H")) {
            displayScreen.displayLedger();

            userChoice = displayScreen.getLedgerChoice(scanner);

            //Switch statement to get the user choice for the ledger
            switch (userChoice) {
                case "A" -> ledger.displayAllTransactions();
                case "D" -> ledger.displayDeposits();
                case "P" -> ledger.displayPayment();
                case "R" -> System.out.println("Thank you\n");
                case "H" -> System.out.println("Returning to Home screen...");
                default -> System.out.println("Invalid option. Please try again");

            }
        }
    }

    //Method to add the deposit.
    public void addDeposit(Scanner scanner) {

        System.out.println("\nAdd Deposit");

        System.out.println("Enter description");
        String description = scanner.nextLine();

        System.out.println("Enter vendor");
        String vendor = scanner.nextLine();

        double amount = getAmount(scanner);

        if (amount <= 0) {
            System.out.println("Amount must be grater then 0");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        //format for to save the answers to the csv file.
        AccountingLedger transaction = new AccountingLedger(
                now.toLocalDate().toString(),
                now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                description,
                vendor,
                amount
        );

        transactions.add(transaction);

        fileManager.saveTransaction((transaction));

        System.out.println("Deposit success full!!");
    }

    //Method to makePayment
    public void makePayment(Scanner scanner) {
        System.out.println("\nMake Payment");

        System.out.println("Enter description");
        String description = scanner.nextLine();

        System.out.println("Enter vendor");
        String vendor = scanner.nextLine();

        double amount = getAmount(scanner);

        if (amount <= 0) {
            System.out.println("Amount must be greater then 0.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        //format for to save the answers to the csv file.
        AccountingLedger transaction = new AccountingLedger(
                now.toLocalDate().toString(),
                now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                description,
                vendor,
                -amount
        );

        transactions.add(transaction);

        fileManager.saveTransaction((transaction));

        System.out.println("Payment was success full!!");
    }

    //Method to check if the amount is a valid number or not
    private double getAmount(Scanner scanner) {
        while (true) {
            System.out.println("Enter amount: ");

            try {
                double amount = Double.parseDouble(scanner.nextLine());

                if (Double.isFinite(amount)) { //Checks if the number is valid or not.
                    return amount;
                }

                System.out.println("Please a valid amount:");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");

            }
        }
    }
}