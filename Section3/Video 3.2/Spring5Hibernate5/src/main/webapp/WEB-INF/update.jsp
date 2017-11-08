<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring5-ReactJs</title>
<style type="text/css">
.errors {
	color: red;
}
</style>
</head>
<body>
	Hello and Welcome to Spring5-ReactJs tutorial
	<br>
	<br>

	<fieldset style="width: fit-content;">
		<legend>Update User</legend>
		<c:url value="/update" var="updateUrl" />
		<form:form action="${updateUrl}" method="post" enctype="text"
			modelAttribute="user">
			<form:errors path="*" cssClass="errors" element="div" />
			<table>
				<tr>
					<td><form:label path="userName">Name : </form:label></td>
					<td><form:input type="text" path="userName" value="${toUpdate.userName}" /></td>
				</tr>
				<tr>
					<td><form:label path="phone">Please Enter Phone</form:label></td>
					<td><form:input type="text" path="phone" value="${toUpdate.phone}" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="userId" value="${toUpdate.userId}"></td>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form:form>
	</fieldset>

</body>
</html>