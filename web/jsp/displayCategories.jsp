<%-- 
    Document   : displayCategories
    Created on : Oct 13, 2024, 8:57:07 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Categories"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Categories> vector = (Vector<Categories>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
        %>
        <form action="CategoriesController" method="get">
            <p> 
                Search Name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="clear" >
            </p>
        </form>
        <p> <a href="CategoriesController?service=insertCategories"> insert Categories </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px"><%=title%></caption>
            <th>CategoryID</th>
            <th>CategoryName</th>
            <th>Description</th>
            <th>Picture</th>
            <th>Update</th>
            <th>Delete</th>
            <%for (Categories categories : vector) {%>
            <tr>
                <td><%=categories.getCategoryID()%></td>
                <td><a href="ProductURL?service=DisplayProducts&CategoryID=<%=categories.getCategoryID()%>"><%=categories.getCategoryName()%></a></td>
                <td><%=categories.getDescription()%></td>
                <td><%=categories.getPicture()%></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
</body>
</html>
