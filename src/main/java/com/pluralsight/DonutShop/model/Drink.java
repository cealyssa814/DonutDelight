package com.pluralsight.DonutShop.model;

import com.pluralsight.DonutShop.enums.DrinkFlavor;
import com.pluralsight.DonutShop.enums.DrinkSize;

public class Drink {

    private DrinkFlavor flavor;
    private DrinkSize size;

    public Drink(DrinkFlavor flavor, DrinkSize size) {
        this.flavor = flavor;
        this.size = size;
    }

    public DrinkFlavor getFlavor() {
        return flavor;
    }

    public void setFlavor(DrinkFlavor flavor) {
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }

    public double getPrice(){
        // PREMIUM ITEM: Milkshakes are always more expensive.
        // Using startsWith() just like in the Pricing.java example you wrote earlier.
        if (flavor.name().startsWith("MILKSHAKE"))
            return switch (size) {
                case SMALL -> 3.75;
                case MEDIUM -> 4.25;
                case LARGE -> 4.75;
            };

        // SPECIALTY LEMONADES (Blueberry, Mango, Cranberry, Strawberry)
        if (flavor.name().startsWith("LEMONADE")) {
            // Lemonade only comes in medium + large (per your project requirements)
            return switch (size) {
                case MEDIUM -> 3.25;
                case LARGE -> 3.75;
                default -> 0;  // Safety fallback (should never happen)
            };
        }

        // NORMAL FOUNTAIN DRINKS — the “baseline” category
        if (flavor == DrinkFlavor.FOUNTAIN) {
            return switch (size) {
                case SMALL -> 1.50;
                case MEDIUM -> 1.95;
                case LARGE -> 2.25;
            };
        }

        // Coffee or tea (non-premium, but priced higher than fountain drinks)
        if (flavor == DrinkFlavor.COFFEE || flavor == DrinkFlavor.TEA) {
            return switch (size) {
                case SMALL -> 1.95;
                case MEDIUM -> 2.25;
                case LARGE -> 2.50;
            };
        }

        // Fallback (should never be hit)
        return 0;
    }
}
