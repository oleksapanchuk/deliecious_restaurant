<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="log.in"/></title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>


<c:forEach var="order" items="${ sessionScope.order_list }">

    ${order.getOrderId()}</br>
</c:forEach>


<jsp:include page="includes/footer.jsp"/>

</body>
</html>
