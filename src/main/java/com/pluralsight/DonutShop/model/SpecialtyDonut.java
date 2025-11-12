package com.pluralsight.DonutShop.model;

import com.pluralsight.DonutShop.enums.*;

import java.util.List;

// Inheritance: extends Donut;
// Polymorphism: overrides price()/description().
public class SpecialtyDonut extends Donut {

    private final String funName;   // ex: "Maple Bacon Crunch"
    private final double fixedPrice; // $4.50 flat

    private SpecialtyDonut(String funName, double fixedPrice) {
        super();
        this.funName = funName;
        this.fixedPrice = fixedPrice;
    }

    // #1: Cake donut + maple icing + caramel drizzle + bacon topping ($4.50)
    public static SpecialtyDonut mapleBaconCrunch() {
        SpecialtyDonut d = new SpecialtyDonut("Maple Bacon Crunch", 4.50);
        // Setting parts using the fluent builder from Donut.
        d.dough(Dough.CAKE)
                .coating(Coating.MAPLE_ICING)
                .toppings(List.of(Topping.BACON))
                .drizzles(List.of(Drizzle.CARAMEL))
                // size isn’t relevant to fixed price, but I’ll record THREE for clarity.
                .size(PackSize.THREE)
                .extra(false);
        return d;
    }

    // #2: Yeast donut + vanilla coating + oreo pieces + chocolate drizzle ($4.50)
    public static SpecialtyDonut cookiesAndCremeDream() {
        SpecialtyDonut d = new SpecialtyDonut("Cookies & Creme Dream", 4.50);
        d.dough(Dough.YEAST)
                .coating(Coating.VANILLA_ICING)
                .toppings(List.of(Topping.OREO_PIECES))
                .drizzles(List.of(Drizzle.CHOCOLATE))
                .size(PackSize.THREE)
                .extra(false);
        return d;
    }

    @Override
    public double price() {
        // Overriding to force the flat $4.50, ignoring premium & size rules.
        return fixedPrice;
    }

    @Override
    public String description() {
        // Highlighting the specialty name and still showing the parts.
        return funName + " — "
                + super.description().replaceFirst("^\\d+-pack ", ""); // hide pack prefix
    }
}
