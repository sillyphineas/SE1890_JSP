<%-- 
    Document   : insertRegion
    Created on : Oct 13, 2024, 10:13:50 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="RegionURL" method="post">
            <input type="hidden" name="service" value="insertRegion"/>
        <table>
            <caption>Insert Region</caption>
            <tr> 
                <td><label for="RegionID">RegionID</label></td>
                <td><input type="text" name="RegionID" id="RegionID" readonly></td>
            </tr>
            <tr>
                <td><label for="RegionDescription">RegionDescription</label></td>
                <td><input type="text" name="RegionDescription" id="RegionDescription"></td>
            </tr>
            <tr>
                <td><label for="RegionStatus">RegionStatus</label></td>
                <td><input type="text" name="RegionStatus" id="RegionStatus"></td>
            </tr>
            <tr>
                <td><input type="submit" value="insertRegion" name="submit"></td>
                <td><input type="reset" value="Clear"></td>
            </tr>
        </table>
    </form>
    </body>
</html>
