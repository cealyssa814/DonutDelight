package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.*;
import com.pluralsight.DonutShop.filing.LedgerLogger;
import com.pluralsight.DonutShop.filing.ReceiptWriter;
import com.pluralsight.DonutShop.model.Donut;
import com.pluralsight.DonutShop.model.SpecialtyDonut;
import com.pluralsight.DonutShop.util.InputHelper;
import com.pluralsight.DonutShop.util.ThemedPrinter;

import java.util.List;

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
                    "4) Signature Donut 🍩" +
                    "5) Specialty Donut 🍩🥓" +
                    "6) Checkout 👛  " +
                    "0) Cancel");
            int c = InputHelper.choose("Choose: ", 0, 6);
            switch (c) {
                case 0 -> { System.out.println("Canceled."); return; }
                case 1 -> addDonut(order);
                case 2 -> addDrink(order);
                case 3 -> addSnackDeal(order);
                case 4 -> addSignature(order);
                case 5 -> addSpecialty(order);     // <-- NEW
                case 6 -> {
                    checkout(order);
                    return;
                }
                } // finish & save
            }
        }

    private void addSignature(Order order) {
        // TODO: if you have preset "SignatureDonut" (not the $4.50 specialties), pick and add here.
        // Example if you make a map of presets:
        // Donut preset = SignatureDonut.mapleBaconDream(PackSize.SIX);
        // order.addDonut(preset);
        ThemedPrinter.println("(Signature Donuts TBD)");
    }

    private void addSnackDeal(Order order) {
        // TODO: If you implemented a composite snack deal, create it and add.
        // For now, you can flip a flag on the order if that’s your current model.
        // order.setSnackDeal(true);
        ThemedPrinter.println("(Snack Deal flow TBD)");
    }

    // addDonut(): guided configuration using enums (safer than free text, follows abstraction ideas)
        private void addDonut(Order order) {
            // Start with a tan background and red text for the donut-building section
            ThemedPrinter.enable();

            // --- Prompt user with themed text ---
            ThemedPrinter.println("🍩 Let's build your perfect donut!");
            ThemedPrinter.println("\n(Premium toppings cost an extra $1.00 each and are marked with ★)");

            Dough dough = InputHelper.chooseEnum("Choose your dough 🥯:", Dough.class);
            Coating coat = InputHelper.chooseEnum("Choose your coating 🍩:", Coating.class);
            PackSize size = InputHelper.chooseEnum("Choose your pack size 📦:", PackSize.class);

            // Let the user know which are premium before chooseMany runs
            ThemedPrinter.println("Premium toppings: BACON★, PEANUTS★, OREO PIECES★, TOASTED COCONUT★");

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

    private void addSpecialty(Order order) {
        ThemedPrinter.println("\n— Specialty Donuts —");
        ThemedPrinter.println("1) Maple Bacon Crunch   ($4.50)  [cake + maple icing + caramel drizzle + bacon]");
        ThemedPrinter.println("2) Cookies & Creme Dream ($4.50) [yeast + vanilla icing + oreo + chocolate drizzle]");
        int pick = InputHelper.choose("Choose: ", 1, 2);

        if (pick == 1) {
            SpecialtyDonut d = SpecialtyDonut.mapleBaconCrunch(); // <-- FIXED PACKAGE/CLASS
            order.addDonut(d);                                    // <-- use your existing addDonut(..)
            ThemedPrinter.println("Added specialty: " + d.description());
        } else {
            SpecialtyDonut d = SpecialtyDonut.cookiesAndCremeDream(); // <-- FIXED PACKAGE/CLASS
            order.addDonut(d);
            ThemedPrinter.println("Added specialty: " + d.description());
        }
    }
}

