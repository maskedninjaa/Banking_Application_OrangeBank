package javaproject4;

import java.util.Scanner;

public class Account {

    private final String account; // type of account
    private double balance; // balance of the account

    public Account(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Account details: " + "\n" +
                "\naccount type = " + account +
                "\nnew balance = " + balance;
    }

    // deposit method
    public void deposit() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an amount to deposit: ");
        double amount = input.nextDouble(); // amount variable used to deposit and withdraw money from the account
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Sorry, negative values are invalid");
        }
    }

    public void add(double amount) {
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Sorry, negative values are invalid");
        }
    }

    // withdraw method
    public void withdraw() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an amount to withdraw: ");
        double amount = input.nextDouble(); // if number is negative, give an error message
        if (amount <= balance && amount >= 0) {
            balance -= amount;
        } else {
            System.out.println("Sorry, negative values are invalid");
        }
    }

    public boolean remove(double amount) {
        if (amount <= balance && amount >= 0) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Sorry, negative values are invalid");
            return false;
        }
    }
}
