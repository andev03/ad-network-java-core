/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import dao.UsersDAO;
import dto.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author user
 */
public class RegisterCustomerController extends HttpServlet {

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
            String firstName = request.getParameter("txtfirstName");
            String lastName = request.getParameter("txtlastName");
            String telephone = request.getParameter("txttelephoneRegister");
            String pass = request.getParameter("txtpassword");
            String confirmPass = request.getParameter("txtconfirmPassword");

            UsersDAO userDao = new UsersDAO();
            CustomerDAO cusDao = new CustomerDAO();
            if (cusDao.isTelephoneRegister(telephone)) {
                // Nếu số điện thoại đã được đăng ký, chuyển hướng người dùng đến trang lỗi
                request.setAttribute("Already", "This telephone number has already been registered");
                request.getRequestDispatcher("view/platform/login.jsp").forward(request, response);
            } else if (!pass.equalsIgnoreCase(confirmPass)) {
                // Nếu mật khẩu và mật khẩu xác nhận không khớp, chuyển hướng người dùng đến trang lỗi
                request.setAttribute("NotMatch", "Your password and your confirm password is not match");
                request.getRequestDispatcher("view/platform/login.jsp").forward(request, response);
            } else {
                // Thêm một người dùng mới vào bảng Users
                int userId = userDao.insertUsersRegister(firstName, lastName);

                // Kiểm tra xem việc chèn có thành công không
                if (userId > 0) {
                    // Nếu thành công, chèn thông tin đăng ký khách hàng vào bảng Customer
                    int result = cusDao.insertCustomerRegister(userId, telephone, pass);

                    if (result > 0) {
                        // Nếu cả hai thao tác chèn đều thành công, chuyển hướng người dùng đến trang thành công
                        request.setAttribute("Success", "Register success! Please Log In again in Customer LogIn");
                        request.getRequestDispatcher("view/platform/login.jsp").forward(request, response);
                    }
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
