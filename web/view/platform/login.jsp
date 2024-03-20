<%-- 
    Document   : login
    Created on : Jan 28, 2024, 10:24:27 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/AD_Network/assets/css/login.css" />
        <title>Log In</title>
    </head>

    <body>
        <%String msg1 = (String) request.getAttribute("ErrorAccountEmp");
            String msg2 = (String) request.getAttribute("ErrorLoginEmp");
            String message1 = (msg1 != null) ? msg1 : ((msg2 != null) ? msg2 : null);
        %>
        <%String msg3 = (String) request.getAttribute("ErrorAccountCus");
            String msg4 = (String) request.getAttribute("ErrorLoginCus");
            String message2 = (msg3 != null) ? msg3 : ((msg4 != null) ? msg4 : null);
        %>
        <%String msg5 = (String) request.getAttribute("NotMatch");
            String msg6 = (String) request.getAttribute("Already");
            String msg7 = (String) request.getAttribute("Success");
            String message3 = (msg5 != null) ? msg5 : ((msg6 != null) ? msg6 : ((msg7 != null) ? msg7 : null));
        %>
        <div class="container" id="container">    
            <!--Phần đăng nhập của Employee-->
            <div class="form-container sign-up-container">
                <form action="/AD_Network/MainController" method="post">
                    <h1>Employee</h1>
                    <input type="email" placeholder="Email" name="txtEmail" required/>
                    <input type="password" placeholder="Password" name="txtPassword" required/>
                    <% if (message1 != null) {%>
                    <p style="color: red; font-weight: bold; margin: 5px;">
                        <%= message1%>
                    </p>
                    <% }%>
                    <button class="signin" name="action" value="loginEmployee">Sign In</button>
                </form>
            </div>
            <!--Phần đăng nhập của Customer-->
            <div class="form-container sign-in-container log-in-container">
                <form action="/AD_Network/MainController" method="post">
                    <h1>Customer</h1>
                    <input type="tel" placeholder="Telephone Number" name="txtTelephoneNumber" required/>
                    <input type="password" placeholder="Password" name="txtPassword" required/>
                    <% if (message2 != null) {%>
                    <p style="color: red; font-weight: bold; margin: 5px;">
                        <%= message2%>
                    </p>
                    <% }%>
                    <button class="signin" name="action" value="loginCustomer">Sign In</button>
                    <p>Don't have an account? <a class="anchor" id="regisCus">Registration</a></p>
                </form>
            </div>
            <!--Phần đăng kí của Customer-->
            <div class="form-container registration-container" id="regisForm">
                <form action="/AD_Network/MainController" method="post">
                    <h1>Registration</h1>
                    <div class="Name">
                        <input type="text" placeholder="First Name" name="txtfirstName" required/>
                        <input type="text" placeholder="Last Name" name="txtlastName" required/>
                    </div>
                    <input type="tel" placeholder="Telephone Number" name="txttelephoneRegister" pattern="[0-9]{10,13}" title="Số điện thoại phải từ 10 đến 13 chữ số" required/>
                    <input type="password" placeholder="Password" name="txtpassword" required/>
                    <input type="password" placeholder="Confirm Password" name="txtconfirmPassword" required/>
                    <button class="signin" name="action" value="registerCustomer">Registration</button>
                    <p>Already have an account?<a class="anchor" id="logInCus">Sign In</a></p>
                    <% if (message3 != null) {%>
                    <p style="color: red; font-weight: bold; margin: 5px;">
                        <%= message3%>
                    </p>
                    <% }%>
                </form>
            </div>
            <!--Các banner chào mừng riêng-->
            <div class="overlay-container">
                <div class="overlay-X">
                    <!--Chào mừng Customer-->
                    <div class="overlay-panel-X overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>We're thrilled to have you here again</p>
                        <button class="ghost" id="signInCus">For Customer</button>
                    </div>
                    <!--Chào mừng Employee-->
                    <div class="overlay-panel-X overlay-right">
                        <h1>Welcome to the platform! </h1>
                        <p>Get ready to make a difference and contribute to our team's success</p>
                        <button class="ghost" id="signInEmp">For Employee</button>
                    </div>
                </div>
            </div>
        </div>

        <button class="backButton" onclick="window.location.href = '/AD_Network/'">Back to Home </button>

        <script src="/AD_Network/assets/js/login.js"></script>
    </body>
</html>
