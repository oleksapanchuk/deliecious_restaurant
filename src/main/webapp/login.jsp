<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale" value="${not empty sessionScope.locale ? sessionScope.locale : 'ua'}" scope="session"/>
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
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;"/>
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form method="post" class="needs-validation" action="controller" novalidate>
                                    <input type="hidden" name="action" value="log-in">

                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <img id="logo" src="img/logo_red.png" alt="logo there"/>
                                        <span class="h1 fw-bold mb-0">
                                                Delicious Restaurant
                                        </span>
                                    </div>

                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">
                                        <fmt:message key="sign_in.in.acc"/>
                                    </h5>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="inputLogin"><fmt:message key="email.address"/></label>
                                        <input type="email"
                                               id="inputLogin"
                                               name="login_email"
                                               class="form-control form-control-lg"
                                               maxlength="100"
                                               pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$"
                                               value="${ requestScope.login_email }"
                                               required />
                                        <span class="invalid-feedback"><fmt:message key="sign_up.inv.email"/></span>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="inputPassword"><fmt:message key="password"/></label>
                                        <input type="password"
                                               id="inputPassword"
                                               name="login_password"
                                               class="form-control form-control-lg"
                                               required/>
                                        <span class="invalid-feedback"><fmt:message key="sign_in.empty.field"/></span>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button type="submit" class="btn btn-danger btn-lg btn-block">
                                            <fmt:message key="log.in"/>
                                        </button>
                                    </div>

                                    <a class="small text-muted" href="#">
                                        <fmt:message key="sign_in.forgot.pass"/>
                                    </a>
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;">
                                        <fmt:message key="sign_in.havent.acc"/>
                                        <a href="sign_up.jsp" style="color: #393f81;">
                                            <span style="color: #6e040f"><fmt:message key="sign_in.reg.here"/></span>
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

<jsp:include page="includes/sweetalert.jsp"/>

<script type="text/javascript">
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        const forms = document.querySelectorAll('.needs-validation');

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>


</body>
</html>