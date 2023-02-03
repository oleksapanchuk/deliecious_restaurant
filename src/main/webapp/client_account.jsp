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

<section class="vh-200 section-style">
    <div class="container h-100 container-style" style="min-height: 400px;">

        <h1>Full name: ${ sessionScope.auth.getFirstName() } ${ sessionScope.auth.getLastName() }</h1><br>
        <h1>Email: ${ sessionScope.auth.getEmail() }</h1>

    </div>
</section>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>

