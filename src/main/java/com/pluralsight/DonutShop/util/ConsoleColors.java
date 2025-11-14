package com.pluralsight.DonutShop.util;

public final class ConsoleColors {
    private ConsoleColors() {}

    // reset all attributes
    public static final String RESET = "\u001B[0m";

    // 24-bit dynamic foreground color
    public static String fg(int r, int g, int b) {
        return "\u001B[38;2;" + r + ";" + g + ";" + b + "m";
    }

    // 24-bit dynamic background color
    public static String bg(int r, int g, int b) {
        return "\u001B[48;2;" + r + ";" + g + ";" + b + "m";
    }

    // wrap text with fg + bg + reset
    public static String wrap(String text, int fr, int fg, int fb, int br, int bg, int bb) {
        return bg(br, bg, bb) + fg(fr, fg, fb) + text + RESET;
    }


    // Light Salmon background (#FFA07A → 255, 160, 122)
    public static final String BG_SALMON = bg(255, 160, 122);

    // Midnight Blue text (#191970 → 25, 25, 112)
    public static final String FG_MIDNIGHT = fg(25, 25, 112);

    // Optional darker midnight accent (for headers)
    public static final String FG_MIDNIGHT_ACCENT = fg(15, 15, 70);
}
