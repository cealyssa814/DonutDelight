package com.pluralsight.DonutShop.util;
import java.util.*;

// I wrote this helper to centralize all console input logic.
// — Pattern comes from my SandwichShop menu flows: repeat prompts until valid.
// — Also mirrors workbook advice to separate concerns (UI vs. business). (6a - Interfaces & abstraction)
public final class InputHelper {
    private static final Scanner in = new Scanner(System.in); // SandwichShop used a single Scanner

    // Reusable bounded integer prompt (SandwichShop-style input loop)
    public static int choose(String prompt, int min, int max){
        while(true){
            ThemedPrinter.print(prompt); // UI line (SandwichShop)
            try{
                int v = Integer.parseInt(in.nextLine().trim()); // safe parse like SandwichShop
                if (v>=min && v<=max) return v; // guard rails
            }catch(Exception ignored){}
            ThemedPrinter.println("Please enter a number between " + min + " and " + max + "."); // friendly retry
        }
    }

    // y/n prompt (SandwichShop pattern)
    public static boolean yesNo(String prompt){
        while(true){
            ThemedPrinter.print(prompt + " (y/n): ");
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("y"))
                return true;
            if (s.equals("n"))
                return false;
            ThemedPrinter.println("Type y or n.");
        }
    }

    // Choose ONE enum value — a generalization of SandwichShop’s single-choice menus
    public static <E extends Enum<E>> E chooseEnum(String title, Class<E> type){
        E[] values = type.getEnumConstants();
        ThemedPrinter.println(title); //same output as System.println, but in tan/red
        for (int i=0;i<values.length;i++){
            ThemedPrinter.println((i+1) + ") " + nice(values[i])); // display pretty names
        }
        int pick = choose("Choose: ", 1, values.length);
        return values[pick-1];
    }

    // Choose MANY enum values — a generalization of SandwichShop’s “add ingredients” multi-select
    public static <E extends Enum<E>> List<E> chooseMany(String title, Class<E> type){
        E[] values = type.getEnumConstants();
        ThemedPrinter.println(title + " (comma separated, or 0 for none)");
        for (int i=0;i<values.length;i++){
            ThemedPrinter.println((i+1) + ") " + nice(values[i]));
        }
        while(true){
            ThemedPrinter.print("Your picks: ");
            String line = new Scanner(System.in).nextLine().trim();
            if (line.equals("0"))
                return new ArrayList<>();
            String[] parts = line.split(",");
            List<E> result = new ArrayList<>();
            boolean ok = true;
            for (String p : parts){
                try{
                    int idx = Integer.parseInt(p.trim());
                    if (idx<1||idx>values.length){
                        ok=false;
                        break;
                    }
                    result.add(values[idx-1]);
                }catch(Exception ex){ ok=false;
                    break;
                }
            }
            if (ok)
                return result;
            ThemedPrinter.println("Please use numbers from the list above (or 0).");
        }
    }

    // Pretty-print enum names ("CHOCOLATE_ICING" -> "chocolate icing")
    private static String nice(Enum<?> e){
        return e.name().toLowerCase().replace('_',' '); // presentation detail for user/receipt
        // Rationale: keep business enums constant-like, but show friendly output.
    }
}

