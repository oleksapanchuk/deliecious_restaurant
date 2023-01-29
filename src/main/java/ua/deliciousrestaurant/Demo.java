package ua.deliciousrestaurant;

import ua.deliciousrestaurant.exception.DaoException;
import ua.deliciousrestaurant.utils.PasswordCode;

public class Demo {
    public static void main(String[] args) throws DaoException {
        String str1 = "1221";
        String str2 = "123";

        System.out.println(PasswordCode.encodePassword(str1).get());

    }
}
