package com.KevinFinucan.TheHouse;

public interface Game {

    /*
     Game is an interface that requires an implementation of toString.
     The purpose of the interface is to allow all games to be combined into a list, and have a common
     implementation for printing their name & details. The interface was chosen over a parent-class
     because each game's name, description, and implementation are entirely unique;
     however, the app will need to be able to handle them as a group.
     */

    String toString();
}
