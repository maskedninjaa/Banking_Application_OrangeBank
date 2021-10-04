package javaproject4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An interactive banking application which helps the user navigate between different options
 * @author Wasim Nasser
 */

public class OrangeBank {

    // 1. Fix navigation process for entirety of application / add functions where needed

    public static void operateAccount(Account account, Client client, ArrayList<Client> clients) {
        Scanner input = new Scanner(System.in);
        int action;
        do {
            System.out.println();
            System.out.println("**********************************************************");
            System.out.println("Press 1 to make a deposit! Press 2 to make a withdraw!");
            System.out.println("Press 3 to make a transfer within your own account!");
            System.out.println("Press 4 to make a transfer to someone else's account!");
            System.out.println("Press 5 to check your balance! Press 6 to exit this menu!");
            System.out.println("**********************************************************");
            System.out.println();
            action = input.nextInt();
            switch (action) {
                case 1 -> account.deposit();
                case 2 -> account.withdraw();
                case 3 -> transferWithin(client);
                case 4 -> transferToAnotherUser(client, clients);
                case 5 -> System.out.println(account);
            }
        } while (action != 6);
    }

    public static void transferWithin(Client client) {  // transfer function for within a user's account
        if (client.getCheckingAccount() == null || client.getSavingsAccount() == null) { // to transfer one needs to have more than one account type saved to their account
            System.out.println("You need both a checking and savings account in order to transfer within your own account.");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Which account would you like to transfer from? 'c' for checking or 's' for savings");
        String transferFrom = input.nextLine();
        System.out.println("Which account would you like to transfer to? 'c' for checking or 's' for savings");
        String transferTo = input.nextLine();
        System.out.println("How much money would you like to transfer?");
        double transferAmount = input.nextDouble();
        if (transferFrom.equals("c") && transferTo.equals("s")) {
            if (client.getCheckingAccount().remove(transferAmount)) {
                client.getSavingsAccount().add(transferAmount);
            }
        } else if (transferFrom.equals("s") && transferTo.equals("c")) {
            if (client.getSavingsAccount().remove(transferAmount)) {
                client.getCheckingAccount().add(transferAmount);
            }
        } else {
            System.out.println("Something must've went wrong, try again if needed!");
        }
    }

    public static void transferToAnotherUser(Client clientFrom, ArrayList<Client> clients) { // transfer function between one account and another - should be void
        if (clientFrom.getCheckingAccount() == null || clientFrom.getSavingsAccount() == null) { // to transfer one needs to have more than one account type saved to their account
            System.out.println("You need both a checking and savings account in order to transfer to someone else's account.");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the unique ID of the person who you wish to transfer to.");
        int id = input.nextInt();
        input.nextLine();
        for (Client clientTo : clients) {
            if (clientTo.getId() == id) { // if client id matches, then this code will be applied
                System.out.println("How much money would you like to transfer?");
                double transferAmount = input.nextDouble();
                if (clientFrom.getCheckingAccount().remove(transferAmount)) {
                    clientTo.getCheckingAccount().add(transferAmount);
                }
                return; // return statement which sends you back to the caller (operateAccount in this case)
            }
        }
        System.out.println("The unique ID does not exist!");
    }

    public static Client getClient(ArrayList<Client> clients, int id) { // takes an input of the list of clients registered with the bank, and their unique ID's
        for (Client client : clients) { // for each loop to iterate through the list of clients and find the client based on their unique id
            if (client.getId() == id) {
                return client; // returns the client once a match is found
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // scanner object named 'input'

        ArrayList<Client> clients = new ArrayList<>(); // This creates an arraylist of the clients registered with the bank.
        // Note that the 'clients' variable used here is independent of the one used in the function that finds the client by iterating through the arraylist

        int x = 0; // Variable for the outer loop/nested loop/switch statement

        String accessAccount, accountType, accessExistingAccount;

        try {
            while (x == 0) { // outer loop
                System.out.println("Press 1 to create an account, Press 2 to login, 3 to exit the application, and 0 to come back to this menu!");
                x = input.nextInt();
                input.nextLine();
                while (x == 1 || x == 2 || x == 3) { // inner loop
                    switch (x) {
                        case 1 -> {
                            System.out.println("Hello, and welcome to Orange Bank! Please provide your full name, and an automatically generated ID/password will be given to you!");
                            Client client = new Client(input.nextLine());
                            clients.add(client);
                            System.out.println(client); // displays client's full name, and the automatically generated ID/password
                            System.out.println("\nIf you would like to login with your new account, press 2! If you would like to go back to the main menu, press 0!");
                            x = input.nextInt();
                            input.nextLine();
                        }
                        case 2 -> {
                            System.out.println("Please enter your six-digit ID first, followed by your password!"); // Asks the client to log in with their username (ID) and password
                            int id = input.nextInt(); // id of the client
                            input.nextLine(); // prevents skip
                            Client client = getClient(clients, id);
                            String pw = input.nextLine(); // pw of the client
                            if (client != null) {
                                if (pw.equals(client.getPassword())) {
                                    System.out.println("Welcome " + client.getName() + " !");
                                    do {
                                        System.out.println("\nWould you like to access the accounts page (press 2) or return to the main menu (press 0)?");
                                        x = input.nextInt();
                                        input.nextLine();
                                        if (x == 2) {
                                            System.out.println("\nDo you want to make a new account or access an existing one? 'n' for new, 'e' for existing");
                                            accessAccount = input.nextLine();
                                            if (accessAccount.equals("n")) { // for new accounts
                                                System.out.println("\nPlease create a new account! Press 'c' for a new checking account, or 's' for a new savings account!");
                                                accountType = input.nextLine(); // creates a new account of type checking or savings
                                                if (accountType.equals("c")) {
                                                    if (client.getCheckingAccount() == null) { // prevents making a new checking account if it already exists
                                                        client.setCheckingAccount();
                                                        operateAccount(client.getCheckingAccount(), client, clients);
                                                    } else {
                                                        System.out.println("\nA checking account already exists.");
                                                    }
                                                } else if (accountType.equals("s")) {
                                                    if (client.getSavingsAccount() == null) { // prevents making a new savings account if it already exists
                                                        client.setSavingsAccount();
                                                        operateAccount(client.getSavingsAccount(), client, clients);
                                                    } else {
                                                        System.out.println("\nA savings account already exists.");
                                                    }
                                                } else {
                                                    System.out.println("\nYou entered the wrong input! You were supposed to enter 'c' for checking or 's' for savings");
                                                }
                                            } else if (accessAccount.equals("e")) { // for existing accounts
                                                if (client.getCheckingAccount() != null || client.getSavingsAccount() != null) { // to ensure at least one account exists
                                                    System.out.println("\nWhich account would you like to access? 'c' for checking or 's' for savings");
                                                    accessExistingAccount = input.nextLine(); // variable used to access existing accounts
                                                    if (accessExistingAccount.equals("c") && client.getCheckingAccount() != null) {// to prevent null checking account from being created
                                                        operateAccount(client.getCheckingAccount(), client, clients);
                                                    } else if (accessExistingAccount.equals("s") && client.getSavingsAccount() != null) { // to prevent null savings account from being created
                                                        operateAccount(client.getSavingsAccount(), client, clients);
                                                    } else {
                                                        System.out.println("\nYou entered the wrong input! You were supposed to enter 'c' for checking or 's' for savings");
                                                    }
                                                } else {
                                                    System.out.println("\nYou have to create an account first! Make sure to press 'n' this time.");
                                                }
                                            } else {
                                                System.out.println("\nYou have to enter 'n' for a new account or 'e' for an existing account!");
                                            }
                                        }
                                    } while (x == 2);
                                } else { // if password is incorrect
                                    System.out.println("\nSorry, this password is incorrect! Press 2 to try to log in again, press 0 to go back to the main menu!");
                                    x = input.nextInt();
                                    input.nextLine();
                                }
                            } else { // if user ID does not exist or is invalid
                                System.out.println("\nSorry, this account either does not exist, or the user ID provided is invalid! Press 2 to try to log in again, press 0 to go back to the main menu!");
                                x = input.nextInt();
                                input.nextLine();
                            }
                        }
                        case 3 -> {
                            System.out.println("\nBye! Have a good day!");
                            System.exit(0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("\nSorry, something went wrong!");
        }
    }
}




