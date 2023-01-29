package ua.deliciousrestaurant.utils;

import ua.deliciousrestaurant.constant.RegexConstant;

import java.util.regex.Pattern;

public final class Validator {

    private static boolean validator(String text, String name, Pattern regex) {
        return regex.matcher(text).matches();
    }

    public static boolean validEmail(String email) {
        return validator(email, "email", RegexConstant.VALID_EMAIL);
    }





}
