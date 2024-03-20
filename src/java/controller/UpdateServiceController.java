/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class UpdateServiceController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int result = 0;
            String priceUpdate = request.getParameter("priceUpdate");
            String statusSer = request.getParameter("statusSer");
            String serviceId = request.getParameter("serviceId");

            if (!statusSer.equalsIgnoreCase("") && priceUpdate.equalsIgnoreCase("")) {
                result = new ServiceDAO().updateStatusService(Integer.parseInt(serviceId), Integer.parseInt(statusSer));
            } else if (!priceUpdate.equalsIgnoreCase("") && statusSer.equalsIgnoreCase("")) {
                result = new ServiceDAO().updatePriceService(Integer.parseInt(serviceId), Double.parseDouble(priceUpdate));
            } else if (!priceUpdate.equalsIgnoreCase("") && !statusSer.equalsIgnoreCase("")) {
                result = new ServiceDAO().updatePriceAndStatusService(Integer.parseInt(serviceId), Double.parseDouble(priceUpdate), Integer.parseInt(statusSer));
            }
            request.setAttribute("result", result);
            request.setAttribute("typeEdit", request.getParameter("typeEdit"));
            request.getRequestDispatcher("view/employee/admin/manageServices.jsp").forward(request, response);
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
