<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>

<div class="panel panel-default">
	<div class="panel-heading">Enter the advertisement details</div>
	<div class="panel-body">
		<form action='<c:url value="/secure/newAd"/>' method="post">
			<div class="form-group">
				<label>Title</label> 
				<input type="text" class="form-control" placeholder="Title" name="title" required="required" maxlength="255">
			</div>
			<div class="form-group">
				<label>Text</label> 
				<input type="text" class="form-control" placeholder="Text" name="text" required="required">
			</div>
			<button type="submit" class="btn btn-primary">Create</button>
		</form>
	</div>
</div>

<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>