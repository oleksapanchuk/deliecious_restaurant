package ua.deliciousrestaurant.controller.action.impl.client;

import ua.deliciousrestaurant.controller.action.Action;
import ua.deliciousrestaurant.exception.ServiceException;
import ua.deliciousrestaurant.model.dto.ClientDTO;
import ua.deliciousrestaurant.service.ServiceFactory;
import ua.deliciousrestaurant.utils.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.deliciousrestaurant.constant.ActionConstant.*;

public class AddFundsAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String funds = request.getParameter(FUNDS);

        if (!ValidationUtil.isInt(funds)) {
            request.getSession().setAttribute(ERROR, "error.wrong.int.number");
            request.getSession().setAttribute(VALID_STATUS, "error.wrong.int.number");

            return PAGE_ACCOUNT;
        }

        ClientDTO clientDTO = (ClientDTO) request.getSession().getAttribute(AUTH);

        ServiceFactory.getInstance().getClientService().addFundsToWallet(
                clientDTO.getClientId(),
                Integer.parseInt(funds));


        ServiceFactory.getInstance().getClientService().updateWalletBalance(clientDTO);

        request.getSession().setAttribute(AUTH, clientDTO);

        return PAGE_ACCOUNT;
    }
}
