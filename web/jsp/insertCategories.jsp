<%-- 
    Document   : insertCategories
    Created on : Oct 13, 2024, 8:57:19 PM
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
        <div>
            <form action="CategoriesController" method="post">
                <input type="hidden" name="service" value="insertCategories">

                <table>
                    <caption>
                        InsertCategories
                    </caption>

                    <tr>
                        <td><label for="CategoryID">CategoryID</label></td>
                        <td>
                            <input type="text" name="CategoryID" id="CategoryID" readonly />
                        </td>
                    </tr>

                    <tr>
                        <td><label for="CategoryName">CategoryName</label></td>
                        <td><input type="text" name="CategoryName" id="CategoryName" /></td>
                    </tr>

                    <tr>
                        <td><label for="Description">Description</label></td>
                        <td><input type="text" name="Description" id="Description" /></td>
                    </tr>

                    <tr>
                        <td><label for="Picture">Picture</label></td>
                        <td><input type="text" name="Picture" id="Picture" /></td>
                    </tr>


                    <tr>
                        <td><input type="submit" name="submit" value="insertCategories"></td>
                        <td><input type="reset" value="clear"></td>
                    </tr>


                </table>
            </form>
        </div>
    </body>
</html>
