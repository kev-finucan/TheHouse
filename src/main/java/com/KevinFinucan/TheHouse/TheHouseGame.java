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
    Convenience convenience = new Convenience();
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


    /*
    Run is the primary method that handles the app's main menu and overall functionality. Run calls the execute methods
    for the prize and game classes. Each execute method accepts a scanner and a current balance int, and returns
    an adjusted balance. This allows The House to run on only one instance of Scanner which is then passed between all
    methods that need it, and to maintain a current balance by passing an adjusted balance between the methods.
     */
    public void run() {

        convenience.displayWelcomeMessage();
        menuOptionInt = mainMenu(userInput);

        while (true) {
            switch (menuOptionInt) {
                case 1 -> {
                    userAccount.getBalanceString(); // Prints the player's balance of tokens; Default is 100.
                    menuOptionInt = mainMenu(userInput);
                }
                case 2 -> {
                    List<Game> games = initializeGamesList();
                    for (Game game : games) {
                        System.out.println(game.toString()); // Prints the name and description of the 4 House games
                    }
                    menuOptionInt = mainMenu(userInput);
                }
                case 3 -> {
                    // Sub-menu for games, implemented below. Allows player's to select and play The House games.
                    gamesMenu(userInput);
                    menuOptionInt = mainMenu(userInput);
                }
                case 4 -> {
                    userAccount.setBalance(prize.executePrizes(userInput, userAccount.getBalanceInt()));
                    menuOptionInt = mainMenu(userInput);
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
    public int mainMenu(Scanner userInput) {
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
            menuOptionInt = convenience.promptForInt(userInput);
        }
        return menuOptionInt;
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

    /*
    Games menu displays the menu for selecting a game to play, and executes the players' selected game
     */
    public void gamesMenu(Scanner userInput) {
        convenience.displayGameMenu();
        int gameToPlay = convenience.promptForInt(userInput);
        while (!(gameToPlay == 1 || gameToPlay == 2 || gameToPlay == 3 || gameToPlay == 4)) {
            System.out.println("Please choose a valid game selection (1-4): ");
            gameToPlay = convenience.promptForInt(userInput);
        }
        switch (gameToPlay) {
            case 1 -> userAccount.setBalance(theTenSidedDieOfDoom.execute(userInput, userAccount.getBalanceInt()));
            case 2 -> System.exit(0); // The Un-Flipping-Believable Coin implementation to come
            case 3 -> System.exit(0); // Not Your Average Slots implementation to come
            case 4 -> System.exit(0); // Twenty-Sided Higher or Lower implementation to come
        }
    }
}
