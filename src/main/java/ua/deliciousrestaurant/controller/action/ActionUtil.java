package ua.deliciousrestaurant.controller.action;

import java.util.StringJoiner;

import static ua.deliciousrestaurant.constant.ActionConstant.ACTION;
import static ua.deliciousrestaurant.constant.ActionConstant.CONTROLLER_PAGE;

public class ActionUtil {


    public static String getActionToRedirect(String action, String... parameters) {
        String base = CONTROLLER_PAGE + "?" + ACTION + "=" + action;
        StringJoiner stringJoiner = new StringJoiner("&", "&", "").setEmptyValue("");
        for (int i = 0; i < parameters.length; i+=2) {
            stringJoiner.add(parameters[i] + "=" + parameters[i + 1]);
        }
        return base + stringJoiner;
    }

}
