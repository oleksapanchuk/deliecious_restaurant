package ua.deliciousrestaurant.utils;

import java.util.regex.Pattern;

import static ua.deliciousrestaurant.constant.RegexConstant.VALID_EMAIL;

public final class ValidationUtil {

    private static boolean validator(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    public static boolean validEmail(String email) {
        return validator(email, VALID_EMAIL);
    }


    public static boolean isInt(String str) {
        try {
            int number = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
