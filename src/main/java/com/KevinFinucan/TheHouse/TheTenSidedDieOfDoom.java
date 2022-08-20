package com.KevinFinucan.TheHouse;

import java.util.Random;

public class TheTenSidedDieOfDoom implements Game {

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


    @Override
    public String toString() {
        return NAME + "\n" + DESCRIPTION;
    }


    public void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
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
