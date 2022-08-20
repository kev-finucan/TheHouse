package com.KevinFinucan.TheHouse;

public class TwentySidedHigherOrLower implements Game {

    private static final String name = "Twenty-Sided Higher Or Lower";
    private static final String description =
            """
                    In Twenty-Sided Higher Or Lower, players bet whether the next roll of a 20-sided die
                    will be higher or lower than the last roll. This is the only game that allows players to maintain
                    a statistical advantage. As such, each win will return your bet, but your winnings will not multiply.
                    Slow and steady wins the race!
                    """;


    @Override
    public String toString() {
        return name + "\n" + description;
    }

}
