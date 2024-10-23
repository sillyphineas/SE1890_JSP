<%-- 
    Document   : displayRegion
    Created on : Oct 13, 2024, 10:09:37 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Region"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Region> vector = (Vector<Region>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
        %>
        <form action="RegionURL" method="get">
            <p> 
                Search Name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="clear" >
            </p>
        </form>
        <p> <a href="RegionURL?service=insertRegion"> insert Region </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px"><%=title%></caption>
            <th>RegionID</th>
            <th>RegionDescription</th>
            <th>RegionStatus</th>
            <th>Update</th>
            <th>Delete</th>
            <%for (Region region : vector) {%>
            <tr>
                <td><%=region.getRegionID()%></td>
                <td><%=region.getRegionDescription()%></td>
                <td><%=region.getRegionStatus()%></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
</body>
</html>