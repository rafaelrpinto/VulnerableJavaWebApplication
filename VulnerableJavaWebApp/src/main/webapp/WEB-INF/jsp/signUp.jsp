<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>

<c:if test="${emailExists == true}">
	<script type="text/javascript">
		alert('The provided e-mail already exists.');
	</script>
</c:if>


<div class="panel panel-default">
	<div class="panel-heading">Enter the advertisement details</div>
	<div class="panel-body">
		<form action='<c:url value="/signUp"/>' method="post">
			<div class="form-group">
				<label>Name</label> 
				<input type="text" class="form-control" placeholder="Name" name="name" required="required" maxlength="255">
			</div>
			<div class="form-group">
				<label>E-mail</label> 
				<input type="email" class="form-control" placeholder="E-mail" name="email" required="required" maxlength="255">
			</div>
			<div class="form-group">
				<label>Password</label> 
				<input type="password" class="form-control" placeholder="Password" name="password" required="required" maxlength="255">
			</div>
			<button type="submit" class="btn btn-primary">Create</button>
		</form>
	</div>
</div>

<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>