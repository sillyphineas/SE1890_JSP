<%-- 
    Document   : PaymentPage
    Created on : Oct 18, 2024, 11:25:17 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Bill"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Bill> vector = (Vector<Bill>)request.getAttribute("data");
        %>
        <h1>Trang thanh toán: </h1>
        OrderID : <%=vector.get(0).getOrderID()%>
        </br>
        OrderDate : <%=vector.get(0).getOrderDate()%>
        </br>
        RequiredDate : <%=vector.get(0).getRequiredDate()%>
        </br>
        ContactName(Người mua) : <%=vector.get(0).getContactName()%>
        </br>
        LastName(Người bán) : <%=vector.get(0).getLastName()%>
        </br>
        <table border="1">
            <caption style="color:red;font-size:40px">Thông Tin Sản phẩm</caption>
            <th>ProductID</th>
            <th>ProductName</th>
            <th>UnitPrice</th>
            <th>Quantity</th>
            <th>Discount</th>
            <th>Total</th>
                <%for (Bill b : vector) {%>
            <tr>

                <td><%=b.getProductID()%></td>
                <td><%=b.getProductName()%></td>
                <td><%=b.getUnitPrice()%></td>
                <td><%=b.getQuantity()%></td>
                <td><%=b.getDiscount()%></td>
                <td><%=b.getTotal()%></td>
            </tr>
            <%}%>
        </table>

    </body>
</html>
