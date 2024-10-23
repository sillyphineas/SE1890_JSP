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
import entity.Region;
import jakarta.servlet.RequestDispatcher;
import model.DAORegion;

/**
 *
 * @author HP
 */
@WebServlet(name="RegionController", urlPatterns={"/RegionURL"})
public class RegionController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAORegion dao = new DAORegion();
        
        try (PrintWriter out = response.getWriter()) {
            
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllRegion";
            }
            if (service.equals("deleteRegion")) {
                dao.deleteRegion(Integer.parseInt(request.getParameter("regionID")));
                response.sendRedirect("RegionURL?service=listAllRegion");
                
            }
            
            if(service.equals("insertRegion")){
                String submit = request.getParameter("submit");
                if (submit == null) {
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/insertRegion.jsp");
                    rd.forward(request, response);
                } else {
                    //get data
                    String RegionID = request.getParameter("RegionID");
                    String RegionDescription = request.getParameter("RegionDescription");
                    String RegionStatus = request.getParameter("RegionStatus");

                    //int regionID = Integer.parseInt(RegionID);
                    int RegionStatuS = Integer.parseInt(RegionStatus);
                    Region reg = new Region(RegionDescription, RegionStatuS) ;
                    int n = dao.addRegion(reg);
                    response.sendRedirect("RegionURL?service=listAllRegion");
                }
               
            }
            if(service.equals("listAllRegion")){
                String sql = "SELECT * FROM Region";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT * FROM Region";
                } else {
                    String rname = request.getParameter("rname");
                    sql = "  SELECT * FROM Region\n"
                            + "  where RegionDescription like '%" + rname + "%'";
                }
                Vector<Region> vector = dao.getRegion(sql);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/displayRegion.jsp");
                //set data
                request.setAttribute("data", vector);
                request.setAttribute("title", "Region manager");
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
