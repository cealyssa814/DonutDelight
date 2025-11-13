package com.pluralsight.DonutShop.model;

import com.pluralsight.DonutShop.enums.DrinkFlavor;
import com.pluralsight.DonutShop.enums.DrinkSize;

public class Drink {

    private DrinkFlavor flavor;
    private DrinkSize size;

    public Drink(DrinkFlavor flavor, DrinkSize size) {
        this.flavor = flavor;
        this.size = size;
    }

    public DrinkFlavor getFlavor() {
        return flavor;
    }

    public void setFlavor(DrinkFlavor flavor) {
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }

    public double getPrice(){
        return 0;
    }
}
