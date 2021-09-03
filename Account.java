package javaproject4;

import java.util.Scanner;

public class Account {

    private String account; // type of account
    private double balance; // balance of the account
    private double amount; // amount variable used to deposit and withdraw money from the account

    public Account(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Account details: " + "\n" +
                "\naccount type = " + account +
                "\nnew balance = " + balance;
    }

    public String getAccount() {
        return account;
    }

    // deposit method
    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an amount to deposit: ");
        amount = scanner.nextDouble();
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Sorry, negative values are invalid");
        }
    }

    // withdraw method
    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an amount to withdraw: ");
        amount = scanner.nextDouble(); // if number is negative, give an error message
        if (amount <= balance && amount >= 0) {
            balance -= amount;
        } else {
            System.out.println("Sorry, negative values are invalid");
        }
    }
}
