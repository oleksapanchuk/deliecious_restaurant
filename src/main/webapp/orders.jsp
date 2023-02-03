<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="orders"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<section class="section-style py-2">
    <div class="container container-style py-1">
        <div class="row px-2">
            <div class="col-11 border border-2 border-danger rounded-3 mx-4 px-0 my-3">
                <form method="get" action="controller">
                    <input type="hidden" name="action" value="view-orders-for-user">
                    <input type="hidden" name="sort_field" value="${ sessionScope.sort_field }">
                    <input type="hidden" name="sort_order" value="${ sessionScope.sort_order }">
                    <input type="hidden" name="client_id_filter" value="${ sessionScope.client_id_filter }">
                    <input type="hidden" name="order_status" value="${ sessionScope.order_status }">
                    <input type="hidden" name="offset" value="0">
                    <input type="hidden" name="records" value="8">
                    <input type="hidden" name="cur_page" value="1">

                    <div class="input-group">
                        <input type="search" name="search_field" class="col-9 form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                        <button type="submit" class="col-3 btn btn-outline-danger">
                            <fmt:message key="search" />
                        </button>
                    </div>

                </form>
            </div>
        </div>

        <div class="row px-2 py-2">

            <div class="col-4  border border-2 border-danger rounded-3 mx-4 px-0" >
                <div class="dropdown">
                    <button class=" col-6 btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton1"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        Status
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li>
                            <a
                                    class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                                    href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=-1&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="menu.category.all.upcase"/>
                            </a>
                        </li>
                        <li>
                            <a
                                    class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                                    href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=0&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                canceled
                            </a>
                        </li>
                        <li>
                            <a
                                    class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                                    href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=1&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                ordered
                            </a>
                        </li>
                        <li>
                            <a
                                    class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                                    href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=2&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                preparing
                            </a>
                        </li>
                        <li>
                            <a
                                    class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                                    href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=3&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                delivering
                            </a>
                        </li>
                        <li>
                            <a
                                    class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                                    href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=4&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                delivered
                            </a>
                        </li>
                    </ul>

                    <label for="orderSelectButton" class="col-5 text-danger text-center">
                        <c:choose>
                            <c:when test="${ sessionScope.status_id == -1 }">
                                <fmt:message key="menu.category.all.lowcase" />
                            </c:when>
                            <c:when test="${ sessionScope.status_id == 0 }">
                                canceled
                            </c:when>
                            <c:when test="${ sessionScope.status_id == 1 }">
                                ordered
                            </c:when>
                            <c:when test="${ sessionScope.status_id == 2 }">
                                preparing
                            </c:when>
                            <c:when test="${ sessionScope.status_id == 3 }">
                                delivering
                            </c:when>
                            <c:otherwise>
                                delivered
                            </c:otherwise>
                        </c:choose>
                    </label>

                </div>
            </div>

            <div class="col-3 border border-2 border-danger rounded-3 mx-4 px-0">
                <div class="dropdown">
                    <button class="col-6 btn btn-danger dropdown-toggle" type="button" id="selectSortFieldForOrders"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="menu.sort.by"/>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'order_date' ? 'text-danger' : '' }"
                               href="controller?action=view-orders-for-user&sort_field=order_date&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                sort by date
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'status_id' ? 'text-danger' : '' }"
                               href="controller?action=view-orders-for-user&sort_field=status_id&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                status
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'order_liked' ? 'text-danger' : '' }"
                               href="controller?action=view-orders-for-user&sort_field=order_liked&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                liked
                            </a>
                        </li>
                    </ul>
                    <label for="selectSortFieldForOrders" class="col-5 text-danger text-center">
                        <c:choose>
                            <c:when test="${ sessionScope.sort_field == 'order_date' }">
                                sort by date
                            </c:when>
                            <c:when test="${ sessionScope.sort_field == 'status_id' }">
                                status
                            </c:when>
                            <c:otherwise>
                                liked
                            </c:otherwise>
                        </c:choose>
                    </label>
                </div>
            </div>

            <div class="col-3 border border-2 border-danger rounded-3 mx-4 px-0">
                <div class="dropdown">
                    <button class="col-6 btn btn-danger dropdown-toggle" type="button" id="orderSelectButton"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="menu.order.by"/>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="orderSelectButton">
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_order && sessionScope.sort_order  == 'asc' ? 'text-danger' : '' }"
                               href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=asc&category_filter_id=${ sessionScope.category_filter_id }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="menu.asc"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_order && sessionScope.sort_order  == 'desc' ? 'text-danger' : '' }"
                               href="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=desc&category_filter_id=${ sessionScope.category_filter_id }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="menu.des"/>
                            </a>
                        </li>
                    </ul>
                    <label for="orderSelectButton" class="col-5 text-danger text-center">
                        <c:choose>
                            <c:when test="${ sessionScope.sort_order == 'asc'}">
                                <fmt:message key="menu.asc"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="menu.des"/>
                            </c:otherwise>
                        </c:choose>
                    </label>
                </div>
            </div>

        </div>
    </div>
</section>

<c:if test="${ sessionScope.order_list.size() == 0 }">
    <section class="vh-200 section-style py-2">
        <div class="container h-100 container-style" style="min-height: 400px;">
            <div class="row my-2">

                <span class="h1 bold">No orders there</span>

            </div>
        </div>
    </section>
</c:if>

<c:if test="${ sessionScope.order_list.size() > 0 }">

    <section class="vh-200 section-style py-2">
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
                            <td class="text-center"><c:out value="${ order.getOrderTotalPrice() }"/> <fmt:message
                                    key="currency"/></td>
                            <td class="text-center">
                                <c:choose>

                                    <c:when test="${ order.getStatusId() == 1 }">
                                        <div class="text-dark">
                                            <i class="far fa-dot-circle"> </i>
                                            <fmt:message key="order.status.ordered"/>
                                        </div>
                                    </c:when>

                                    <c:when test="${ order.getStatusId() == 2 }">
                                        <div class="text-info">
                                            <i class="far fa-dot-circle"> </i>
                                            <fmt:message key="order.status.preparing"/>
                                        </div>
                                    </c:when>

                                    <c:when test="${ order.getStatusId() == 3 }">
                                        <div class="text-warning">
                                            <i class="far fa-dot-circle"> </i>
                                            <fmt:message key="order.status.delivering"/>
                                        </div>
                                    </c:when>

                                    <c:when test="${ order.getStatusId() == 4 }">
                                        <div class="text-success">
                                            <i class="far fa-dot-circle"> </i>
                                            <fmt:message key="order.status.delivered"/>
                                        </div>
                                    </c:when>

                                    <c:otherwise>
                                        <div class="text-danger">
                                            <i class="far fa-dot-circle"> </i>
                                            <fmt:message key="order.status.canceled"/>
                                        </div>
                                    </c:otherwise>

                                </c:choose>

                            </td>
                            <td class="text-center">
                                <a class="btn btn-warning" href="#">
                                    <fmt:message key="order.show.more"/>
                                </a>
                            </td>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${ order.isOrderLiked() }">
                                        <a class="btn btn-danger"
                                           href="controller?action=set-like-for-order&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page }&order-id=${ order.getOrderId() }&is-liked-order=true"
                                        >
                                            <i class="fa-solid fa-heart"></i>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-secondary"
                                           href="controller?action=set-like-for-order&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page }&order-id=${ order.getOrderId() }&is-liked-order=false">
                                            <i class="fa-regular fa-heart"></i>
                                        </a>
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

    <c:set var="href" scope="request"
           value="controller?action=view-orders-for-user&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&"/>
    <jsp:include page="includes/pagination.jsp"/>

</c:if>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
