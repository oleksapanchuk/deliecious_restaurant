package ua.deliciousrestaurant.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static ua.deliciousrestaurant.constant.ActionConstant.LOCALE;

public class LocaleFilter extends HttpFilter {
    private String defaultLocale;

    @Override
    public void init(FilterConfig config) {
        defaultLocale = "en";
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String locale = request.getParameter(LOCALE);

        if (locale != null && !locale.isEmpty()) {
            request.getSession().setAttribute(LOCALE, locale);
            response.addCookie(new Cookie(LOCALE, locale));
            response.sendRedirect(request.getHeader("referer"));
        } else {
            request.getSession().setAttribute(LOCALE, getCookiesLocale(request));
            chain.doFilter(request, response);
        }
    }

    private String getCookiesLocale(HttpServletRequest request) {
        return Stream.ofNullable(request.getCookies())
                .flatMap(Arrays::stream)
                .filter(cookie -> cookie.getName().equals(LOCALE))
                .map(Cookie::getValue)
                .findAny().orElse(defaultLocale);
    }

}