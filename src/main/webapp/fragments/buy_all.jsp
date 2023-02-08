<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!-- Button trigger modal -->
<h4 class="col-5 text-end" style="margin: 10px; padding: 15px 45px;">
    <fmt:message key="cart.total.price.top"/>${ requestScope.totalPrice > 0 ? requestScope.totalPrice : 0 }
    <fmt:message key="currency"/>
</h4>
<c:choose>
    <c:when test="${ sessionScope.auth == null }">
        <a href="login.jsp"
           class="nav-link col-3 mx-2 text-uppercase btn-grad-red"
        >
            <fmt:message key="cart.check.out"/>
        </a>
    </c:when>
    <c:otherwise>
        <a type="button"
           class="nav-link col-3 mx-2 text-uppercase btn-grad-red"
           data-bs-toggle="modal"
           data-bs-target="#buyModal">
            <fmt:message key="cart.check.out"/>
        </a>
    </c:otherwise>
</c:choose>

<!-- Modal -->
<div class="modal fade" id="buyModal" tabindex="-1"
     aria-labelledby="buyModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <form method="post" action="controller">
                <input type="hidden" name="action" value="order-all">
                <input type="hidden" name="total_price" value="${ requestScope.totalPrice }">

                <div class="modal-header">
                    <h5 class="modal-title" id="buyModalLabel">
                        <fmt:message key="cart.order.details"/>
                    </h5>
                    <button type="button" class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close">
                    </button>
                </div>

                <div class="modal-body">

                    <div class="row d-flex justify-content-start">
                        <div class="col-6">
                            <div class="row">
                                <h6><fmt:message key="cart.total.amount.of.due"/></h6>
                            </div>
                            <div class="row">
                                <h5 class="text-muted">${ requestScope.totalPrice} <fmt:message key="currency"/></h5>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="row">
                                <h6><fmt:message key="account.balance"/></h6>
                            </div>
                            <div class="row">
                                <h5 class="text-muted">${ sessionScope.auth.getWalletBalance() } <fmt:message key="currency"/></h5>
                            </div>
                        </div>
                    </div>

                    <div class="row input-group mb-3">
                        <label class="form-label" for="addressField">
                            <fmt:message key="cart.input.address.delivery"/>
                        </label>
                        <input type="text"
                               name="address_buy"
                               id="addressField"
                               class="form-control mx-2"
                               aria-label="Amount (to the nearest dollar)">
                    </div>

                </div>

                <%-- buttons --%>
                <div class="modal-footer">
                    <c:if test="${ requestScope.totalPrice > sessionScope.auth.getWalletBalance() }">
                        <a href="client_account.jsp"
                           class="btn btn-success btn-lg">
                            <fmt:message key="account.btn.add.funds"/>
                        </a>
                    </c:if>
                    <button type="submit"
                            class="btn ${ requestScope.totalPrice > sessionScope.auth.getWalletBalance() ? 'btn-secondary btn-lg disabled' : 'btn-grad-green-01'}"
                            data-bs-dismiss="modal">
                        <fmt:message key="cart.buy"/>
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

