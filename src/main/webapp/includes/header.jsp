<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>


<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

                <%-- logo --%>
                <li>
                    <a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                        <img id="logo" src="img/logo_yellow.png" alt="logo there"/>
                    </a>
                </li>
                <%-- name --%>
                <li>
                    <a href="index.jsp" class="nav-link px-2 font-logo-text">
                        Delicious Restaurant
                    </a>
                </li>
                <%-- home --%>
                <li>
                    <a href="index.jsp" class="nav-link px-2 menu-item">
                        <fmt:message key="home"/>
                    </a>
                </li>

                <%-- orders --%>
                <c:if test="${ sessionScope.role eq 'CLIENT'}">
                    <li>
                        <a href="controller?action=view-orders-for-user&sort_field=order_id&sort_order=des&client_id_filter=${ sessionScope.auth.getClientId() }&order_status=-1&offset=0&records=8&cur_page=1"
                           class="nav-link px-2 menu-item">
                            <fmt:message key="orders"/>
                        </a>
                    </li>
                </c:if>

                <%-- menu, cart, about us --%>
                <c:if test="${ sessionScope.role eq null or sessionScope.role eq 'CLIENT'}">
                    <%-- menu --%>
                    <li>
                        <a href="controller?action=view-menu&sort_field=p.category_id&sort_order=asc&category_filter_id=0&offset=0&records=8&cur_page=1"
                           class="nav-link menu-item px-2 ">
                            <fmt:message key="menu"/>
                        </a>
                    </li>
                    <%-- cart --%>
                    <li>
                        <a href="controller?action=view-cart" class="nav-link menu-item px-2">
                            <fmt:message key="cart"/>
                            <span class="badge btn-warning px-2">${ cart_list.size() }</span>
                        </a>
                    </li>
                    <%-- about us --%>
                    <li>
                        <a href="about_us.jsp" class="nav-link menu-item px-2 ">
                            <fmt:message key="about_us"/>
                        </a>
                    </li>
                </c:if>

                <%-- edit menu, users, orders --%>
                <c:if test="${ sessionScope.role eq 'MANAGER' }">
                    <%-- edit menu --%>
                    <li>
                        <a href="edit_menu_manager.jsp" class="nav-link menu-item px-0">
                            <fmt:message key="mng.edit.menu"/>
                        </a>
                    </li>
                    <%-- users --%>
                    <li>
                        <a href="controller?action=view-all-clients" class="nav-link menu-item  px-0">
                            <fmt:message key="mng.clients"/>
                        </a>
                    </li>
                    <%-- orders --%>
                    <li>
                        <a
                                class="nav-link menu-item px-0"
                                href="controller?action=view-orders-for-managers&sort_field=order_id&sort_order=des&client_id_filter=0&order_status=-1&offset=0&records=8&cur_page=1"
                        >
                            <fmt:message key="mng.orders"/>
                        </a>
                    </li>
                </c:if>

                <li>
                    <form method="POST" class="d-flex mt-2 lang-switch">
                        <label>
                            <select name="locale" class="form-select" onchange='submit();'>
                                <option value="en" ${sessionScope.locale eq 'en' ? 'selected' : ''}>
                                    <fmt:message key="en"/>
                                </option>
                                <option value="uk_UA" ${sessionScope.locale eq 'uk_UA' ? 'selected' : ''}>
                                    <fmt:message key="ua"/>
                                </option>
                            </select>
                        </label>
                    </form>
                </li>

                <c:choose>
                    <c:when test="${ sessionScope.auth != null }">
                        <li class="row px-4">
                            <a class="nav-link col-5" href="controller?action=view-client-account">
                                <i class="fa-regular fa-circle-user h1 text-white"></i>
                            </a>
                            <button type="button" class="btn btn-warning pt-2 col-7">
                                <a class="nav-link a_btn-2" href="controller?action=log_out">
                                    <fmt:message key="log.out"/>
                                </a>
                            </button>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <button type="button" class="btn btn-outline-light me-2  pt-2">
                                <a class="nav-link a_btn-1" href="login.jsp">
                                    <fmt:message key="log.in"/>
                                </a>
                            </button>
                        </li>
                        <li>
                            <button type="button" class="btn btn-warning  pt-2">
                                <a class="nav-link a_btn-2" href="sign_up.jsp">
                                    <fmt:message key="sign.up"/>
                                </a>
                            </button>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>

        </div>
    </div>
</header>
