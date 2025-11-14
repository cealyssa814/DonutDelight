package com.pluralsight.DonutShop;


import com.pluralsight.DonutShop.userinterface.Menu;
import com.pluralsight.DonutShop.util.ThemedPrinter;

public class Main {
    public static void main(String[] args) {
        ThemedPrinter.enable();   // make sure theme is ON for the whole app

        new Menu().run(); // start console UI
    }
}

