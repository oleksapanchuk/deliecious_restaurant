<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>


<section class="py-0 border-bottom border-5 rounded-bottom"  style="border: rgba(166,47,57,0.97);">
    <div class="container" >
        <nav class="navbar navbar-expand-lg bg-white sticky-top p-3 navbar-light">
            <div class="container">
                <a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <img id="logo" src="img/logo_red.png" alt="logo there" style="width: 50px; height: 50px;"/>
                    <strong class="font-logo-text">Delicious Restaurant</strong>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class=" collapse navbar-collapse" id="navbarNavDropdown">

                    <ul class="navbar-nav ms-auto ">
                        <%-- home --%>
                        <li>
                            <a class="nav-link mx-2 text-uppercase active"
                               href="index.jsp">
                                <fmt:message key="home"/>
                            </a>
                        </li>

                        <%-- menu --%>
                        <c:if test="${ sessionScope.role eq null or sessionScope.role eq 'CLIENT'}">
                            <li class="nav-item">
                                <a class="nav-link mx-2 text-uppercase"
                                   aria-current="page"
                                   href="controller?action=view-menu&sort_field=p.category_id&sort_order=asc&category_filter_id=0&offset=0&records=8&cur_page=1"
                                >
                                    <fmt:message key="menu"/>
                                </a>
                            </li>
                        </c:if>

                        <%-- orders --%>
                        <c:if test="${ sessionScope.role eq 'CLIENT'}">
                            <li>
                                <a href="controller?action=view-orders-for-user&sort_field=order_date&sort_order=desc&client_id_filter=${ sessionScope.auth.getClientId() }&order_status=-1&search_field=NoNe&offset=0&records=8&cur_page=1"
                                   class="nav-link mx-2 text-uppercase">
                                    <fmt:message key="orders"/>
                                </a>
                            </li>
                        </c:if>

                        <%-- about us --%>
                        <c:if test="${ sessionScope.role eq null or sessionScope.role eq 'CLIENT'}">
                            <li>
                                <a class="nav-link mx-2 text-uppercase"
                                   href="about_us.jsp"
                                >
                                    <fmt:message key="about_us"/>
                                </a>
                            </li>
                        </c:if>

                        <%-- edit menu, users, orders --%>
                        <c:if test="${ sessionScope.role eq 'MANAGER' }">
                            <%-- edit menu --%>
                            <li>
                                <a class="nav-link mx-2 text-uppercase"
                                   href="edit_menu_manager.jsp">
                                    <fmt:message key="mng.edit.menu"/>
                                </a>
                            </li>
                            <%-- users --%>
                            <li>
                                <a class="nav-link mx-2 text-uppercase"
                                   href="controller?action=view-all-clients">
                                    <fmt:message key="mng.clients"/>
                                </a>
                            </li>
                            <%-- orders --%>
                            <li>
                                <a class="nav-link mx-2 text-uppercase"
                                   href="controller?action=view-orders-for-managers&sort_field=order_id&sort_order=des&client_id_filter=0&order_status=-1&offset=0&records=8&cur_page=1">
                                    <fmt:message key="mng.orders"/>
                                </a>
                            </li>
                        </c:if>

                    </ul>

                    <ul class="navbar-nav ms-auto mt-2 ">
                        <%-- switch-language --%>
                        <li class="nav-item">
                            <form method="post">
                                <select name="locale" class="form-select" onchange='submit();' id="switch-lang"
                                        style="font-size: 14px; font-weight: 500;">
                                    <option value="en" ${sessionScope.locale eq 'en' ? 'selected' : ''}>
                                        <fmt:message key="en"/>
                                    </option>
                                    <option value="uk_UA" ${sessionScope.locale eq 'uk_UA' ? 'selected' : ''}>
                                        <fmt:message key="ua"/>
                                    </option>
                                </select>
                                <label for="switch-lang"></label>
                            </form>
                        </li>

                        <%-- cart --%>
                        <c:if test="${ sessionScope.role eq null or sessionScope.role eq 'CLIENT'}">
                            <li class="nav-item">
                                <a class="nav-link mx-2 text-uppercase"
                                   href="controller?action=view-cart">
                                    <i class="fa-solid fa-cart-shopping me-1"></i> <fmt:message key="cart"/> <span
                                        class="badge px-2 "
                                        style="opacity: .6; background-color: rgba(81,1,0,0.78);">${ cart_list.size() }</span></a>
                            </li>
                        </c:if>

                        <%-- account --%>
                        <li class="nav-item">

                            <c:choose>
                                <c:when test="${ sessionScope.auth != null }">
                                    <a class="nav-link mx-2 text-uppercase" href="client_account.jsp">
                                        <i class="fa-solid fa-circle-user me-1"></i> <fmt:message key="account"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a class="nav-link mx-2 text-uppercase" href="login.jsp">
                                        <i class="fa-solid fa-right-to-bracket me-1"></i> <fmt:message key="log.in"/>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
</section>


