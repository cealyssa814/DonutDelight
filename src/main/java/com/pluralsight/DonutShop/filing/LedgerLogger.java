package com.pluralsight.DonutShop.filing;

import com.pluralsight.DonutShop.userinterface.Order;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Append a CSV line per order — inspired by GymLedger/Transactions “running history” concept.
public final class LedgerLogger {
    private LedgerLogger(){}
    public static void append(Order order) {
        // Create a folder named "ledger" to store my CSV file.
        // 'File' represents a path on the computer — could be a file or folder.
        File dir = new File("ledger");

        // Check if the folder already exists.
        // If it doesn't, create it using mkdirs() (makes parent directories too if needed).
        if (!dir.exists()) dir.mkdirs(); // makes sure I have somewhere to save my data

        // Create a File object for "orders.csv" inside the ledger folder.
        File csv = new File(dir, "orders.csv");

        // Check if this is a *new* file (meaning it doesn’t exist yet).
        // I’ll use this later to decide whether to print a CSV header line.
        boolean newFile = !csv.exists();

        // Create a PrintWriter connected to a FileWriter in "append" mode (true).
        // This means: if the file already exists, add to the end instead of overwriting.
        // I’m using try-with-resources so Java automatically closes the writer when done.
        try (PrintWriter pw = new PrintWriter(new FileWriter(csv, true))) {

            // If the file is new, write a header line at the top.
            // This gives my CSV file readable column names for future reference.
            if (newFile) pw.println("timestamp,total,items,drink,snackDeal"); // header row for first run

            // Create a timestamp for when the order was saved.
            // This helps me track when each order happened.
            String ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Count how many donuts were in this order and convert it to text.
            String items = order.donuts().size() + " donuts";

            // Get the drink name (if one was ordered) using Optional.
            // If drink() is empty, it returns an empty string instead of crashing.
            String drink = order.drink().map(Enum::name).orElse("");

            // Get the snack deal flag (true/false) as text.
            String snack = String.valueOf(order.snackDeal());

            // Write all the order details to the CSV file in one line.
            // %s = text, %.2f = decimal formatted to 2 places, %n = new line.
            pw.printf("%s,%.2f,%s,%s,%s%n", ts, order.total(), items, drink, snack);

            // If anything goes wrong (like missing permissions), ignore for now.
            // In a real app, I’d probably log or show an error, but here it’s optional.
        } catch (IOException ignored) {

        }
    }
}
