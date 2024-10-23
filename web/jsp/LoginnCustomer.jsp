<%-- 
    Document   : LoginnCustomer
    Created on : Oct 19, 2024, 3:59:47 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String message = (String) request.getAttribute("message");%>
        <%=(message!=null?message:"")%>
        <form method="POST" action="CustomerURL">
            <p>Username: <input type="text" name="username"/>
            <p>Password: <input type="password" name="password"/>
                <input type="hidden" name="service" value="loginCustomer">
                <input type="submit" value="Login" name="submit"/>
                <input type="reset" value="Clear"/>
        </form>
    </body>
</html>
