package com.KevinFinucan.TheHouse;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheHouseGame {

    /*
    System In Out Console is a class containing methods to run the main menu and I/O from the console,
    created for organizational purposes to help keep main clean and readable.
    The implementation for the app is broken into smaller methods which are integrated within a run method.
     */

    /*
    To run the app, declare instances of Convenience Class, Prizes, Account, and the 4 House games
     */
    ConvenienceVariablesAndMethods convenienceVariablesAndMethods = new ConvenienceVariablesAndMethods();
    Prize prize = new Prize();
    Account userAccount = new Account();
    TheTenSidedDieOfDoom theTenSidedDieOfDoom = new TheTenSidedDieOfDoom();
    TheUnFlippingBelievableCoin theUnFlippingBelievableCoin = new TheUnFlippingBelievableCoin();
    NotYourAverageSlots notYourAverageSlots = new NotYourAverageSlots();
    TwentySidedHigherOrLower twentySidedHigherOrLower = new TwentySidedHigherOrLower();


    // declare new scanner, menu option int at 0, and menu option String at empty string.
    Scanner userInput = new Scanner(System.in);
    int menuOptionInt = 0;
    String menuOptionString = "";

    // declare all necessary global properties to be accessed and used by each game

    // All games properties
    int betAmount = 0;
    boolean isWinner = false;

    //Ten Sided Die of Doom properties
    int rangeMin = 0;
    int rangeMax = 0;
    double betMultiplier = 0;

    /*
    Run is the primary method that handles the app's main menu and overall functionality.
     */
    public void run() {

        convenienceVariablesAndMethods.displayWelcomeMessage();
        menuOptionInt = mainMenu();

        while (true) {
            switch (menuOptionInt) {
                case 1 -> {
                    userAccount.getBalanceString(); // Prints the player's balance of tokens; Default is 100.
                    menuOptionInt = mainMenu();
                }
                case 2 -> {
                    List<Game> games = initializeGamesList();
                    for (Game game : games) {
                        System.out.println(game.toString()); // Prints the name and description of the 4 House games
                    }
                    menuOptionInt = mainMenu();
                }
                case 3 -> {
                    // Sub-menu for games, implemented below. Allows player's to select and play The House games.
                    gamesMenu();
                    menuOptionInt = mainMenu();
                }
                case 4 -> {
                    prize.displayPrizes();
                    prizesMenu(); // Displays The House's 5 prize options and allows players to buy prizes with tokens
                    menuOptionInt = mainMenu();
                }
                case 5 -> { // This option of for scaredy-cats who know they'll never beat The House.
                    System.out.println(
                            "We understand. Sometimes the safest course is to flee and live to bet another day.");
                    System.exit(0);
                }
            }
        }
    }


    // HELPER & MENU METHODS

    /*
    The main menu method redefines menu-option at 0 and prompts for a selection.
    This method is run after every final action is completed in each sub-menu.
    Main menu calls promptForInt, which enforces that an int is returned,
    and then re-prompts for a selection as needed until an int provided matches a menu-option 1-5.
     */
    public int mainMenu() {
        menuOptionInt = 0;
        System.out.println(
                """
                        What would you like to do?
                        (1) View your balance
                        (2) View our games
                        (3) Play game
                        (4) View prizes
                        (5) Flee The House
                        """);

        while (!(menuOptionInt == 1 || menuOptionInt == 2 || menuOptionInt == 3 ||
                menuOptionInt == 4 || menuOptionInt == 5)) {
            menuOptionInt = promptForInt();
        }
        return menuOptionInt;
    }

    // Prompt for int enforces that the player's entry must be an int.
    public int promptForInt() {
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
    public String promptForYesNo() {
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

    // Returns a list containing all available games
    public List<Game> initializeGamesList() {
        List<Game> games = new ArrayList<>();
        games.add(theTenSidedDieOfDoom);
        games.add(theUnFlippingBelievableCoin);
        games.add(notYourAverageSlots);
        games.add(twentySidedHigherOrLower);
        return games;
    }

    // prizeMenu is the inner menu for prizes. Players can choose to use their tokens to purchase a prize
    public void prizesMenu() {
        System.out.println("Would you like to purchase a prize with your tokens?: ");
        menuOptionString = promptForYesNo();
        switch (menuOptionString) {
            case "y" -> purchasePrize(userAccount.getBalanceInt());
            case "n" -> System.out.println("Okay, maybe later. Remember to bet on games to win tokens. Good luck!");
        }
    }

    /*
  purchasePrize is an inner prize menu that allows players to select a prize for purchase,
  verifies that they have enough tokens to purchase the prize,
  and returns their prize (if they have enough tokens) or directs them to play games to win more tokens.
   */
    public void purchasePrize(int currentBalance) {
        System.out.println("Which prize would you like to purchase (1-5): ");
        int prizeSelection = promptForInt();
        while (!(prizeSelection == 1 || prizeSelection == 2 || prizeSelection == 3 ||
                prizeSelection == 4 || prizeSelection == 5)) {
            System.out.println("Please enter a valid prize selection: ");
            prizeSelection = promptForInt();
        }
        switch (prizeSelection) {
            case 1 -> purchasePrize(currentBalance, prize.getPrize1Cost(), prize.getPrize1String());
            case 2 -> purchasePrize(currentBalance, prize.getPrize2Cost(), prize.getPrize2String());
            case 3 -> purchasePrize(currentBalance, prize.getPrize3Cost(), prize.getPrize3String());
            case 4 -> purchasePrize(currentBalance, prize.getPrize4Cost(), prize.getPrize4String());
            case 5 -> purchasePrize(currentBalance, prize.getPrize5Cost(), prize.getPrize5String());
        }
    }

    // This method verifies players have enough tokens to purchase the prize they have selected.
    public void purchasePrize(int currentBalance, int prizeCost, String prizeString) {
        if (currentBalance >= prizeCost) {
            System.out.println("Please enjoy your new " + prizeString + "!");
            userAccount.setBalance(userAccount.getBalanceInt() - prizeCost);
        } else { // The House is not your friend, and does not offer prizes on credit.
            System.out.println(
                    "Sorry, you don't have enough tokens for this prize. " +
                            "Remember to bet on games to win tokens. Good luck!" + "\n");
        }
    }


    /*
    Games menu displays the menu for selecting a game to play, and executes the players' selected game
     */
    public void gamesMenu() {
        convenienceVariablesAndMethods.displayGameMenu();
        int gameToPlay = promptForInt();
        while (!(gameToPlay == 1 || gameToPlay == 2 || gameToPlay == 3 || gameToPlay == 4)) {
            System.out.println("Please choose a valid game selection (1-4): ");
            gameToPlay = promptForInt();
        }
        switch (gameToPlay) {
            case 1 -> executeTenSidedDieOfDoom();
            case 2 -> System.exit(0); // The Un-Flipping-Believable Coin implementation to come
            case 3 -> System.exit(0); // Not Your Average Slots implementation to come
            case 4 -> System.exit(0); // Twenty-Sided Higher or Lower implementation to come
        }
    }

    public void executeTenSidedDieOfDoom() {
        boolean isPlaying = true; // boolean to allow users to play additional rounds without returning to the main menu
        boolean betLocked = false; // boolean to allow user to review their range and bet before committing.
        while (isPlaying) {
            theTenSidedDieOfDoom.displayWelcomeMessage();
            while (!betLocked) {
                rangeMin = promptForLowHighValue("lowest");
                rangeMax = promptForLowHighValue("highest");
                enforceAccurateMinMaxRelationship(); // enforces maxRange must be >= minRange & switches them as needed
                promptForBet();
                if (rangeMin == rangeMax) {
                    System.out.println(
                            "Would you like to lock in your bet of " + betAmount + " tokens for the die to land " +
                                    "on " + rangeMin + "?");
                } else {
                    System.out.println(
                            "Would you like to lock in your bet of " + betAmount + " tokens for the die to land " +
                                    "within the range of " + rangeMin + " through " + rangeMax + "?");
                }
                String lockBet = promptForYesNo();
                betLocked = (lockBet.equals("y"));
            }
            betMultiplier = theTenSidedDieOfDoom.setBetMultiplier(rangeMin, rangeMax);
            isWinner = theTenSidedDieOfDoom.rollDieAndSetIsWInner(rangeMin, rangeMax);
            // If player is a winner, their winnings will be converted to an int, rounded in their favor.
            executeWinLossResults((int) Math.ceil(betAmount * betMultiplier));
            betLocked = false; // redefined to allow the player to reset their range and bet amount in additional rounds
            System.out.println("Would you like to play again: ");
            String playAgain = promptForYesNo();
            if (playAgain.equals("n")) {
                isPlaying = false; // Additional rounds is the default
            }
        }
    }

    /*
    This method sets the low & high values of the range for the Ten Sided Die of Doom bet.
    The method will enforce that the value entered must fall within 1-10 range, and re-prompt as needed.
     */
    public int promptForLowHighValue(String lowHigh) {
        System.out.println(
                "Please choose the " + lowHigh + " value (1-10) that you think the die roll will land within: ");
        int value = promptForInt();
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
    This method is utilized by all games to obtain a bet amount. The method will ensure that
    players have enough tokens in their account to place the bet, and will re-prompt as needed.
    */
    public void promptForBet() {
        System.out.println("How much will you bet on this round?: ");
        betAmount = promptForInt();
        while (betAmount > userAccount.getBalanceInt()) {
            System.out.println(
                    "Sorry, eager beaver, but you don't have that many tokens to bet! " +
                            "Please choose an amount you can absorb. Your account currently holds " +
                            userAccount.getBalanceInt() + " tokens. " +
                            "How much will you bet on this round?: ");
            betAmount = promptForInt();
        }
    }

    /*
    This method is utilized by all games to confirm the players win or loss
    & make applicable adjustments to their account balance at the conclusion of each bet.
     */
    public void executeWinLossResults(int winnings) {
        if (isWinner) {
            System.out.println("Congratulations, you're a winner! " +
                    winnings + " tokens have been added to your account.");
            userAccount.setBalance(userAccount.getBalanceInt() + winnings);
        } else {
            System.out.println("Oops, looks like The House won this round. Better luck next time!");
            userAccount.setBalance(userAccount.getBalanceInt() - betAmount);
        }
    }
}
