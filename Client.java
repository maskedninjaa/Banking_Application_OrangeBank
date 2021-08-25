package javaproject4;

import java.util.Random;

public class Client {

    private String name; // name of the client
    private int id; // id used to log in
    private String password; // password used to log in

    public Client(String name) {
        this.name = name; // name of the client
        setId(); // generates a random six-digit ID for the client
        setPassword(); // generates a random ten-digit password for the client
    }

    @Override
    public String toString() {
        return "New client " + name + "!" +
                "\nHere is your new ID: " + id +
                "\nHere is your new password: " + password +
                "\nPlease save this information somewhere safe!";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    // creates six-digit ID for simplicity's sake
    public void setId() {
        Random randomID = new Random();
        int max = 999999;
        int min = 100000;
        this.id = randomID.nextInt((max - min) + 1) + min;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        Random randomPW = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*-_=+./";
        StringBuilder randomPassword = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            randomPassword.append(characters.charAt(randomPW.nextInt(characters.length())));
        }
        this.password = randomPassword.toString();
    }
}
