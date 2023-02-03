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

        <div class="row my-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col"><fmt:message key="mng.clients.role"/></th>
                    <th scope="col"><fmt:message key="first.name"/></th>
                    <th scope="col"><fmt:message key="last.name"/></th>
                    <th scope="col"><fmt:message key="email.address"/></th>
                    <th scope="col" class="text-center"><fmt:message key="mng.clients.details"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="client" items="${ sessionScope.clients }">
                    <tr>
                        <td><c:out value="${ client.getClientId() }"/></td>
                        <td><c:out value="${ client.getRole() }"/></td>
                        <td><c:out value="${ client.getFirstName() }"/></td>
                        <td><c:out value="${ client.getLastName() }"/></td>
                        <td><c:out value="${ client.getEmail() }"/></td>
                        <td class="text-center">
                            <a class="btn btn-warning"
                               href="#">
                                <fmt:message key="order.show.more"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>
</section>


<c:set var="href" scope="request"
       value="controller?action=view-all-clients&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&"/>
<jsp:include page="includes/pagination.jsp"/>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
