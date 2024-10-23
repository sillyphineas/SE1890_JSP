/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;
import java.sql.ResultSet;
/**
 *
 * @author HP
 */
@WebServlet(name="ProductController", urlPatterns={"/ProductURL"})
public class ProductController extends HttpServlet {
   
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
        DAOProduct dao = new DAOProduct();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if(service == null) {
                service = "listAllProducts";
            }
            if (service.equals("deleteProduct")) {
                dao.deleteProduct(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("ProductURL?service=listAllProducts");
            }
            
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsSup = dao.getData("select [SupplierID],[CompanyName] from [dbo].[Suppliers]");
                    ResultSet rsCate = dao.getData("select [CategoryID],[CategoryName] from [dbo].[Categories]");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCate", rsCate);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/insertProduct.jsp");
                    rd.forward(request, response);
                } else {
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data(double check)
                    if (ProductName.equals("")) {
                        out.print("product name is not empty");
                    }
                    //convert

                    int SupplierId = Integer.parseInt(SupplierID);
                    int CategoryId = Integer.parseInt(CategoryID);
                    double UnitPricE = Double.parseDouble(UnitPrice);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock);
                    int UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder);
                    int ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = Integer.parseInt(Discontinued) == 1 ? true : false;
                    Product pro = new Product(ProductName, SupplierId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                    int n = dao.addProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }
                
                
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    Vector<Product> vector = dao.getProducts("select * from Products where ProductID = " + pid);
                    request.setAttribute("vector", vector);
                    
                    ResultSet rsSup = dao.getData("select [SupplierID],[CompanyName] from [dbo].[Suppliers]");
                    ResultSet rsCate = dao.getData("select [CategoryID],[CategoryName] from [dbo].[Categories]");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCate", rsCate);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/updateProduct.jsp");
                    rd.forward(request, response);
                } else {
                    String ProductID = request.getParameter("ProductID");
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data(double check)
                    if (ProductName.equals("")) {
                        out.print("product name is not empty");
                    }
                    //convert
                    int ProductId = Integer.parseInt(ProductID);
                    int SupplierId = Integer.parseInt(SupplierID);
                    int CategoryId = Integer.parseInt(CategoryID);
                    double UnitPricE = Double.parseDouble(UnitPrice);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock);
                    int UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder);
                    int ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = Integer.parseInt(Discontinued) == 1 ? true : false;
                    Product pro = new Product(ProductId ,ProductName, SupplierId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                    int n = dao.updateProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }
                
                
            }
            if (service.equals("listAllProducts")) {
               
                String sql = "select * from Products";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from Products";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "select * from Products where ProductName like '%" + pname + "%'";
                }
                Vector<Product> vector = dao.getProducts(sql);
                //select view
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayProduct.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Product manager");
                rd.forward(request, response);
            }
            
            if (service.equals("DisplayProducts")) {
                int CategoryID = Integer.parseInt(request.getParameter("CategoryID"));
                String sql = "select * from Products as p where p.CategoryID = " + CategoryID;
                Vector<Product> vector = dao.getProducts(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayProduct.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Product manager");
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
