<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring5-ReactJs</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/mainStyle.css" />
</head>
<body>
	<c:if test="${not empty name}">
	Hello <h2>${name}</h2>
	</c:if>
	<br>
	<c:if test="${not empty age}">
	Your age: ${age}
	</c:if>
	<c:if test="${not empty error}">
	<h3 style="color:red;">${error}</h3>
	</c:if>
	<c:if test="${not empty employee}">
		<h2>Employee details having Employee Id=221</h2>
		<table>
			<tr>
				<td>Name</td>
				<td>${employee.name}</td>
			</tr>
			<tr>
				<td>Age</td>
				<td>${employee.age}</td>
			</tr>
			<tr>
				<td>Phone No</td>
				<td>${employee.phoneNo}</td>
			</tr>
			<tr>
				<td>Address</td>
				<td>${employee.address}</td>
			</tr>
		</table>
	</c:if>
	<div id="next" class="modalDialog">
		<div>
		<c:url var="homeUrl" value="/"></c:url>
		<a href="${homeUrl}" title="Close" class="close">X</a> 
		<c:if test="${not empty redirectedEmployee}">
		<h3 style="color:green;">Signup successful with below details</h3>
		<table>
			<tr>
				<td>Name</td>
				<td>${redirectedEmployee.name}</td>
			</tr>
			<tr>
				<td>Age</td>
				<td>${redirectedEmployee.age}</td>
			</tr>
			<tr>
				<td>Address</td>
				<td>${redirectedEmployee.address}</td>
			</tr>
			<tr>
				<td>Phone No</td>
				<td>${redirectedEmployee.phoneNo}</td>
			</tr>
		</table>
		</c:if>
		</div>
	</div>
</body>
</html>