/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContractDAO;
import dao.ContractServiceDAO;
import dao.ServiceDAO;
import dao.TransactionDAO;
import dto.Customer;
import dto.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class EndPaymentController extends HttpServlet {

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
            int paymentType = Integer.parseInt(request.getParameter("paymentType"));
            int transCycle = Integer.parseInt(request.getParameter("transCycle"));
            Customer cus = (Customer) request.getSession().getAttribute("customer");
            int cusId = cus.getAccId();
            double totalPrice = Double.parseDouble(new DecimalFormat("##############").format(Double.parseDouble(request.getParameter("totalPrice"))));
            if (request.getParameter("typeBuy").equalsIgnoreCase("buyNow")) {
                int serviceId = Integer.parseInt(request.getParameter("service"));
                new TransactionDAO().insertForBuy(cusId, paymentType, transCycle, totalPrice, serviceId);
                response.sendRedirect("/AD_Network/MainController?view=viewHomePage&value=true");
            } else {
                Map<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("cart");
                int transactionId = new TransactionDAO().insertForCart(cusId, paymentType, transCycle, totalPrice);
                int contractId = new ContractDAO().insertContractForCart(cusId, transactionId, transCycle);

                for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                    Service service = new ServiceDAO().getServiceForCartByName(entry.getKey());
                    int serviceId = service.getServiceId();
                    new ContractServiceDAO().insertContractService(serviceId, contractId);
                }
                request.getSession().setAttribute("cart", null);
                response.sendRedirect("/AD_Network/MainController?view=viewHomePage");
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
