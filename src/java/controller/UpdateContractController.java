/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContractDAO;
import dao.ContractRequestDAO;
import dao.RequestDAO;
import dao.TransactionDAO;
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
public class UpdateContractController extends HttpServlet {

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
            // Phần này hơi rối ông k hiểu đoạn nào thì hỏi tôi
            String contractIdParameter = request.getParameter("contractid");
            String customerIdParameter = request.getParameter("customerId");
            String employeeIdParameter = request.getParameter("employeeId");
            if (contractIdParameter != null && customerIdParameter != null && employeeIdParameter != null) {
                // Lấy requestId từ hành động update request
                int requestId = new RequestDAO().updateStatusRequestByContractId(Integer.parseInt(employeeIdParameter), Integer.parseInt(contractIdParameter));
                // Từ requestId đã tìm được ở trên, đưa xuống để tìm contractId muốn cập nhật
                int contractId = new ContractRequestDAO().getContractIdByRequestId(requestId);
                // update contract từ contractId đã lấy ở trên
                new ContractDAO().updateInformationContractForUpdateContract(Integer.parseInt(employeeIdParameter), contractId);
                // Lấy transaction từ contract đã update ở trên
                int transactionId = new ContractDAO().getTransactionIdByContractId(contractId);
                // update status transaction theo transaction đã lấy ở trên
                new TransactionDAO().updateTransaction(transactionId);
                // update status của contract muốn gia hạn thành đã gia hạn 
                int result = new ContractDAO().updateStatusContractAfterUpdate(Integer.parseInt(contractIdParameter));
                // chuyển hướng đến trang contract của admin và đưa ra câu thông báo
                if (result > 0) {
                    // dùng response để chuyển hướng để người dùng k thể tạo 1 request chứa hành động đã thực thi mỗi khi reload lại trang
                    response.sendRedirect("/AD_Network/MainController?view=viewManageContract&result=succesfull");
                } else {
                    response.sendRedirect("/AD_Network/MainController?view=viewManageContract&result=failed");
                }
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
