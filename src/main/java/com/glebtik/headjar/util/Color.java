package com.glebtik.headjar.util;

public enum Color {
    BLANK("", 0),
    WHITE("white_", 1),
    ORANGE("orange_", 2),
    MAGENTA("magenta_", 3),
    LIGHT_BLUE("light_blue_", 4),
    YELLOW("yellow_", 5),
    LIME("lime_", 6),
    PINK("pink_", 7),
    GRAY("gray_", 8),
    LIGHT_GRAY("light_gray_", 9),
    CYAN("cyan_", 10),
    PURPLE("purple_", 11),
    BLUE("blue_", 12),
    BROWN("brown_", 13),
    GREEN("green_", 14),
    RED("red_", 15),
    BLACK("black_", 16);

    public final String prefix;
    public final int colorValue;
    Color(String prefix, int colorValue) {
        this.prefix = prefix;
        this.colorValue = colorValue;
    }
}
