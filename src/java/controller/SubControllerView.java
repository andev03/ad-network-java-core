/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class SubControllerView extends HttpServlet {

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
            String url = "";
            String view = request.getParameter("view");

            switch (view) {
                case CONSTANTS.VIEWCOMINGSOON:
                    url = "view/platform/comingsoon.jsp";
                    break;
                case CONSTANTS.VIEWINTERNETSERVICE:
                    url = "view/customer/service/internetService.jsp";
                    break;
                case CONSTANTS.VIEWCLOUDSERVICE:
                    url = "view/customer/service/cloudService.jsp";
                    break;
                case CONSTANTS.VIEWTELEVISIONSERVICE:
                    url = "view/customer/service/televisionService.jsp";
                    break;
                case CONSTANTS.VIEWCAMERASERVICE:
                    url = "view/customer/service/cameraService.jsp";
                    break;
                case CONSTANTS.VIEWHOMEPAGE:
                    url = "view/customer/customerPage.jsp";
                    break;
                case CONSTANTS.VIEWLOGIN:
                    url = "view/platform/login.jsp";
                    break;

                //-----------------------------Customer-----------------------------------------
                case CONSTANTS.VIEWEDITPROFILECUS:
                    url = "view/customer/editProfileCustomer.jsp";
                    break;
                case CONSTANTS.VIEWPROFILECUS:
                    url = "view/customer/profileCustomer.jsp";
                    break;
                case CONSTANTS.VIEWCONTRACTCUS:
                    url = "view/customer/contractView.jsp";
                    break;
                case CONSTANTS.VIEWHISTORYREQUESTCUS:
                    url = "view/customer/historyView.jsp";
                    break;
                //-----------------------------EMPLOYEE-----------------------------------------
                case CONSTANTS.VIEWMANAGECLIENT:
                    url = "view/employee/admin/manageClient.jsp";
                    break;
                case CONSTANTS.VIEWMANAGETECHNICIAN:
                    url = "view/employee/admin/manageTechnician.jsp";
                    break;
                case CONSTANTS.VIEWMANAGECONTRACT:
                    url = "view/employee/admin/manageContract.jsp";
                    break;
                case CONSTANTS.VIEWMANAGEPAYMENT:
                    url = "view/employee/admin/managePayment.jsp";
                    break;
                case CONSTANTS.VIEWMANAGESERVICE:
                    url = "view/employee/admin/manageServices.jsp";
                    break;
                case CONSTANTS.VIEWMANAGEREQUEST:
                    url = "view/employee/admin/manageRequest.jsp";
                    break;
                case CONSTANTS.VIEWEDITPROFILEEMP:
                    url = "view/employee/editProfileEmployee.jsp";
                    break;
                case CONSTANTS.VIEWPROFILEEMP:
                    url = "view/employee/profileEmployee.jsp";
                    break;
            }
            request.getRequestDispatcher(url).forward(request, response);
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
