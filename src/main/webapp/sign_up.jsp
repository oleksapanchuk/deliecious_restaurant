<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="sign.up"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<div class="col-lg-5 mx-auto p-4 py-md-5">
    <tags:header value="sign.up"/>

    <form method="POST" action="controller">
        <input type="hidden" name="action" value="sign-up">

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputFirstName"><fmt:message key="first.name"/></label>
                <input type="password" class="form-control" id="inputFirstName" placeholder="<fmt:message key="first.name"/>">
            </div>

            <div class="form-group col-md-6">
                <label for="inputLastName"><fmt:message key="last.name"/></label>
                <input type="password" class="form-control" id="inputLastName" placeholder="<fmt:message key="last.name"/>">
            </div>
            <div class="form-group col-md-6">
                <label for="inputEmail"><fmt:message key="email.address"/></label>
                <input type="email" class="form-control" id="inputEmail" placeholder="<fmt:message key="email.address"/>">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword"><fmt:message key="password"/></label>
                <input type="password" class="form-control" id="inputPassword" placeholder="<fmt:message key="password"/>">
            </div>

            <div class="form-group col-md-6">
                <label for="inputAddress"><fmt:message key="address"/></label>
                <input type="password" class="form-control" id="inputAddress" placeholder="<fmt:message key="address"/>">
            </div>
        </div>

        <button type="submit" class="btn btn-xl btn-warning"><fmt:message key="sign.up"/></button>

    </form>

    <p class="fs-6 col-md-8">
        <fmt:message key="have.account"/>
        <a href="signIn.jsp" class="link-dark"><fmt:message key="log.in"/></a>
    </p>
</div>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
