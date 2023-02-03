package ua.deliciousrestaurant.exception.se;

import ua.deliciousrestaurant.exception.ServiceException;

import static ua.deliciousrestaurant.constant.ExceptionConstant.NO_SUCH_CLIENT_EXCEPTION;

public class NoSuchClientException extends ServiceException {

    public NoSuchClientException() { super(NO_SUCH_CLIENT_EXCEPTION); }

}
