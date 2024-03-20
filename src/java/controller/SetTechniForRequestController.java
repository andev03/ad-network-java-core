/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RequestDAO;
import dto.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author nguye
 */
public class SetTechniForRequestController extends HttpServlet {

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
            String requestIdPara = request.getParameter("requestId");
            String statusUpdate = request.getParameter("statusUpdate");
            String employeeIdPara = request.getParameter("employeeId");
            int result = 0;
            System.out.println(statusUpdate.equalsIgnoreCase("4"));
            if ((requestIdPara != null || statusUpdate != null) && employeeIdPara != null) {
                if (request.getParameter("statusFirst").equalsIgnoreCase("Hủy") && statusUpdate.equalsIgnoreCase("1")) {
                    String sql = "Update Request Set empId = ?, statusNo = 1, finishDate = null Where requestId = ?";
                    result = new RequestDAO().setRequestToTechni(Integer.parseInt(employeeIdPara), Integer.parseInt(requestIdPara), sql);
                } else if (statusUpdate.equalsIgnoreCase("4") && request.getSession().getAttribute("admin") != null) {
                    Employee emp = (Employee) request.getSession().getAttribute("admin");
                    String sql = "Update Request Set empId = ?, statusNo = 4, finishDate = GETDATE() Where requestId = ?";
                    result = new RequestDAO().setRequestToTechni(emp.getAccId(), Integer.parseInt(requestIdPara), sql);
                } else {
                    String sql = "Update Request Set empId = ?, statusNo = 1 Where requestId = ?";
                    result = new RequestDAO().setRequestToTechni(Integer.parseInt(employeeIdPara), Integer.parseInt(requestIdPara), sql);
                }
            }
            if (result > 0) {
                response.sendRedirect("/AD_Network/MainController?view=viewManageRequest&result=successfull");
            } else {
                response.sendRedirect("/AD_Network/MainController?view=viewManageRequest&result=failed");
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
// if (true) {
//                    String sql = "Update Request Set empId = ?, statusNo = 1 Where requestId = ?";
//                    result = new RequestDAO().setRequestToTechni(Integer.parseInt(employeeIdPara), Integer.parseInt(requestIdPara), sql);
//                } else
