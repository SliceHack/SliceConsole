package com.sliceclient.console.util.color;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum Colors {
    RED("&c", "#FF0000"), GREEN("&a", "#00FF00"),
    BLUE("&b", "#0000FF"), YELLOW("&e", "#FFFF00"),
    WHITE("&f", "#FFFFFF"), BLACK("&0", "#000000"),
    DARK_RED("&4", "#8B0000"), DARK_GREEN("&2", "#006400"),
    DARK_BLUE("&1", "#00008B"), DARK_YELLOW("&6", "#8B8B00"),
    DARK_WHITE("&7", "#000000"), GRAY("&8", "#808080"),
    DARK_GRAY("&9", "#A9A9A9"), LIGHT_RED("&c", "#FFA500"),
    LIGHT_GREEN("&a", "#90EE90"), LIGHT_BLUE("&b", "#ADD8E6"),
    LIGHT_YELLOW("&e", "#FFFFE0"), RESET("&r", "#FFFFFF"),
    PINK("&d", "#FF1493"), ORANGE("&6", "#FFA500"),
    PURPLE("&5", "#800080"), CYAN("&3", "#008080");

    private final String colorCode, hex;
}
