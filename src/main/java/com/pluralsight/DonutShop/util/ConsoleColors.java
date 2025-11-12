package com.pluralsight.DonutShop.util;

public final class ConsoleColors {
    private ConsoleColors() {

    }

    // reset all attributes
    public static final String RESET = "\u001B[0m";

    // 24-bit foreground and background
    public static String fg(int r, int g, int b) {
        return "\u001B[38;2;" + r + ";" + g + ";" + b + "m";
    }
    public static String bg(int r, int g, int b) {
        return "\u001B[48;2;" + r + ";" + g + ";" + b + "m";
    }

    // Convenience: wrap text with fg+bg and reset at the end
    public static String wrap(String text, int fr, int fg, int fb, int br, int bg, int bb) {
        return bg(br, bg, bb) + fg(fr, fg, fb) + text + RESET;
    }
}
