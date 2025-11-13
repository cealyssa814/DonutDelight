package com.pluralsight.DonutShop.filing;

import com.pluralsight.DonutShop.enums.DrinkSize;
import com.pluralsight.DonutShop.userinterface.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

// Append a CSV line per order — inspired by GymLedger/Transactions “running history” concept.
//
// LedgerLogger:
// This class records each order in a CSV ledger file (orders.csv).
// It's basically the "GymLedger" idea but for donuts instead of gym supplies.

public final class LedgerLogger {

    // Folder name where I keep ALL ledger files.
    // Having a constant makes it easy to change later if I want a different structure.
    private static final String LEDGER_FOLDER = "ledger";

    // File name for the main orders CSV file.
    private static final String LEDGER_FILE = "orders.csv";

    // Utility class: I don't need to create objects of this type, so I hide the constructor.
    private LedgerLogger() {

    }

//
//     Append one Order as a single line in the ledger CSV.
//     This gives me a history of all orders, similar to the GymLedger transaction history.
//
    public static void append(Order order) {
        // Make sure the "ledger" folder exists.
        // This mirrors what we did in GymLedger with the data folder.
        File dir = new File(LEDGER_FOLDER);

        // If the folder does NOT exist, create it (mkdirs handles nested folders safely).
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("Created ledger folder.");
            }
        }

        // Create a File object that points to ledger/orders.csv.
        File csv = new File(dir, LEDGER_FILE);

        // Check if this is the very first time we're creating the file.
        // If so, we want to write a header row with column names.
        boolean newFile = !csv.exists();

        // Open a FileWriter in APPEND mode (true) so we add lines instead of overwriting.
        // Wrap it in a PrintWriter so we can use printf/println (easier formatting).
        // try-with: automatically closes the writer at the end (IO workshop best practice).
        try (PrintWriter pw = new PrintWriter(new FileWriter(csv, true))) {

            // If it's a new file, write the header line once at the top.
            // This makes the CSV easier to read in Excel/Sheets later.
            if (newFile) {
                pw.println("timestamp,total,donutCount,drink,drinkSize,snackDeal");
            }

            // Build out each column for this order.

            // (a) timestamp: when this order was logged.
            // SimpleDateFormat is from the IO/GymLedger pattern for readable time stamps.
            String ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // (b) total: use Order.total() so all money rules stay in Order/Pricing.
            double total = order.total();

            // (c) donutCount: how many donuts in this order.
            String donutCount = String.valueOf(order.donuts().size());

            // (d) drink: use Optional from Order.drink() and convert enum to name or blank.
            String drink = order.drink()
                    .map(Enum::name)   // if present, use the enum name (e.g., FOUNTAIN, LEMONADE_STRAWBERRY)
                    .orElse("");       // if no drink, leave this CSV field empty

            // (e) drinkSize: similar to drink, but we log size (SMALL, MEDIUM, LARGE).
            String drinkSize = order.drinkSize()
                    .map(DrinkSize::name) // print the enum name if present
                    .orElse("");          // no size if no drink was set

            // (f) snackDeal: true or false — convert to String so printf can print it easily.
            String snack = String.valueOf(order.snackDeal());

            // Write one CSV row with all fields.
            // %s = string, %.2f = decimal with 2 places, %n = newline (platform-independent).
            pw.printf("%s,%.2f,%s,%s,%s,%s%n",
                    ts,        // timestamp
                    total,     // order total
                    donutCount,
                    drink,
                    drinkSize,
                    snack);

            // At this point, the row is written to the buffer; when the try block exits,
            // the PrintWriter/FileWriter are automatically flushed and closed.

        } catch (IOException e) {
            // If something goes wrong writing to the file, I don't want my whole app to crash.
            // For now, I just print an error message. In a bigger app, I'd log this somewhere.
            System.out.println("Error writing to ledger: " + e.getMessage());
        }
    }
}