package com.pluralsight.DonutShop;

import com.pluralsight.DonutShop.enums.*;
import com.pluralsight.DonutShop.model.Donut;

import java.util.List;

public class Menu {

    public void run(){
        while(true){
            System.out.println("\n🍩 Donut Delight");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            int choice = InputHelper.choose("Choose: ", 0, 1);
            if (choice==0) return;
            handleOrder();
        }
    }

    private void handleOrder(){
        Order order = new Order();
        while(true){
            System.out.println("\n1) Add Donut  2) Add Drink  3) Add Snack Deal  4) Checkout  0) Cancel");
            int c = InputHelper.choose("Choose: ", 0, 4);
            switch (c){
                case 0 -> { System.out.println("Order canceled.");
                    return;
                }         // [From SandwichShop] cancel path
                case 1 -> addDonut(order);
                case 2 -> addDrink(order);
                case 3 -> order.setSnackDeal(InputHelper.yesNo("Add snack deal now?"));   // bundle toggle
                case 4 -> { checkout(order);
                    return;
                }
            }
        }
    }

    private void addDonut(Order order){
        Dough dough = InputHelper.chooseEnum("Choose dough:", Dough.class);              // [Enums extension]
        Coating coat = InputHelper.chooseEnum("Choose coating:", Coating.class);
        PackSize size = InputHelper.chooseEnum("Choose pack size:", PackSize.class);
        List<Topping> tops = InputHelper.chooseMany("Pick toppings (0 for none):", Topping.class);
        List<Drizzle> driz = InputHelper.chooseMany("Pick drizzles (0 for none):", Drizzle.class);
        boolean extra = InputHelper.yesNo("Add extra toppings surcharge?");              // [From Pizza-Licious] same concept

        Donut d = new Donut()
                .dough(dough).coating(coat).size(size)
                .toppings(tops).drizzles(driz).extra(extra);

        System.out.println("Added: " + d);                                              // [From SandwichShop] echo added item
        order.addDonut(d);
    }

    private void addDrink(Order order){
        Drink drink = InputHelper.chooseEnum("Choose drink:", Drink.class);             // [From Pizza-Licious] drinks list
        order.setDrink(drink);
        System.out.println("Added drink: " + drink);
    }

    private void checkout(Order order){
        System.out.println("\n" + order.summary());                                     // [From SandwichShop] summary before save

    }
}
