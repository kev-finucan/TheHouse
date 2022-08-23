package com.KevinFinucan.TheHouse;

import java.util.Scanner;

public interface Game {

    /*
     Game is an interface that requires implementations of methods common to all the mini-game classes, and allows
     the games to be combined within a list. The interface was chosen over a parent-class because each game's name,
     description, and implementation are entirely unique. Mini-games are more like instances of game than children.
     */

    int execute(Scanner userInput, int currentBalance);

    String toString();
}
