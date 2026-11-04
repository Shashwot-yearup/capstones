package com.pluralsight;

import java.util.Scanner;

public class MoneyTrackApp {
    DisplayScreen Screen = new DisplayScreen();

    public void run(Scanner scanner) {

        String choice = "";

        //While loop to keep running until the user hit x.
        while (!choice.equals("X")) {
            Screen.mainScreen();

            choice = Screen.getUserChoice(scanner); //saves the user choice

            //Switch Statement to see which
            switch (choice) {
                case "D" -> addDeposit(scanner);
                //case -> "D" -> makePayment()
                //case -> "D" -> displayLedger()
                case "X" -> System.out.println("Thank you\n");
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

        }
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