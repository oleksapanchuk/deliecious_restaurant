<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>


<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <img id="logo" src="img/logo_yellow.png" alt="logo there"/>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li>
                    <a href="index.jsp" class="nav-link px-2 font-logo-text">
                        Delicious Restaurant
                    </a>
                </li>
                <li>
                    <a href="index.jsp" class="nav-link px-2 menu-item">
                        <fmt:message key="home"/>
                    </a>
                </li>
                <li>
                    <a href="controller?action=view-menu&sort-field=p.category_id&sort-order=asc&category-filter-id=0&offset=0&records=8&cur_page=1" class="nav-link px-2 menu-item">
                        <fmt:message key="menu"/>
                    </a>
                </li>

                <c:if test="${ sessionScope.role eq 'CLIENT'}">
                    <li><a href="controller?action=view-orders-for-user" class="nav-link px-2 menu-item"><fmt:message key="orders"/></a></li>
                </c:if>

                <c:if test="${ sessionScope.role eq null or sessionScope.role eq 'CLIENT'}">
                    <li><a href="controller?action=view-cart" class="nav-link px-2 menu-item"><fmt:message key="cart"/> <span class="badge btn-warning px-2">${ cart_list.size() }</span></a></li>
                    <li><a href="about_us.jsp" class="nav-link px-2 menu-item"><fmt:message key="about_us"/></a></li>
                </c:if>

                <c:if test="${ sessionScope.role eq 'MANAGER' }">
                    <li><a href="#" class="nav-link px-2 menu-item"><fmt:message key="users"/></a></li>
                    <li><a href="#" class="nav-link px-2 menu-item"><fmt:message key="orders"/></a></li>
                </c:if>

            </ul>

            <form method="POST" class="d-flex mt-1 lang-switch">
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

            <div class="text-end">
                <c:choose>
                    <c:when test="${ sessionScope.auth != null }">
                        <button type="button" class="btn btn-warning">
                            <a class="nav-link a_btn-2" href="controller?action=log_out">
                                <fmt:message key="log.out"/>
                            </a>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-outline-light me-2">
                            <a class="nav-link a_btn-1" href="login.jsp">
                                <fmt:message key="log.in"/>
                            </a>
                        </button>
                        <button type="button" class="btn btn-warning">
                            <a class="nav-link a_btn-2" href="sign_up.jsp">
                                <fmt:message key="sign.up"/>
                            </a>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>
</header>
