package com.KevinFinucan.TheHouse;

public class NotYourAverageSlots implements Game {

    private static final String name = "Not Your Average Slots";
    private static final String description =
            """
                    In Not Your Average Slots, players place 5 combined bets on the outcome of a slot machine,
                    with the potential to win five different ways! The five rows of the slot machine contain
                    shapes, colors, numbers, letters, and animals. The player will bet on the result of each one.
                    Correctly guessing any one of them wins the bet! The more you guess right - the higher the win!
                    But watch out; If you miss all five, your bet goes to The House.
                    """;


    @Override
    public String toString() {
        return name + "\n" + description;
    }

}
