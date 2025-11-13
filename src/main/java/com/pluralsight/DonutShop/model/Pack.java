package com.pluralsight.DonutShop.model;

import com.pluralsight.DonutShop.enums.PackSize;

public class Pack {

    private PackSize size;  // the user's chosen pack size

    public Pack(PackSize size) {
        this.size = size;
    }

    public PackSize getSize() {
        return size;
    }

    public void setSize(PackSize size) {
        this.size = size;
    }

    public double getPrice() {
        return switch (size) {
            case THREE   -> 3.50;
            case SIX     -> 6.50;
            case NINE    -> 9.50;
            case TWELVE  -> 12.00;
        };
    }

    @Override
    public String toString() {
        // For receipts / summaries
        return size.name().toLowerCase().replace('_', ' ');
    }
}