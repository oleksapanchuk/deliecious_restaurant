package ua.deliciousrestaurant.exception;

public class ServiceException extends Exception {

    public ServiceException() { }

    public ServiceException(String message) { super(message); }

    public ServiceException(Throwable cause) { super(cause); }

}
