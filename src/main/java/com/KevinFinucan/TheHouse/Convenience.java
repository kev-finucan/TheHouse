package com.KevinFinucan.TheHouse;

import java.util.Scanner;

public class Convenience {

    /*
    The Convenience class contains methods and strings that help the app run smoothly and display
    messages in the menu. The methods/strings are separated from the HouseGame class and grouped together in order to
    improve overall readability and organization of the app, and be accessible by all classes.
     */

    private static final String WELCOME_MESSAGE =
            """
                        WELCOME to The HOUSE! You've heard the stories; The House always wins.
                        But there are multiple sides to every story. Do you have what it takes to be the exception?
                        Will you be the one to BEAT THE HOUSE?
                        You have been credited an account with 100 tokens.
                        Use your tokens to bet on any of our 4 nerve-wracking games of chance on your way to beating the odds.
                        Or, use your tokens to collect our marvellous prizes - proof that you beat The House (or at least got a consolation gift).
                        If you can turn 100 tokens into 10,000, you can use your tokens to obtain a Winner's 'I beat the House' Trophy,
                        certifying, definitively, that you have what it takes to BEAT THE HOUSE.
                        """;

    private static final String GAME_MENU =
            """
                    Which game would you like to play? (to see game details, select "View our games" from the main menu):
                    
                    1. The Ten Sided Die of Doom
                    2. The Un-Flipping-Believable Coin
                    3. Not Your Average Slots
                    4. Twenty-Sided Higher or Lower
                    """;

    public void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void displayGameMenu() {
        System.out.println(GAME_MENU);
    }

    // Prompt for int enforces that the player's entry must be an int.
    public int promptForInt(Scanner userInput) {
        int value = 0;
        while (value == 0) {
            try {
                System.out.println("Please enter your selection: ");
                value = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Oops. The House didn't catch that. Please try again.");
            }
        }
        return value;
    }

    // Prompt for y/n enforces that the player's entry must be y/n
    public String promptForYesNo(Scanner userInput) {
        String response = "";
        while (response.equals("")) {
            System.out.println("Enter 'Y' or 'N': ");
            // Any string starting with 'Y/y' is assumed to be affirmative and is taken as 'y'
            // Any string starting with 'N/n' is assumed to be negative and is taken as 'n'
            response = userInput.nextLine().substring(0, 1).toLowerCase();
            if (!(response.equals("y") || response.equals("n"))) {
                response = "";
                System.out.println("Oops. The House didn't catch that. Please try again.");
            }
        }
        return response;
    }

    /*
    This method is utilized by all games to obtain a bet amount. The method will ensure that
    players have enough tokens in their account to place the bet, and will re-prompt as needed.
    */
    public int promptForBet(Scanner userInput, int currentBalance) {
        int betAmount;
        System.out.println("How much will you bet on this round?: ");
        betAmount = promptForInt(userInput);
        while (betAmount > currentBalance) {
            System.out.println(
                    "Sorry, eager beaver, but you don't have that many tokens to bet! " +
                            "Please choose an amount you can absorb. Your account currently holds " +
                            currentBalance + " tokens. " +
                            "How much will you bet on this round?: ");
            betAmount = promptForInt(userInput);
        }
        return betAmount;
    }

    /*
    This method is utilized by all games to confirm the players win or loss
    & make applicable adjustments to their account balance at the conclusion of each bet.
     */
    public int executeWinLossResults(int winnings, int currentBalance, int betAmount, boolean isWinner) {
        int updatedBalance;
        if (isWinner) {
            System.out.println("Congratulations, you're a winner! " +
                    winnings + " tokens have been added to your account.");
            updatedBalance = currentBalance + winnings;
        } else {
            System.out.println("Oops, looks like The House won this round. Better luck next time!");
            updatedBalance = currentBalance - betAmount;
        }
        return updatedBalance;
    }


}
