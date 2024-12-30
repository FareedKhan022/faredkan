<%@page import="com.jsp.shoppingcart_application.dto.Product" %>
<%@page import="com.jsp.shoppingcart_application.dto.Merchant" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Merchant merchant = (Merchant) session.getAttribute("merchantinfo");
List<Product> pro= merchant.getProduct();
%>
<a href="viewitemsfromcart">View Cart</a>

<table cellpadding="20px" border="1">
<th>id</th>
<th>brand</th>
<th>category</th>
<th>price</th>
<th>stock</th>

            <%for(Product p : pro){%>
<tr>
            <td><%=  p.getId() %> </td>
            <td> <%= p.getBrand() %> </td>
            
            <td> <%= p.getCategory() %> </td>
            <td> <%= p.getPrice() %> </td>
            <td> <%= p.getStock() %> </td>

</tr>
<%
}
%>

</table>

</body>
</html>