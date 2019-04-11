<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Contact</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th {
	text-align: left;
	background-color: #4CAF50;
	color: whitebackground-color: #4CAF50;
	color: white;
	padding: 8px;
	
}

td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

</style>
</head>
<body>

	<h1>Search By</h1>

	<c:url var="url" value="/searchContact" />
	<form:form modelAttribute="person" method="post" action="${url}">
	Select: <form:select path="selection" items="${person.arr}" />
		<br>
		<form:input path="answer" />
		<br>
		<input type="submit" value="Search" />
	</form:form>

	<h1>Contacts</h1>

	<table border="0">
		<tr>
				<th>Name</th>
				<th>Phone Number</th>
				<th>Address</th>
				<th>Email</th>
			
		</tr>

		<c:forEach items="${personList}" var="item">
				<tr>
					<td>${item.name }</td>
					<td>${item.phone }</td>
					<td>${item.address }</td>
					<td>${item.email }</td>
				</tr>
			</c:forEach>

	</table>


</body>
</html>