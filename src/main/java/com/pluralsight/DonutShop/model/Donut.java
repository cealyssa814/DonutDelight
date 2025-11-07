package com.pluralsight.DonutShop.model;

import com.pluralsight.DonutShop.enums.*;
import com.pluralsight.Pricing;

import java.util.*;

// [From Advanced OOP] Encapsulation of state + behavior (price()).
public class Donut {
    private Dough dough;
    private Coating coating;
    private List<Toppings> toppings = new ArrayList<>();
    private List<Drizzle> drizzles = new ArrayList<>();
    private PackSize size;
    private boolean extraToppings; // [From Pizza-Licious] “extra toppings” flag

    // [Fluent setters: convenience for Menu wiring; similar to builder style used in prior labs]
    public Donut dough(Dough v){ this.dough=v; return this; }
    public Donut coating(Coating v){ this.coating=v; return this; }
    public Donut toppings(List<Toppings> v){ this.toppings=v; return this; }
    public Donut drizzles(List<Drizzle> v){ this.drizzles=v; return this; }
    public Donut size(PackSize v){ this.size=v; return this; }
    public Donut extra(boolean v){ this.extraToppings=v; return this; }

    // [From Pizza-Licious] deterministically compute price from selected options
    public double price(){
        double base = Pricing.base(size);
        int included = 3; // first three toppings included
        int extras = Math.max(0, toppings.size() - included);
        double extraCost = extras * Pricing.extraToppingUnit();
        if (extraToppings) extraCost += Pricing.extraToppingUnit();
        return base + extraCost;
    }

    // [From SandwichShop] readable summary of the built item
    @Override public String toString(){
        return size.qty + "-Pack " + nice(dough) + " / " + nice(coating) +
                " | toppings: " + list(toppings) +
                " | drizzles: " + list(drizzles) +
                String.format(" | $%.2f", price());
    }

    private static String nice(Enum<?> e){ return e.name().toLowerCase().replace('_',' '); }
    private static String list(Collection<? extends Enum<?>> e){
        return e.isEmpty() ? "(none)"
                : e.stream().map(Donut::nice).toList().toString();
    }
}
