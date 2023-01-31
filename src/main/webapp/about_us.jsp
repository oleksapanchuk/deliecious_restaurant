<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="about_us"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<div class="container min-size-2">
    <h1 class="pt-5 text-center text-danger">
        <fmt:message key="about_us"/>
    </h1>
</div>


<jsp:include page="includes/footer.jsp"/>

</body>
</html>