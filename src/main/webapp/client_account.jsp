<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="mng.clients"/></title>
</head>
<body>

<jsp:include page="includes/header.jsp"/>

<section class="h-75 section-style">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-lg-11 mb-4 mb-lg-0">
                <div class="card mb-3" style="border-radius: .5rem;">
                    <div class="row g-0">

                        <div class="col-md-4 gradient-custom text-center text-white"
                             style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="Avatar" class="img-fluid my-5" style="width: 80px;"/>
                            <h5>${ sessionScope.auth.getFirstName() } ${ sessionScope.auth.getLastName() }</h5>
                            <c:choose>
                                <c:when test="${ sessionScope.auth.getRole() == 'MANAGER'}">
                                    <p class="text-uppercase"><fmt:message key="role.manager"/></p>
                                </c:when>
                                <c:otherwise>
                                    <p class="text-uppercase"><fmt:message key="role.client"/></p>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="col-md-8">
                            <div class="card-body p-4">
                                <h6><fmt:message key="account.information"/></h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6><fmt:message key="account.email"/></h6>
                                        <p class="text-muted">${ sessionScope.auth.getEmail() }</p>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6><fmt:message key="account.phone"/></h6>
                                        <p class="text-muted">
                                            <c:choose>
                                                <c:when test="${ sessionScope.auth.getPhoneNum() == null || sessionScope.auth.getPhoneNum() == '' }">
                                                    <fmt:message key="account.absent.info"/>
                                                </c:when>
                                                <c:otherwise>
                                                    ${ sessionScope.auth.getPhoneNum() }
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                </div>
                                <div class="row pt-1">
                                    <div class="col-12 mb-3">
                                        <h6><fmt:message key="account.address"/></h6>
                                        <p class="text-muted">${ sessionScope.auth.getAddress() }</p>
                                    </div>
                                </div>

                                <%-- balance and statistics --%>
                                <c:if test="${ sessionScope.role == 'CLIENT' }">
                                    <hr class="mt-0 mb-4">
                                    <div class="row pt-1">
                                        <div class="col-4 mb-3">
                                            <h6><fmt:message key="account.balance"/></h6>
                                            <p class="text-muted">100 <fmt:message key="currency"/></p>
                                        </div>
                                        <div class="col-4 mb-3">
                                            <h6><fmt:message key="account.total.orders"/></h6>
                                            <p class="text-muted">7</p>
                                        </div>
                                        <div class="col-4 mb-3">
                                            <h6><fmt:message key="account.total.funds.spent"/></h6>
                                            <p class="text-muted">777 <fmt:message key="currency"/></p>
                                        </div>
                                    </div>
                                </c:if>

                                <%-- logout button --%>
                                <div class="row">
                                    <div class="col-4 d-flex justify-content-start mt-4">
                                        <a href="#"
                                           class="nav-link mx-2 text-uppercase my-btn-red">
                                            <i class="fa-solid fa-money-bill me-1"></i></i><fmt:message key="account.btn.add.funds"/>
                                        </a>
                                    </div>
                                    <div class="col-5 d-flex justify-content-center mt-4">
                                        <a href="#"
                                           class="nav-link mx-2 text-uppercase my-btn-red">
                                            <i class="far fa-edit me-1"></i><fmt:message key="account.btn.edit.profile"/>
                                        </a>
                                    </div>
                                    <div class="col-3 d-flex justify-content-end mt-4">
                                        <a href="controller?action=log_out"
                                           class="nav-link mx-2 text-uppercase my-btn-red">
                                            <i class="fa-solid fa-right-from-bracket me-1"></i><fmt:message key="account.btn.logout"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>

