package com.pluralsight.DonutShop.model;

import com.pluralsight.DonutShop.enums.DrinkFlavor;
import com.pluralsight.DonutShop.enums.DrinkSize;
import com.pluralsight.DonutShop.userinterface.Pricing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Aggregator for line items (GymLedger accumulation patterns)
public class Order {
    private final List<Donut>
            donuts = new ArrayList<>(); // like ledger line items
    private DrinkFlavor drinkFlavor;       // a la carte
    private DrinkSize drinkSize;
    private boolean snackDeal; // bundle toggle

    public void addDonut(Donut d) {
        donuts.add(d);
    } // mirror SandwichShop “add to cart”

    public void setDrink(DrinkFlavor d, DrinkSize size) {
        drinkFlavor = d;
        this.drinkSize = size;
    }

    public void setSnackDeal(boolean v) {
        snackDeal = v;
    }

    // ====== totals ======
    // Calculates the total price for the current order.
    public double total() {

        // 1) Sum all donut prices (GymLedger-style stream sum)
        double t = donuts.stream()
                .mapToDouble(Donut::price)
                .sum();

        // 2) If a drink was ordered, add its price using Pricing
        if (drinkFlavor != null) {
            DrinkSize size = (drinkSize != null) ? drinkSize : DrinkSize.MEDIUM;
            t += Pricing.drink(drinkFlavor, size);
        }

        // 3) If snack deal is enabled, add the bundle upcharge
        if (snackDeal) {
            t += Pricing.snackDealUpcharge();
        }

        // 4) Return final numeric total, no recursion, no formatting
        return t;
    }


    // ❌ This block is what caused the StackOverflowError:
    // I tried to mix "summary string building" and "total calculation"
    // inside the same method. total() calls total() again here.
    // ===== SUMMARY (FORMATTING ONLY — SAFE TO CALL total() ONCE) =====
    public String summary() {
        StringBuilder sb = new StringBuilder("--- 🍩Donut Delight Order🍩 ---\n");

        // List donuts with their own toString() formatting
        for (int i = 0; i < donuts.size(); i++) {
            sb.append("#")
                    .append(i + 1)
                    .append(" ")
                    .append(donuts.get(i))
                    .append("\n");
        }

        // Show drink info if there is a drink
        if (drinkFlavor != null) {
            DrinkSize size = (drinkSize != null) ? drinkSize : DrinkSize.MEDIUM;
            sb.append(String.format(
                    "Drink: %s (%s) (+$%.2f)\n",
                    drinkFlavor,
                    size,
                    Pricing.drink(drinkFlavor, size)
            ));
        }

        // Show snack deal line if applicable
        if (snackDeal) {
            sb.append(String.format(
                    "Snack deal bundle (+$%.2f)\n",
                    Pricing.snackDealUpcharge()
            ));
        }

        // IMPORTANT: This is the ONLY place summary calls total().
        // total() NEVER calls summary(), so there’s no infinite loop.
        // ⛔ Problem: calling total() from inside total()
        sb.append(String.format("Total: $%.2f%n", total()));
        // Also wrong: trying to parse the text summary as a double
        return sb.toString();
    }

    // This method returns the list of donuts in the current order.
    // Used Collections.unmodifiableList() so other parts of the program
    // (like the menu or checkout) can *see* what donuts were added, but they can't accidentally change the list itself.

    // This protects the internal data of my Order class — a good example of *encapsulation* in OOP.
    // If I just returned the raw "donuts" list, other code could modify or delete donuts directly, breaking my totals.
    public List<Donut> donuts() {

        return Collections.unmodifiableList(donuts); // safely exposes a read-only view of the donut list
    }

    // This method returns the drink (if the user ordered one).
// Wrapped it in Optional so I don’t have to worry about null pointer errors later.
// For example:
    // if someone calls drink().get() without checking first,
    // it’ll clearly indicate if a drink doesn’t exist.

    // Using Optional also makes my intent obvious —
        // there *might* be a drink, or there might not be.
        // It’s cleaner than returning null.
    public Optional<DrinkFlavor> drink() {

        return Optional.ofNullable(drinkFlavor); // returns an Optional that’s empty if no drink was set
    }

    public Optional<DrinkSize> drinkSize() {
        return Optional.ofNullable(drinkSize);
    }

    // ====== snack deal ======
    public boolean snackDeal() {

        return snackDeal;
    }

    public void setDrink(DrinkFlavor d) {
    }
}