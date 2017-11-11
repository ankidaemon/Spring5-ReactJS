<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring5-ReactJs</title>
<script type="text/javascript">
var ctx = "${pageContext.request.contextPath}";
function getUser(){
	var id=document.getElementById("userid").value;
	window.location = ctx+'/'+id;
}
function deleteUser(id){
	window.location = ctx+'/delete/'+id;
}
</script>
<style type="text/css">
.errors{
color:red;
}
</style>
</head>
<body>
	<h2 style="display:inline-block;">
	Welcome ${pageContext.request.userPrincipal.name}
	</h2>	
	<div style="display:inline-block; float: right;">
	<c:url value="/logout" var="logOutUrl"/>
    	<form:form name="form" action="${logOutUrl}" method="post">
            <input type="submit" value="log Out" />
    </form:form>
    </div>	
	<br>
	<br>
	<c:if test="${not empty added}"><p style="color:green;font-weight:bold;">${added}</p></c:if>
	<c:if test="${not empty deleted}"><p style="color:green;font-weight:bold;">${deleted}</p></c:if>
	<c:if test="${not empty updated}"><p style="color:green;font-weight:bold;">${updated}</p></c:if>
	<c:if test="${empty userList}">
	<table>
		<tr>
			<td>
				<fieldset style="width: fit-content;">
					<legend>Create</legend>
					<c:url value="/create" var="createUrl" />
					<form:form action="${createUrl}" method="post" enctype="text" modelAttribute="user">
						<form:errors path="*" cssClass="errors" element="div" /> 
						<table>
							<tr>
								<td><form:label path="userName">Name : </form:label></td>
								<td><form:input type="text"	path="userName" /></td>
							</tr>
							<tr>
								<td><form:label path="phone">Please Enter Phone</form:label></td>
								<td><form:input type="text"	path="phone" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit"></td>
							</tr>
						</table>
					</form:form>
				</fieldset>
			</td>
			<td>
				<fieldset style="width: fit-content;">
				<legend>User Finder By ID</legend>
				
					User-Id:
					<input type="text" name="userId" id="userid" />
					<button type="button" onclick="getUser()">Find User</button>
				</fieldset>
			</td>
			<td>
				<fieldset style="width: fit-content;">
				<legend>User Finder By Name</legend>
				<c:url value="/nameFinder" var="nameFinder" />
					<form action="${nameFinder}" method="POST">
						<input type="text" name="name" value=""/>
						<input type="submit" name="Find User" value="Find User"/>
					</form>
				</fieldset>
			</td>
			<td>
				<fieldset style="width: fit-content;">
				<legend>Find all Users</legend>
					<c:url value="/all" var="allUrl" />
					<form action="${allUrl}" method="get">
					<input type="submit" value="Find All Users"/>
					</form>
				</fieldset>
			</td>
		</tr>
	</table>
	</c:if>
	
	<c:if test="${not empty userList}">
		<c:url value="/updatePage" var="updatePageUrl" />
		<table>
			<tr>
				<td>Name</td>
				<td>Phone</td>
				<td>Update</td>
				<td>Delete</td>
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.userName}</td>
					<td>${user.phone}</td>
					<td>
						<form action="${updatePageUrl}" method="POST">
						<input type="hidden" name="id" value="${user.userId}"/>
						<input type="submit" name="Update" value="Update"/>
						</form>
					</td>
					<td>
						<button type="button" onclick="deleteUser('${user.userId}')">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>