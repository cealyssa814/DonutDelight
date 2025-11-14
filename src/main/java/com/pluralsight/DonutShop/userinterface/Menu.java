package com.pluralsight.DonutShop.userinterface;

import com.pluralsight.DonutShop.enums.*;
import com.pluralsight.DonutShop.filing.LedgerLogger;
import com.pluralsight.DonutShop.filing.ReceiptWriter;
import com.pluralsight.DonutShop.model.Donut;
import com.pluralsight.DonutShop.model.Order;
import com.pluralsight.DonutShop.model.SpecialtyDonut;
import com.pluralsight.DonutShop.util.InputHelper;
import com.pluralsight.DonutShop.util.ThemedPrinter;

import java.util.List;

public class Menu {

    // run(): main home loop — same idea as SandwichShop "Home -> New Order -> Exit"
    //ThemedPrinter adds tan and red hue to the code
    public void run(){
        while(true){ // repeat until user chooses Exit
            ThemedPrinter.enable();
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║                     🍩 DONUT DELIGHT 🍩                    ║ ");
            ThemedPrinter.println("║              Fresh • Warm • Made With Love 💕              ║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║    ✨ 1) Build Your Dream Donut 📝                         ║ ");
            ThemedPrinter.println("║    ✨ 0) Exit the Shop 🚪                                  ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════╝ ");

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
            ThemedPrinter.enable();
            ThemedPrinter.println("");
            ThemedPrinter.println("╔══════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║                           🧾 CURRENT ORDER 🧾                        ║ ");
            ThemedPrinter.println("╠══════════════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║  1️⃣  Add Donut 🍩                                                    ║ ");
            ThemedPrinter.println("║  2️⃣  Add Drink 🥤                                                    ║ ");
            ThemedPrinter.println("║  3️⃣  Add Snack Deal Combo 🍩🥤                                       ║ ");
            ThemedPrinter.println("║  4️⃣  Choose a Specialty Donut 💎                                     ║ ");
            ThemedPrinter.println("║  5️⃣  Checkout & View Receipt 💳                                      ║ ");
            ThemedPrinter.println("║  0️⃣  Cancel Order ❌                                                 ║ ");
            ThemedPrinter.println("╚══════════════════════════════════════════════════════════════════════╝ ");
            ThemedPrinter.println("");
            //A point I got stuck: When taking the Signature Donut Option out of the menu
            // and forgot the change my case numbers (resulting in my checkout not
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
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩 Let's build your perfect donut! 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║                    (Premium toppings cost an extra $1.00 each and are marked with ★)                       ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");
            Dough dough = InputHelper.chooseEnum("Choose your dough 🥯:", Dough.class);
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩 Let's build your perfect donut! 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║                    (Premium toppings cost an extra $1.00 each and are marked with ★)                       ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");
            Coating coat = InputHelper.chooseEnum("Choose your coating 🍩:", Coating.class);
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩 Let's build your perfect donut! 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║                    (Premium toppings cost an extra $1.00 each and are marked with ★)                       ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");
            PackSize size = InputHelper.chooseEnum("Choose your pack size 📦:", PackSize.class);

            // Let the user know which are premium before chooseMany runs
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩 Let's build your perfect donut! 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║                    (Premium toppings cost an extra $1.00 each and are marked with ★)                       ║ ");
            ThemedPrinter.println("║                    Premium toppings: BACON★, PEANUTS★, OREO PIECES★, TOASTED COCONUT★   n                  ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");


            List<Topping> tops = InputHelper.chooseMany("Pick toppings (0 for none):", Topping.class);
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩 Let's build your perfect donut! 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║                    (Premium toppings cost an extra $1.00 each and are marked with ★)                       ║ ");
            ThemedPrinter.println("║                    Premium toppings: BACON★, PEANUTS★, OREO PIECES★, TOASTED COCONUT★   n                  ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

            List<Drizzle> driz = InputHelper.chooseMany("Pick drizzles (0 for none):", Drizzle.class);
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩 Let's build your perfect donut! 🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩🍩║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════╣ ");
            ThemedPrinter.println("║                    (Premium toppings cost an extra $1.00 each and are marked with ★)                       ║ ");
            ThemedPrinter.println("║                    Premium toppings: BACON★, PEANUTS★, OREO PIECES★, TOASTED COCONUT★   n                  ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

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

            // Add donut to order
            order.addDonut(d);

            // Reset colors back to normal before returning
            //Alyssa this is quite necessary to the theme.
            ThemedPrinter.disable();
        }

    // addDrink(): single-choice flow (Pizza-licious drinks)
    private void addDrink(Order order){
        // Ask which drink
        ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
        ThemedPrinter.println("║ 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤  Why not pair a sweet treat with a drink? 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤 🥤🥤🥤🥤🥤║ ");
        ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

        DrinkFlavor drinkFlavor = InputHelper.chooseEnum("Choose drink 🥤:", DrinkFlavor.class);

        // Decide which size menu to show based on drink type.
        // Fountain gets S/M/L; Lemonades get M/L; others get single default (MEDIUM).
        DrinkSize chosenSize;

        if (drinkFlavor == DrinkFlavor.FOUNTAIN) {
            // Fountain: offer all sizes (Small, Medium, Large)
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤  Why not pair a sweet treat with a drink? 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤 🥤🥤🥤🥤🥤║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

            ThemedPrinter.println("Choose size for FOUNTAIN drink:");
            ThemedPrinter.println("1) Small");
            ThemedPrinter.println("2) Medium");
            ThemedPrinter.println("3) Large");

            int pick = InputHelper.choose("Size: ", 1, 3);
            chosenSize = switch (pick) {
                case 1 -> DrinkSize.SMALL;
                case 2 -> DrinkSize.MEDIUM;
                case 3 -> DrinkSize.LARGE;
                default -> DrinkSize.MEDIUM;
            };
            order.setDrink(drinkFlavor, chosenSize); // size-aware setter

        } else if (drinkFlavor.name().startsWith("LEMONADE")) {
            // Lemonades: only Medium and Large
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤  Why not pair a sweet treat with a drink? 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤 🥤🥤🥤🥤🥤║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

            ThemedPrinter.println("Choose size for LEMONADE:");
            ThemedPrinter.println("1) Medium");
            ThemedPrinter.println("2) Large");

            int pick = InputHelper.choose("Size: ", 1, 2);
            chosenSize = (pick == 2) ? DrinkSize.LARGE : DrinkSize.MEDIUM;
            order.setDrink(drinkFlavor, chosenSize);

        } else {
            // All other drinks (coffee/tea/milkshakes) keep the single default price.
            // We still store MEDIUM so the summary prints a size consistently.
            ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║ 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤  Why not pair a sweet treat with a drink? 🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤🥤 🥤🥤🥤🥤🥤║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

            order.setDrink(drinkFlavor, DrinkSize.MEDIUM);
        }

        ThemedPrinter.println("Added drink 🥤: " + drinkFlavor +
                " (" + order.drinkSize().orElse(DrinkSize.MEDIUM) + ")");
    }

    // checkout(): summarize, save receipt, append to ledger (GymLedger), and thanks the user
    private void checkout(Order order) {
        // 1) Print the summary (this uses Order.summary(), which calls total() once)
        ThemedPrinter.println("");
        ThemedPrinter.println("╔══════════════════════════════════════════╗ ");
        ThemedPrinter.println("║           💳 CHECKOUT SUMMARY 💳         ║ ");
        ThemedPrinter.println("╚══════════════════════════════════════════╝ ");
        ThemedPrinter.println(order.summary());


        // 2) Ask to save receipt
        if (InputHelper.yesNo("Save receipt to file 🧾?")) {
            try {
                String path = ReceiptWriter.save(order);
                ThemedPrinter.println("Saved to: " + path);
            } catch (Exception e) {
                ThemedPrinter.println("Failed to save: " + e.getMessage());
            }
        }

        // 3) Log to ledger
        LedgerLogger.append(order);

        // 4) Thank the user
        ThemedPrinter.println("Thank you!");
    }

    private void addSpecialty(Order order) {
        ThemedPrinter.println("");
        ThemedPrinter.println("╔════════════════════════════════════════════════════╗ ");
        ThemedPrinter.println("║               💎 Specialty Creations 💎            ║ ");
        ThemedPrinter.println("╠════════════════════════════════════════════════════╣ " );
        ThemedPrinter.println("║  1️⃣  Maple Bacon Crunch      ($4.50)               ║ ");
        ThemedPrinter.println("║      🧁 Cake donut • Maple icing • Caramel drizzle ║ ");
        ThemedPrinter.println("║      🥓 Bacon topping                              ║ ");
        ThemedPrinter.println("║                                                    ║ ");
        ThemedPrinter.println("║  2️⃣  Cookies & Creme Dream   ($4.50)               ║ ");
        ThemedPrinter.println("║      🍩 Yeast donut • Vanilla coating              ║ ");
        ThemedPrinter.println("║      🪵 Oreo pieces • Chocolate drizzle            ║ ");
        ThemedPrinter.println("║                                                    ║ ");
        ThemedPrinter.println("║  0️⃣  Back to Order Menu                            ║ ");
        ThemedPrinter.println("╚════════════════════════════════════════════════════╝ ");
        ThemedPrinter.println("");
        int pick = InputHelper.choose("Choose: ", 1, 2);

        if (pick == 1) {
            SpecialtyDonut d = SpecialtyDonut.mapleBaconCrunch(); //
            order.addDonut(d);
            ThemedPrinter.println("");
            ThemedPrinter.println("╔════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║               💎 Specialty Creations 💎            ║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════╣ " );
            ThemedPrinter.println("║  1️⃣  Maple Bacon Crunch      ($4.50)               ║ ");
            ThemedPrinter.println("║      🧁 Cake donut • Maple icing • Caramel drizzle ║ ");
            ThemedPrinter.println("║      🥓 Bacon topping                              ║ ");
            ThemedPrinter.println("║                                                    ║ ");
            ThemedPrinter.println("║  2️⃣  Cookies & Creme Dream   ($4.50)               ║ ");
            ThemedPrinter.println("║      🍩 Yeast donut • Vanilla coating              ║ ");
            ThemedPrinter.println("║      🪵 Oreo pieces • Chocolate drizzle            ║ ");
            ThemedPrinter.println("║                                                    ║ ");
            ThemedPrinter.println("║  0️⃣  Back to Order Menu                            ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════╝ ");
            ThemedPrinter.println("");
            ThemedPrinter.println("Added specialty: " + d.description());
        } else {
            SpecialtyDonut d = SpecialtyDonut.cookiesAndCremeDream();
            order.addDonut(d);
            ThemedPrinter.println("");
            ThemedPrinter.println("╔════════════════════════════════════════════════════╗ ");
            ThemedPrinter.println("║               💎 Specialty Creations 💎            ║ ");
            ThemedPrinter.println("╠════════════════════════════════════════════════════╣ " );
            ThemedPrinter.println("║  1️⃣  Maple Bacon Crunch      ($4.50)               ║ ");
            ThemedPrinter.println("║      🧁 Cake donut • Maple icing • Caramel drizzle ║ ");
            ThemedPrinter.println("║      🥓 Bacon topping                              ║ ");
            ThemedPrinter.println("║                                                    ║ ");
            ThemedPrinter.println("║  2️⃣  Cookies & Creme Dream   ($4.50)               ║ ");
            ThemedPrinter.println("║      🍩 Yeast donut • Vanilla coating              ║ ");
            ThemedPrinter.println("║      🪵 Oreo pieces • Chocolate drizzle            ║ ");
            ThemedPrinter.println("║                                                    ║ ");
            ThemedPrinter.println("║  0️⃣  Back to Order Menu                            ║ ");
            ThemedPrinter.println("╚════════════════════════════════════════════════════╝ ");
            ThemedPrinter.println("");
            ThemedPrinter.println("Added specialty: " + d.description());
        }
    }
    private void addSnackDeal(Order order) {
        // If your Order has a boolean flag:
        order.setSnackDeal(true);
        // Offer drink selection for the bundle if you want:
        ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
        ThemedPrinter.println("║ 🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤   Pick a drink for the snack deal   🥤🍩🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤║ ");
        ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

        if (InputHelper.yesNo("Pick a drink for the snack deal?")) {
            DrinkFlavor d = InputHelper.chooseEnum("Choose drink 🥤:", DrinkFlavor.class);
            order.setDrink(d); // or add polymorphically if you support items list
        }
        ThemedPrinter.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗ ");
        ThemedPrinter.println("║ 🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤   Pick a drink for the snack deal   🥤🍩🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤🥤🍩🥤🍩🥤║ ");
        ThemedPrinter.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════╝ ");

        ThemedPrinter.println("Snack deal added.");
    }
}



