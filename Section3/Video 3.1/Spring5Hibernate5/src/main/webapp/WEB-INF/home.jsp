<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring5-ReactJs</title>
<script type="text/javascript">
var ctx = "${pageContext.request.contextPath}";
function getUser(){
	var id=document.getElementById("userId").val;
	alert(id);
	window.location = ctx+'/'+id;
}
</script>
</head>
<body>
	Hello and Welcome to Spring5-ReactJs tutorial
	<br>
	<br>
	<table>
		<tr>
			<td>
				<fieldset style="width: fit-content;">
					<legend>Create</legend>
					<c:url value="/create" var="createUrl" />
					<form action="${createUrl}" method="post">
						<table>
							<tr>
								<td>Name</td>
								<td><input type="text" name="name" placeholder="Enter your Name"></td>
							</tr>
							<tr>
								<td>Phone</td>
								<td><input type="text" name="phone" placeholder="Enter your Phone"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit"></td>
							</tr>
						</table>
					</form>
				</fieldset>
			</td>
			<td>
				<fieldset style="width: fit-content;">
				<legend>User Finder</legend>
				
					User-Id:
					<input type="text" name="userId" id="userId" />
					<button type="button" onclick="getUser()"></button>
				</fieldset>
			</td>
		</tr>
	</table>


	
</body>
</html>