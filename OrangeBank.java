package javaproject4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An interactive banking application which helps the user navigate between different options
 * The OrangeBank class is for clients to navigate through the banking website
 * The Client class is used to store personal/login information about the client
 * The Account class is used to store general banking details about the client
 *
 * @author Wasim Nasser
 */

public class OrangeBank {

    // 1. Fix navigation process for entirety of application***

    // 2. Add ability to transfer between checking and savings account for an individual user

    // 3. Add the ability to transfer between two different accounts (from one user to another)

    public static void getMenu(Account account) {
        Scanner input = new Scanner(System.in);
        int action;
        do {
            System.out.println();
            System.out.println("*************************************************************************************");
            System.out.println("Press 1 to make a deposit! Press 2 to make a withdraw! Press 3 to check your balance!");
            System.out.println("Press 4 to go to back to the main menu!"); // need to add transfer money from one account to another, and other stuff
            System.out.println("*************************************************************************************");
            System.out.println();
            action = input.nextInt();
            switch (action) {
                case 1 -> account.deposit();
                case 2 -> account.withdraw();
                case 3 -> System.out.println(account);
            }
        } while (action != 4);
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

        // try {
        while (x == 0) { // outer loop
            System.out.println("Press 1 to create an account, Press 2 to login, 3 to exit the application, and 0 to come back to this menu!");
            x = input.nextInt();
            input.nextLine();
            while (x == 1 || x == 2 || x == 3 || x == 4) { // inner loop
                switch (x) {
                    case 1 -> {
                        System.out.println("Hello, and welcome to Orange Bank! Please provide your full name, and an automatically generated ID/password will be given to you!");
                        Client client = new Client(input.nextLine());
                        clients.add(client);
                        System.out.println(client); // displays client's full name, and the automatically generated ID/password
                        System.out.println();
                        System.out.println("If you would like to login with your new account, press 2! If you would like to go back to the main menu, press 0!");
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
                                System.out.println();
                                do {
                                    System.out.println("Would you like to make a new account or access an existing one? 'n' for new, 'e' for existing");
                                    accessAccount = input.nextLine();
                                    if (accessAccount.equals("n")) { // for new accounts
                                        System.out.println("Please create a new account! Press 'c' for a new checking account, or 's' for a new savings account!");
                                        accountType = input.nextLine(); // creates a new account of type checking or savings
                                        if (accountType.equals("c")) {
                                            if (client.getCheckingAccount() == null) { // prevents making a new checking account if it already exists
                                                client.setCheckingAccount();
                                                getMenu(client.getCheckingAccount());
                                                x = 0;
                                            }
                                        }
                                        if (accountType.equals("s")) {
                                            if (client.getSavingsAccount() == null) { // prevents making a new savings account if it already exists
                                                client.setSavingsAccount();
                                                getMenu(client.getSavingsAccount());
                                                x = 0;
                                            }
                                        }
                                    } else if (accessAccount.equals("e")) { // for existing accounts
                                        if (client.getCheckingAccount() != null || client.getSavingsAccount() != null) { // to ensure at least one account exists
                                            System.out.println("Which account would you like to access? 'c' for checking or 's' for savings");
                                            accessExistingAccount = input.nextLine(); // variable used to access existing accounts
                                            if (accessExistingAccount.equals("c") && client.getCheckingAccount() != null) {// to prevent null checking account from being created
                                                getMenu(client.getCheckingAccount());
                                                x = 0;
                                            }
                                            if (accessExistingAccount.equals("s") && client.getSavingsAccount() != null) { // to prevent null savings account from being created
                                                getMenu(client.getSavingsAccount());
                                                x = 0;
                                            }
                                        } else {
                                            System.out.println();
                                            System.out.println("You have to create an account first! Make sure to press 'n' this time.");
                                            System.out.println(); // have to figure this one out...
                                        }
                                    } else {
                                        System.out.println();
                                        System.out.println("You have to enter 'n' for a new account or 'e' for an existing account!");
                                        System.out.println();
                                    }
                                } while((!(accessAccount.equals("n") || accessAccount.equals("e"))));
                            } else { // if password is incorrect
                                System.out.println();
                                System.out.println("Sorry, this password is incorrect! Press 2 to try to log in again, press 0 to go back to the main menu!");
                                x = input.nextInt();
                                input.nextLine();
                            }
                        } else { // if user ID does not exist or is invalid
                            System.out.println("Sorry this account does not exist! Press 2 to try to log in again, press 0 to go back to the main menu!");
                            x = input.nextInt();
                            input.nextLine();
                        }
                    }
                    case 3 -> {
                        System.out.println("Bye! Have a good day!");
                        System.exit(0);
                    }
                }
            }
        }
        // } catch (Exception e) {System.out.println("There seems to be something wrong!"); }
    }
}




