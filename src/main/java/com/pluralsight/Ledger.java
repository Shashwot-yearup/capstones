package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Ledger {

    private ArrayList<AccountingLedger> transactions;

    //loads the transactions
    public Ledger(ArrayList<AccountingLedger> transactions){
        this.transactions = transactions;
    }

    //Method to display the all the transactions
    public void displayAllTransactions() {
        System.out.println("\n========== ALL TRANSACTIONS ==========");
        displayTransactions(transactions);
    }

    //Method to display only the deposits
    public  void displayDeposits(){
        ArrayList<AccountingLedger> deposits = new ArrayList<>();

        //for loop to go through the list and if statement to get the positive value only.
        for(AccountingLedger transaction: transactions){
            if (transaction.getAmount() > 0 ){
                deposits.add(transaction);
            }
        }
        System.out.println("System.out.println(\"\\n========== DEPOSITS ==========\");\n");
        displayTransactions(deposits);
    }

    //Method to do all sort and check if the list is empty.
    private void displayTransactions(ArrayList<AccountingLedger> list){

        //Checks if the list is empty.
        if(list.isEmpty()){
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
}
