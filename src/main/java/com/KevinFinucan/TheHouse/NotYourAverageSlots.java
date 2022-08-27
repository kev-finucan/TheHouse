package com.KevinFinucan.TheHouse;

import java.util.Random;
import java.util.Scanner;

public class NotYourAverageSlots implements Game {

    private static final String name = "Not Your Average Slots";
    private static final String description =
            """
                    In Not Your Average Slots, players place 5 combined bets on the outcome of a slot machine,
                    with the potential to win five different ways! The five rows of the slot machine contain
                    colors, shapes, numbers, letters, and animals. The player will bet on the result of each one.
                    Correctly guessing any one of them wins the bet! The more you guess right - the higher the win!
                    But watch out; If you miss all five, your bet goes to The House.
                    """;

    private static final String WELCOME_MESSAGE =
            """
                    Welcome to Not Your Average Slots! This game has 5 categories: colors, shapes, numbers, letters,
                    and animals. The slots will show 1 of each of them. Place your bet in each category.
                    If you correctly guess any one of them, you're a winner! The more categories you guess correctly,
                    the more you win! But be careful, if the slots don't land on any of your guesses,
                    your bet goes to The House.
                    """;

    Convenience convenience = new Convenience();

    // Variables for game functionality
    String slotColorString;
    String slotShapeString;
    String slotNumberString;
    String slotLetterString;
    String slotAnimalString;
    String[] slotResultsArray;
    int betAmount;
    int slotMatches = 0;
    boolean isWinner;
    int winnings = 0;

    @Override
    public String toString() {
        return name + "\n" + description;
    }

    public void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    @Override
    public void execute(Scanner userInput, Account playerAccount) {
        boolean isPlaying = true; // boolean to allow users to play additional rounds without returning to the main menu
        boolean betLocked = false; // boolean to allow user to review their range and bet before committing.
        displayWelcomeMessage();
        while (isPlaying) {
            while (!betLocked) {
                promptForSlotSelections(userInput);
                betAmount = convenience.promptForBet(userInput, playerAccount.getBalanceInt());
                System.out.println("Would you like to lock in your bet of " + betAmount + " for the slots to " +
                        "land on: " + slotColorString + " " + slotShapeString + " " + slotNumberString + " " +
                        slotLetterString + " " + slotAnimalString + "?");
                String lockBet = convenience.promptForYesNo(userInput);
                betLocked = (lockBet.equals("y"));
            }
            rollSlotsAndSetResults();
            findMatchesAndSetIsWinner();
            setWinningsAndPrintMatches();
            playerAccount.setBalance
                    (convenience.executeWinLossResults(winnings, playerAccount.getBalanceInt(), betAmount, isWinner));
            betLocked = false; // redefined to allow the player to reset their range and bet amount in additional rounds
            System.out.println("Would you like to play again?: ");
            String playAgain = convenience.promptForYesNo(userInput);
            if (playAgain.equals("n")) {
                isPlaying = false; // Additional rounds is the default
            }
        }
    }

    // Compares the players' selections to the slot results and determines how many matches. The player is a winner
    // if they have at least one much.
    public void findMatchesAndSetIsWinner() {
        if (slotColorString.equals(slotResultsArray[0])) {
            slotMatches += 1;
        }
        if (slotShapeString.equals(slotResultsArray[1])) {
            slotMatches += 1;
        }
        if (slotNumberString.equals(slotResultsArray[2])) {
            slotMatches += 1;
        }
        if (slotLetterString.equals(slotResultsArray[3])) {
            slotMatches += 1;
        }
        if (slotAnimalString.equals(slotResultsArray[4])) {
            slotMatches += 1;
        }
        isWinner = (slotMatches > 0);
    }

    public void setWinningsAndPrintMatches() {
        switch (slotMatches) {
            case 0 -> System.out.println("You didn't match any categories.");
            case 1 -> {
                System.out.println("You matched 1 category!");
                winnings += (int) Math.ceil(betAmount * 1.5);
            }
            case 2 -> {
                System.out.println("You matched 2 categories!");
                winnings += betAmount * 2;
            }
            case 3 -> {
                System.out.println("You matched 3 categories!");
                winnings += betAmount * 3;
            }
            case 4 -> {
                System.out.println("You matched 4 categories!");
                winnings += betAmount * 4;
            }
            case 5 -> {
                System.out.println("Great job - You matched all 5 categories!");
                winnings += betAmount * 5;
            }
        }
    }

    /*
    This method will randomly select a result for each of the five slots, return the holistic slot result to the
    player, and split the string into an array. slotResultArray will be populated with one result on each index 0-4.
     */
    public void rollSlotsAndSetResults() {
        Random random = new Random();
        String slotResults = "";
        int slot = random.nextInt(5) + 1;
        switch (slot) {
            case 1 -> slotResults += "Red ";
            case 2 -> slotResults += "Yellow ";
            case 3 -> slotResults += "Green ";
            case 4 -> slotResults += "Blue ";
            case 5 -> slotResults += "Purple ";
        }
        slot = random.nextInt(5) + 1;
        switch (slot) {
            case 1 -> slotResults += "Square ";
            case 2 -> slotResults += "Circle ";
            case 3 -> slotResults += "Triangle ";
            case 4 -> slotResults += "Pentagon ";
            case 5 -> slotResults += "Hexagon ";
        }
        slot = random.nextInt(5) + 1;
        switch (slot) {
            case 1 -> slotResults += "One ";
            case 2 -> slotResults += "Two ";
            case 3 -> slotResults += "Three ";
            case 4 -> slotResults += "Four ";
            case 5 -> slotResults += "Five ";
        }
        slot = random.nextInt(5) + 1;
        switch (slot) {
            case 1 -> slotResults += "A ";
            case 2 -> slotResults += "B ";
            case 3 -> slotResults += "C ";
            case 4 -> slotResults += "D ";
            case 5 -> slotResults += "E ";
        }
        slot = random.nextInt(5) + 1;
        switch (slot) {
            case 1 -> slotResults += "Dog ";
            case 2 -> slotResults += "Cat ";
            case 3 -> slotResults += "Bird ";
            case 4 -> slotResults += "Hamster ";
            case 5 -> slotResults += "Rabbit ";
        }
        System.out.println("The slots landed on: " + slotResults);
        slotResultsArray = slotResults.split(" ");
    }

    // This method will prompt for a valid selection for the 5 slot options
    public void promptForSlotSelections(Scanner userInput) {
        int slotColorInt;
        int slotShapeInt;
        int slotNumberInt;
        int slotLetterInt;
        int slotAnimalInt;
        System.out.println(
                """
                        Slot Colors:
                        1. Red
                        2. Yellow
                        3. Green
                        4. Blue
                        5. Purple
                                                
                        Please choose your color selection(1-5): """);
        slotColorInt = convenience.promptForInt(userInput);
        slotColorInt = convenience.verifySelectionIsValidOption(slotColorInt, 5, userInput);
        switch (slotColorInt) {
            case 1 -> slotColorString = "Red";
            case 2 -> slotColorString = "Yellow";
            case 3 -> slotColorString = "Green";
            case 4 -> slotColorString = "Blue";
            case 5 -> slotColorString = "Purple";
        }
        System.out.println(
                """
                        Slot Shapes:
                        1. Square
                        2. Circle
                        3. Triangle
                        4. Pentagon
                        5. Hexagon
                                                
                        Please choose your shape selection(1-5): """);
        slotShapeInt = convenience.promptForInt(userInput);
        slotShapeInt = convenience.verifySelectionIsValidOption(slotShapeInt, 5, userInput);
        switch (slotShapeInt) {
            case 1 -> slotShapeString = "Square";
            case 2 -> slotShapeString = "Circle";
            case 3 -> slotShapeString = "Triangle";
            case 4 -> slotShapeString = "Pentagon";
            case 5 -> slotShapeString = "Hexagon";
        }
        System.out.println(
                """
                        Slot Numbers:
                        1. One
                        2. Two
                        3. Three
                        4. Four
                        5. Five
                                                
                        Please choose your number selection(1-5): """);
        slotNumberInt = convenience.promptForInt(userInput);
        slotNumberInt = convenience.verifySelectionIsValidOption(slotNumberInt, 5, userInput);
        switch (slotColorInt) {
            case 1 -> slotNumberString = "One";
            case 2 -> slotNumberString = "Two";
            case 3 -> slotNumberString = "Three";
            case 4 -> slotNumberString = "Four";
            case 5 -> slotNumberString = "Five";
        }
        System.out.println(
                """
                        Slot Letters:
                        1. A
                        2. B
                        3. C
                        4. D
                        5. E
                                                
                        Please choose your letter selection(1-5): """);
        slotLetterInt = convenience.promptForInt(userInput);
        slotLetterInt = convenience.verifySelectionIsValidOption(slotLetterInt, 5, userInput);
        switch (slotColorInt) {
            case 1 -> slotLetterString = "A";
            case 2 -> slotLetterString = "B";
            case 3 -> slotLetterString = "C";
            case 4 -> slotLetterString = "D";
            case 5 -> slotLetterString = "E";
        }
        System.out.println(
                """
                        Slot Animals:
                        1. Dog
                        2. Cat
                        3. Bird
                        4. Hamster
                        5. Rabbit
                                                
                        Please choose your animal selection(1-5): """);
        slotAnimalInt = convenience.promptForInt(userInput);
        slotAnimalInt = convenience.verifySelectionIsValidOption(slotAnimalInt, 5, userInput);
        switch (slotColorInt) {
            case 1 -> slotAnimalString = "Dog";
            case 2 -> slotAnimalString = "Cat";
            case 3 -> slotAnimalString = "Bird";
            case 4 -> slotAnimalString = "Hamster";
            case 5 -> slotAnimalString = "Rabbit";
        }
    }

}
