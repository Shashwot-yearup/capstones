package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MoneyTrackApp manager = new MoneyTrackApp();

        manager.run(scanner);

        scanner.close();
    }
}
