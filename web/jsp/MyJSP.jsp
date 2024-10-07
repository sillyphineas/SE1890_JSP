<%-- 
    Document   : MyJSP
    Created on : Oct 3, 2024, 10:21:26 AM
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
        <!--Script: code thuần java trong cặp <%//Code java%>-->
        <%
            int a = 100;
            out.println("<h2>a="+a+"</h2>");
        %>
        <!<!-- Expression -->
        <%//=parameter%>
        <h2 style="color:red">value a= <%=a*2%></h2>
        <%
            for (int i = 10; i <= 1000; i+=10) {  
        %>
        <hr width="<%=i%>">
        <%}%>
        <p>declare <%!//code%>
        <%int localMax=1000;%>
        <%!int globalMax=2000;%>
        <P> local max = <%=localMax%>
        <P> global max = <%=globalMax%>
        <%! String displayMax() {
            //out.print("local max = " + localMax);
            return "global max = " + globalMax;
        }%>
    </body>
</html>
