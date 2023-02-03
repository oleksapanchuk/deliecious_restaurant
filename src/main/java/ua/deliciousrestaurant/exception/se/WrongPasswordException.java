package ua.deliciousrestaurant.exception.se;

import ua.deliciousrestaurant.exception.ServiceException;

import static ua.deliciousrestaurant.constant.ExceptionConstant.ENTER_WRONG_EMAIL_FORMAT;

public class WrongPasswordException extends ServiceException {
    public WrongPasswordException() { super(ENTER_WRONG_EMAIL_FORMAT); }
}
