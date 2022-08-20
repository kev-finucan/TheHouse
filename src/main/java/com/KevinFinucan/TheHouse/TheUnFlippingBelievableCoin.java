package com.KevinFinucan.TheHouse;

public class TheUnFlippingBelievableCoin implements Game {

    private static final String name = "The Un-Flipping-Believable Coin";
    private static final String description =
            """
                    In the The The Un-Flipping-Believable Coin, players bet on the outcome of a simple coin toss,
                    with the potential to grow your account balance exponentially - Un-Flipping-Believable!
                    Every time you correctly guess the outcome of a coin toss, your return doubles.
                    But watch out - as soon as you miss a coin toss, you lose everything you've gained,
                    plus your original bet.
                    """;

    @Override
    public String toString() {
        return name + "\n" + description;
    }

}