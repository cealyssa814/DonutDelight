package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.Drink;
import com.pluralsight.DonutShop.model.Donut;

import java.util.*;

// Aggregator for line items (GymLedger accumulation patterns)
public class Order {
    private final List<Donut>
            donuts = new ArrayList<>(); // like ledger line items
    private Drink drink;       // a la carte
    private boolean snackDeal; // bundle toggle

    public void addDonut(Donut d){
        donuts.add(d);
    } // mirror SandwichShop “add to cart”
    public void setDrink(Drink d){
        drink = d;
    }
    public void setSnackDeal(boolean v){
        snackDeal = v;
    }

    public double total(){
        double t = donuts.stream().mapToDouble(Donut::price).sum(); // GymLedger: stream sum technique
        if (drink != null)
            t += Pricing.drink(drink);                // drink charge
        if (snackDeal)
            t += Pricing.snackDealUpcharge();             // bundle charge
        return t;
    }

    public String summary(){
        StringBuilder sb = new StringBuilder("--- 🍩Donut Delight Order🍩 ---\n"); // SandwichShop style summary
        for (int i=0;i<donuts.size();i++){
            sb.append("#").append(i+1).append(" ").append(donuts.get(i)).append("\n");
        }
        if (drink != null) sb.append(String.format("Drink: %s (+$%.2f)\n", drink, Pricing.drink(drink)));
        if (snackDeal) sb.append(String.format("Snack deal bundle (+$%.2f)\n", Pricing.snackDealUpcharge()));
        sb.append(String.format("Total: $%.2f%n", total()));
        return sb.toString();
    }

    // This method returns the list of donuts in the current order.
// Used Collections.unmodifiableList() so other parts of the program
// (like the menu or checkout) can *see* what donuts were added, but they can't accidentally change the list itself.
//
// This protects the internal data of my Order class — a good example of *encapsulation* in OOP.
// If I just returned the raw "donuts" list, other code could modify or delete donuts directly, breaking my totals.
    public List<Donut> donuts(){

        return Collections.unmodifiableList(donuts); // safely exposes a read-only view of the donut list
    }

    // This method returns the drink (if the user ordered one).
// Wrapped it in Optional so I don’t have to worry about null pointer errors later.
// For example:
// if someone calls drink().get() without checking first,
// it’ll clearly indicate if a drink doesn’t exist.

// Using Optional also makes my intent obvious —
// there *might* be a drink, or there might not be.
// It’s cleaner and safer than returning null.
    public Optional<Drink> drink() {

        return Optional.ofNullable(drink); // returns an Optional that’s empty if no drink was set
    }
    public boolean snackDeal(){

        return snackDeal;
    }
}
