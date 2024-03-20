<%-- 
    Document   : headerLogIn
    Created on : Feb 25, 2024, 9:59:40 AM
    Author     : user
--%>

<%@page import="dto.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:useBean id="contract" class="dao.ContractDAO" scope="page"/>
        <c:set var="contractInfor" value="${contract.getContractByCustomerId(sessionScope.customer.accId)}" scope="page"/>
        <header
            class="container-fluid row sticky-top bg-white"
            style="height: 100px; padding: 0; margin: 0"
            >
            <div
                class="col-3 d-flex flex-wrap justify-content-center imgBrand"
                style="height: 100%; padding: 0"

                >
                <a href="/AD_Network/view/customer/customerPage.jsp"
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
                class="col-6 d-flex flex-wrap align-items-center content_header"
                style="height: 100px"
                >
                <!-- Search-->

                <form class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto" role="search" action="/AD_Network/view/platform/comingsoon.jsp">
                    <div class="input-group">
                        <span class="input-group-text" style="color: #FFFFFF; background-color: #212529;"><i class="fa fa-search"></i></span>
                        <input type="search" class="form-control form-control-dark text-bg-dark text-light" placeholder="Search..." aria-label="Search" onclick="return window.confirm('This featured is under developed!')"/>
                    </div>
                </form>
                <!-- Login và Sign-up-->
                <div class="text-end">
                    <h5>Welcome back, ${sessionScope.customer.userId.fullName}</h5>
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
                    <strong>${sessionScope.customer.userId.fullName}</strong>
                </a>

                <ul
                    class="dropdown-menu text-small shadow"
                    style="width: 25%; margin-left: 0"
                    >
                    <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewProfileCustomer">Profile</a></li>  
                        <c:choose>
                            <c:when test="${not empty contractInfor}">
                            <li><a class="dropdown-item">Contract</a></li>
                                <c:forEach items="${contractInfor}" var="contract">
                                    <c:set var="i" value="${i+1}" scope="page"/> 
                                <ul>
                                    <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewContractCustomer&contractId=${contract.contractId}">Contract ${i}</a></li>
                                </ul>     
                                </c:forEach>
                            <li><a class="dropdown-item" href="/AD_Network/MainController?view=viewHistoryCustomer">History</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a class="dropdown-item" onclick="return window.confirm('You don\'t have any Contract yet!')" style="cursor: pointer;">Contract</a></li>
                            <li><a class="dropdown-item" onclick="return window.confirm('You don\'t have any Request History yet!')" style="cursor: pointer;">History</a></li>
                            </c:otherwise>
                        </c:choose>
                    <li><hr class="dropdown-divider" /></li>
                    <form action="/AD_Network/MainController" method="post">
                        <button name="action" value="logoutUser" class="dropdown-item">Sign out</button>
                    </form>
                </ul>
            </div>
        </header>
    </body>
</html>