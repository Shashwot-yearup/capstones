package com.pluralsight;

public class AccountingLedger {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    //Constructor
    public AccountingLedger(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //Getter
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
}