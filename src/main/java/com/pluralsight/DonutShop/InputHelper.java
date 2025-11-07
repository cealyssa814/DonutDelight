package com.pluralsight.DonutShop;

import java.util.*;

// [From SandwichShop] Reusable console helpers: read ints safely, read y/n,
// choose from lists. We used this same pattern for menu prompting there.
public final class InputHelper {
    private static final Scanner in = new Scanner(System.in);

    // [From SandwichShop] bounded integer read (robust prompt loop)
    public static int choose(String prompt, int min, int max){
        while(true){
            System.out.print(prompt);
            try{
                int v = Integer.parseInt(in.nextLine().trim());
                if (v>=min && v<=max) return v;
            }catch(Exception ignored){}
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    // [From SandwichShop] simple yes/no loop
    public static boolean yesNo(String prompt){
        while(true){
            System.out.print(prompt + " (y/n): ");
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("y")) return true;
            if (s.equals("n")) return false;
            System.out.println("Type y or n.");
        }
    }

    // [Extension, grounded in SandwichShop] Pick ONE from an enum list
    public static <E extends Enum<E>> E chooseEnum(String title, Class<E> type){
        E[] values = type.getEnumConstants();
        System.out.println(title);
        for (int i=0;i<values.length;i++){
            System.out.println((i+1) + ") " + nice(values[i]));
        }
        int pick = choose("Choose: ", 1, values.length);
        return values[pick-1];
    }

    // [Extension, grounded in SandwichShop] Pick MANY from an enum list
    public static <E extends Enum<E>> List<E> chooseMany(String title, Class<E> type){
        E[] values = type.getEnumConstants();
        System.out.println(title + " (comma separated, or 0 for none)");
        for (int i=0;i<values.length;i++){
            System.out.println((i+1) + ") " + nice(values[i]));
        }
        while(true){
            System.out.print("Your picks: ");
            String line = new Scanner(System.in).nextLine().trim();
            if (line.equals("0")) return new ArrayList<>();
            String[] parts = line.split(",");
            List<E> result = new ArrayList<>();
            boolean ok = true;
            for (String p : parts){
                try{
                    int idx = Integer.parseInt(p.trim());
                    if (idx<1||idx>values.length){ ok=false; break; }
                    result.add(values[idx-1]);
                }catch(Exception ex){ ok=false; break; }
            }
            if (ok) return result;
            System.out.println("Please use numbers from the list above (or 0).");
        }
    }

    private static String nice(Enum<?> e){
        return e.name().toLowerCase().replace('_',' ');
    }
}

