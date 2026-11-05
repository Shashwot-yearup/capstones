package com.pluralsight;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileManager {

    private final Path filePath = Path.of(
            "src", "main", "resources", "transactions.csv"); //Path to find the file.

    // load transactions for the csv file
    public ArrayList<AccountingLedger> loadTransactions() {

        ArrayList<AccountingLedger> transactions = new ArrayList<>();

        //Catch any errors that happen while accessing or reading the file.
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent()); //used to safely create all non-existent parent directories for a given file path before you attempt to create or write to the file itself.
                Files.createFile(filePath);
                return transactions;
            }

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 5) {
                    System.out.println("Skipping invalid transaction: " + line);
                    continue;
                }

                //Converts the transaction information for the csv file
                try {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    AccountingLedger transaction = new AccountingLedger(date, time,
                            description, vendor, amount); //Creates new transaction object

                    transactions.add(transaction);

                } catch (NumberFormatException e) {
                    System.out.println("Skipping transaction with invalid amount: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }

    //Method to save a new transaction to the csv file.
    public void saveTransaction(AccountingLedger transaction) {

        try {
            Files.createDirectories(filePath.getParent());

            String line = String.format(
                    Locale.US,
                    "%s|%s|%s|%s|%.2f%n", //format for the string.
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription().replace("|", "/"),
                    transaction.getVendor().replace("|", "/"),
                    transaction.getAmount()
            );

            System.out.println("Saving transction: " + line);

            //Write to the file.
            Files.writeString(
                    filePath,
                    line,
                    StandardOpenOption.CREATE,//Creates a new file if it does not exist.
                    StandardOpenOption.APPEND //Appends the new transaction without deleting existing records
            );

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}