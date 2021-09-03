package javaproject4;

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

    // need to improve the application a bit more, but so far it's pretty decent with the amount of time put into it
    // try this

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // scanner object named 'input'

        int clientID = 0;

        String clientPassword = "";

        String clientName = "";

        Account checkingAccount = new Account();

        Account savingsAccount = new Account();

        String accessExistingAccount;

        int action;

        int x = 0;

        try {
            while (x == 0) { // outer loop
                System.out.println("Press 1 to create an account, Press 2 to login, 3 to exit the application, and 0 to come back to this menu!");
                // 4 to see all client's registered with the bank
                x = input.nextInt();
                input.nextLine();
                while (x == 1 || x == 2 || x == 3) { // inner loop
                    switch (x) {
                        case 1 -> {
                            System.out.println("Hello, and welcome to Orange Bank! Please provide your full name, and an automatically generated ID/password will be given to you!");
                            Client client = new Client(input.nextLine()); // creates a new client using the Client constructor
                            System.out.println(client); // displays client's full name, and the automatically generated ID/password
                            clientID = client.getId(); // puts client's ID into a variable of type int
                            clientPassword = client.getPassword(); // puts client's password into a variable of type string
                            clientName = client.getName(); // stores client's name into a variable of type string
                            System.out.println();
                            System.out.println("If you would like to login with your new account, press 2! If you would like to go back to the main menu, press 0!");
                            x = input.nextInt();
                            input.nextLine();
                        }
                        case 2 -> {
                            System.out.println("Please enter your six-digit ID first, followed by your password!"); // Asks the client to log in with their username (ID) and password
                            int id = input.nextInt(); // id of the client
                            input.nextLine(); // prevents skip
                            String pw = input.nextLine(); // pw of the client
                            if (id == clientID && pw.equals(clientPassword)) {
                                System.out.println("Welcome " + clientName + "!"); // displays client's name
                                System.out.println();
                                System.out.println("Would you like to make a new account or access an existing one? 'n' for new, 'e' for existing");
                                String accessAccount = input.nextLine();
                                if (accessAccount.equals("n")) { // for new accounts
                                    System.out.println("Please create a new account! Press 'c' for a new checking account, or 's' for a new savings account!");
                                    String accountType = input.nextLine(); // creates a new account of type checking or savings
                                    if (accountType.equals("c")) {
                                        // *need to prevent making a new checking account if it already exists*
                                        checkingAccount = new Account("checking");
                                        do {
                                            System.out.println();
                                            System.out.println("*************************************************************************************");
                                            System.out.println("Press 1 to make a deposit! Press 2 to make a withdraw! Press 3 to check your balance!");
                                            System.out.println("Press 4 to go to back to the main menu!"); // need to add transfer money from one account to another, and other stuff
                                            System.out.println("*************************************************************************************");
                                            System.out.println();
                                            action = input.nextInt();
                                            switch (action) {
                                                case 1 -> checkingAccount.deposit();
                                                case 2 -> checkingAccount.withdraw();
                                                case 3 -> System.out.println(checkingAccount);
                                                case 4 -> x = 0;
                                            }
                                        } while (action != 4);
                                    }
                                    if (accountType.equals("s")) {
                                        // *need to prevent making a new savings account if it already exists*
                                        savingsAccount = new Account("savings");
                                        do {
                                            System.out.println();
                                            System.out.println("*************************************************************************************");
                                            System.out.println("Press 1 to make a deposit! Press 2 to make a withdraw! Press 3 to check your balance!");
                                            System.out.println("Press 4 to go to back to the main menu!"); // need to add transfer money from one account to another, and other stuff
                                            System.out.println("*************************************************************************************");
                                            System.out.println();
                                            action = input.nextInt();
                                            switch (action) {
                                                case 1 -> savingsAccount.deposit();
                                                case 2 -> savingsAccount.withdraw();
                                                case 3 -> System.out.println(savingsAccount);
                                                case 4 -> x = 0;
                                            }
                                        } while (action != 4);
                                    }
                                } else if (accessAccount.equals("e")) { // for existing accounts
                                    if (checkingAccount.getAccount().equals("checking") || savingsAccount.getAccount().equals("savings")) { // to ensure at least one account exists
                                        System.out.println("Which account would you like to access? 'c' for checking or 's' for savings");
                                        accessExistingAccount = input.nextLine();
                                        if (accessExistingAccount.equals("c") && checkingAccount.getAccount().equals("checking")) { // to prevent null checking account from being created
                                            do {
                                                System.out.println();
                                                System.out.println("*************************************************************************************");
                                                System.out.println("Press 1 to make a deposit! Press 2 to make a withdraw! Press 3 to check your balance!");
                                                System.out.println("Press 4 to go to back to the main menu!"); // need to add transfer money from one account to another, and other stuff
                                                System.out.println("*************************************************************************************");
                                                System.out.println();
                                                action = input.nextInt();
                                                switch (action) {
                                                    case 1 -> checkingAccount.deposit();
                                                    case 2 -> checkingAccount.withdraw();
                                                    case 3 -> System.out.println(checkingAccount);
                                                    case 4 -> x = 0;
                                                }
                                            } while (action != 4);
                                        }
                                        if (accessExistingAccount.equals("s") && savingsAccount.getAccount().equals("savings")) { // to prevent null savings account from being created
                                            do {
                                                System.out.println();
                                                System.out.println("*************************************************************************************");
                                                System.out.println("Press 1 to make a deposit! Press 2 to make a withdraw! Press 3 to check your balance!");
                                                System.out.println("Press 4 to go to back to the main menu!"); // need to add transfer money from one account to another, and other stuff
                                                System.out.println("*************************************************************************************");
                                                System.out.println();
                                                action = input.nextInt();
                                                switch (action) {
                                                    case 1 -> savingsAccount.deposit();
                                                    case 2 -> savingsAccount.withdraw();
                                                    case 3 -> System.out.println(savingsAccount);
                                                    case 4 -> x = 0;
                                                }
                                            } while (action != 4);
                                        }
                                    } else { // if user does not enter the correct input, they will have to re-login
                                        System.out.println();
                                        System.out.println("You have to enter the correct input! ");
                                        System.out.println();
                                    }
                                } else { // if login or password is incorrect, this message will be displayed
                                    System.out.println();
                                    System.out.println("Sorry, this account does not exist! Press 2 to try to log in again. To go back to the main menu, press 0.");
                                    x = input.nextInt();
                                    input.nextLine(); // prevents skip
                                }
                                System.out.println();
                            }
                        }
                        case 3 -> {
                            System.out.println("Bye! Have a good day!");
                            System.exit(0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("There seems to be something wrong!");
        }
    }
}



