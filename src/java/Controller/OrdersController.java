/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import entity.Bill;
import entity.Orders;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOBill;
import model.DAOOrders;

/**
 *
 * @author HP
 */
@WebServlet(name="OrdersController", urlPatterns={"/OrdersControllerURL"})
public class OrdersController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAOOrders dao = new DAOOrders();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllOrders";
            }
            if (service.equals("deleteOrders")) {
                dao.deleteOrders(Integer.parseInt(request.getParameter("oid")));
                response.sendRedirect("OrdersControllerURL?service=listAllOrders");
            }

            if (service.equals("insertOrders")) {
                // Lấy dữ liệu từ request
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsEmp = dao.getData("select [EmployeeID],[LastName] from [dbo].[Employees]");
                    ResultSet rsCus = dao.getData("select [CustomerID],[CompanyName] from [dbo].[Customers]");
                    ResultSet rsShip = dao.getData("select [ShipperID],[CompanyName] from [dbo].[Shippers]");
                    request.setAttribute("rsEmp", rsEmp);
                    request.setAttribute("rsCus", rsCus);
                    request.setAttribute("rsShip", rsShip);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/insertOrders.jsp");
                    rd.forward(request, response);
                } else {
                    String CustomerID = request.getParameter("CustomerID");
                    String EmployeeID = request.getParameter("EmployeeID");
                    String OrderDate = request.getParameter("OrderDate");
                    String RequiredDate = request.getParameter("RequiredDate");
                    String ShippedDate = request.getParameter("ShippedDate");
                    String ShipVia = request.getParameter("ShipVia");
                    String Freight = request.getParameter("Freight");
                    String ShipName = request.getParameter("ShipName");
                    String ShipAddress = request.getParameter("ShipAddress");
                    String ShipCity = request.getParameter("ShipCity");
                    String ShipRegion = request.getParameter("ShipRegion");
                    String ShipPostalCode = request.getParameter("ShipPostalCode");
                    String ShipCountry = request.getParameter("ShipCountry");

                    // check
                    if (CustomerID.equals("")) {
                        out.print("CustomerID must not be empty");
                        return;
                    }

                    // convert value
                    int EmployeeId = Integer.parseInt(EmployeeID);
                    int ShipViA = Integer.parseInt(ShipVia);
                    double FreighT = Double.parseDouble(Freight);

                    // tạo đối tượng mới

                    //Orders or = new Orders(ShipViA, CustomerID, EmployeeId, OrderDate, RequiredDate, ShippedDate, ShipViA, FreighT, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                    Orders or = new Orders(CustomerID, EmployeeId, OrderDate, RequiredDate, ShippedDate, ShipViA, FreighT, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                    // Thêm đơn hàng vào database
                    int n = dao.insertOrders(or);
                    response.sendRedirect("OrdersControllerURL?service=listAllOrders");
                }
            }

            if (service.equals("listAllOrders")) {
                String sql = "SELECT * FROM Orders";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT * FROM Orders";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "SELECT * FROM Orders\n"
                            + "Where CustomerID like '%" + pname + "%'";
                }
                Vector<Orders> vector = dao.getOrders(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayOrders.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Orders manager");
                rd.forward(request, response);
            }
            
            if (service.equals("checkCus")) {
                String CustomerID = request.getParameter("CusId");
                String sql = "SELECT o.OrderID, o.CustomerID, o.EmployeeID, o.OrderDate, o.RequiredDate, o.ShippedDate, o.ShipVia, \n"
                        + "o.Freight, o.ShipName, o.ShipAddress, o.ShipCity, o.ShipRegion, o.ShipPostalCode, o.ShipCountry\n"
                        + "FROM Orders AS o\n"
                        + "LEFT JOIN [dbo].[Customers] AS c\n"
                        + "ON c.CustomerID = o.CustomerID WHERE c.CustomerID = '" + CustomerID + "'";
                Vector<Orders> listOrder = dao.getOrders(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayOrders.jsp");
                //set data
                request.setAttribute("data", listOrder);
                request.setAttribute("title", "Orders manager");
                rd.forward(request, response);
            }
            
            if (service.equals("GenBill")) {
                int OrderId = Integer.parseInt(request.getParameter("orderId"));
                String sql = "select o.OrderID, o.OrderDate, o.RequiredDate, c.ContactName, e.LastName, p.ProductID, p.ProductName,\n"
                        + "od.UnitPrice, od.Quantity, od.Discount\n"
                        + "from [dbo].[Orders] as o join [dbo].[Customers] as c on o.CustomerID = c.CustomerID\n"
                        + "join Employees as e on o.EmployeeID = e.EmployeeID\n"
                        + "join [dbo].[Order Details] as od on o.OrderID = od.OrderID\n"
                        + "join Products as p on od.ProductID = p.ProductID\n"
                        + "where o.OrderID = '"+ OrderId +"'";
                DAOBill daoB = new DAOBill();
                Vector<Bill> listBill = daoB.getBill(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/PaymentPage.jsp");
                //set data
                request.setAttribute("data", listBill);
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
