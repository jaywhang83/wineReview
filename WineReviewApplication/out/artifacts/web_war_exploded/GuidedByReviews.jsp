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

    input[type="checkbox"] {
        margin-left: 2rem;
    }

    input[type="submit"] {
        background-color: #593196;
        border: none;
        color: white;
        padding: 16px 32px;
        text-decoration: none;
        margin: 4px 2px;
        width: 100%;
        cursor: pointer;
        text-align: center;
    }

    .button {
        width: 80%;
        margin: auto;
        margin-bottom: 20rem;
    }
</style>

<form action="guidedbyreviews" method="post">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">Favorite</th>
            <th scope="col">State</th>
            <th scope="col">Variety</th>
            <th scope="col">Winery</th>
            <th scope="col">Description</th>
            <th scope="col">Rating</th>
            <th scope="col">Price</th>
            <th scope="col">Reviewer</th>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="review">
            <a href="">
                <tr>
                    <td>
                        <input class="form-check-input" type="checkbox"
                               name="${review.getReviewId()}"
                               value="${review.getVariety().getGrape()}"/>
                    </td>
                    <td>${review.getProvince().getProvinceName()}</td>
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
            </a>
        </c:forEach>
        </tbody>
    </table>

    <div class="button">
        <input type="submit" value="Submit">
    </div>
</form>

<jsp:include page="Footer.jsp"/>
