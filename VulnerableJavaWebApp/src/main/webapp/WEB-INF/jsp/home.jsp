<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>

<div class="row">
	<c:if test="${sessionUser !=  null}">
		<button type="button" class="btn btn-success"onclick="document.location.href='<c:url value="/secure/newAd"/>'">Create Advertisement</button>
	</c:if>
</div>

<div class="row">&nbsp;</div>

<div class="row">
	<ul class="list-group">
		<c:choose>
			<c:when test="${not empty ads}">
				<c:forEach items="${ads}" var="ad">
					<div class="panel panel-default">
						<div class="panel-heading"><strong>${ad.title}</strong> (posted by ${ad.user.name} on ${ad.createDate})</div>
						<div class="panel-body">${ad.text}</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h5>No advertisements found for <strong>"${keyword}"</strong></h5>
				<button type="button" class="btn btn-primary"onclick="document.location.href='<c:url value="/"/>'">Go Back</button>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>