package ua.deliciousrestaurant.constant;

import java.util.regex.Pattern;

public final class RegexConstant {

    public static final Pattern VALID_FIRST_NAME = Pattern.compile("[A-ZІА-Я][a-zіа-я]*");
    public static final Pattern VALID_LAST_NAME = Pattern.compile("[a-zа-яіІА-ЯA-Z]+([ '-][a-zа-яіА-ЯA-Z]+)*");
    public static final Pattern VALID_EMAIL = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.\\-]+@[a-zA-Z0-9.\\-]+$");
    public static final String VALID_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";

}
