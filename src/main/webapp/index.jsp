<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>


<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title>Delicious Restaurant</title>
</head>

<body>

<jsp:include page="includes/header.jsp"/>

<section class="vh-200" style="background-color: #eee; padding: 0 0;">
    <div class="masthead">
        <div class="container">

            <tags:name_project text="Welcome To Our Restaurant!" reg="low"/>

            <tags:name_project text="Delicious Restaurant" reg="upper"/>

            <a class="btn btn-danger btn-xl text-uppercase" href="controller?action=view-menu&sort_field=p.category_id&sort_order=asc&category_filter_id=0&offset=0&records=8&cur_page=1">
                <fmt:message key="menu"/>
            </a>
        </div>
    </div>
</section>

</body>
</html>
