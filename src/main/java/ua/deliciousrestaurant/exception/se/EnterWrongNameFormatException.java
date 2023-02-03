package ua.deliciousrestaurant.exception.se;

import ua.deliciousrestaurant.exception.ServiceException;

import static ua.deliciousrestaurant.constant.ExceptionConstant.ENTER_WRONG_NAME_FORMAT;

public class EnterWrongNameFormatException extends ServiceException {
    public EnterWrongNameFormatException() { super(ENTER_WRONG_NAME_FORMAT); }
}
