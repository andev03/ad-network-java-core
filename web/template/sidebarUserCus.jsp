<%-- 
    Document   : sidebarUserExtra
    Created on : Feb 25, 2024, 6:59:21 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/AD_Network/assets/css/sidebarStyle.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <title>A&D Network Sidebar</title>
    </head>
    <body>
        <div class="col-3 fullItem" style="
             height: 100%;
             top: 100px;
             position: fixed;
             padding: 0;
             ">
            <nav id="navbar-example3" class="h-100 flex-column align-items-stretch border-end">
                <nav class="nav nav-pills flex-column">
                    <a class="nav-link hover" href="/AD_Network/MainController?view=viewHomePage">Home</a>
                    <a class="nav-link hover" href="/AD_Network/view/customer/customerPage.jsp#about">About</a>
                    <a class="nav-link hover" href="/AD_Network/view/customer/customerPage.jsp#service">Service</a>
                    <nav class="nav nav-pills flex-column">
                        <a class="nav-link ms-3 my-1 hover" href="/AD_Network/MainController?view=viewInternetService">Internet</a>
                        <a class="nav-link ms-3 my-1 hover" href="/AD_Network/MainController?view=viewCloudService">Cloud</a>
                        <a class="nav-link ms-3 my-1 hover" href="/AD_Network/MainController?view=viewTelevisionService">Television</a>
                        <a class="nav-link ms-3 my-1 hover" href="/AD_Network/MainController?view=viewCameraService">Camera</a>
                    </nav>
                    <a class="nav-link hover" href="/AD_Network/MainController?view=viewComingsoon">Support</a>
                    <a class="nav-link hover" href="/AD_Network/view/customer/customerPage.jsp#details">Details</a>
                </nav>
            </nav>
        </div>
    </body>
</html>
