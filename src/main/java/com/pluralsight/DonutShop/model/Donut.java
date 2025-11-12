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
        double base = Pricing.base(size);
        int included = 3; // first 3 toppings included
        int extras = Math.max(0, toppings.size() - included);
        double extraCost = extras * Pricing.extraToppingUnit();
        if (extraToppings) extraCost += Pricing.extraToppingUnit();
        return base + extraCost;
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
}
