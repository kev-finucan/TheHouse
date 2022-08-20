package com.KevinFinucan.TheHouse;

import java.util.Scanner;

public class Prize {

    /*
    The prize class contains Strings for prizes, prize menu, and ints for their costs in tokens.
    Players can spend the tokens they've won from betting on games to claim prizes.
    The strings and ints are called by the SystemInOutConsole methods.
     */

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
    public void displayPrizes() {
        System.out.println(AVAILABLE_PRIZES);
    }


}
