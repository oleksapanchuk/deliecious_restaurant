package ua.deliciousrestaurant.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRequest extends HttpServletRequestWrapper {

    private final Map<String, Object> attributes = new HashMap<>();
    private final List<Cookie> cookies = new ArrayList<>();
    private final HttpSession session = new MySession();
    private String encoding;

    public MyRequest(HttpServletRequest request) {
        super(request);
    }

    public void addCookie(String name, String value){
        cookies.add(new Cookie(name, value));
    }

    @Override
    public void setAttribute(String name, Object object) {
        attributes.put(name, object);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public HttpSession getSession() {
        return session;
    }

    @Override
    public HttpSession getSession(boolean create) {
        return session;
    }

    @Override
    public String getCharacterEncoding() {
        return encoding;
    }

    @Override
    public void setCharacterEncoding(String enc) {
        encoding = enc;
    }

    @Override
    public Cookie[] getCookies() {
        return cookies.isEmpty() ? null : cookies.toArray(Cookie[]::new);
    }

}
