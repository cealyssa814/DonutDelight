package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.Drink;
import com.pluralsight.DonutShop.model.Donut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    // Calculates the total price for the current order.
    public double total() {

        // Start with the total cost of all donuts.
        // I used a Stream to sum up each Donut’s price (from GymLedger example).
        // Donut::price is a method reference — it means “call price() on each donut.”
        double t = donuts.stream().mapToDouble(Donut::price).sum(); // GymLedger: stream sum technique

        // If a drink was ordered, add its cost from the Pricing class.
        // Pricing handles all the logic for drink costs to keep this clean.
        if (drink != null)
            t += Pricing.drink(drink); // add drink charge if one exists

        // If this order includes the snack deal (bundle), add its upcharge.
        // Again, Pricing handles this so the rule is only in one place.
        if (snackDeal)
            t += Pricing.snackDealUpcharge(); // add bundle charge

        // Return the final total (donuts + drink + bundle).
        return t;
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

    public boolean summary() {
        return false;
    }
}
