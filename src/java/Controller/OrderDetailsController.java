/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import entity.OrderDetails;
import jakarta.servlet.RequestDispatcher;
import java.sql.ResultSet;
import model.DAOOrderDetails;
/**
 *
 * @author HP
 */
@WebServlet(name="OrderDetailsController", urlPatterns={"/OrderDetailsController"})
public class OrderDetailsController extends HttpServlet {
   
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
        DAOOrderDetails dao = new DAOOrderDetails();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllOrderDetails";
            }
//            if (service.equals("deleteOrderDetails")) {
//                dao.deleteOrderDetails(Integer.parseInt(request.getParameter("oid")),Integer.parseInt(request.getParameter("pid")));
//                response.sendRedirect("OrderDetailsController?service=listAllOrderDetails");
//            }
            
            if(service.equals("insertOrderDetails")){
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsOrder = dao.getData("select [OrderID],[OrderDate] from [dbo].[Orders]");
                    ResultSet rsPro = dao.getData("select [ProductID],[ProductName] from [dbo].[Products]");
                    request.setAttribute("rsOrder", rsOrder);
                    request.setAttribute("rsPro", rsPro);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/insertOrderDetails.jsp");
                    rd.forward(request, response);
                } else {
                    String OrderID = request.getParameter("OrderID");
                    String ProductID = request.getParameter("ProductID");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String Quantity = request.getParameter("Quantity");
                    String Discount = request.getParameter("Discount");
                    String OrderDetailStatus = request.getParameter("OrderDetailStatus");
                    
                    int OrderId = Integer.parseInt(OrderID);
                    int ProductId = Integer.parseInt(ProductID);
                    double UnitPricE = Double.parseDouble(UnitPrice);
                    int QuantitY = Integer.parseInt(Quantity);
                    double DiscounT = Double.parseDouble(Discount);
                    int OrderDetailStatuS = Integer.parseInt(OrderDetailStatus);

                    OrderDetails od = new OrderDetails(OrderId, ProductId, UnitPricE, QuantitY, DiscounT, OrderDetailStatuS);
                    int n = dao.addOrderDetails(od);
                    response.sendRedirect("OrderDetailsController?service=listAllOrderDetails");
                }
                
            }
            if (service.equals("listAllOrderDetails")) {
                String sql = "select * from [dbo].[Order Details]";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from [Order Details]";
                } else {
                    String Lname = request.getParameter("Lname");
                    sql = "select * from [Order Details] where OrderID = " + Lname;
                }
                // Truy vấn dữ liệu từ database
                Vector<OrderDetails> vector = dao.getOrderDetails(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayOrderDetail.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Order Detail manager");
                rd.forward(request, response);
            }

        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
