package com.pluralsight.DonutShop.util;

// - call ThemedPrinter.enable() once at app start to color ALL subsequent output
//   (until you call ThemedPrinter.disable()).
// - OR call ThemedPrinter.println(...) per line if you prefer localized styling.

public final class ThemedPrinter {
    // tracks if the theme is active
    private static boolean enabled = true;

    // Turns theme on
    public static void enable() {
        enabled = true;
    }

    // Turns theme off
    public static void disable() {
        enabled = false;
    }

    // Auto-themed println()
    public static void println(String message) {

        if (enabled) {
            // Use your salmon + midnight color combo
            System.out.println(
                    ConsoleColors.BG_SALMON +
                            ConsoleColors.FG_MIDNIGHT +
                            message +
                            ConsoleColors.RESET
            );
        } else {
            // No theme — print normally
            System.out.println(message);
        }
    }

    // Same but without newline if ever needed
    public static void print(String message) {

        if (enabled) {
            System.out.print(
                    ConsoleColors.BG_SALMON +
                            ConsoleColors.FG_MIDNIGHT +
                            message +
                            ConsoleColors.RESET
            );
        } else {
            System.out.print(message);
        }
    }
}