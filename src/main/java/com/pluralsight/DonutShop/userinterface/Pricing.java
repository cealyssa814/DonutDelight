package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.DrinkFlavor;
import com.pluralsight.DonutShop.enums.DrinkSize;
import com.pluralsight.DonutShop.enums.PackSize;
import com.pluralsight.DonutShop.enums.Topping;

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
    public static double premiumToppingUnit(){

        return 1.00;
    }

    // Helper to total the premium surcharge for any donut’s toppings list.
    public static double premiumChargeFor(List<Topping> toppings){
        long premiumCount = toppings.stream().filter(PREMIUM_TOPPINGS::contains).count();
        return premiumCount * premiumToppingUnit();
    }

    // expose whether a topping is premium (UI might show a star ★)
    public static boolean isPremium(Topping t){

        return PREMIUM_TOPPINGS.contains(t);
    }
//-F-I-N-A-L- -C-H-A-N-G-E-S-: Adding S/M/L and M/L to lemonade and fountain drinks
    // ==========================================
    // if size is not supplied, we assume MEDIUM.
    // ==========================================
    public static double drink(DrinkFlavor d) {
        // This preserves existing callers (Menu.addDrink, Order.total)
        return drink(d, DrinkSize.MEDIUM);
    }

//New size-aware pricing:
//- Fountain: S/M/L
//- Lemonades: M/L only
//- Milkshakes: fixed price (unchanged)
//- Coffee/Tea: keep flat price for now
//
//Workbook tie-in:
//- Advanced OOP: Overloading methods for extended behavior.
//- Single Responsibility: All pricing (not UI) stays here.

    public static double drink(DrinkFlavor d, DrinkSize size) {

        // Milkshakes still use a fixed premium price
        if (d.name().startsWith("MILKSHAKE")) {
            return 4.00;
        }

        // Lemonades: MEDIUM and LARGE only.
        if (d.name().startsWith("LEMONADE")) {
            // I enforce valid sizes here to keep Menu and Order simpler.
            if (size == DrinkSize.SMALL)
                size = DrinkSize.MEDIUM; //defaults to medium if a user puts in small.

            switch (size) {
                case MEDIUM:
                    return 3.00;
                case LARGE:
                    return 3.50;
                default:
                    return 3.00; // fallback
            }
        }

        // Fountain: S/M/L
        if (d == DrinkFlavor.FOUNTAIN) {
            switch (size) {
                case SMALL:
                    return 1.75;
                case MEDIUM:
                    return 2.50;
                case LARGE:
                    return 3.00;
            }
        }

        // Coffee/Tea or any other drink types: keep flat price from before.
        return 2.50;
    }
}
