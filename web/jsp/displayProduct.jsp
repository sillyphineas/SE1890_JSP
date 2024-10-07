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
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
        <form action="ProductURL" method="post">
            <input type="hidden" name="service" value="insertProduct"/>
            <table>
                <caption>Insert Product</caption>
                <tr>
                    <td><label for="ProductID">Product</label></td>
                    <td><input type="text" name="ProductID" id="ProductID" readonly></td>
                </tr>
                <tr>
                    <td><label for="ProductName">ProductName</label></td>
                    <td><input type="text" name="ProductName" id="ProductName"></td>
                </tr>
                <tr>
                    <td><label for="SupplierID">Supplier</label></td>
                    <td><select name="SupplierID" id="SupplierID">
                            <option value="1">Exotic Liquids</option>
                            <option value="2">New Orleans Cajun Delights</option>
                            <option value="3">Grandma Kelly's Homestead</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="CategoryID">Category</label></td>
                    <td><select name="CategoryID" id="CategoryID">
                            <option value="1">Beverages</option>
                            <option value="2">Condiments</option>
                            <option value="3">Confections</option>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td><label for="QuantityPerUnit">QuantityPerUnit</label></td>
                    <td><input type="text" name="QuantityPerUnit" id="QuantityPerUnit"></td>
                </tr>
                <tr>
                    <td><label for="UnitPrice">UnitPrice</label></td>
                    <td><input type="text" name="UnitPrice" id="UnitPrice"></td>
                </tr>
                <tr>
                    <td><label for="UnitsInStock">UnitsInStock</label></td>
                    <td><input type="text" name="UnitsInStock" id="UnitsInStock"></td>
                </tr>
                <tr>
                    <td><label for="UnitsOnOrder">UnitsOnOrder</label></td>
                    <td><input type="text" name="UnitsOnOrder" id="UnitsOnOrder"></td>
                </tr>
                <tr>
                    <td><label for="ReorderLevel">ReorderLevel</label></td>
                    <td><input type="text" name="ReorderLevel" id="ReorderLevel"></td>
                </tr>
                <tr>
                    <td><label for="Discontinued">Discontinued</label></td>
                    <td>
                        <input type="radio" name="Discontinued" id="Discontinued" value="0">Discontinued
                        <input type="radio" name="Discontinued" id="Discontinued" value="1">Continued
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertProduct" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
    <tr></tr>
</body>
</html>
