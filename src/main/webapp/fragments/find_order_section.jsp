<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<section class="section-style py-3">
    <div class="container container-style py-1">

        <%-- searching --%>
        <div class="row px-5 mb-4 mt-3">
            <div class="col-12">
                <form method="get" action="controller">
                    <input type="hidden" name="action" value="${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }">
                    <input type="hidden" name="sort_field" value="${ sessionScope.sort_field }">
                    <input type="hidden" name="sort_order" value="${ sessionScope.sort_order }">
                    <input type="hidden" name="client_id_filter" value="${ sessionScope.client_id_filter }">
                    <input type="hidden" name="order_status" value="${ sessionScope.order_status }">
                    <input type="hidden" name="offset" value="0">
                    <input type="hidden" name="records" value="8">
                    <input type="hidden" name="cur_page" value="1">

                    <div class="input-group ">
                        <input type="search"
                               name="search_field"
                               class="col-9 form-control border-danger rounded"
                               placeholder="${ sessionScope.search_field == null ? 'Order ID' : sessionScope.search_field }"
                               aria-label="Search"
                               aria-describedby="search-addon"/>
                        <button type="submit" class="col-3 btn btn-outline-danger">
                            <fmt:message key="search"/>
                        </button>
                    </div>

                </form>
            </div>
        </div>

        <%-- status, sort field, order sort --%>
        <div class="row px-5 mb-4 d-flex justify-content-start">

            <%-- status --%>
            <div class="col-4">
                <div class="dropdown">
                    <button class=" col-6 btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton1"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="order.status.only"/>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=-1&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="order.all"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=0&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <span><fmt:message key="order.status.canceled"/></span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=1&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <span><fmt:message key="order.status.ordered"/></span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=2&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <span><fmt:message key="order.status.preparing"/></span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=3&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <span><fmt:message key="order.status.delivering"/></span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=4&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <span><fmt:message key="order.status.delivered"/></span>
                            </a>
                        </li>
                    </ul>

                    <label for="orderSelectButton" class="col-5 text-danger text-center">
                        <c:choose>
                            <c:when test="${ sessionScope.order_status == -1 }">
                                <span class="text-uppercase"><fmt:message key="order.all"/></span>
                            </c:when>
                            <c:when test="${ sessionScope.order_status == 0 }">
                                <span class="text-uppercase"><fmt:message key="order.status.canceled"/></span>
                            </c:when>
                            <c:when test="${ sessionScope.order_status == 1 }">
                                <span class="text-uppercase"><fmt:message key="order.status.ordered"/></span>
                            </c:when>
                            <c:when test="${ sessionScope.order_status == 2 }">
                                <span class="text-uppercase"><fmt:message key="order.status.preparing"/></span>
                            </c:when>
                            <c:when test="${ sessionScope.order_status == 3 }">
                                <span class="text-uppercase"><fmt:message key="order.status.delivering"/></span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-uppercase"><fmt:message key="order.status.delivered"/></span>
                            </c:otherwise>
                        </c:choose>
                    </label>

                </div>
            </div>

            <%-- sort field --%>
            <div class="col-4">
                <div class="dropdown">
                    <button class="col-6 btn btn-danger dropdown-toggle" type="button" id="selectSortFieldForOrders"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="menu.sort.by"/>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'order_id' ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=order_id&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="order.sort.by.date"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'status_id' ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=status_id&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="order.status.only"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'order_liked' ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=order_liked&sort_order=${ sessionScope.sort_order }&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="order.liked"/>
                            </a>
                        </li>
                    </ul>
                    <label for="selectSortFieldForOrders" class="col-5 text-danger text-center">
                        <c:choose>
                            <c:when test="${ sessionScope.sort_field == 'order_id' }">
                                <span class="text-uppercase"><fmt:message key="order.sort.by.date"/></span>
                            </c:when>
                            <c:when test="${ sessionScope.sort_field == 'status_id' }">
                                <span class="text-uppercase"><fmt:message key="order.status.only"/></span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-uppercase"><fmt:message key="order.liked"/></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                </div>
            </div>

            <%-- order sort --%>
            <div class="col-4">
                <div class="dropdown">
                    <button class="col-6 btn btn-danger dropdown-toggle" type="button" id="orderSelectButton"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="menu.order.by"/>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="orderSelectButton">
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_order && sessionScope.sort_order  == 'asc' ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=asc&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="menu.asc"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item ${ not empty sessionScope.sort_order && sessionScope.sort_order  == 'desc' ? 'text-danger' : '' }"
                               href="controller?action=${ not empty sessionScope.role && sessionScope.role == 'CLIENT' ? 'view-orders-for-user' : 'view-orders-for-managers' }&sort_field=${ sessionScope.sort_field }&sort_order=desc&client_id_filter=${ sessionScope.client_id_filter }&order_status=${ sessionScope.order_status }&search_field=${ sessionScope.search_field }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                <fmt:message key="menu.des"/>
                            </a>
                        </li>
                    </ul>
                    <label for="orderSelectButton" class="col-5 text-danger text-center">
                        <c:choose>
                            <c:when test="${ sessionScope.sort_order == 'asc'}">
                                <span class="text-uppercase"><fmt:message key="menu.asc"/></span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-uppercase"><fmt:message key="menu.des"/></span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                </div>
            </div>

        </div>

    </div>
</section>