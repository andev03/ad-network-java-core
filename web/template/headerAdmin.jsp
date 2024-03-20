<%-- 
    Document   : headerAdmin
    Created on : Feb 25, 2024, 10:36:14 AM
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
            <div
                class="col-3 d-flex flex-wrap imgBrand"
                style="height: 100%; padding: 0; justify-content: space-around;"

                >

                <div style="height: 100px; padding: 0; display: flex; align-items: center; justify-content: center;">
                    <a

                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                        href="#"
                        ><img
                            src="/AD_Network/assets/img/icon/bar-dropdown.svg"
                            alt="A&D Telecom"
                            title="A&D Telecom"
                            style="width: 40px; height: 100px;"
                            />
                    </a>

                    <ul
                        class="dropdown-menu text-small shadow"
                        style="width: 21.2%; margin-left: 0;"
                        >
                        <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewManageClient">Manage Client</a></li>
                        <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewManageTechnician">Manage Technican</a></li>
                        <li><hr class="dropdown-divider"/></li>
                        <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewManageContract">Manage Contract</a></li>
                        <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewManagePayment">Manage Payment</a></li>
                        <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewManageService">Manage Service</a></li>
                        <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewManageRequest">Manage Request</a></li>
                    </ul>
                </div>
                <a href="/AD_Network/view/employee/admin/manageClient.jsp"
                   ><img
                        src="/AD_Network/assets/img/brand.png"
                        alt="A&D Telecom"
                        title="A&D Telecom"
                        style="width: 100px"
                        />
                </a>
                <!-- Middle content header-->
            </div>

            <div
                class="col-6 d-flex flex-wrap align-items-center justify-content-evenly content_header"
                style="height: 100px;"
                >
                <nav
                    class="d-flex align-items-center " 
                    >
                    <a class="btn btn-success" href="/AD_Network/view/employee/admin/modeClient/modeClient.jsp" 
                       role="button" style="margin: 2px;"
                       >Mode Client</a
                    >
                    <a
                        class="btn btn-danger"
                        href="/AD_Network/view/employee/admin/manageClient.jsp"
                        role="button" style="margin: 2px;"
                        >Admin Home</a
                    >
                </nav>
                <!-- Login và Sign-up-->
                <div class="text-end">

                    <h5>Welcome back, ${sessionScope.admin.userId.fullName}</h5>
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
                    <strong>${sessionScope.admin.userId.fullName}</strong>
                </a>

                <ul
                    class="dropdown-menu text-small shadow"
                    style="width: 25%; margin-left: 0"
                    >
                    <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewProfileEmployee">Profile</a></li>
                    <li><hr class="dropdown-divider" /></li>
                    <li>
                        <form action="/AD_Network/MainController" method="post">
                            <button name="action" value="logoutUser" class="dropdown-item">Sign out</button>
                        </form>
                    </li>
                </ul>
            </div>
        </header>
    </body>
</html>
