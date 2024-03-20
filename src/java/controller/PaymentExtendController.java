/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContractDAO;
import dao.ContractRequestDAO;
import dao.ContractServiceDAO;
import dao.RequestDAO;
import dao.ServiceDAO;
import dao.TransactionDAO;
import dto.Customer;
import dto.Service;
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
public class PaymentExtendController extends HttpServlet {

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
            String serviceParameter = request.getParameter("serviceExtend");
            String serviceNameSplit = serviceParameter.substring(1, serviceParameter.length() - 1);
            String serviceName[] = serviceNameSplit.split(", ");
            Customer cus = (Customer) request.getSession().getAttribute("customer");
            int transactionId = new TransactionDAO().insertTransactionForExtend(cus.getAccId(),
                    Integer.parseInt(request.getParameter("paymentType")),
                    Integer.parseInt(request.getParameter("transCycle")),
                    Double.parseDouble(request.getParameter("totalPrice")));
            int contractNo = 1;
            switch (request.getParameter("transCycle")) {
                case "1":
                    contractNo = 1;
                    break;
                case "6":
                    contractNo = 2;
                    break;
                case "12":
                    contractNo = 3;
                    break;
            }
            int contractId = new ContractDAO().inserContractForExtend(cus.getAccId(), transactionId, contractNo, Integer.parseInt(request.getParameter("transCycle")));
            int requestId = new RequestDAO().insertRequestForExtend(cus.getAccId(), Integer.parseInt(request.getParameter("contractId")));
            new ContractRequestDAO().insertContractRequest(contractId, requestId);
            new ContractDAO().updateStatusContractForExtend(Integer.parseInt(request.getParameter("contractId")));
            int result = 0;
            for (String string : serviceName) {
                Service ser = new ServiceDAO().getServiceForCartByName(new String(string.split("=")[0].getBytes("iso-8859-1"), "utf-8"));
                result = new ContractServiceDAO().insertContractService(ser.getServiceId(), contractId);
            }
            if (result > 0) {
                response.sendRedirect("/AD_Network/MainController?view=viewContractCustomer&result=successfull&contractId=" + request.getParameter("contractId"));
            } else {
                response.sendRedirect("/AD_Network/MainController?view=viewContractCustomer&result=failed&contractId=" + request.getParameter("contractId"));
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
