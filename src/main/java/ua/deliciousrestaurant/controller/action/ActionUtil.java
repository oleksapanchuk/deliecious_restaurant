package ua.deliciousrestaurant.controller.action;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

import static ua.deliciousrestaurant.constant.ActionConstant.ACTION;
import static ua.deliciousrestaurant.constant.ActionConstant.PAGE_CONTROLLER;

public class ActionUtil {

    public static boolean isGetMethod(HttpServletRequest request) {
        return request.getMethod().equals("GET");
    }

    public static void setValueToRequestFromSession(HttpServletRequest request, String attributeName) {
        Object obj = request.getSession().getAttribute(attributeName);
        if (obj != null) {
            request.setAttribute(attributeName, obj);
            request.getSession().removeAttribute(attributeName);
        }
    }


    public static String getActionToRedirect(String action, String... parameters) {
        String base = PAGE_CONTROLLER + "?" + ACTION + "=" + action;
        StringJoiner stringJoiner = new StringJoiner("&", "&", "").setEmptyValue("");
        for (int i = 0; i < parameters.length; i+=2) {
            stringJoiner.add(parameters[i] + "=" + parameters[i + 1]);
        }
        return base + stringJoiner;
    }

    public static String redirect(HttpServletRequest request) {
        String [] url = request.getHeader("referer").split("Delicious_Restaurant/");
        return url[1];
    }

}
