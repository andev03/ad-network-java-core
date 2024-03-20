<%-- 
    Document   : headerTechnician
    Created on : Feb 26, 2024, 8:52:00 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A&D Network Admin Header</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
    </head>
    <body>
        <header
            class="container-fluid row sticky-top bg-white"
            style="height: 100px; padding: 0; margin: 0; width: 100%"
            >
            <div class="col-3 d-flex flex-wrap justify-content-center imgBrand" style="height: 100%; padding: 0" >
                <a href="/AD_Network/view/employee/technician/manageRequestTECH.jsp"><img src="/AD_Network/assets/img/brand.png" alt="A&D Telecom" title="A&D Telecom" style="width: 100px" />
                </a>
                <!-- Middle content header-->
            </div>

            <div
                class="col-6 d-flex flex-wrap align-items-center justify-content-center content_header"
                style="height: 100px;"
                >
                <!-- Login và Sign-up-->
                <div class="text-end">
                    <h5>Welcome back, ${sessionScope.technician.userId.fullName}</h5>
                </div>
            </div>

            <div class="col-3" style="height: 100px; padding: 0">
                <a
                    class="d-flex flex-wrap text-dark text-decoration-none align-items-center justify-content-md-center dropdown-toggle link-info loginIcon"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                    href="#"
                    ><img
                        src="/AD_Network/assets/img/icon/person-fill-add.svg"
                        alt="A&D Telecom"
                        title="A&D Telecom"
                        style="width: 40px; height: 100px"
                        />
                    <strong>${sessionScope.technician.userId.fullName}</strong>
                </a>

                <ul
                    class="dropdown-menu text-small shadow"
                    style="width: 25%; margin-left: 0"
                    >
                    <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewProfileEmployee">Profile</a></li>
                    <li><hr class="dropdown-divider" /></li>
                    <form action="/AD_Network/MainController" method="post">
                        <button name="action" value="logoutUser" class="dropdown-item">Sign out</button>
                    </form>
                </ul>
            </div>
        </header>
    </body>
</html>
