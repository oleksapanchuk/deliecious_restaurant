package ua.deliciousrestaurant.controller.action;

import ua.deliciousrestaurant.controller.action.impl.client.*;
import ua.deliciousrestaurant.controller.action.impl.manager.ChangeStatusAction;
import ua.deliciousrestaurant.controller.action.impl.manager.ViewClientsAction;
import ua.deliciousrestaurant.controller.action.impl.manager.ViewOrdersForManagerAction;
import ua.deliciousrestaurant.controller.action.impl.user.*;

import java.util.HashMap;
import java.util.Map;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public final class ActionFactory {

    private static ActionFactory actionFactory;
    private static Map<String, Action> actionMap = new HashMap<>();

    public static ActionFactory getInstance() {
        if (actionFactory == null) {
            actionFactory = new ActionFactory();

            actionMap.put(ACTION_DEFAULT, new DefaultAction());

            actionMap.put(ACTION_LOGIN, new LoginAction());
            actionMap.put(ACTION_SIGN_UP, new SignUpAction());
            actionMap.put(ACTION_LOGOUT, new LogoutAction());

            actionMap.put(ACTION_ADD_TO_CART, new AddToCartAction());
            actionMap.put(ACTION_ORDER_NOW, new BuyNowAction());
            actionMap.put(ACTION_REMOVE_FROM_CART, new RemoveFromCartAction());
            actionMap.put(ACTION_INC_DEC_QUANTITY, new QuantityIncDecAction());
            actionMap.put(ACTION_ORDER_ALL, new BuyAllAction());
            actionMap.put(ACTION_VIEW_MENU, new ViewMenuAction());
            actionMap.put(ACTION_VIEW_CART, new ViewCartAction());
            actionMap.put(ACTION_VIEW_ORDERS_FOR_USER, new ViewOrdersForUserAction());
            actionMap.put(ACTION_SET_LIKE_FOR_ORDER, new SetLikeForOrderAction());
            actionMap.put(ACTION_VIEW_CLIENT_ACCOUNT, new ViewClientAccountAction());
            actionMap.put(ACTION_VIEW_ALL_CLIENTS, new ViewClientsAction());
            actionMap.put(ACTION_VIEW_ORDERS_FOR_MANAGER, new ViewOrdersForManagerAction());
            actionMap.put(ACTION_CHANGE_ORDER_STATUS, new ChangeStatusAction());


        }

        return actionFactory;
    }

    private ActionFactory() { }

    public Action createAction(String action) {
        return actionMap.getOrDefault(action, new DefaultAction());
    }


}
