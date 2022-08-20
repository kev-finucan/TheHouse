package com.KevinFinucan.TheHouse;

import java.util.Scanner;

public class ConvenienceVariablesAndMethods {

    /*
    The ConvenienceVariablesAndMethods class contains methods and strings that help the app run smoothly and display messages in the menu.
    The methods/strings are separated from the HouseGame class and grouped together in order to improve
    overall readability and organization of the app
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


}
