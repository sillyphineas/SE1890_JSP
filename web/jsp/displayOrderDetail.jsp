<%-- 
    Document   : displayOrderDetail
    Created on : Oct 13, 2024, 10:49:51 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.OrderDetails"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<OrderDetails> vector = (Vector<OrderDetails>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
        %>
        <form action="OrderDetailsController" method="get">
            <p> 
                Search Name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="clear" >
            </p>
        </form>
        <p> <a href="OrderDetailsController?service=insertOrderDetails"> insert Order Details </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px"><%=title%></caption>
            <th>OrderID</th>
            <th>ProductID</th>
            <th>UnitPrice</th>
            <th>Quantity</th>
            <th>Discount</th>
            <th>OrderDetailStatus</th>
            <th>Update</th>
            <th>Delete</th>
            <%for (OrderDetails od : vector) {%>
            <tr>
                <td><%=od.getOrderID()%></td>
                <td><%=od.getProductID()%></td>
                <td><%=od.getUnitPrice()%></td>
                <td><%=od.getQuantity()%></td>
                <td><%=od.getDiscount()%></td>
                <td><%=od.getOrderDetailStatus()%></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
</body>
</html>
