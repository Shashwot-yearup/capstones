package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Creates a scanner
        Scanner scanner = new Scanner(System.in);

        //Creates a new object
        MoneyTrackApp manager = new MoneyTrackApp();

        //Calls the method run in MoneytrackApp();
        manager.run(scanner);

        //closing the scanner
        scanner.close();//closing the scanner
    }
}