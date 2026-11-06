package com.pluralsight;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Ledger {

    private ArrayList<AccountingLedger> transactions;

    //loads the transactions
    public Ledger(ArrayList<AccountingLedger> transactions) {
        this.transactions = transactions;
    }

    //Method to display the all the transactions
    public void displayAllTransactions() {
        System.out.println("\n========== ALL TRANSACTIONS ==========");
        displayTransactions(transactions);
    }

    //Method to display only the deposits(+)
    public void displayDeposits() {
        ArrayList<AccountingLedger> deposits = new ArrayList<>();

        //for loop to go through the list and if statement to get the positive value only.
        for (AccountingLedger transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        System.out.println("System.out.println(\"\\n========== DEPOSITS ==========\");\n");
        displayTransactions(deposits);
    }

    //Method to display all the payments(-)
    public void displayPayment() {
        ArrayList<AccountingLedger> payment = new ArrayList<>();

        //for loop to go through the list and if statement to get the negative value only.
        for (AccountingLedger transaction : transactions) {
            if (transaction.getAmount() < 0) {
                payment.add(transaction);
            }
        }

        System.out.println("\n========== PAYMENTS ==========");
        displayTransactions(payment);
    }

    //Method to do all sort and check if the list is empty.
    private void displayTransactions(ArrayList<AccountingLedger> list) {

        //Checks if the list is empty.
        if (list.isEmpty()) {
            System.out.println("No transactions found");
            return;
        }

        ArrayList<AccountingLedger> sortedList = new ArrayList<>(list);

        //Sorts the list using the comparator.
        sortedList.sort(
                Comparator.comparing(
                        (AccountingLedger t) ->
                                LocalDateTime.parse(t.getDate() + "T" + t.getTime())
                ).reversed());

        System.out.println("--------------------------------------------------------------------------");

        System.out.printf("%-12s %-10s %-20s %-18s %10s%n",
                "Date", "Time", "Description", "Vendor", "Amount");

        System.out.println("--------------------------------------------------------------------------");

        //for loop to loop through each lines of the list.
        for (AccountingLedger transaction : sortedList) {

            //Format to prints the sorted list.
            System.out.printf("%-12s %-10s %-20s %-18s $%9.2f%n",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }

        System.out.println("--------------------------------------------------------------------------");
    }

    //Method to display transactions between two dates.
    public void displayDateRangeReport(String reportName, LocalDate startDate, LocalDate endDate) {
        ArrayList<AccountingLedger> reportTransactions = new ArrayList<>();

        //for loop to search for date and parse it to date.
        for (AccountingLedger transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());

            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                reportTransactions.add(transaction);
            }

            System.out.println("\n========== " + reportName + " ==========");
            System.out.println("From: " + startDate);
            System.out.println("To:   " + endDate);

            displayTransactions(reportTransactions);
            displayReportTotals(reportTransactions);
        }
    }

    //Method to search by vendor
    public void searchByVendor(String vendorName) {

        ArrayList<AccountingLedger> matchingTransactions = new ArrayList<>();

        for (AccountingLedger transaction : transactions) {
            if (transaction.getVendor().toLowerCase()
                    .contains(vendorName.toLowerCase())) {

                matchingTransactions.add(transaction);
            }
        }

        System.out.println("\n========== VENDOR SEARCH ==========");
        System.out.println("Search: " + vendorName);

        displayTransactions(matchingTransactions);
        displayReportTotals(matchingTransactions);
    }

    //Method to calculate report total.
    private void displayReportTotals(ArrayList<AccountingLedger> list) {

        double deposits = 0;
        double payments = 0;

        for (AccountingLedger transaction : list) {

            if (transaction.getAmount() > 0) {
                deposits += transaction.getAmount();
            } else {
                payments += Math.abs(transaction.getAmount()); //Gets the absolute value.
            }

        }

        System.out.println("\nREPORT TOTALS");

        System.out.printf("Total Deposits: $%.2f%n", deposits);
        System.out.printf("Total Payments: $%.2f%n", payments);
        System.out.printf("Net Total:      $%.2f%n", deposits - payments);

        System.out.println("====================================");
    }

    //Method to calculate total deposits.
    public double calculateDeposits() {
        double total = 0;

        for (AccountingLedger transaction : transactions) {
            if (transaction.getAmount() > 0) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    //Method to calculate total payments.
    public double calculatePayments() {
        double total = 0;

        for (AccountingLedger transaction : transactions) {
            if (transaction.getAmount() < 0) {
                total += Math.abs(transaction.getAmount());
            }
        }

        return total;
    }

    //Method to calculate account total balance.
    public double calculateBalance() {
        return calculateDeposits() - calculatePayments();
    }

    //Method to display account summary.
    public void displayAccountSummary() {
        System.out.println("\n===Account Summary===");

        System.out.printf("\nTotal Deposits: $%.2f%n", calculateDeposits());
        System.out.printf("Total Payments: $%.2f%n", calculatePayments());
        System.out.printf("Account Balance: $%.2f%n%n", calculateBalance());

        System.out.println("========================\n\n");
    }
}