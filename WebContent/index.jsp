<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${emplist}" var="e">
				<tr>
					<td>${e.id}</td>
					<td>${e.lastname}</td>
					<td>${e.firstname}</td>
					<td>${e.salary}</td>
					<td>${e.date}</td>
		  		</tr>
	</c:forEach>

</body>
</html>