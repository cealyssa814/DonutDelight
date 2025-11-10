package com.pluralsight.DonutShop;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ReceiptWriter {
    private ReceiptWriter(){}

    public static String save(Order order) throws IOException {
        File dir = new File("receipts");
        if (!dir.exists()) dir.mkdirs(); // ensure folder exists
        String ts = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        File out = new File(dir, "order-" + ts + ".txt");
        try(PrintWriter pw = new PrintWriter(new FileWriter(out))){
            pw.print(order.summary()); // SandwichShop-style readable output
        }
        return out.getAbsolutePath();
    }
}
