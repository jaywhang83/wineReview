<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="Header.jsp"/>


<style>
    .table {
        width: 80%;
        margin: 4rem auto;
        border: 1px solid black;
        padding: 1rem;
        border-radius: 5px;
    }

    .alert {
        width: 50%;
        margin: 3rem auto;
    }
</style>

<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th scope="col">State</th>
        <th scope="col">Variety</th>
        <th scope="col">Winery</th>
        <th scope="col">Description</th>
        <th scope="col">Rating</th>
        <th scope="col">Price</th>
        <th scope="col">Reviewer</th>
    </tr>

    <div class="alert alert-primary">
        Results: <b>${reviews.size()}</b>
    </div>

    </thead>
    <tbody>
    <c:forEach items="${reviews}" var="review">
        <tr>
            <th>${review.getProvince().getProvinceName()}</th>
            <td>${review.getVariety().getGrape()}</td>
            <td>${review.getWinery().getWineryName()}</td>
            <td>${review.getDescription()}</td>
            <td>${review.getPoints()}</td>
            <td>${review.getPrice()}</td>
            <td>
                <a href="showbyreviewer?reviewer=${review.getReviewer().getTwitterHandle()}">
                        ${review.getReviewer().getTwitterHandle()}
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="Footer.jsp"/>
