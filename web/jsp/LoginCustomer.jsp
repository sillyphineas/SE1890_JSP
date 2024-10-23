<%-- 
    Document   : LoginCustomer
    Created on : Oct 17, 2024, 11:32:23 AM
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
        <form method="POST" action="../CustomerURL">
            <p>Username: <input type="text" name="username"/>
            <p>Password: <input type="password" name="password"/>
                <input type="hidden" name="service" value="LoginFunction">
                <input type="submit" value="Login" name="submitLogin"/>
                <input type="reset" value="Clear"/>
        </form>
    </body>
</html>
