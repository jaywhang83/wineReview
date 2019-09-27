<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="Header.jsp"/>

<style>
    input[type="submit"] {
        background-color: #593196;
        color: white;
        border: none;
        padding: 16px 32px;
        text-decoration: none;
        width: 100%;
        cursor: pointer;
        text-align: center;
        margin-top: 2rem;
    }

    .button {
        width: 80%;
        margin: auto;
        margin-bottom: 20rem;
        margin-top: 5rem;
    }

    .form-check-wrapper {
        display: flex;
        flex-wrap: wrap;
        font-size: 135%;
    }

    .check-wrapper {
        width: 150px;
    }
    .form-check-input {
        margin-top: .7rem;
    }
    .selection-wrapper {
        margin: 5rem;
    }
    h2 {
        text-align: center;
        margin-bottom: 4rem;
    }
</style>


<div class="selection-wrapper">
    <h2>Please select your favorite note</h2>
    <form class="form-check form-check-wrapper" action="guidedbynotes" method="post">

        <c:forEach items="${tastingNotes}" var="note">

            <div class="form-check check-wrapper custom-control custom-radio">
                <input class="form-check-input" type="radio"
                       id="${note.getNote()}"
                       name="notes"
                       value="${note.getNote()}"/>
                <label class="form-check-label" for="${note.getNote()}">
                        ${note.getNote()}
                </label>
            </div>

        </c:forEach>

        <br/><br/><br/>

        <div class="button">
            <input type="submit" value="Submit">
        </div>

    </form>
</div>

<jsp:include page="Footer.jsp"/>
