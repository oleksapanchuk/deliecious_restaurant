<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="about">
    <div class="container">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${requestScope.cur_page <= 1}">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link text-xl-start text-danger" href="#">1</a></li>
                        <li class="page-item"><a class="page-link text-xl-start text-dark" href="${requestScope.href}offset=${requestScope.offset + 8}&records=8">2</a></li>
                        <li class="page-item"><a class="page-link text-xl-start text-dark" href="${requestScope.href}offset=${requestScope.offset + 16}&records=8">3</a></li>
                        <li class="page-item">
                            <a class="page-link text-dark" href="${requestScope.href}offset=${requestScope.offset + 8}&records=8"
                               tabindex="-1">Next</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link text-dark" href="${requestScope.href}offset=${requestScope.offset - 8}&records=8"
                               tabindex="-1">Previous</a>
                        </li>


                        <c:choose>
                            <c:when test="${ requestScope.cur_page == requestScope.total_pages}">
                                <li class="page-item"><a class="page-link text-dark" href="${requestScope.href}offset=${requestScope.offset - 16}&records=8">${requestScope.total_pages - 2}</a></li>
                                <li class="page-item"><a class="page-link text-dark" href="${requestScope.href}offset=${requestScope.offset - 8}&records=8">${requestScope.total_pages - 1}</a></li>
                                <li class="page-item"><a class="page-link text-danger" href="#">${requestScope.cur_page}</a></li>
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1">Next</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link text-dark" href="${requestScope.href}offset=${requestScope.offset - 8}&records=8">${requestScope.cur_page - 1}</a></li>
                                <li class="page-item"><a class="page-link text-danger" href="#">${requestScope.cur_page}</a></li>
                                <li class="page-item"><a class="page-link text-dark" href="${requestScope.href}offset=${requestScope.offset + 8}&records=8">${requestScope.cur_page + 1}</a></li>
                                <li class="page-item">
                                    <a class="page-link text-dark" href="${requestScope.href}offset=${requestScope.offset + 8}&records=8"
                                       tabindex="-1">Next</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        </c:otherwise>
                </c:choose>



            </ul>
        </nav>
    </div>
</section>
