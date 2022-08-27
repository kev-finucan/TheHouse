package com.KevinFinucan.TheHouse;

import java.util.Random;
import java.util.Scanner;

public class TwentySidedHigherOrLower implements Game {

    private static final String name = "Twenty-Sided Higher Or Lower";
    private static final String description =
            """
                    In Twenty-Sided Higher Or Lower, players bet whether the next roll of a 20-sided die will
                    be higher or lower than the last roll. An equal roll is always a loss. This is the only game that
                    allows players to maintain a statistical advantage. As such, each win will only 50% over your bet.
                    Your winnings can be rolled forward into additional bets; But remember, if you lose, you lose
                    everything you've rolled forward plus your original bet!
                    """;

    private static final String WELCOME_MESSAGE =
            """
                    Welcome to Twenty-Sided Higher or Lower. The House will roll a 20-sided die.
                    Bet whether the next roll will be higher or lower than the previous roll. An equal roll is
                    always a loss. Next, choose your bet amount. If you win, you get 50% over your bet!
                    Roll it forward to increase your winnings. Can you ride your statistical advantage all the way
                    to beating The House?
                    """;

    Convenience convenience = new Convenience();

    // Variables for game functionality
    int standingRoll;
    boolean isWinner;
    int winnings = 0; // Instantiated at zero to allow for differentiating first round w/additional rounds.
    String higherOrLowerBet;
    int betAmount;


    @Override
    public String toString() {
        return name + "\n" + description;
    }

    public void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    @Override
    public void execute(Scanner userInput, Account playerAccount) {
        int rollingBalance = playerAccount.getBalanceInt();
        boolean isPlaying = true; // boolean to allow users to play additional rounds and roll their bet forward
        boolean winningsRolledForward = false; // Set to allow winnings to replace bet for additional rounds
        boolean betLocked = false; // boolean to allow user to review their bet before committing.
        displayWelcomeMessage();
        while (isPlaying) {
            if (!(winningsRolledForward)) {
                setStandingRoll();
                while (!betLocked) {
                    higherOrLowerBet = promptForHigherOrLower(userInput);
                    betAmount = convenience.promptForBet(userInput, playerAccount.getBalanceInt());
                    winnings = betAmount;
                    System.out.println("Would you like to lock in your bet of " + betAmount + " for the next roll to " +
                            "be " + higherOrLowerBet + " than " + standingRoll + "?");
                    String lockBet = convenience.promptForYesNo(userInput);
                    betLocked = (lockBet.equals("y"));
                }
            } else {
                while (!betLocked) {
                    higherOrLowerBet = promptForHigherOrLower(userInput);
                    System.out.println("Would you like to lock in your bet of " + rollingBalance + " for the next roll to " +
                            "be " + higherOrLowerBet + " than " + standingRoll + "?");
                    String lockBet = convenience.promptForYesNo(userInput);
                    betLocked = (lockBet.equals("y"));
                }
            }
            // Generates a new roll, sets isWinner, and redefines the standing roll with the new roll.
            rollDieAndSetIsWInner();
            if (isWinner) {
                // If player is a winner, their winnings will be converted to an int, rounded in their favor.
                winnings = (int) Math.ceil(winnings * 1.5);
                winningsRolledForward = true;
            } // Redefined to allow player to place a fresh bet if replaying after a loss
            else {
                winningsRolledForward = false;
            }
            rollingBalance = convenience.executeWinLossResults
                    (winnings, playerAccount.getBalanceInt(), betAmount, isWinner);
            betLocked = false; // redefined to allow the player to reset their bet in additional rounds
            playAgainPrompt(isWinner);
            String playAgain = convenience.promptForYesNo(userInput);
            if (playAgain.equals("n")) {
                isPlaying = false; // Additional rounds is the default
            }
            if (!isWinner) {
                playerAccount.setBalance(rollingBalance);
            }
        }
        if (isWinner) {
            playerAccount.setBalance(rollingBalance);
        }
    }

    // Prompt for h/t enforces that the player's entry must be h/t
    public String promptForHigherOrLower(Scanner userInput) {
        String response = "";
        while (response.equals("")) {
            System.out.println("The standing roll is: " + standingRoll + ". Will you bet the next roll will be " +
                    "'Higher' or 'Lower' (H/L)?: ");
            // Any string starting with 'H/h' is assumed to be higher
            // Any string starting with 'T/t' is assumed to be lower
            response = userInput.nextLine().substring(0, 1).toLowerCase();
            if (!(response.equals("h") || response.equals("l"))) {
                response = "";
                System.out.println("Oops. The House didn't catch that. Please try again.");
            }
        }
        switch (response) {
            case "h" -> {
                response = "Higher";
            }
            case "l" -> response = "Lower";
        }
        return response;
    }

    public void playAgainPrompt(boolean isWinner) {
        if (isWinner) {
            System.out.println(
                    "Would you like to roll your bet forward for a chance to win even more?: ");
        } else {
            System.out.println("Would you like to play again?: ");
        }
    }

    /*
   Random.nextInt generates a pseudo-random int between 0 and the bound number provided.
   A bound number of 10 is provided in order to generate an int 0-9.
   1 is added to simulate a 10-sided die with values 1-10.
    */
    public void rollDieAndSetIsWInner() {
        Random random = new Random();
        int roll = random.nextInt(20) + 1;
        System.out.println("The House rolled: " + roll);
        isWinner = ((roll > standingRoll && higherOrLowerBet.equals("Higher")) ||
                (roll < standingRoll && higherOrLowerBet.equals("Lower")));
        standingRoll = roll;
    }

    public void setStandingRoll() {
        Random random = new Random();
        standingRoll = random.nextInt(20) + 1;
    }

}
