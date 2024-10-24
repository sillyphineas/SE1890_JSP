/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;
import entity.Employee;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOEmployee;

/**
 *
 * @author HP
 */
@WebServlet(name="EmployeeController", urlPatterns={"/EmployeeURL"})
public class EmployeeController extends HttpServlet {
   
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
        DAOEmployee dao = new DAOEmployee();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllEmployees";
            }
            if (service.equals("deleteEmployee")) {
                dao.deleteEmployee(Integer.parseInt(request.getParameter("eid")));
                response.sendRedirect("EmployeeURL?service=listAllEmployees");
            }
            
            if(service.equals("insertEmployee")){
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsRep = dao.getData("select [EmployeeID], [LastName] from [dbo].[Employees]");
                    request.setAttribute("rsRep", rsRep);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/insertEmployee.jsp");
                    rd.forward(request, response);
                } else {
                    //String EmployeeID = request.getParameter("EmployeeID");
                    String ReportsTo = request.getParameter("ReportsTo");
                    String LastName = request.getParameter("LastName");
                    String FirstName = request.getParameter("FirstName");
                    String Title = request.getParameter("Title");
                    String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                    String BirthDate = request.getParameter("BirthDate");
                    String HireDate = request.getParameter("HireDate");
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String HomePhone = request.getParameter("HomePhone");
                    String Extension = request.getParameter("Extension");
                    String Photo = request.getParameter("Photo");
                    String Notes = request.getParameter("Notes");
                    String PhotoPath = request.getParameter("PhotoPath");
                    String EmployStatus = request.getParameter("EmployStatus");

                    int ReportsTO = Integer.parseInt(ReportsTo);
                    int EmployStatuS = Integer.parseInt(EmployStatus);
                    Employee epl = new Employee(ReportsTO, LastName, 
                            FirstName, Title, TitleOfCourtesy, BirthDate, 
                            HireDate, Address, City, Region, PostalCode, 
                            Country, HomePhone, Extension, Photo, Notes, PhotoPath, EmployStatuS);
                    int n = dao.insertEmployee(epl);
                    response.sendRedirect("EmployeeURL?service=listAllEmployees");
                }
                
            }
            if (service.equals("listAllEmployees")) {
                /* TODO output your page here. You may use following sample code. */
//                HttpSession session = request.getSession();
//
//                // Lấy tham số từ form
//                String submit = request.getParameter("submit");
//                String sort = request.getParameter("sort");
//                String lastName;
//
//                if (submit != null) {
//                    // Nếu có tìm kiếm, lưu từ khóa tìm kiếm vào session
//                    lastName = request.getParameter("Lname");
//                    session.setAttribute("lastName", lastName);
//                } else {
//                    // Nếu không có submit, lấy từ khóa từ session
//                    lastName = (String) session.getAttribute("lastName");
//                }
//
//                String sortBy = request.getParameter("sortBy");
//                String sortOrder = request.getParameter("sortOrder");
//
//                // Câu truy vấn SQL ban đầu
//                String sql = "SELECT * FROM Employees";
//                if (lastName != null && !lastName.isEmpty()) {
//                    sql += " WHERE LastName LIKE '%" + lastName + "%'";
//                }
//
//                // Kiểm tra xem người dùng có yêu cầu sắp xếp không
//                if (sort != null && sortBy != null && sortOrder != null) {
//                    sql += " ORDER BY " + sortBy + " " + sortOrder;
//                }
                String sql = "select * from Employees";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from Employees";
                } else {
                    String Lname = request.getParameter("Lname");
                    sql = "select * from Employees where LastName like '%" + Lname + "%'";
                }
                // Truy vấn dữ liệu từ database
                Vector<Employee> vector = dao.getEmployee(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayEmployee.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Employee manager");
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
