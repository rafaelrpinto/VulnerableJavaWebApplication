<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A simple web application</title>


<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>
	<div class="container">


		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<li><a href="/">Home</a></li>
				</ul>
			</div>

			<div class="collapse navbar-collapse">
				<form class="navbar-form navbar-left"
					action='<c:url value="/search"/>' method="post">
					<div class="form-group">
						<input type="text" class="form-control"
							placeholder="Type a search term" name="keyword">
					</div>
					<button type="submit" class="btn btn-default">Search</button>
				</form>

				<c:choose>
					<c:when test="${sessionUser !=  null}">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="javascript:void(0)">Hi ${sessionUser.name}</a></li>
							<li><button type="button" class="btn btn-danger navbar-btn"
									onclick="document.location.href='<c:url value="/logout"/>'">Logout</button></li>
						</ul>

					</c:when>
					<c:otherwise>
						<div class="row navbar-right">
							<button type="button" class="btn btn-success navbar-btn "
								onclick="document.location.href='<c:url value="/signUp"/>'">Sign
								up</button>

							<button type="button" class="btn btn-primary navbar-btn"
								onclick="document.location.href='<c:url value="/login"/>'">Sign
								in</button>
						</div>
					</c:otherwise>
				</c:choose>


			</div>
		</div>
		</nav>