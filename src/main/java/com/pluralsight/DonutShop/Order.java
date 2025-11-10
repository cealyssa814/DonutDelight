package com.pluralsight.DonutShop;

import com.pluralsight.DonutShop.enums.Drink;
import com.pluralsight.DonutShop.model.Donut;
import com.pluralsight.Pricing;

import java.util.*;

// Aggregator for line items (GymLedger accumulation patterns)
public class Order {
    private final List<Donut> donuts = new ArrayList<>(); // like ledger line items
    private Drink drink;       // a la carte
    private boolean snackDeal; // bundle toggle

    public void addDonut(Donut d){ donuts.add(d); } // mirror SandwichShop “add to cart”
    public void setDrink(Drink d){ drink = d; }
    public void setSnackDeal(boolean v){ snackDeal = v; }

    public double total(){
        double t = donuts.stream().mapToDouble(Donut::price).sum(); // GymLedger: stream sum technique
        if (drink != null) t += Pricing.drink(drink);                // drink charge
        if (snackDeal) t += Pricing.snackDealUpcharge();             // bundle charge
        return t;
    }

    public String summary(){
        StringBuilder sb = new StringBuilder("--- Donut Delight Order ---\n"); // SandwichShop style summary
        for (int i=0;i<donuts.size();i++){
            sb.append("#").append(i+1).append(" ").append(donuts.get(i)).append("\n");
        }
        if (drink != null) sb.append(String.format("Drink: %s (+$%.2f)\n", drink, Pricing.drink(drink)));
        if (snackDeal) sb.append(String.format("Snack deal bundle (+$%.2f)\n", Pricing.snackDealUpcharge()));
        sb.append(String.format("Total: $%.2f%n", total()));
        return sb.toString();
    }


    public List<Donut> donuts(){
        return Collections.unmodifiableList(donuts);
    }
    public Optional<Drink> drink() {
        return Optional.ofNullable(drink);
    }
    public boolean snackDeal(){
        return snackDeal;
    }
}
