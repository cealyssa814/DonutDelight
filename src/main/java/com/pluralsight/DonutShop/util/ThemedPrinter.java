package com.pluralsight.DonutShop.util;

// - call ThemedPrinter.enable() once at app start to color ALL subsequent output
//   (until you call ThemedPrinter.disable()).
// - OR call ThemedPrinter.println(...) per line if you prefer localized styling.

public final class ThemedPrinter {
    private ThemedPrinter() {

    }

    // Theme colors
    private static final String START = ConsoleColors.bg(210, 180, 140) + ConsoleColors.fg(255, 0, 0);
    private static final String END   = ConsoleColors.RESET;

    // Apply theme for ALL following console output (until disable() is called)
    public static void enable() {
        System.out.print(START);
        // Do not println; keep the style "open" across prints
    }

    // Reset console to defaults
    public static void disable() {

        System.out.print(END);
    }

    // Per-line helpers (auto wrap + reset)
    public static void println(String s) {

        System.out.println(START + s + END);
    }
    public static void print(String s) {

        System.out.print(START + s + END);
    }
}
