package com.pluralsight;


import com.pluralsight.DonutShop.enums.Drink;
import com.pluralsight.DonutShop.enums.PackSize;

public final class Pricing {
    private Pricing(){}

    // [From Pizza-Licious] base price by size (inches there; pack size here)
    public static double base(PackSize s){
        return switch (s){
            case THREE -> 3.50;
            case SIX -> 6.50;
            case NINE -> 9.50;
            case TWELVE -> 12.00;
        };
    }

    // [From Pizza-Licious] extra toppings surcharge after a free allowance
    public static double extraToppingUnit(){ return 0.50; }

    // [From Pizza-Licious “Drinks”] tiered pricing by category
    public static double drink(Drink d){
        if (d.name().startsWith("MILKSHAKE")) return 4.00;
        if (d.name().startsWith("LEMONADE"))  return 3.00;
        return 2.50; // fountain, coffee, tea
    }

    // [Custom Capstone spec] optional bundle adder if you price Snack Deal separately
    public static double snackDealUpcharge(){ return 4.00; }
}
