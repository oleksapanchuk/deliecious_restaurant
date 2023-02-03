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

<section class="vh-200 section-style">
    <div class="container h-100 container-style" style="min-height: 400px;">

        <div class="row my-2">
            <span class="h1"><fmt:message key="${ sessionScope.error }"/></span>
        </div>

        <div class="row my-2">
            <span class="h2"><fmt:message key="${ sessionScope.login_email }"/></span>
        </div>

    </div>
</section>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
