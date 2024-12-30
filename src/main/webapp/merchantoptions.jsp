<%@page import="com.jsp.shoppingcart_application.dto.Merchant" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%Merchant m=(Merchant)session.getAttribute("merchantinfo");%>
<% 
if(m!=null){
%>
<h2 >${msg}</h2>
<h1><a href="addproduct">Add Product</a></h1>
<h1><a href="viewallproducts.jsp">view All Products</a></h1>
<%
} else {
%>

<a href="merchantloginform.jsp">Login First</a>

<%
}
%>

</body>
</html>
