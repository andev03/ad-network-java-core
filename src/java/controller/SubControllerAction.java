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
public class SubControllerAction extends HttpServlet {

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
            String ac = request.getParameter("action");
            switch (ac) {
                case CONSTANTS.LOGINEMPLOYEE:
                    url = "LoginEmployeeController";
                    break;
                case CONSTANTS.LOGOUTUSER:
                    url = "LogoutUserController";
                    break;
                case CONSTANTS.PAGINATION:
                    url = "PaginationController";
                    break;
                case CONSTANTS.SORTBYALL:
                    url = "SortController";
                    break;
                case CONSTANTS.VIEWPAYMENTBYDATE:
                    url = "ViewPaymentByDate";
                    break;
                case CONSTANTS.VIEWREQUESTBYDATE:
                    url = "ViewRequestByDateController";
                    break;
                case CONSTANTS.UPDATESTATUSREQUESTOFTECHNI:
                    url = "UpdateStatusRequestOfTechni";
                    break;
                case CONSTANTS.VIEWREQUESTBYDATEFORTECHNI:
                    url = "ViewRequestByDateForTechniController";
                    break;
                case CONSTANTS.SORTREQUESTFORTECHNI:
                    url = "SortRequestForTechniController";
                    break;
                case CONSTANTS.EDITCUSTOMER:
                    url = "EditCustomerController";
                    break;
                case CONSTANTS.CREATEFORSERVICES:
                    url = "CreateForServicesController";
                    break;
                case CONSTANTS.UPDATESERVICE:
                    url = "UpdateServiceController";
                    break;
                case CONSTANTS.CREATECONTRACT:
                    url = "CreateContractController";
                    break;
                case CONSTANTS.UPDATECONTRACT:
                    url = "UpdateContractController";
                    break;
                case CONSTANTS.UPDATESTATUSTECHNI:
                    url = "UpdateStatusTechniController";
                    break;
                case CONSTANTS.PAYMENTEXTEND:
                    url = "PaymentExtendController";
                    break;
                case CONSTANTS.SETTECHNIFORREQUEST:
                    url = "SetTechniForRequestController";
                    break;
//-------------------------------------Customer----------------------------------
                case CONSTANTS.LOGINCUSTOMER:
                    url = "LoginCustomerController";
                    break;
                case CONSTANTS.SEARCHBYPHONE:
                    url = "SearchCustomerController";
                    break;
                case CONSTANTS.BUYACTION:
                    url = "BuyController";
                    break;
                case CONSTANTS.ADDTOCART:
                    url = "AddToCartController";
                    break;
                case CONSTANTS.ENDPAYMENT:
                    url = "EndPaymentController";
                    break;
                case CONSTANTS.REGISTERCUSTOMER:
                    url = "RegisterCustomerController";
                    break;
                case CONSTANTS.EXTENDCONTRACT:
                    url = "ExtendContractController";
                    break;
//-------------------------------------Admin----------------------------------
                case CONSTANTS.SEARCHBYEMAIL:
                    url = "SearchTechnicianController";
                    break;
                case CONSTANTS.SEARCHSERVICEBYNAME:
                    url = "SearchServiceController";
                    break;
                case CONSTANTS.SEARCHBYCUSNAMECONTRACT:
                    url = "SearchCustomerNameContract";
                    break;
                case CONSTANTS.SEARCHPAYMENTBYCUSNAME:
                    url = "SearchPaymentNameController";
                    break;
                case CONSTANTS.SEARCHREQUESTBYCUSNAME:
                    url = "SearchRequestNameController";
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
