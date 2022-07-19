package com.sliceclient.console.util.color;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Color utility class
 *
 * @author Nick
 * */
@UtilityClass
public class ColorUtil {

    /** hex codes **/
    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    /**
     * Formats using html and hex colors
     *
     * @param text the string to format
     * */
    public static String format(String text) {
        Matcher matcher = pattern.matcher(text);

        // get text before color
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        while (matcher.find())
            matcher.appendReplacement(sb, "<font color=\"" + matcher.group() + "\">");
        sb.append("</font>");
        matcher.appendTail(sb);
        sb.append("</html>");
        return sb.toString();
    }

    /**
     * Gets the count of hex colors in the string
     * @param text the string to check
     * */
    public int getHexCount(String text) {
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        List<String> colors = new ArrayList<>();
        while (matcher.find()) {
            if(!colors.contains(matcher.group())) {
                colors.add(matcher.group());
                count++;
            }
        }
        return count;
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

    /**
     * Checks if a string is a hex color
     *
     * @param s the string to check
     * */
    public static boolean isHex(String s) {
        return pattern.matcher(s).matches();
    }

    /**
     * Gets the hex color from a string
     *
     * @param s the string to get the hex color from
     * */
    public String getHex(String s) {
        Matcher match = pattern.matcher(s);

        String color = null;
        while(match.find()) {
            color = s.substring(match.start(), match.end());
        }
        return color;
    }

    /**
     * Gets the hex color Matcher#end() from a string
     *
     * @param s the string to get the hex color match end from
     * */
    public int getHexMatchEnd(String s) {
        Matcher match = pattern.matcher(s);

        int end = 0;
        while(match.find()) {
            end = match.end();
        }
        return end;
    }

}
