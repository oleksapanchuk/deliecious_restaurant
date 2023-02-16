<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!-- Button trigger modal -->
<a type="button"
   class="nav-link mx-2 text-uppercase my-btn-red"
   data-bs-toggle="modal"
   data-bs-target="#editProfileModal">
    <i class="far fa-edit me-1"></i><fmt:message key="account.btn.edit.profile"/>
</a>

<!-- Modal -->
<div class="modal fade" id="editProfileModal" tabindex="-1"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                    <fmt:message key="account.btn.edit.profile"/>
                </h5>
                <button type="button" class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">
                </button>
            </div>

            <form method="post" action="controller" class="needs-validation" novalidate>
                <input type="hidden" name="action" value="edit_profile">

                <div class="modal-body">
                    <div class="mb-3">

                        <%-- first name --%>
                        <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fa-regular fa-user fa-lg me-3 fa-fw mt-2"></i>
                            <div class="form-outline flex-fill mb-0">
                                <label class="form-label" for="inputFirstName">
                                    <fmt:message key="first.name"/>
                                </label>
                                <input type="text"
                                       id="inputFirstName"
                                       name="fname"
                                       class="form-control"
                                       maxlength="40"
                                       pattern="[A-Za-zА-Яа-я'ҐІЇЩЬЮЄґіїєщью \-]{1,40}"
                                       value="${ sessionScope.auth.getFirstName() }"
                                       required/>
                                <span class="invalid-feedback"><fmt:message key="sign_up.inv.fname"/></span>
                            </div>
                        </div>

                        <%-- last name --%>
                        <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fas fa-user fa-lg me-3 fa-fw mt-2"></i>
                            <div class="form-outline flex-fill mb-0">
                                <label class="form-label" for="inputLastName"><fmt:message
                                        key="last.name"/></label>
                                <input type="text"
                                       id="inputLastName"
                                       name="lname"
                                       class="form-control"
                                       maxlength="40"
                                       pattern="[A-Za-zА-Яа-я'ҐІЇЩЬЮЄґіїєщью \-]{1,40}"
                                       value="${ sessionScope.auth.getLastName() }"
                                       required/>
                                <span class="invalid-feedback"><fmt:message key="sign_up.inv.lname"/></span>
                            </div>
                        </div>

                        <%-- email --%>
                        <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fas fa-envelope fa-lg me-3 fa-fw mt-2"></i>
                            <div class="form-outline flex-fill mb-0">
                                <label class="form-label" for="inputEmail"><fmt:message
                                        key="email.address"/></label>
                                <input type="email"
                                       id="inputEmail"
                                       name="email"
                                       class="form-control"
                                       maxlength="100"
                                       pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$"
                                       value="${ sessionScope.auth.getEmail() }"
                                       required/>
                                <span class="invalid-feedback"><fmt:message key="sign_up.inv.email"/></span>
                            </div>
                        </div>

                        <%-- address --%>
                        <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fa-solid fa-location-pin fa-lg me-3 fa-fw mt-2"></i>
                            <div class="form-outline flex-fill mb-0">
                                <label class="form-label" for="inputAddress"><fmt:message
                                        key="address"/></label>
                                <input type="text"
                                       id="inputAddress"
                                       name="address"
                                       class="form-control"
                                       maxlength="45"
                                       value="${ sessionScope.auth.getAddress() }"
                                       required/>
                                <span class="invalid-feedback"><fmt:message key="sign_up.inv.address"/></span>
                            </div>
                        </div>

                        <%-- phone number --%>
                        <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fa-sharp fa-solid fa-phone fa-lg me-3 fa-fw mt-2"></i>
                            <div class="form-outline flex-fill mb-0">
                                <label class="form-label" for="inputPhone">
                                    <fmt:message key="account.phone"/>
                                </label>
                                <input type="text"
                                       id="inputPhone"
                                       name="address"
                                       class="form-control"
                                       maxlength="45"
                                       value="${ sessionScope.auth.getPhoneNum() }"
                                       required/>
                                <span class="invalid-feedback">
                                    <%-- TODO --%>
                                    <fmt:message key="sign_up.inv.address"/>
                                </span>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="modal-footer">
                    <a href="controller?action=change_password"
                       class="btn my-btn-red">
                        Change password!
                    </a>
                    <button type="submit"
                            class="btn btn-success"
                            data-bs-dismiss="modal">
                        <fmt:message key="account.btn.edit.profile"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
