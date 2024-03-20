<%--
    Document   : comingsoon
    Created on : Jan 28, 2024, 10:17:50 PM
    Author     : nguye
--%>

<%@page import="dto.Customer"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css">
        <title>Coming Soon</title>
    </head>
    <body style="margin: 0">

        <c:if test="${sessionScope.admin == null}">
            <c:redirect url="/" />
        </c:if>

        <!-- Header of the website -->
        <jsp:include page="/template/headerAdmin.jsp" />
        <main class="container-fluid row" style="padding: 0; margin: 0">
            <jsp:include page="/view/employee/admin/modeClient/template/sidebarModeExtra.jsp" />
            <div class="col-3"></div>
            <div class="col-9 d-flex justify-content-center" data-bs-spy="scroll"
                 data-bs-target="#list-example" data-bs-smooth-scroll="true" tabindex="0"
                 style="padding: 0;">
                <img src="/AD_Network/assets/img/banner_content/comingsoon.png" alt="Coming Soon!" style="width: 50%;">
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>