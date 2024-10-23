<%-- 
    Document   : displayEmployee
    Created on : Oct 13, 2024, 8:05:15 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Employee"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Employee> vector = (Vector<Employee>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
        %>
        <form action="EmployeeURL" method="get">
            <p> 
                Search Name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="clear" >
            </p>
        </form>
        <p> <a href="EmployeeURL?service=insertEmployee"> insert employee </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px"><%=title%></caption>
            <th>EmployeeID</th>
            <th>LastName</th>
            <th>FirstName</th>
            <th>Title</th>
            <th>TitleOfCourtesy</th>
            <th>BirthDate</th>
            <th>HireDate</th>
            <th>Address</th>
            <th>City</th>
            <th>Region</th>
            <th>PostalCode</th>
            <th>Country</th>
            <th>HomePhone</th>
            <th>Extension</th>
            <th>Photo</th>
            <th>Notes</th>
            <th>ReportsTo</th>
            <th>PhotoPath</th>
            <th>EmployStatus</th>
            <th>Update</th>
            <th>Delete</th>
            <%for (Employee em : vector) {%>
            <tr>
                <td><%=em.getEmployeeID()%></td>
                <td><%=em.getLastName()%></td>
                <td><%=em.getFirstName()%></td>
                <td><%=em.getTitle()%></td>
                <td><%=em.getTitleOfCourtesy()%></td>
                <td><%=em.getBirthDate()%></td>
                <td><%=em.getHireDate()%></td>
                <td><%=em.getAddress()%></td>
                <td><%=em.getCity()%></td>
                <td><%=em.getRegion()%></td>
                <td><%=em.getPostalCode()%></td>
                <td><%=em.getCountry()%></td>
                <td><%=em.getHomePhone()%></td>
                <td><%=em.getExtension()%></td>
                <td><%=em.getPhoto()%></td>
                <td><%=em.getNotes()%></td>
                <td><%=em.getReportsTo()%></td>
                <td><%=em.getPhotoPath()%></td>
                <td><%=em.getEmployStatus()%></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
</body>
</html>