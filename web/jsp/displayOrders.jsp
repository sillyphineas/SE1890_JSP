<%-- 
    Document   : displayOrders
    Created on : Oct 13, 2024, 9:29:28 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Orders"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Orders> vector = (Vector<Orders>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
        %>
        <form action="OrdersControllerURL" method="get">
            <p> 
                Search Name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="clear" >
            </p>
        </form>
        <p> <a href="OrdersControllerURL?service=insertOrders"> insert Orders </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px"><%=title%></caption>
            <th>OrderID</th>
            <th>CustomerID</th>
            <th>EmployeeID</th>
            <th>OrderDate</th>
            <th>RequiredDate</th>
            <th>ShippedDate</th>
            <th>ShipVia</th>
            <th>Freight</th>
            <th>ShipName</th>
            <th>ShipAddress</th>
            <th>ShipCity</th>
            <th>ShipRegion</th>
            <th>ShipPostalCode</th>
            <th>ShipCountry</th>
            <th>Update</th>
            <th>Delete</th>
            <%for (Orders orders : vector) {%>
            <tr>
                <td>
                    <a href="OrdersControllerURL?service=GenBill&orderId=<%=orders.getOrderID()%>"><%=orders.getOrderID()%></a>
                </td>
                <td><%=orders.getCustomerID()%></td>
                <td><%=orders.getEmployeeID()%></td>
                <td><%=orders.getOrderDate()%></td>
                <td><%=orders.getRequiredDate()%></td>
                <td><%=orders.getShippedDate()%></td>
                <td><%=orders.getShipVia()%></td>
                <td><%=orders.getFreight()%></td>
                <td><%=orders.getShipName()%></td>
                <td><%=orders.getShipAddress()%></td>
                <td><%=orders.getShipCity()%></td>
                <td><%=orders.getShipRegion()%></td>
                <td><%=orders.getShipPostalCode()%></td>
                <td><%=orders.getShipCountry()%></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
</body>
</html>
