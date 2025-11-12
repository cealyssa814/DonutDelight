package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.*;

import java.util.EnumSet;
import java.util.List;

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
        if (d.name().startsWith("MILKSHAKE"))
            return 4.00; // premium
        if (d.name().startsWith("LEMONADE"))
            return 3.00; // flavored lemonades
        return 2.50; // fountain/coffee/tea baseline
    }

    //Bundle adder for Snack Deal
    public static double snackDealUpcharge() {

        return 4.00;
    }
    // === NEW: Premium toppings =========================================
    // I’m using an EnumSet to list which toppings are premium.
    // If we change “what’s premium” later, we only touch this list.
    private static final EnumSet<Topping> PREMIUM_TOPPINGS = EnumSet.of(
            Topping.BACON,
            Topping.PEANUTS,
            Topping.OREO_PIECES,
            Topping.TOASTED_COCONUT
            // ^ I chose these as “premium”. We can add/remove anytime.
    );

    // Each premium topping adds $1.00. Keeping the number here centralizes the rule.
    public static double premiumToppingUnit(){ return 1.00; }

    // Helper to total the premium surcharge for any donut’s toppings list.
    public static double premiumChargeFor(List<Topping> toppings){
        long premiumCount = toppings.stream().filter(PREMIUM_TOPPINGS::contains).count();
        return premiumCount * premiumToppingUnit();
    }

    // expose whether a topping is premium (UI might show a star ★)
    public static boolean isPremium(Topping t){
        return PREMIUM_TOPPINGS.contains(t);
    }
}

