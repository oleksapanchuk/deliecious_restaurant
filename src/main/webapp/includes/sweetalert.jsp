<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<input type="hidden" id="valid_status" value="${ sessionScope.valid_status }">
<input type="hidden" id="locale" value="${ sessionScope.locale }">
<c:set var="valid_status" value="none" scope="session"/>
<script type="text/javascript" src="js/validating-alert.js"></script>