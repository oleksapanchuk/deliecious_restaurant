package ua.deliciousrestaurant.controller;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.controller.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.deliciousrestaurant.constant.ActionConstant.ACTION;

public class Controller extends HttpServlet {
    private static final ActionFactory ACTION_FACTORY = ActionFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(process(request, response)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(process(request, response));
    }

    private String process(HttpServletRequest request, HttpServletResponse response) {
        Action action = ACTION_FACTORY.createAction(request.getParameter(ACTION));
        String path = null;
        try {
            path = action.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}