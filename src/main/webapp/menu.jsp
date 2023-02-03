<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <%@include file="includes/head.jsp" %>
    <title><fmt:message key="menu"/></title>
</head>
<body>

<jsp:include page="includes/header.jsp"/>

<section class="section-style" style="padding: 10px 0;">
    <div class="container h-100 container-style">
        <form action="" method="post">
            <div class="row my-3 py-3 border border-danger rounded">

                <div class="col-4 border border-2 border-danger rounded-3 mx-4 px-0">
                    <div class="dropdown">
                        <button class=" col-6 btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton1"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="menu.category" />
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li>
                                <a
                                        class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == 0 ? 'text-danger' : '' }"
                                        href="controller?action=view-menu&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=0&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page} ">
                                    <fmt:message key="menu.category.all.upcase"/>
                                </a>
                            </li>
                            <c:forEach var="item" items="${ sessionScope.categories }">
                                <li>
                                    <a
                                            class="dropdown-item ${ not empty sessionScope.category_filter_id && sessionScope.category_filter_id  == item.getKey() ? 'text-danger' : '' }"
                                            href="controller?action=view-menu&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ item.getKey() }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                            ${ item.getValue() }
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>

                        <label for="orderSelectButton" class="col-5 text-danger text-center">
                            <c:choose>
                                <c:when test="${ sessionScope.category_filter_id == 0 }">
                                    <fmt:message key="menu.category.all.lowcase" />
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="item" items="${ sessionScope.categories }">
                                        <c:if test="${ item.getKey() == sessionScope.category_filter_id }">
                                            ${ item.getValue().toLowerCase() }
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </label>

                    </div>
                </div>

                <div class="col-3 border border-2 border-danger rounded-3 mx-4 px-0">
                    <div class="dropdown">
                        <button class="col-6 btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton2"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="menu.sort.by" />
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton2">
                            <li>
                                <a class="dropdown-item ${ not empty sessionScope.sort_field && (sessionScope.sort_field  == 'p.prod_eng_name' || sessionScope.sort_field  == 'p.prod_ua_name') ? 'text-danger' : '' }"
                                   href="controller?action=view-menu&sort_field=${sessionScope.locale == 'en' ? 'p.prod_eng_name' : 'p.prod_ua_name' }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                    <fmt:message key="menu.sort.by.name.of.dish" />
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'p.prod_cost' ? 'text-danger' : '' }"
                                   href="controller?action=view-menu&sort_field=p.prod_cost&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                    <fmt:message key="menu.sort.by.cost" />
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item ${ not empty sessionScope.sort_field && sessionScope.sort_field  == 'p.category_id' ? 'text-danger' : '' }"
                                   href="controller?action=view-menu&sort_field=p.category_id&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                    <fmt:message key="menu.sort.by.category" />
                                </a>
                            </li>
                        </ul>
                        <label for="orderSelectButton" class="col-5 text-danger text-center">
                            <c:choose>
                                <c:when test="${sessionScope.sort_field == 'p.prod_eng_name' || sessionScope.sort_field == 'p.prod_ua_name' }">
                                    <fmt:message key="menu.sort.by.name.of.dish" />
                                </c:when>
                                <c:when test="${ sessionScope.sort_field == 'p.prod_cost'}">
                                    <fmt:message key="menu.sort.by.cost" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="menu.sort.by.category" />
                                </c:otherwise>
                            </c:choose>
                        </label>
                    </div>
                </div>

                <div class="col-3 border border-2 border-danger rounded-3 mx-4 px-0">
                    <div class="dropdown">
                        <button class="col-6 btn btn-danger dropdown-toggle" type="button" id="orderSelectButton"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="menu.order.by" />
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="orderSelectButton">
                            <li>
                                <a class="dropdown-item ${ not empty sessionScope.sort_order && sessionScope.sort_order  == 'asc' ? 'text-danger' : '' }"
                                   href="controller?action=view-menu&sort_field=${ sessionScope.sort_field }&sort_order=asc&category_filter_id=${ sessionScope.category_filter_id }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                    <fmt:message key="menu.asc" />
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item ${ not empty sessionScope.sort_order && sessionScope.sort_order  == 'desc' ? 'text-danger' : '' }"
                                   href="controller?action=view-menu&sort_field=${ sessionScope.sort_field }&sort_order=desc&category_filter_id=${ sessionScope.category_filter_id }&offset=${ requestScope.offset }&records=8&cur_page=${ requestScope.cur_page}">
                                    <fmt:message key="menu.des" />
                                </a>
                            </li>
                        </ul>
                        <label for="orderSelectButton" class="col-5 text-danger text-center">
                            <c:choose>
                                <c:when test="${ sessionScope.sort_order == 'asc'}">
                                    <fmt:message key="menu.asc" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="menu.des" />
                                </c:otherwise>
                            </c:choose>
                        </label>
                    </div>
                </div>

            </div>
        </form>
    </div>
</section>

<section class="vh-200 section-style" style="padding: 10px 0;">
    <div class="container h-100 container-style">
        <div class="row border border-danger rounded">

            <c:forEach var="product" items="${ sessionScope.products }">
                <div class="col-md-3 my-3">
                    <div class="card w-100" style="width: 18rem; background-color: #efe3e3">
                        <img class="card-img-top" src="${ product.getImgProduct() }" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${ product.getNameProduct() }</h5>
                            <h5 class="card-title"><fmt:message key="price"/>${ product.getCostProduct() }</h5>
                            <h5 class="card-title"><fmt:message key="category"/>${ product.getCategoryName() }</h5>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="controller?action=atc&id=${ product.getIdProduct() }" class="btn btn-danger">
                                    <fmt:message key="add.to.cart"/>
                                </a>
                                <a href="controller?action=order-now&prod-id=${ product.getIdProduct() }&quantity=1"
                                   class="btn btn-warning">
                                    <fmt:message key="buy.now"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</section>

<c:set var="href" scope="request"
       value="controller?action=view-menu&sort_field=${ sessionScope.sort_field }&sort_order=${ sessionScope.sort_order }&category_filter_id=${ sessionScope.category_filter_id }&"/>
<jsp:include page="includes/pagination.jsp"/>

<jsp:include page="includes/footer.jsp"/>

</body>
</html>
