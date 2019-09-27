<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="Header.jsp"/>

<div class="container">
    <div class="jumbotron mt-3">
        <h1>Guide Options</h1>
        <p class="lead">Select your guide!</p>
        <a class="btn btn-lg btn-primary" href="guidedbynotes" role="button">Select by note</a>
        <a class="btn btn-lg btn-primary" href="guidedbyreviews" role="button">Select by review</a>
    </div>
</div>


<jsp:include page="Footer.jsp"/>
