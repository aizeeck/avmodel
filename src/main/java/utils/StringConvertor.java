package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aizeeck on 23.07.17.
 */
public class StringConvertor {

    public static int convertToInt(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        if (input.contains("-")) {
            return 0;
        }
        if (input.contains("unknown")) {
            return 0;
        }
        if (input.contains("Unlim")) {
            return Integer.MAX_VALUE;
        }

        Pattern p = Pattern.compile("-?\\d+( \\d+)*");
        Matcher matcher = p.matcher(input);
        if (matcher.find()) {
            input = input.substring(matcher.start(), matcher.end());
        }
        input = input.replaceAll(" ", "");
        return Integer.parseInt(input);
    }

    public static long convertToLong(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        if (input.contains("-")) {
            return 0;
        }
        if (input.contains("unknown")) {
            return 0;
        }
        Pattern p = Pattern.compile("-?\\d+( \\d+)*");
        Matcher matcher = p.matcher(input);
        if (matcher.find()) {
            input = input.substring(matcher.start(), matcher.end());
        }
        input = input.replaceAll(" ", "");
        return Long.parseLong(input);
    }

    public static double convertToDouble(String input) {
        if (input == null || input.isEmpty()) {
            return 0.00;
        }
        if (input.substring(0, 2).equals("-$")) {
            return (0 - convertToDouble(input.substring(2, input.length()-1)));
        }
        if (input.isEmpty()) {
            return 0.00d;
        }
        if (input.contains("---")) {
            return 0;
        }
        if (input.contains("unknown")) {
            return 0;
        }
        Pattern p = Pattern.compile("-?\\d+( \\d+)*?\\.?\\d+");
        Matcher matcher = p.matcher(input);
        if (matcher.find()) {
            input = input.substring(matcher.start(), matcher.end());
        } else {
            input = String.valueOf(StringConvertor.convertToInt(input));
        }
        input = input.replaceAll(" ", "");
        return Double.parseDouble(input);

    }



}
