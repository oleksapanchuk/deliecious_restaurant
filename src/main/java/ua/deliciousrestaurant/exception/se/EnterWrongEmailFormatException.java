package ua.deliciousrestaurant.exception.se;

import ua.deliciousrestaurant.exception.ServiceException;

import static ua.deliciousrestaurant.constant.ExceptionConstant.WRONG_PASSWORD;

public class EnterWrongEmailFormatException extends ServiceException {
    public EnterWrongEmailFormatException() { super(WRONG_PASSWORD); }
}
