package com.pluralsight.DonutShop.model;
import com.pluralsight.DonutShop.enums.*;
import com.pluralsight.DonutShop.userinterface.Pricing;

import java.util.*;

// Encapsulated item with state + price() behavior — classic OOP (6a).
public class Donut {
    private Dough dough;
    private Coating coating;
    private List<Topping> toppings = new ArrayList<>();
    private List<Drizzle> drizzles = new ArrayList<>();
    private PackSize size;
    private boolean extraToppings; // “extra toppings” switch from Pizza-licious rubric

    // Setters to make Menu wiring cleaner (inspired by SandwichShop assembly flow)
    public Donut dough(Dough v){
        this.dough=v;
        return this;
    }
    public Donut coating(Coating v){
        this.coating=v;
        return this;
    }
    public Donut toppings(List<Topping> v){
        this.toppings=v;
        return this;
    }
    public Donut drizzles(List<Drizzle> v){
        this.drizzles=v;
        return this;
    }
    public Donut size(PackSize v){
        this.size=v;
        return this;
    }
    public Donut extra(boolean v){
        this.extraToppings=v;
        return this;
    }

    // Deterministic price, like Pizza-licious’ Pizza.price()
    public double price(){
        // 1) Start with base price by pack size (3/6/9/12)
        double base = Pricing.base(size);

        // 2) Non-premium “extra toppings” after first 3: +$0.50 each
        int included = 3;
        int extras = Math.max(0, toppings.size() - included);
        double extraCost = extras * Pricing.extraToppingUnit();

        // 3) “Extra toppings” toggle (same as Pizza-licious): +$0.50 if true
        if (extraToppings) extraCost += Pricing.extraToppingUnit();

        // 4) NEW: premium toppings surcharge (+$1.00 each premium topping)
        double premium = Pricing.premiumChargeFor(toppings);

        // 5) sum everything
        return base + extraCost + premium;
    }

    @Override public String toString(){
        return size.qty + "-Pack " + nice(dough) + " / " + nice(coating) +
                " | toppings: " + list(toppings) +
                " | drizzles: " + list(drizzles) +
                String.format(" | $%.2f", price()); // GymLedger taught us to format monetary output consistently
    }

    private static String nice(Enum<?> e){

        return e.name().toLowerCase().replace('_',' ');
    }
    private static String list(Collection<? extends Enum<?>> e){
        return e.isEmpty() ? "(none)" : e.stream().map(Donut::nice).toList().toString();
    }
    public String description() {
        // Labels premium toppings with a star ★ to make it obvious in the UI/receipt.
        String tops = (toppings==null || toppings.isEmpty()) ? "(none)" :
                toppings.stream()
                        .map(t -> nice(t) + (Pricing.isPremium(t) ? "★" : ""))
                        .toList().toString();

        return size.qty + "-pack "
                + nice(dough) + " / " + nice(coating)
                + " | tops: " + tops
                + " | driz: " + list(drizzles)
                + String.format(" ($%.2f)", price());
    }
}
