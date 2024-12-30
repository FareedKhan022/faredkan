<%@page import="java.util.List"%>
<%@ page import="com.jsp.shoppingcart_application.dto.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Item> items= (List<Item>)request.getAttribute("itemslist");
	double totalprice= (Double) request.getAttribute("itemslist");
%>
<table>
	<th>Brand</th>
	<th>Category</th>
	<th>Quantity</th>
	<th>Price</th>
	
	<%
	for (Item i : items){
	%>
		<tr>
			<td> <%= i.getBrand() %> </td>
			<td> <%= i.getCategory() %>
			<td> <%= i.getQuantity() %> </td>
			<td> <%= i.getPrice() %> </td>
		</tr>
		<%
		}
		%>
</table>
TotalPrice: <%=totalprice%>

<a>Buy Now</a>
</body>
</html>