package com.pluralsight.DonutShop.userinterface;
import com.pluralsight.DonutShop.enums.*;     // (Use typed enums for safer menus)
import com.pluralsight.DonutShop.util.InputHelper;  // (Re-using SandwichShop input flow)
import com.pluralsight.DonutShop.filing.LedgerLogger;
import com.pluralsight.DonutShop.filing.ReceiptWriter;
import com.pluralsight.DonutShop.model.Donut;
import com.pluralsight.DonutShop.util.ThemedPrinter;

import java.util.List;                    // (Collections — standard Java List)

public class Menu {

    // run(): main home loop — same idea as SandwichShop "Home -> New Order -> Exit"
    //ThemedPrinter adds tan and red hue to the code
    public void run(){
        while(true){ // repeat until user chooses Exit
            ThemedPrinter.println("\n============================🍩 Welcome to Donut Delight 🍩============================");     // banner (UI polish)
            ThemedPrinter.println("                                   1) Create a New Order 📝");           // option 1
            ThemedPrinter.println("                                   0) Exit →");                // option 0
            int choice = InputHelper.choose("Choose: ", 0, 1); // robust input (SandwichShop pattern)
            if (choice==0)
                return;                        // leave program
            handleOrder();                                // go into order flow
        }
    }

    // handleOrder(): manages a single order session (like Sandwich "build a sandwich")
    private void handleOrder(){
        Order order = new Order(); // aggregation container (GymLedger style totals)
        while(true){
            ThemedPrinter.println("\n1) Add Donut 🍩  " +
                    "2) Add Drink 🥤  " +
                    "3) Add Snack Deal 🥤🍩  " +
                    "4) Checkout 👛  " +
                    "0) Cancel");
            int c = InputHelper.choose("Choose: ", 0, 4); // validated menu selection
            switch (c){
                case 0 -> {
                    ThemedPrinter.println("Order canceled. ❌");
                    return;
                } // cancel path (SandwichShop had this)
                case 1 -> addDonut(order);           // add a configured donut
                case 2 -> addDrink(order);           // add one drink (a la carte)
                case 3 -> order.setSnackDeal(InputHelper.yesNo("Add snack deal now? 🥤🍩")); // bundle toggle (custom spec)
                case 4 -> {
                    checkout(order);
                    return;
                } // finish & save
            }
        }
    }

    // addDonut(): guided configuration using enums (safer than free text, follows abstraction ideas)
    private void addDonut(Order order) {
        // Start with a tan background and red text for the donut-building section
        ThemedPrinter.enable();

        // --- Prompt user with themed text ---
        ThemedPrinter.println("🍩 Let's build your perfect donut!");

        Dough dough = InputHelper.chooseEnum("Choose your dough 🥯:", Dough.class);
        Coating coat = InputHelper.chooseEnum("Choose your coating 🍩:", Coating.class);
        PackSize size = InputHelper.chooseEnum("Choose your pack size 📦:", PackSize.class);
        List<Topping> tops = InputHelper.chooseMany("Pick toppings (0 for none):", Topping.class);
        List<Drizzle> driz = InputHelper.chooseMany("Pick drizzles (0 for none):", Drizzle.class);
        boolean extra = InputHelper.yesNo("Add extra toppings surcharge?");

        // Build donut object
        Donut d = new Donut()
                .dough(dough)
                .coating(coat)
                .size(size)
                .toppings(tops)
                .drizzles(driz)
                .extra(extra);

        // --- Output confirmation message in color ---
        ThemedPrinter.println("Added: " + d);

        // Add donut to order (normal logic)
        order.addDonut(d);

        // Reset colors back to normal before returning (important!)
        ThemedPrinter.disable();
    }

    // addDrink(): single-choice flow (Pizza-licious drinks)
    private void addDrink(Order order){
        Drink drink = InputHelper.chooseEnum("Choose drink 🥤:", Drink.class);
        order.setDrink(drink);
        ThemedPrinter.println("Added drink 🥤: " + drink);
    }

    // checkout(): summarize, save receipt, append to ledger (GymLedger), and thank the user
    private void checkout(Order order){
        ThemedPrinter.println("\n" + order.summary());                 // Sandwich-style summary
        if (InputHelper.yesNo("Save receipt to file 🧾?")){
            try{
                String path = ReceiptWriter.save(order);            // persist to /receipts (IO workbook)
                ThemedPrinter.println("Saved to: " + path);
            }catch(Exception e){
                ThemedPrinter.println("Failed to save: " + e.getMessage());
            }
        }
        LedgerLogger.append(order);                                 // record to /ledger/orders.csv (GymLedger idea)
        ThemedPrinter.println("Thank you!");
    }
}
