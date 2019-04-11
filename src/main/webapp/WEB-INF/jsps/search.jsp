<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Contact</title>

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

	


</body>
</html>