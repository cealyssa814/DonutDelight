package com.pluralsight.DonutShop.model;

import com.pluralsight.DonutShop.enums.*;
import com.pluralsight.DonutShop.userinterface.Pricing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


  
    public double price() {
        double base = Pricing.base(size);
        int included = 3;
        int extras = Math.max(0, toppings.size() - included);
        double extraCost = extras * Pricing.extraToppingUnit();
        boolean extra = false;
        if (extra) extraCost += Pricing.extraToppingUnit();

        double premium = Pricing.premiumChargeFor(toppings);
        return base + extraCost + premium;
    }

    @Override
    public String toString() {
        String tops = toppings == null || toppings.isEmpty() ? "(none)"
                : toppings.stream()
                .map(t -> t.name().toLowerCase().replace('_',' ') + (Pricing.isPremium(t) ? "★" : ""))
                .toList().toString();
        String driz = drizzles == null || drizzles.isEmpty() ? "(none)"
                : drizzles.stream().map(d -> d.name().toLowerCase().replace('_',' ')).toList().toString();

        return new StringBuilder().append(size.qty).append("-pack ").append(dough.name().toLowerCase().replace('_', ' ')).append(" / ").append(coating.name().toLowerCase().replace('_', ' ')).append(" | tops: ").append(tops).append(" | driz: ").append(driz).append(String.format(" ($%.2f)", price())).toString();
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
