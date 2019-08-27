<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextpath" value="${pageContext.request.contextPath}"></c:set>
	<form action="${contextpath}/registerUser" method ="post">
		UserName : <input type="text" name="username"><br>
		Password : <input type="password" name="password"><br>
		UserRole : <input type="text" name="userrole"> <br> <input
			type="submit" value="Register">
	</form>


</body>
</html>