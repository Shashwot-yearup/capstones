package com.pluralsight;

import java.time.LocalDate;
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

        String userChoice = "";

        //While loop to keep running until the user hit x.
        while (!userChoice.equals("X")) {

            ledger.displayAccountSummary();

            displayScreen.mainScreen();

            userChoice = displayScreen.getUserChoice(scanner); //saves the user choice

            //Switch Statement to get which option did the user choose.
            switch (userChoice) {
                case "D" -> addDeposit(scanner);
                case "P" -> makePayment(scanner);
                case "L" -> displayLedgerMenu(scanner);
                case "X" -> System.out.println("Thank you\n");
                default -> System.out.println("Invalid option. Please try again");
            }
        }
    }

    //Method to display the Ledger screen.
    public void displayLedgerMenu(Scanner scanner) {
        String userChoiceLedger = "";

        while (!userChoiceLedger.equals("H")) {
            displayScreen.displayLedger();

            userChoiceLedger = displayScreen.getLedgerChoice(scanner);

            //Switch statement to get the user choice for the ledger
            switch (userChoiceLedger) {
                case "A" -> ledger.displayAllTransactions();
                case "D" -> ledger.displayDeposits();
                case "P" -> ledger.displayPayment();
                case "R" -> displayReportMenu(scanner);
                case "H" -> System.out.println("Returning to Home screen...");
                default -> System.out.println("Invalid option. Please try again");

            }
        }
    }

    //Method to display the reports menu.
    public void displayReportMenu(Scanner scanner) {
        String userChoiceReport = "";

        while (!userChoiceReport.equals("B")) {
            displayScreen.displayReports();

            userChoiceReport = displayScreen.getReportChoice(scanner);

            LocalDate today = LocalDate.now();

            switch (userChoiceReport) {

                //Month to Date.
                case "M" -> ledger.displayDateRangeReport("Month to Date,",
                        today.withDayOfMonth(1),
                        today);

                //Previous Month.
                case "P" -> {
                    LocalDate previousMonth = today.minusMonths(1);

                    ledger.displayDateRangeReport(
                            "Previous Month",
                            previousMonth.withDayOfMonth(1),
                            previousMonth.withDayOfMonth((previousMonth.lengthOfMonth())));
                }

                //Year to date.
                case "Y" -> ledger.displayDateRangeReport(
                        "Year to date",
                        today.withDayOfYear(1),
                        today);

                //Previous year.
                case "L" -> {
                    LocalDate previousYear = today.minusYears(1);

                    ledger.displayDateRangeReport(
                            "Previous Year",
                            previousYear.withDayOfYear(1),
                            previousYear.withDayOfYear((previousYear.lengthOfYear())));
                }

                //Search by vendor name.
                case "V" -> {

                    System.out.println("Enter the Vendor name you want to search");

                    String vendorName = scanner.nextLine().trim();

                    //checks if the vendor name is empty or not.
                    if (vendorName.isEmpty()){
                    System.out.println("please enter a vendor name");
                    }else {
                    ledger.searchByVendor(vendorName);
                    }
                }

                //Returns to the ledger Screen.
                case "B" -> System.out.println("Returning to ledger screen...");

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

        //Creates new transaction.
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

        //Creates new transaction.
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