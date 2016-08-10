<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>

<c:if test="${invalidCredentials == true}">
	<script type="text/javascript">
		alert('Invalid credentials.');
	</script>
</c:if>



<div class="panel panel-default">
	<div class="panel-heading">Please enter you credentials</div>
	<div class="panel-body">
		<form action='<c:url value="/login"/>' method="post" id="loginForm">
			<div class="form-group">
				<label>Email address</label> 
				<input type="email" class="form-control" placeholder="Email" id="login" name="login" required="required" maxlength="100">
			</div>
			<div class="form-group">
				<label>Password</label> 
				<input type="password" class="form-control" placeholder="Password" id="password" name="password" required="required" maxlength="100">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>

	</div>
</div>

<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>