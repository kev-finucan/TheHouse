package com.KevinFinucan.TheHouse;

import java.util.Scanner;

public class Prize {

    /*
    The prize class contains Strings for prizes, prize menu, and ints for their costs in tokens.
    Players can spend the tokens they've won from betting on games to claim prizes.
    The strings and ints are called by the SystemInOutConsole methods.
     */

    Convenience convenience = new Convenience();

    private static final String PRIZE_1 = "\"I was beat by The House and all I got was this lousy keychain\" keychain";
    private static final String PRIZE_2 = "Small stuffed dolphin";
    private static final String PRIZE_3 = "Giant teddy bear";
    private static final String PRIZE_4 = "\"The House\" official board game";
    private static final String PRIZE_5 = "The official \"I BEAT THE HOUSE\" trophy";

    private static final String AVAILABLE_PRIZES =
            """
                    The House's available prizes are:
                    1. "I was beat by The House and all I got was this lousy keychain" keychain - 100 tokens
                    2. Small stuffed dolphin - 500 tokens
                    3. Giant teddy bear 2,000 tokens
                    4. "The House" official board game - 5,000 tokens
                    5. The official "I BEAT THE HOUSE" gold-ish trophy - 10,000 tokens
                    """;

    private static final int PRIZE_1_COST = 100;
    private static final int PRIZE_2_COST = 500;
    private static final int PRIZE_3_COST = 2000;
    private static final int PRIZE_4_COST = 5000;
    private static final int PRIZE_5_COST = 10000;

    //GETTERS
    public String getPrize1String() {
        return PRIZE_1;
    }

    public String getPrize2String() {
        return PRIZE_2;
    }

    public String getPrize3String() {
        return PRIZE_3;
    }

    public String getPrize4String() {
        return PRIZE_4;
    }

    public String getPrize5String() {
        return PRIZE_5;
    }

    public int getPrize1Cost() {
        return PRIZE_1_COST;
    }

    public int getPrize2Cost() {
        return PRIZE_2_COST;
    }

    public int getPrize3Cost() {
        return PRIZE_3_COST;
    }

    public int getPrize4Cost() {
        return PRIZE_4_COST;
    }

    public int getPrize5Cost() {
        return PRIZE_5_COST;
    }

    // Methods
    public int executePrizes(Scanner userInput, int currentBalance) {
        displayPrizes();
        return prizesMenu(userInput, currentBalance);
    }

    public void displayPrizes() {
        System.out.println(AVAILABLE_PRIZES);
    }

    // prizeMenu is the inner menu for prizes. Players can choose to use their tokens to purchase a prize
    public int prizesMenu(Scanner userInput, int currentBalance) {
        int updatedBalance = currentBalance;
        System.out.println("Would you like to purchase a prize with your tokens?: ");
        String menuOptionString = convenience.promptForYesNo(userInput);
        switch (menuOptionString) {
            case "y" -> updatedBalance = purchasePrize(userInput, currentBalance);
            case "n" -> System.out.println("Okay, maybe later. Remember to bet on games to win tokens. Good luck!");
        }
        return updatedBalance;
    }

    /*
  purchasePrize is an inner prize menu that allows players to select a prize for purchase,
  verifies that they have enough tokens to purchase the prize,
  and returns their prize (if they have enough tokens) or directs them to play games to win more tokens.
   */
    public int purchasePrize(Scanner userInput, int currentBalance) {
        int updatedBalance = currentBalance;
        System.out.println("Which prize would you like to purchase (1-5): ");
        int prizeSelection = convenience.promptForInt(userInput);
        while (!(prizeSelection == 1 || prizeSelection == 2 || prizeSelection == 3 ||
                prizeSelection == 4 || prizeSelection == 5)) {
            System.out.println("Please enter a valid prize selection: ");
            prizeSelection = convenience.promptForInt(userInput);
        }
        switch (prizeSelection) {
            case 1 -> updatedBalance = purchasePrize(currentBalance, PRIZE_1_COST, PRIZE_1);
            case 2 -> updatedBalance = purchasePrize(currentBalance, PRIZE_2_COST, PRIZE_2);
            case 3 -> updatedBalance = purchasePrize(currentBalance, PRIZE_3_COST, PRIZE_3);
            case 4 -> updatedBalance = purchasePrize(currentBalance, PRIZE_4_COST, PRIZE_4);
            case 5 -> updatedBalance = purchasePrize(currentBalance, PRIZE_5_COST, PRIZE_5);
        }
        return updatedBalance;
    }

    /*
    This method verifies players have enough tokens to purchase the prize they have selected and adjusts their
    running balance if a prize is purchased.
     */
    public int purchasePrize(int currentBalance, int prizeCost, String prizeString) {
        int updatedBalance = currentBalance;
        if (currentBalance >= prizeCost) {
            System.out.println("Please enjoy your new " + prizeString + "!");
            updatedBalance -= prizeCost;
        } else { // The House is not your friend, and does not offer prizes on credit.
            System.out.println(
                    "Sorry, you don't have enough tokens for this prize. " +
                            "Remember to bet on games to win tokens. Good luck!" + "\n");
        }
        return updatedBalance;
    }
}
