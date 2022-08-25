package com.KevinFinucan.TheHouse;

import java.util.Random;
import java.util.Scanner;

public class TheTenSidedDieOfDoom implements Game {

    Convenience convenience = new Convenience();

    private static final String NAME = "The Ten Sided Die of Doom";
    private static final String DESCRIPTION =
            """
                    In the The Ten Sided Die of Doom, players bet on the outcome of a ten-sided die roll.
                    Choose a range by selecting a low and high value, and an amount that you would like to bet.
                    If the ten-sided die roll falls within your selected range, you win! If not, your bet amount is lost.
                    Keep in mind - the smaller the range you select, the greater your reward!
                    In order to win big, you have to risk big.
                    """;

    private static final String WELCOME_MESSAGE =
            """
                    Welcome to The Ten-Sided Die of Doom. The House will roll a 10-sided die.
                    Select your low and high values to set the range you bet the die roll will fall within.
                    Then choose your bet amount. Remember, the smaller your range, the higher your potential winnings!
                    """;


    // Variables for game functionality
    int rangeMin;
    int rangeMax;
    int betAmount;
    double betMultiplier;
    boolean isWinner;
    int winnings;

    // Methods
    @Override
    public String toString() {
        return NAME + "\n" + DESCRIPTION;
    }

    public void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public int execute(Scanner userInput, int currentBalance) {
        int updatedBalance = currentBalance;
        boolean isPlaying = true; // boolean to allow users to play additional rounds without returning to the main menu
        boolean betLocked = false; // boolean to allow user to review their range and bet before committing.
        while (isPlaying) {
            displayWelcomeMessage();
            while (!betLocked) {
                rangeMin = promptForLowHighValue(userInput, "lowest");
                rangeMax = promptForLowHighValue(userInput, "highest");
                enforceAccurateMinMaxRelationship(); // enforces maxRange must be >= minRange & switches them as needed
                betAmount = convenience.promptForBet(userInput, currentBalance);
                if (rangeMin == rangeMax) {
                    System.out.println(
                            "Would you like to lock in your bet of " + betAmount + " tokens for the die to land " +
                                    "on " + rangeMin + "?");
                } else {
                    System.out.println(
                            "Would you like to lock in your bet of " + betAmount + " tokens for the die to land " +
                                    "within the range of " + rangeMin + " through " + rangeMax + "?");
                }
                String lockBet = convenience.promptForYesNo(userInput);
                betLocked = (lockBet.equals("y"));
            }
            betMultiplier = setBetMultiplier(rangeMin, rangeMax);
            isWinner = rollDieAndSetIsWInner(rangeMin, rangeMax);
            // If player is a winner, their winnings will be converted to an int, rounded in their favor.
            winnings = (int) Math.ceil(betAmount * betMultiplier);
            updatedBalance = convenience.executeWinLossResults(winnings, currentBalance, betAmount, isWinner);
            betLocked = false; // redefined to allow the player to reset their range and bet amount in additional rounds
            System.out.println("Would you like to play again?: ");
            String playAgain = convenience.promptForYesNo(userInput);
            if (playAgain.equals("n")) {
                isPlaying = false; // Additional rounds is the default
            }
        }
        return updatedBalance;
    }

    /*
    This method sets the low & high values of the range for the Ten Sided Die of Doom bet.
    The method will enforce that the value entered must fall within 1-10 range, and re-prompt as needed.
     */
    public int promptForLowHighValue(Scanner userInput, String lowHigh) {
        System.out.println(
                "Please choose the " + lowHigh + " value (1-10) that you think the die roll will land within: ");
        int value = convenience.promptForInt(userInput);
        if (value < 1) {
            value = 1;
            System.out.println(
                    "Oops. The lowest number on a 10-sided die is 1! The House has adjusted this value " +
                            "to 1 for you.");
        } else if (value > 10) {
            value = 10;
            System.out.println(
                    "Oops. The highest number on a 10-sided die is 10! The House has adjusted this value " +
                            "to 10 for you.");
        }
        return value;
    }

    /*
    This method will enforce that rangeMin is less than or equal to rangeMax.
    If rangeMax is less than rangeMin, the method will inform the player & switch them.
    */
    public void enforceAccurateMinMaxRelationship() {
        if (rangeMax < rangeMin) {
            int newRangeMin = rangeMax;
            int newRangeMax = rangeMin;
            rangeMin = newRangeMin;
            rangeMax = newRangeMax;
            System.out.println(
                    "Oops. It looks like you switched your high and low values. The House has corrected them for you.");
        }
    }

    /*
    The player's percentage of winnings relative to their bet will be greater when their range is smaller.
    This follows the basic betting principle of higher risk, higher reward, and encourages the player
    to place higher risk bets.
     */
    public double setBetMultiplier(int rangeMin, int rangeMax) {
        double betMultiplier = 0;
        int rangeSpan = rangeMax - rangeMin;
        switch (rangeSpan) {
            case 9 -> {
                System.out.println(
                        "You're a cheeky one, aren't you? You may be a winner, but bragging rights " +
                        "are all you will get for this round!");
                betMultiplier = 0;
            }
            case 8 -> betMultiplier = 1;
            case 7 -> betMultiplier = 1.25;
            case 6 -> betMultiplier = 1.5;
            case 5 -> betMultiplier = 1.75;
            case 4 -> betMultiplier = 2;
            case 3 -> betMultiplier = 2.5;
            case 2 -> betMultiplier = 3;
            case 1 -> betMultiplier = 3.5;
            case 0 -> betMultiplier = 4;
        }
        return betMultiplier;
    }

    /*
    Random.nextInt generates a pseudo-random int between 0 and the bound number provided.
    A bound number of 10 is provided in order to generate an int 0-9.
    1 is added to simulate a 10-sided die with values 1-10.
     */
    public boolean rollDieAndSetIsWInner(int rangeMin, int rangeMax) {
        Random random = new Random();
        int roll = random.nextInt(10) + 1;
        System.out.println("The House rolled: " + roll);
        return (roll >= rangeMin && roll <= rangeMax); //If the roll is within the bet range, the player is a winner
    }

}
