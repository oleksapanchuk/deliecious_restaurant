package ua.deliciousrestaurant.exception.se;

import ua.deliciousrestaurant.exception.ServiceException;

import static ua.deliciousrestaurant.constant.ExceptionConstant.ENTER_WRONG_PASSWORD_FORMAT;

public class EnterWrongPasswordFormatException extends ServiceException {
    public EnterWrongPasswordFormatException() { super(ENTER_WRONG_PASSWORD_FORMAT); }
}
