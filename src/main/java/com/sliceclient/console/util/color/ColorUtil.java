package com.sliceclient.console.util.color;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Color utility class
 *
 * @author Nick
 * */
@UtilityClass
public class ColorUtil {

    /** for hex */
    private static final Pattern pattern = Pattern.compile("#[a-fA-f0-9]{6}");

    /**
     * Formats using html and hex colors
     *
     * @param text the string to format
     * */
    public static String format(String text) {
        Matcher match = pattern.matcher(text);

        String s = text;
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        boolean hasHex = match.find();
        if(!hasHex) {
            sb.append("<font color=\"#FFFFFF\">").append(text).append("</font>").append("</html>");
            return sb.toString();
        }

        while (match.find()) {
            String color = text.substring(match.start(), match.end());
            text = text.replace(color, "<font color=\"" + color + "\">");
            s = text;
            text = "</font>";
            match = pattern.matcher(text);
        }
        sb.append(s).append(text);
        sb.append("</html>");
        return sb.toString();
    }

    /**
     * Removes hex colors from a string
     *
     * @param text the string to remove hex colors from
     * */
    public static String removeHex(String text) {
        Matcher match = pattern.matcher(text);

        while (match.find()) {
            String color = text.substring(match.start(), match.end());
            text = text.replace(color, "");
            match = pattern.matcher(text);
        }
        return text;
    }

}
