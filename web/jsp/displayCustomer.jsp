<%-- 
    Document   : displayCustomer
    Created on : Oct 7, 2024, 9:27:42 AM
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
            Vector<Customer> vector = (Vector<Customer>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
        %>
        <form action="CustomerURL" method="get">
            <p>Search Name: <input type="text" name="cusId" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllCustomers">
            </p>
        </form>
        <p> <a href="CustomerURL?service=insertCustomer"> insert Customer </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px"><%=title%></caption>
            <th>CustomerID</th>
            <th>CompanyName</th>
            <th>ContactName</th>
            <th>ContactTitle</th>
            <th>Address</th>
            <th>City</th>
            <th>Region</th>
            <th>PostalCode</th>
            <th>Country</th>
            <th>Phone</th>
            <th>Fax</th>
            <th>Update</th>
            <th>Delete</th>
            <%for (Customer cus : vector) {%>
            <tr>
                <td><%=cus.getCustomerID()%></td>
                <td><%=cus.getCompanyName()%></td>
                <td><%=cus.getContactName()%></td>
                <td><%=cus.getContactTitle()%></td>
                <td><%=cus.getAddress()%></td>
                <td><%=cus.getCity()%></td>
                <td><%=cus.getRegion()%></td>
                <td><%=cus.getPostalCode()%></td>
                <td><%=cus.getCountry()%></td>
                <td><%=cus.getPhone()%></td>
                <td><%=cus.getFax()%></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
        <%Customer customer = (Customer)session.getAttribute("customer");%>
        <h2 align="right">
            <%if(customer==null){%>
            <a href="CustomerURL?service=loginCustomer">Login</a>
            <%} else {%>
                <span style="color:red"> Welcome <%=customer.getCompanyName()%></span>
                <a href="CustomerURL?service=logoutCustomer">Logout</a>
            <%}%>
        </h2>
    </body>
</html>
