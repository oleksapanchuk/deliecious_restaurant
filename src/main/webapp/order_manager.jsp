<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="mng.orders"/></title>
</head>
<body>

<jsp:include page="includes/header.jsp"/>

<jsp:include page="fragments/find_order_section.jsp"/>

<c:if test="${ sessionScope.order_list.size() == 0 }">
    <section class="vh-200 section-style py-2">
        <div class="container h-100 container-style" style="min-height: 400px;">
            <div class="row my-2">

                <span class="h1 bold">No orders there</span>

            </div>
        </div>
    </section>
</c:if>

<section class="vh-200 section-style">
    <div class="container h-100 container-style" style="min-height: 400px;">
        <div class="row my-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col" class="text-center"><fmt:message key="order.id"/></th>
                    <th scope="col"><fmt:message key="order.date"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.total.price"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.status"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.details"/></th>
                    <th scope="col" class="text-center"><fmt:message key="order.liked"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="order" items="${ sessionScope.order_list }">
                    <tr>
                        <td class="text-center"><c:out value="${ order.getOrderId() }"/></td>
                        <td><c:out value="${ order.getDate() }"/></td>
                        <td><c:out value="${ order.getOrderTotalPrice() }"/> <fmt:message key="currency"/></td>
                        <td class="text-center">
                            <form method="post" action="controller">
                                <input type="hidden" name="action" value="change_order_status">
                                <input type="hidden" name="order-id" value="${ order.getOrderId() }">

                                <select name="order_status" class="form-select" onchange='submit();' id="switch-lang"
                                        style="font-size: 14px; font-weight: 500;">
                                    <option value="0" ${order.getStatusId() == 0 ? 'selected' : ''}>
                                        <fmt:message key="order.status.canceled"/>
                                    </option>
                                    <option value="1" ${order.getStatusId() == 1 ? 'selected' : ''}>
                                        <fmt:message key="order.status.ordered"/>
                                    </option>
                                    <option value="2" ${order.getStatusId() == 2 ? 'selected' : ''}>
                                        <fmt:message key="order.status.preparing"/>
                                    </option>
                                    <option value="3" ${order.getStatusId() == 3 ? 'selected' : ''}>
                                        <fmt:message key="order.status.delivering"/>
                                    </option>
                                    <option value="4" ${order.getStatusId() == 4 ? 'selected' : ''}>
                                        <fmt:message key="order.status.delivered"/>
                                    </option>
                                </select>
                                <label for="switch-lang"></label>
                            </form>
                        </td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="#"><fmt:message key="order.show.more"/></a>
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${ order.isOrderLiked() }">
                                    <i class="fa-solid fa-heart"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="fa-regular fa-heart"></i>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</section>


<c:set var="href" scope="request" value="controller?action=view-orders-for-managers&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&"/>
<jsp:include page="includes/pagination.jsp"/>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
