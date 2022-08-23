package com.KevinFinucan.TheHouse;

import java.util.Random;
import java.util.Scanner;

public class TheUnFlippingBelievableCoin implements Game {

    private static final String name = "The Un-Flipping-Believable Coin";
    private static final String description =
            """
                    In the The The Un-Flipping-Believable Coin, players bet on the outcome of a simple coin toss,
                    with the potential to grow your account balance exponentially - Un-Flipping-Believable!
                    Every time you correctly guess the outcome of a coin toss, your return doubles.
                    But watch out - as soon as you miss a coin toss, you lose everything you've gained,
                    plus your original bet.
                    """;

    private static final String WELCOME_MESSAGE =
            """
                    Welcome to The Un-Flipping-Believable Coin. The House will flip a coin. Yup, that's it!
                    Select "heads" or "tails". You have a simple 50/50 chance to double your bet!
                    But that's not all. Roll your bet forward and choose "heads" or "tails" again for a chance to double
                    your winnings again! In "The Un-Flipping-Believable Coin," every win doubles
                    your pool of winnings and you can exponentially grow your account! But be careful;
                    If you guess wrong, you'll lose everything you've gained, plus your original bet.
                    """;


    Convenience convenience = new Convenience();

    // Variables for game functionality
    boolean isWinner;
    int winnings = 0; // Instantiated at zero to allow for differentiating first round w/additional rounds.
    String coinFlipBet;
    String coinFlipResults;
    int betAmount;


    @Override
    public String toString() {
        return name + "\n" + description;
    }

    public void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    @Override
    public int execute(Scanner userInput, int currentBalance) {
        int updatedBalance = currentBalance;
        boolean isPlaying = true; // boolean to allow users to play additional rounds and roll their bet forward
        boolean winningsRolledForward = false; // Set to allow winnings to replace bet for additional rounds
        boolean betLocked = false; // boolean to allow user to review their bet before committing.
        while (isPlaying) {
            displayWelcomeMessage();
            if (!(winningsRolledForward)) {
                while (!betLocked) {
                    coinFlipBet = promptForHeadsOrTails(userInput);
                    betAmount = convenience.promptForBet(userInput, currentBalance);
                    winnings = betAmount;
                    System.out.println("Would you like to lock in your bet of " + betAmount + " for the coin to " +
                            "land on " + coinFlipBet + "?");
                    String lockBet = convenience.promptForYesNo(userInput);
                    betLocked = (lockBet.equals("y"));
                }
            } else if (winningsRolledForward) {
                while (!betLocked) {
                    coinFlipBet = promptForHeadsOrTails(userInput);
                    System.out.println(
                            "Would you like to lock in your bet for the coin to land on " + coinFlipBet + "?: ");
                    String lockBet = convenience.promptForYesNo(userInput);
                    betLocked = (lockBet.equals("y"));
                }
            }
            isWinner = flipCoinAndSetIsWInner();
            if (isWinner) {
                winnings *= 2;
            }
            updatedBalance = convenience.executeWinLossResults(winnings, currentBalance, betAmount, isWinner);
            betLocked = false; // redefined to allow the player to reset their bet in additional rounds
            System.out.println("Would you like to roll your bet forward for a chance to double your winnings?: ");
            String playAgain = convenience.promptForYesNo(userInput);
            switch (playAgain) {
                case "y" -> winningsRolledForward = true;
                case "n" -> isPlaying = false; // Additional rounds is the default
            }
        }
        return updatedBalance;
    }

    // Prompt for h/t enforces that the player's entry must be h/t
    public String promptForHeadsOrTails(Scanner userInput) {
        String response = "";
        while (response.equals("")) {
            System.out.println("Enter 'Heads' or 'Tails' (H/T): ");
            // Any string starting with 'H/h' is assumed to be heads
            // Any string starting with 'T/t' is assumed to be tails
            response = userInput.nextLine().substring(0, 1).toLowerCase();
            if (!(response.equals("h") || response.equals("t"))) {
                response = "";
                System.out.println("Oops. The House didn't catch that. Please try again.");
            }
        }
        switch (response) {
            case "h" -> {
                response = "Heads";
            }
            case "t" -> response = "Tails";
        }
        return response;
    }

    /*
    Random.nextInt generates a pseudo-random int between 0 and the bound number provided.
    A bound number of 2 is provided in order to generate an int 0-1.
     */
    public boolean flipCoinAndSetIsWInner() {
        Random random = new Random();
        int coinResult = random.nextInt(2);
        switch (coinResult) {
            case 0 -> coinFlipResults = "Heads";
            case 1 -> coinFlipResults = "Tails";
        }
        System.out.println("The House flipped: " + coinFlipResults);
        return (coinFlipBet.equals(coinFlipResults));
    }

}