package com.KevinFinucan.TheHouse;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Startup {

    public static void main(String[] args) {

        // Declare an instances of System In Out Console to run the app
        TheHouseGame theHouseGame = new TheHouseGame();

        theHouseGame.run();

    }
}
