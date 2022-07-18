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

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    /**
     * Formats using html and hex colors
     *
     * @param text the string to format
     * */
    public static String format(String text) {
        String[] split = text.split(" ");

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");

        int index = 0;
        for(String s : split) {
            if(index != 0) sb.append(" ");

            if(s.startsWith("#")) {
                String hex = s.substring(0, 7), after = s.substring(7);
                sb.append("<font color=\"").append(hex).append("\">").append(after).append("</font>");
            } else {
                sb.append("<font color=\"#FFFFFF\">").append(s).append("</font>");
            }
            index++;
        }
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

    public boolean isHex(String s) {
        return pattern.matcher(s).matches();
    }

}
