<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="sign.up"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<section class="vh-200" style="background-color: #eee;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message key="sign.up"/></p>

                                <form method="post" action="controller" class="mx-1 mx-md-4">
                                    <input type="hidden" name="action" value="sign-up">

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fa-regular fa-user fa-lg me-3 fa-fw  mb-4"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="inputFirstName" name="fname" class="form-control" />
                                            <label class="form-label" for="inputFirstName"><fmt:message key="first.name"/></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw mb-4"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" id="inputLastName" name="lname" class="form-control" />
                                            <label class="form-label" for="inputLastName"><fmt:message key="last.name"/></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw  mb-4"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="email" id="inputEmail" name="email" class="form-control" />
                                            <label class="form-label" for="inputEmail"><fmt:message key="email.address"/></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw  mb-4"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="inputPassword" name="pass" class="form-control" />
                                            <label class="form-label" for="inputPassword"><fmt:message key="password"/></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw  mb-4"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="inputPasswordAgain" name="pass-again" class="form-control" />
                                            <label class="form-label" for="inputPasswordAgain"><fmt:message key="sing_up.repeat.pass" /></label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fa-solid fa-location-pin fa-lg me-3 fa-fw  mb-4"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" id="inputAddress" name="address" class="form-control" />
                                            <label class="form-label" for="inputAddress"><fmt:message key="address" /></label>
                                        </div>
                                    </div>


                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-danger btn-lg"><fmt:message key="sign.up"/></button>
                                    </div>

                                    <p class="text-center text-muted mt-5 mb-0">
                                        <fmt:message key="sign_up.already.login" />
                                        <a href="login.jsp" class="fw-bold text-body">
                                            <u>
                                                <fmt:message key="sign_up.login.here" />
                                            </u>
                                        </a>
                                    </p>

                                </form>

                            </div>
                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                     class="img-fluid" alt="Sample image">

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
