package com.pluralsight.DonutShop.enums;

// [From Pizza-Licious] “size” concept, adapted: inches → pack quantities
public enum PackSize { THREE(3), SIX(6), NINE(9), TWELVE(12);
    public final int qty;
    PackSize(int q){
        this.qty=q;
    }
}
