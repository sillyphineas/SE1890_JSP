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
import model.DAOCustomer;
import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
import java.util.Vector;
@WebServlet(name="CustomerController", urlPatterns={"/CustomerURL"})
public class CustomerController extends HttpServlet {
   
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
        DAOCustomer dao = new DAOCustomer();
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllCustomers";
            }
            if (service.equals("insertCustomer")) {
                
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsSup = dao.getData("select [SupplierID],[CompanyName] from [dbo].[Suppliers]");
                    ResultSet rsCate = dao.getData("select [CategoryID],[CategoryName] from [dbo].[Categories]");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCate", rsCate);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/insertCustomer.jsp");
                    rd.forward(request, response);
                } else {
                    String CustomerID = request.getParameter("CustomerID");
                    String CompanyName = request.getParameter("CompanyName");
                    String ContactName = request.getParameter("ContactName");
                    String ContactTitle = request.getParameter("ContactTitle");
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String Phone = request.getParameter("Phone");
                    String Fax = request.getParameter("Fax");

                    Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                    int n = dao.addCCustomer(cus);
                    response.sendRedirect("CustomerURL?service=listAllCustomers");
                }
            }
            if (service.equals("listAllCustomers")) {
                String sql = "select * from Customers";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from Customers";
                } else {
                    String cusId = request.getParameter("cusId");
                    sql = "select * from Customers where CustomerID like '%" + cusId + "%'";
                }
                Vector<Customer> vector = dao.getCustomers(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayCustomer.jsp");
                request.setAttribute("data", vector);
                request.setAttribute("title", "Customer manager");
                rd.forward(request, response);
            }
            
            if (request.getParameter("submitLogin") != null) {
                Vector<Customer> listCus = dao.getCustomers("select * from Customers");
                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                for (Customer account : listCus) {
                    if (account.getPhone().equals(username) && account.getFax().equals(password)) {
                        request.setAttribute("account", account);
                        RequestDispatcher rd = request.getRequestDispatcher("/jsp/LoginSuccess.jsp");
                        rd.forward(request, response);
                    } else {
                        
                    }
                }

            } else {
                
            }
            
            if (service.equals("loginCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/jsp/LoginnCustomer.jsp").forward(request, response);
                } else {
                    Customer customer = dao.login(request.getParameter("username"), request.getParameter("password"));
                    if (customer==null) {
                        request.setAttribute("message", "login failed");
                        request.getRequestDispatcher("/jsp/LoginnCustomer.jsp").forward(request, response);
                    } else {
                        //login success --> insert customer into session
                        session.setAttribute("customer", customer);
                        //request.getRequestDispatcher("/jsp/displayCustomer.jsp").forward(request, response);
                        response.sendRedirect("CustomerURL?service=listAllCustomers");
                    }
                }
                
            }
            if (service.equals("logoutCustomer")) {
                session.invalidate();
                response.sendRedirect("CustomerURL");
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
