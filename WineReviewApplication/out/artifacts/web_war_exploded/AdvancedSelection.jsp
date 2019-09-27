<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="Header.jsp"/>


<style>
    .form-container {
        width: 50%;
        margin: 4rem auto;
        border: 1px solid black;
        padding: 1rem;
        border-radius: 5px;
    }
</style>

<div class="form-container">
    <h2>Advanced wine filter</h2>
<form action="advancedselection" method="post">
  <div class="form-group">
    <label for="notes">Notes</label>
    <input class="form-control" id="notes" name="notes" placeholder="Tasting Notes">
  </div>
  <div class="form-group">
    <label for="state">State</label>
    <input class="form-control" id="state" name="state" placeholder="state">
  </div>
  <div class="form-group">
    <label for="price">Price</label>
    <input class="form-control" id="price" name="price" placeholder="price">
  </div>
  <div class="form-group">
    <label for="rating">Rating</label>
    <input class="form-control" id="rating" name="rating" placeholder="rating">
  </div>
  <div class="form-group">
    <label for="variety">Variety</label>
    <input class="form-control" id="variety" name="variety" placeholder="variety">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>

<p class="message">
    <b>${messages.success}</b>
</p>



<jsp:include page="Footer.jsp"/>
