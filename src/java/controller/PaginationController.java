/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContractDAO;
import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.RequestDAO;
import dao.ServiceDAO;
import dao.TransactionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Pagination;

/**
 *
 * @author nguye
 */
public class PaginationController extends HttpServlet {

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
            String pageIndex = request.getParameter("pageIndex");
            String pageChooseCheck = request.getParameter("pageChoose");
            String getBeginCus = request.getParameter("beginCus");
            String getEndCus = request.getParameter("endCus");
            String pageManage = request.getParameter("pageManage");
            String pageValue = request.getParameter("pageValue");
            String pageAction = request.getParameter("pageAction");
            int beginCus = 0;
            int endCus = 0;
            Pagination lastPage = new Pagination();
            String url = "";
            switch (pageChooseCheck) {
                case "Previous":
                    pageIndex = Integer.parseInt(pageIndex) - 1 + "";
                    beginCus = Integer.parseInt(getBeginCus) - 5;
                    endCus = Integer.parseInt(getEndCus) - 5;
                    break;
                case "First Page":
                    pageIndex = 1 + "";
                    beginCus = 1;
                    endCus = 5;
                    break;
                case "Last Page":
                    switch (pageManage) {
                        case "pageContract":
                            pageIndex = lastPage.getIndex(new ContractDAO().quantityContract()) + "";
                            break;
                        case "pageClient":
                            switch (pageAction) {
                                case "Search":
                                    pageIndex = lastPage.getIndex(new CustomerDAO().quantityCustomerSearch()) + "";
                                    break;
                                default:
                                    pageIndex = lastPage.getIndex(new CustomerDAO().quantityCustomer()) + "";
                                    break;
                            }
                            break;
                        case "pagePayment":
                            pageIndex = lastPage.getIndex(new TransactionDAO().quantityTracnsaction()) + "";
                            break;
                        case "pageServices":
                            pageIndex = lastPage.getIndex(new ServiceDAO().quantityService()) + "";
                            break;
                        case "pageRequest":
                            pageIndex = lastPage.getIndex(new RequestDAO().quantityRequest()) + "";
                            break;
                        case "pageTechnician":
                            pageIndex = lastPage.getIndex(new EmployeeDAO().quantityTechnician()) + "";
                            break;
                    }

                    beginCus = (Integer.parseInt(pageIndex) * 5) - 4;
                    endCus = Integer.parseInt(pageIndex) * 5;
                    break;
                case "Next":
                    pageIndex = Integer.parseInt(pageIndex) + 1 + "";
                    beginCus = Integer.parseInt(getEndCus) + 1;
                    endCus = Integer.parseInt(getEndCus) + 5;
                    break;
            }

            request.setAttribute("beginCus", beginCus);
            request.setAttribute("endCus", endCus);
            request.setAttribute("pageIndex", pageIndex);
            switch (pageValue) {
                case "Search":
                    request.setAttribute("pageAction", pageAction);
                    request.setAttribute("pageValue", pageValue);
                    break;
                case "viewTransByDate":
                    request.setAttribute("pageAction", pageAction);
                    request.setAttribute("pageValue", pageValue);
                    break;
                case "sortByStatus":
                case "sortByForm":
                case "sortByName":
                    request.setAttribute("pageAction", pageAction);
                    request.setAttribute("pageValue", pageValue);
                    break;
            }
            switch (pageManage) {
                case "pageContract":
                    url = "view/employee/admin/manageContract.jsp";
                    break;
                case "pageClient":
                    url = "view/employee/admin/manageClient.jsp";
                    break;
                case "pagePayment":
                    url = "view/employee/admin/managePayment.jsp";
                    break;
                case "pageServices":
                    url = "view/employee/admin/manageServices.jsp";
                    break;
                case "pageRequest":
                    url = "view/employee/admin/manageRequest.jsp";
                    break;
                case "pageTechnician":
                    url = "view/employee/admin/manageTechnician.jsp";
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
