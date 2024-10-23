<%-- 
    Document   : showCart
    Created on : Oct 21, 2024, 8:42:09 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Cart"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Cart> vector = (Vector<Cart>)request.getAttribute("vectorCart");
            
        %>
        <form action="ProductURL" method="get">
            <p> 
                Search Name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="clear" >
            </p>
        </form>
        <p> <a href="ProductURL?service=insertProduct"> insert Product </a></p>
        <p align="right"> <a href="CartURL?service=showCart"> Show Cart </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px">List product in cart</caption>
            <th>ProductID</th>
            <th>ProductName</th>
            <th>UnitPrice</th>
            <th>Quantity</th>
            <th>Discount</th>
            <th>subTotal</th>
            <th>Remove</th>
            <%for (Cart cart : vector) {%>
            <tr>
                <td><%=cart.getProductID()%></td>
                <td><%=cart.getProductName()%></td>
                <td><%=cart.getUnitPrice()%></td>
                <td><%=cart.getQuantity()%></td>
                <td><%=cart.getDiscount()%></td>
                <td></td>
                <td><a href="CartURL?service=removeCart&pid=<%=cart.getProductID()%>">Remove</a></td>
            </tr>
            <%}%>
        </table>
        <p> <a href="ProductURL">Continue</a></p>
</body>
</html>

