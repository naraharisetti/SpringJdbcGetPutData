<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Product List Page</h1>
<table>
	<tr>
		<th>ID</th><th>Name</th><th>Price</th><th>Date</th>
	</tr>
	<c:forEach var="product" items="${list }">
		<tr>
			<td>${product.id }</td>
			<td>${product.name }</td>
			<td>${product.price }</td>
			<td>${product.dateAdded }</td>
		</tr>
	</c:forEach>
</table>
	<h3>Click Below to EDIT / Enter New Details</h3>

<form action="putdata">	
		Enter Id: <input type="text" name="id">
		Enter Name: <input type="text" name="name">
		Enter Price: <input type="text" name="price">

	<input type="submit" value="Submit">
</form> 
	
</body>
</html>