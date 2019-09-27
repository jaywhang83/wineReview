<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
<link rel="stylesheet" href="bootstrap.min.css">
<head>
    <title>Wine Review Application</title>
  <style>
    .message {
      width: 25%;
      margin: 0 auto;
    }
    .dropdown-menu {
        min-width: 200px !important;
    }

    .my-nav-title {
      padding-left: 5px;
    }
      .dropdown-divider {
        border-top: 1px solid #2e194e;
      }

  </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">Wine Reviews</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/web_war_exploded">Home <span class="sr-only">(current)</span></a>
      </li>

      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Find Wines
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <b class="my-nav-title">Recommendations By:</b>
          <a class="dropdown-item" href="guidedbynotes">Tasting Notes</a>
          <a class="dropdown-item" href="guidedbyreviews">Review</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="advancedselection">Advanced Query</a>
        </div>
      </li>
    </ul>
  </div>
</nav>
