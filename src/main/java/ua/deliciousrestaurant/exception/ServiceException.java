package ua.deliciousrestaurant.exception;

public class ServiceException extends Exception {

    public ServiceException() { super(); }

    private ServiceException(String message) { super(message); }

    public ServiceException(String message, Throwable cause) { super(message, cause); }

    public ServiceException(Throwable cause) { super(cause); }

}
