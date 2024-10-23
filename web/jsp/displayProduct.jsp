<%-- 
    Document   : displayProduct
    Created on : Oct 3, 2024, 11:07:38 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Product> vector = (Vector<Product>)request.getAttribute("data");
            String title = (String)request.getAttribute("title");
        %>
        <form action="ProductURL" method="get">
            <p> 
                Search Name: <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="clear" >
            </p>
        </form>
        <p> <a href="ProductURL?service=insertProduct"> insert Product </a></p>
        <p align="right"> <a href="CartURL?service=showCart"> Show Cart </a></p>
        <table border="1">
            <caption style="color:red;font-size:40px"><%=title%></caption>
            <th>ProductID</th>
            <th>ProductName</th>
            <th>SupplierID</th>
            <th>CategoryID</th>
            <th>QuantityPerUnit</th>
            <th>UnitPrice</th>
            <th>UnitsInStock</th>
            <th>UnitsOnOrder</th>
            <th>ReorderLevel</th>
            <th>Discontinued</th>
            <th>Update</th>
            <th>Delete</th>
            <th>Add To Cart</th>
            <%for (Product pro : vector) {%>
            <tr>
                <td><%=pro.getProductID()%></td>
                <td><%=pro.getSupplierID()%></td>
                <td><%=pro.getProductName()%></td>
                <td><%=pro.getCategoryID()%></td>
                <td><%=pro.getQuantityPerUnit()%></td>
                <td><%=pro.getUnitPrice()%></td>
                <td><%=pro.getUnitsInStock()%></td>
                <td><%=pro.getUnitsOnOrder()%></td>
                <td><%=pro.getReorderLevel()%></td>
                <td><%=pro.isDiscontinued()%></td>
                <td><a href="ProductURL?service=updateProduct&pid=<%=pro.getProductID()%>">Update product</td>
                <td><a href="ProductURL?service=deleteProduct&pi<%=pro.getProdud=<%=pro.getProductID()%>">Delete product</a></td>
                <td><a href="CartURL?service=add2cart&pid=<%=pro.getProductID()%>">Add to cart</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
