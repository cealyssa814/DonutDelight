package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.*;
import com.pluralsight.DonutShop.filing.LedgerLogger;
import com.pluralsight.DonutShop.filing.ReceiptWriter;
import com.pluralsight.DonutShop.model.Donut;
import com.pluralsight.DonutShop.model.SpecialtyDonut;
import com.pluralsight.DonutShop.util.InputHelper;
import com.pluralsight.DonutShop.util.ThemedPrinter;

import java.io.IOException;
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
        Order order = new Order(); // GymLedger style totals
        while(true){
            ThemedPrinter.println("\n1) Add Donut 🍩  " +
                    "2) Add Drink 🥤  " +
                    "3) Add Snack Deal 🥤🍩  " +
                    "4) Specialty Donuts 🍩🥓  " +
                    "5) Checkout 👛  " +
                    "0) Cancel  ");
            //A point I got stuck: When taking the Signature Donut Option out of the menu
            // and forgot the change my case numbers (resulting in my checkout not working.
            int c = InputHelper.choose("Choose: ", 0, 5);
            switch (c) {
                case 0 -> {
                    ThemedPrinter.println("Canceled.");
                    return;
                }
                case 1 -> addDonut(order);
                case 2 -> addDrink(order);
                case 3 -> addSnackDeal(order);
                case 4 -> addSpecialty(order);
                case 5 -> {
                    checkout(order);
                    return;
                }
                } // finish & save
            }
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

            // Reset colors back to normal before returning
            ThemedPrinter.disable();
        }

    // addDrink(): single-choice flow (Pizza-licious drinks)
    private void addDrink(Order order){
        // Ask which drink
        Drink drink = InputHelper.chooseEnum("Choose drink 🥤:", Drink.class);

        // Decide which size menu to show based on drink type.
        // Fountain gets S/M/L; Lemonades get M/L; others get single default (MEDIUM).
        DrinkSize chosenSize;

        if (drink == Drink.FOUNTAIN) {
            // Fountain: offer all sizes (Small, Medium, Large)
            ThemedPrinter.println("Choose size for FOUNTAIN drink:");
            ThemedPrinter.println("1) Small   2) Medium   3) Large");

            int pick = InputHelper.choose("Size: ", 1, 3);
            chosenSize = switch (pick) {
                case 1 -> DrinkSize.SMALL;
                case 2 -> DrinkSize.MEDIUM;
                case 3 -> DrinkSize.LARGE;
                default -> DrinkSize.MEDIUM;
            };
            order.setDrink(drink, chosenSize); // size-aware setter

        } else if (drink.name().startsWith("LEMONADE")) {
            // Lemonades: only Medium and Large
            ThemedPrinter.println("Choose size for LEMONADE:");
            ThemedPrinter.println("1) Medium   2) Large");

            int pick = InputHelper.choose("Size: ", 1, 2);
            chosenSize = (pick == 2) ? DrinkSize.LARGE : DrinkSize.MEDIUM;
            order.setDrink(drink, chosenSize);

        } else {
            // All other drinks (coffee/tea/milkshakes) keep the single default price.
            // We still store MEDIUM so the summary prints a size consistently.
            order.setDrink(drink, DrinkSize.MEDIUM);
        }

        ThemedPrinter.println("Added drink 🥤: " + drink +
                " (" + order.drinkSize().orElse(DrinkSize.MEDIUM) + ")");
    }

    // checkout(): summarize, save receipt, append to ledger (GymLedger), and thanks the user
    private void checkout(Order order) {
        // Print a summary of everything in the order.
        // This uses Order.summary(), which formats donuts, drink, snackDeal, and total.
        ThemedPrinter.println("\n" + order.summary());

        // Ask if they want a receipt file (IO workbook behavior)
        if (InputHelper.yesNo("Save receipt to file 🧾?")) {
            try {
                String path = ReceiptWriter.save(order); // writes to /receipts/...
                ThemedPrinter.println("Saved to: " + path);
            } catch (IOException e) {
                ThemedPrinter.println("Failed to save: " + e.getMessage());
            }
        }

        // Always append order to the ledger CSV (GymLedger style logging)
        LedgerLogger.append(order);

        // Say thanks and return to go back to main menu
        ThemedPrinter.println("Thank you!");
    }

    private void addSpecialty(Order order) {
        ThemedPrinter.println("\n— Specialty Donuts —");
        ThemedPrinter.println("1) Mapl1e Bacon Crunch   ($4.50)  [cake + maple icing + caramel drizzle + bacon]");
        ThemedPrinter.println("2) Cookies & Creme Dream ($4.50) [yeast + vanilla icing + oreo + chocolate drizzle]");
        int pick = InputHelper.choose("Choose: ", 1, 2);

        if (pick == 1) {
            SpecialtyDonut d = SpecialtyDonut.mapleBaconCrunch(); //
            order.addDonut(d);
            ThemedPrinter.println("Added specialty: " + d.description());
        } else {
            SpecialtyDonut d = SpecialtyDonut.cookiesAndCremeDream();
            order.addDonut(d);
            ThemedPrinter.println("Added specialty: " + d.description());
        }
    }
    private void addSnackDeal(Order order) {
        // If your Order has a boolean flag:
        order.setSnackDeal(true);
        // Offer drink selection for the bundle if you want:
        if (InputHelper.yesNo("Pick a drink for the snack deal?")) {
            Drink d = InputHelper.chooseEnum("Choose drink 🥤:", Drink.class);
            order.setDrink(d); // or add polymorphically if you support items list
        }
        ThemedPrinter.println("Snack deal added.");
    }
}



