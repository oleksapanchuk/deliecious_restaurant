package ua.deliciousrestaurant.exception.se;

import ua.deliciousrestaurant.exception.ServiceException;

import static ua.deliciousrestaurant.constant.ExceptionConstant.PASSWORD_NOT_MATCHING;

public class PasswordNotMatchingException extends ServiceException {
    public PasswordNotMatchingException() { super(PASSWORD_NOT_MATCHING); }
}
