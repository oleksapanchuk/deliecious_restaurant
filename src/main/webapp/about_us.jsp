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

<section class="vh-200" style="background-color: #eee;">
    <div class="container h-100 justify-content-center container-style">
        <p class="col-12 text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4" style="padding: 50px 0;">
            <fmt:message key="about_us"/>
        </p>
        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4" style="padding: 50px 0;">
            <a type="button" class="btn btn-danger btn-lg"
               href="#">
                Oleksandr Panchuk
            </a>
        </div>
    </div>
</section>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>