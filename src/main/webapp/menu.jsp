<%@ page import="ua.deliciousrestaurant.model.entity.Product" %>
<%@ page import="ua.deliciousrestaurant.model.dao.DaoFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.deliciousrestaurant.exception.DaoException" %>
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


<div class="container">
    <div class="card-header my-3">
        <div class="row">
            <div class="col-12">
                <h2 class="text-center text-uppercase color1 mb-5">Виберіть категорію</h2>
            </div>
        </div>

        <form action="" method="post">
            <div class="row">

                <div class="col-2 mb-4">
                    <div class="dropdown">
                        <a
                                class="btn btn-primary dropdown-toggle"
                                href="#"
                                role="button"
                                id="dropdownMenuLink"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false"
                        >
                            Dropdown link
                        </a>

                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-2 mb-4">
                    <div class="input-group">

                    </div>
                </div>

                <div class="col-2 mb-4">
                    <div class="input-group">
                        <input type="search" class="form-control border-danger text-danger rounded" placeholder="Search" aria-label="Search"
                               aria-describedby="search-addon"/>
                    </div>
                </div>
                <div class="col-xl-1 mb-4">
                    <div class="input-group">
                        <button type="submit" class="btn btn-outline-danger">
                            <fmt:message key="search"/>
                        </button>
                    </div>
                </div>

            </div>
        </form>

    </div>
    <div class="row">

        <c:forEach var="product" items="${ sessionScope.products }">
            <div class="col-md-3 my-3">
                <div class="card w-100" style="width: 18rem;">
                    <img class="card-img-top" src="${ product.getImgProduct() }" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${ product.getNameProduct() }</h5>
                        <h5 class="card-title">Price: ${ product.getCostProduct() }</h5>
                        <h5 class="card-title">Category: ${ product.getCategoryName() }</h5>
                        <div class="mt-3 d-flex justify-content-between">
                            <a href="controller?action=atc&id=${ product.getIdProduct() }" class="btn btn-danger">Add to cart</a>
                            <a href="controller?action=order-now&prod-id=${ product.getIdProduct() }&quantity=1" class="btn btn-warning">Buy now</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>

<section class="about">
    <div class="container">



        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</section>



<jsp:include page="includes/footer.jsp"/>

</body>
</html>
