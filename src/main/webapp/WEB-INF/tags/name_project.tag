<%@ attribute name="text" required="true" %>
<%@ attribute name="reg" required="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<c:choose>
    <c:when test="${reg == 'upper'}">
        <div class="masthead-heading text-uppercase"
             style="-webkit-text-stroke-width: 2px; -webkit-text-stroke-color: darkred;">
                ${text}
        </div>
    </c:when>
    <c:otherwise>
        <div class="masthead-subheading">
                ${text}
        </div>
    </c:otherwise>
</c:choose>