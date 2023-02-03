<%@ page import="ua.deliciousrestaurant.model.entity.Client" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale" value="${not empty sessionScope.locale ? sessionScope.locale : 'ua'}" scope="session" />
<fmt:setLocale value="${ sessionScope.locale }" scope="session"/>
<fmt:setBundle basename="resources"/>



<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>

    <title><fmt:message key="log.in"/></title>
</head>

<body>

    <jsp:include page="includes/header.jsp"/>

    <section class="vh-200" style="background-color: #eee;">
        <div class="container py-2 pb-2 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                    <div class="card" style="border-radius: 1rem;">
                        <div class="row g-0">
                            <div class="col-md-6 col-lg-5 d-none d-md-block">
                                <img src="https://media.architecturaldigest.com/photos/5e5e792867fb9a0008f2cf2e/master/w_1600%2Cc_limit/RedHerring5.jpg"
                                     alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                            </div>
                            <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                <div class="card-body p-4 p-lg-5 text-black">

                                    <form method="post" action="controller">
                                        <input type="hidden" name="action" value="log-in">

                                        <div class="d-flex align-items-center mb-3 pb-1">
                                            <img id="logo" src="img/logo_red.png" alt="logo there"/>
                                            <span class="h1 fw-bold mb-0">
                                                Delicious Restaurant
                                            </span>
                                        </div>

                                        <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">
                                            <fmt:message key="sign_in.in.acc" />
                                        </h5>

                                        <div class="form-outline mb-4">
                                            <input type="email" id="inputLogin" name="login_email" class="form-control form-control-lg" />
                                            <label class="form-label" for="inputLogin">
                                                <fmt:message key="email.address" />
                                            </label>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="password" id="inputPassword" name="login_password" class="form-control form-control-lg" />
                                            <label class="form-label" for="inputPassword">
                                                <fmt:message key="password" />
                                            </label>
                                        </div>

                                        <div class="pt-1 mb-4">
                                            <button type="submit" class="btn btn-danger btn-lg btn-block" type="button">
                                                <fmt:message key="log.in" />
                                            </button>
                                        </div>

<%--                                        //todo --%>
                                        <a class="small text-muted" href="#">
                                            <fmt:message key="sign_in.forgot.pass" />
                                        </a>
                                        <p class="mb-5 pb-lg-2" style="color: #393f81;">
                                            <fmt:message key="sign_in.havent.acc" />
                                            <a href="sign_up.jsp" style="color: #393f81;">
                                                <fmt:message key="sign_in.reg.here" />
                                            </a>
                                        </p>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="includes/footer.jsp"/>

    <c:set var="error_status" scope="request" value="error"/>
    <input type="hidden" id="error_status" value="${ requestScope.error_status }">

<script type="text/javascript">
    var status = document.getElementById(status).value;
    if (status === "success") {
        swal("Congrats", "Account created", "success");
    } else {
        swal("Error", "Something went wrong!", "error");
    }

</script>

</body>
</html>