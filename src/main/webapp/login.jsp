<%@ page import="ua.deliciousrestaurant.model.entity.Client" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">Client Login</div>
            <div class="card-body">
                <form action="controller" method="post">

                    <input type="hidden" name="action" value="log_in">

                    <div class="form-group">
                        <label><fmt:message key="email.address"/></label>
                        <input type="email" class="form-control" name="login-email" placeholder="<fmt:message key="enter.email"/>" required>
                    </div>

                    <div class="form-group">
                        <label><fmt:message key="password"/></label>
                        <input type="password" class="form-control" name="login-password" placeholder="*************" required>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"><fmt:message key="log.in"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="includes/footer.jsp"/>

</body>
</html>