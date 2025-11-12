package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.*;

// Centralized prices prevent magic numbers across the codebase.
// — Mirrors Pizza-licious price table / separation of concerns.
// — Aligns with Advanced OOP "interfaces & abstraction" (clear API for prices).
public final class Pricing {
    private Pricing() {
    } // utility holder

    public static double base(PackSize s) {
        // Base pack prices (custom spec)
        return switch (s) {
            case THREE -> 3.50;
            case SIX -> 6.50;
            case NINE -> 9.50;
            case TWELVE -> 12.00;
        };
    }

    public static double extraToppingUnit() {
        return 0.50;
    } // Pizza-licious “extra toppings” model

    public static double drink(Drink d) {
        if (d.name().startsWith("MILKSHAKE")) return 4.00; // premium
        if (d.name().startsWith("LEMONADE")) return 3.00; // flavored lemonades
        return 2.50; // fountain/coffee/tea baseline
    }

    //Bundle adder for Snack Deal
    public static double snackDealUpcharge() {
        return 4.00;
    }
}
