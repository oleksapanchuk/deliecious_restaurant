<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="cart"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<c:if test="${ sessionScope.cart_list.size() <= 0 || sessionScope.cart_list == null }">
    <section class="vh-200" style="background-color: #eee;">
        <div class="container h-100 justify-content-center container-style">
            <p class="col-12 text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4" style="padding: 50px 0;">
                <fmt:message key="cart.not.selected"/>
            </p>
            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4" style="padding: 50px 0;">
                <a type="button" class="btn btn-danger btn-lg"
                   href="controller?action=view-menu&sort_field=p.category_id&sort_order=asc&category_filter_id=0&offset=0&records=8&cur_page=1">
                    <fmt:message key="menu"/>
                </a>
            </div>
        </div>
    </section>
</c:if>

<c:if test="${ requestScope.cart_items.size() > 0}">

    <section class="vh-200 section-style" style="padding: 10px 0;">

        <div class="container h-100 container-style my-3">

            <div class="row d-flex justify-content-end my-3 px-2">

                <jsp:include page="fragments/buy_all.jsp"/>

            </div>

        </div>
    </section>

    <section class="vh-200 section-style p-1">
        <div class="container h-100 container-style" style="min-height: 400px;">
            <div class="row my-2">

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"><fmt:message key="cart.name"/></th>
                        <th scope="col"><fmt:message key="cart.category"/></th>
                        <th scope="col"><fmt:message key="cart.price"/></th>
                        <th scope="col" class="text-center"><fmt:message key="cart.quantity"/></th>
                        <th scope="col" class="text-center"><fmt:message key="cart.total.price"/></th>
                        <th scope="col" class="text-center"><fmt:message key="cart.buy.now"/></th>
                        <th scope="col" class="text-center"><fmt:message key="cart.cancel"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="cart" items="${ requestScope.cart_items }">
                        <tr>
                            <td><img class="card-img-to rounded-circle" src="${ cart.getProduct().getImgProduct() }"
                                     alt="Card image cap" style="height:50px;width: 50px"></td>
                            <td><c:out value="${ cart.getProduct().getNameProduct() }"/></td>
                            <td><c:out value="${ cart.getProduct().getCategoryName() }"/></td>
                            <td><c:out value="${ cart.getProduct().getCostProduct() }"/> <fmt:message
                                    key="currency"/></td>
                            <td>
                                <form action="order-all" method="post" class="form-inline">
                                    <input type="hidden" name="id" value="${ cart.getQuantity() }" class="form-input">
                                    <div class="form-group d-flex justify-content-center">
                                        <a class="btn btn-decre"
                                           href="controller?action=inc-dec-quantity&operation=dec&prod_id=${ cart.getProduct().getIdProduct() }"><i
                                                class="fas fa-minus-square"></i></a>
                                        <input type="text" name="quantity" class="form-control w-75 text-center"
                                               value="${ cart.getQuantity() }" readonly>
                                        <a class="btn btn-incre"
                                           href="controller?action=inc-dec-quantity&operation=inc&prod_id=${ cart.getProduct().getIdProduct() }"><i
                                                class="fas fa-plus-square"></i></a>
                                    </div>
                                </form>
                            </td>

                            <td class="text-center"><c:out
                                    value="${ cart.getProduct().getCostProduct() * cart.getQuantity() }"/> <fmt:message
                                    key="currency"/>
                            </td>
                            <td class="text-center">

                                <!-- Button trigger modal -->
                                <c:choose>
                                    <c:when test="${ sessionScope.auth == null }">
                                        <a href="login.jsp"
                                           class="nav-link col-3 mx-2 text-uppercase text-center my-btn-red"
                                        >
                                            <fmt:message key="cart.buy"/>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a type="button"
                                           class="nav-link col-3 mx-2 text-uppercase my-btn-red"
                                           data-bs-toggle="modal"
                                           data-bs-target="#buyNowModal">
                                            <fmt:message key="cart.buy"/>
                                        </a>
                                    </c:otherwise>
                                </c:choose>

                                <!-- Modal -->
                                <div class="modal fade" id="buyNowModal" tabindex="-1"
                                     aria-labelledby="buyModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <form method="post" action="controller">
                                                <input type="hidden" name="action" value="order-now">
                                                <input type="hidden" name="prod_id"
                                                       value="${ cart.getProduct().getIdProduct() }">
                                                <input type="hidden" name="quantity" value="${ cart.getQuantity() }">

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
                                                                <h5 class="text-muted">${ cart.getProduct().getCostProduct() * cart.getQuantity() }
                                                                    <fmt:message key="currency"/></h5>
                                                            </div>
                                                        </div>
                                                        <div class="col-6">
                                                            <div class="row">
                                                                <h6><fmt:message key="account.balance"/></h6>
                                                            </div>
                                                            <div class="row">
                                                                <h5 class="text-muted">${ sessionScope.auth.getWalletBalance() }
                                                                    <fmt:message key="currency"/></h5>
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
                                                    <c:if test="${ cart.getProduct().getCostProduct() * cart.getQuantity()  > sessionScope.auth.getWalletBalance() }">
                                                        <a href="client_account.jsp"
                                                           class="btn btn-success btn-lg">
                                                            <fmt:message key="account.btn.add.funds"/>
                                                        </a>
                                                    </c:if>
                                                    <button type="submit"
                                                            class="btn ${ cart.getProduct().getCostProduct() * cart.getQuantity()  > sessionScope.auth.getWalletBalance() ? 'btn-secondary btn-lg disabled' : 'btn-grad-green-01'}"
                                                            data-bs-dismiss="modal">
                                                        <fmt:message key="cart.buy"/>
                                                    </button>
                                                </div>

                                            </form>
                                        </div>
                                    </div>
                                </div>


                            </td>
                            <td class="text-center">
                                <a class="btn btn-danger"
                                   href="controller?action=remove-from-cart&prod_id=${ cart.getProduct().getIdProduct() }">
                                    <fmt:message key="cart.remove"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
        </div>
    </section>

</c:if>

<jsp:include page="includes/footer.jsp"/>

<%--todo--%>
<input type="hidden" id="status" value="${ requestScope.status }">
<input type="hidden" id="locale" value="${ sessionScope.locale }">
<c:set var="status" value="none" scope="request"/>
<script type="text/javascript">
    var status = document.getElementById("status").value;
    var locale = document.getElementById("locale").value;

    if (status === "order_failed") {
        if (locale == null || locale === "en") {
            swal("Error", "Order not created!", "error");
        } else {
            swal("Помилка", "Замовлення не створено!", "error");
        }
    }

</script>
<%--todo--%>

</body>
</html>
