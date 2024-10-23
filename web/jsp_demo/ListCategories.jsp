<%-- 
    Document   : ListCategories
    Created on : Oct 17, 2024, 10:40:12 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="model.Categories" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Vector<Categories> listCategoryName = (Vector<Categories>) request.getAttribute("listCategoryName");
    %>
    <body>
        <%
            for (int i = 0; i < listCategoryName.size(); i++) {
        %>
            <a href=""><%=listCategoryName.get(i).getCategoryID()%>, <%=listCategoryName.get(i).getCategoryName()%></a>
            <br/>
        <%}%>
    </body>
</html>
