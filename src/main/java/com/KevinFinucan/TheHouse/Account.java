package com.KevinFinucan.TheHouse;

public class Account {

    /*
    The account class is a running balance, starting at 100, that users have to bet on each game.
    The class contains String and int get methods and a set method,
     */

    private int balance = 100; // Default balance starts at 100

    public int getBalanceInt() {return balance;}

    public void getBalanceString() {
        System.out.println("Your account balance is: " + balance + " tokens" + "\n");
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
