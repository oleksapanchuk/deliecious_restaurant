<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!-- Button trigger modal -->
<a type="button"
   class="nav-link mx-2 text-uppercase my-btn-red"
   data-bs-toggle="modal"
   data-bs-target="#exampleModal">
    <i class="fa-solid fa-money-bill me-1"></i><fmt:message key="account.btn.add.funds"/>
</a>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                    <fmt:message key="account.modal.replenishment"/>
                </h5>
                <button type="button" class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>
            <form method="post" action="controller">
                <input type="hidden" name="action" value="add_funds">

                <div class="modal-body">
                    <div class="input-group mb-3">
                        <span class="input-group-text"><fmt:message key="currency"/></span>
                        <input type="text" name="funds" class="form-control" aria-label="Amount (to the nearest dollar)">
                        <span class="input-group-text">.00</span>
                    </div>
                    <c:if test="${ sessionScope.error == 'error.wrong.int.number' }">
                        <div class="row">
                            <p class="text-danger"><fmt:message key="error.wrong.int.number"/></p>
                        </div>
                        <c:set var="error" value="none" scope="session"/>
                    </c:if>
                </div>
                <div class="modal-footer">
                    <button type="submit"
                            class="btn btn-grad-green-01"
                            data-bs-dismiss="modal">
                        <fmt:message key="account.btn.add.funds"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>