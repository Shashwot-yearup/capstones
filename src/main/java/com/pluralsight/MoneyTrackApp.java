package com.pluralsight;

import java.util.Scanner;

public class MoneyTrackApp {
    DisplayScreen Screen = new DisplayScreen();

    public void run(Scanner scanner){
        String choice = "";

        while(!choice.equals("X")){
            Screen.mainScreen();

        }
    }

}
