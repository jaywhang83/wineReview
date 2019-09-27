<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="Header.jsp"/>

<c:forEach items="${reviews}" var="review">
    <p>${review.getDescription()}</p>
</c:forEach>

<jsp:include page="Footer.jsp"/>
