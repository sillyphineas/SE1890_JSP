<%-- 
    Document   : LoginSuccess
    Created on : Oct 17, 2024, 12:05:01 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customer"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customer account = (Customer) request.getAttribute("account");
        %>
        <h1>Thông tin tài khoản: </h1>
        <p><%=account.getCustomerID()%></p>
        <p><%=account.getCompanyName()%></p>
        <a href="OrdersControllerURL?service=checkCus&CusId=<%=account.getCustomerID()%>"><p><%=account.getContactName()%></p></a>
        <p><%=account.getContactTitle()%></p>
        <p><%=account.getAddress()%></p>
        <p><%=account.getCity()%></p>
        <p><%=account.getRegion()%></p>
        <p><%=account.getPostalCode()%></p>
        <p><%=account.getCountry()%></p>
        <p><%=account.getPhone()%></p>
        <p><%=account.getFax()%></p>
    </body>
</html>
