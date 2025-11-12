package com.pluralsight.DonutShop.filing;

import com.pluralsight.DonutShop.userinterface.Order;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Append a CSV line per order — inspired by GymLedger/Transactions “running history” concept.
public final class LedgerLogger {
    private LedgerLogger(){}

    public static void append(Order order) {
        File dir = new File("ledger");
        if (!dir.exists()) dir.mkdirs(); //does this exist?
        File csv = new File(dir, "orders.csv");
        boolean newFile = !csv.exists();
        try (PrintWriter pw = new PrintWriter(new FileWriter(csv, true))) {
            if (newFile) pw.println("timestamp,total,items,drink,snackDeal"); // header
            String ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String items = order.donuts().size()+" donuts";
            String drink = order.drink().map(Enum::name).orElse("");
            String snack = String.valueOf(order.snackDeal());
            pw.printf("%s,%.2f,%s,%s,%s%n", ts, order.total(), items, drink, snack);
        } catch (IOException ignored) {

        }
    }
}
