<%-- 
    Document   : headerUser
    Created on : Feb 22, 2024, 10:10:52 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <title>A&D Network Header</title>
    </head>
    <body>
        <header class="container-fluid row sticky-top bg-white" style="height: 100px; padding: 0; margin: 0">

            <div class="col-3 d-flex flex-wrap justify-content-center imgBrand" style="height: 100%; padding: 0" >
                <a href="/AD_Network/MainController?view=viewHomePage"><img src="/AD_Network/assets/img/brand.png" alt="A&D Telecom" title="A&D Telecom" style="width: 100px" />
                </a>
                <!-- Middle content header-->
            </div>

            <div class="col-6 d-flex flex-wrap align-items-center content_header" style="height: 100px;">
                <!-- Search-->

                <form class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto" role="search" action="/AD_Network/view/platform/comingsoon.jsp">
                    <div class="input-group">
                        <span class="input-group-text" style="color: #FFFFFF; background-color: #212529;"><i class="fa fa-search"></i></span>
                        <input type="search" class="form-control form-control-dark text-bg-dark text-light" placeholder="Search..." aria-label="Search" onclick="return window.confirm('This featured is under developed!')"/>
                    </div>
                </form>

                <!-- Login và Sign-up-->
                <div class="text-end">
                    <a href="/AD_Network/MainController?view=viewLoginCustomer" class="text-decoration-none btn btn-warning" style="margin: 10px">Login</a>
                </div>
            </div>


            <div class="col-3" style="height: 100px; padding: 0;">
                <a class="d-flex flex-wrap text-dark text-decoration-none align-items-center justify-content-md-center link-info loginIcon"
                   href="/AD_Network/"><img src="/AD_Network/assets/img/icon/person-fill-add.svg" alt="A&D Telecom" title="A&D Telecom"
                                         style="width: 40px; height: 100px;" />
                    <h5>Guest</h5>
                </a>
            </div>
        </header>
    </body>
</html>
