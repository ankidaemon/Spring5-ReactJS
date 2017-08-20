<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring5-ReactJs</title>
</head>
<body>
	Hello and Welcome to Spring5-ReactJs tutorial
	<br>
	<br>
	<table>
		<tr>
			<td>
				<fieldset style="width: fit-content;">
					<legend>Passed as RequestParam</legend>
					<c:url value="/info" var="infoUrl" />
					<form action="${infoUrl}" method="post">
						<table>
							<tr>
								<td>Name</td>
								<td><input type="text" name="name"
									placeholder="Enter your Name"></td>
							</tr>
							<tr>
								<td>Age</td>
								<td><input type="text" name="age"
									placeholder="Enter your age (Optional)"></td>
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
					<legend>Passed as FormParam</legend>
					<c:url value="/signUp" var="signUpUrl" />
					<form action="${signUpUrl}" method="post">
						<table>
							<tr>
								<td>Name</td>
								<td><input type="text" name="name"
									placeholder="Enter your Name"></td>
							</tr>
							<tr>
								<td>Age</td>
								<td><input type="text" name="age"
									placeholder="Enter your age"></td>
							</tr>
							<tr>
								<td>Phone No</td>
								<td><input type="text" name="phoneNo"
									placeholder="Enter your phone no"></td>
							</tr>
							<tr>
								<td>Address</td>
								<td><input type="text" name="address"
									placeholder="Enter your address"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Sign Up"></td>
							</tr>
						</table>
					</form>
				</fieldset>
			</td>
			<td>
				<fieldset style="width: fit-content;">
					<legend>Multipart Support</legend>
					<c:if test="${not empty uploadResult}">
						<p style="color: green;">${uploadResult}</p>
					</c:if>
					<c:if test="${not empty error}">
						<p style="color: red;">${error}</p>
					</c:if>
					<c:url value="/uploadFiles" var="uploadFiles" />
					<form action="${uploadFiles}" method="post"
						enctype="multipart/form-data">
						<input type="file" name="file" multiple></input> <input
							type="submit" value="upload" />
					</form>
				</fieldset>
			</td>
		</tr>
	</table>

	<br />
	<br />
	<c:url value="/221" var="pathUrl" />
	<fieldset style="width: fit-content;">
		<legend>Passed as PathVariable</legend>
		<h3>
			<a href="${pathUrl}">Give me the details of employee having
				Employee-Id=221</a>
		</h3>
	</fieldset>
</body>
</html>