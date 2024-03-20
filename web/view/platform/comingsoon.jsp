<%--
    Document   : comingsoon
    Created on : Jan 28, 2024, 10:17:50 PM
    Author     : nguye
--%>

<%@page import="dto.Customer"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css">
        <title>Coming Soon</title>
    </head>
    <body style="margin: 0">
        <!-- Header of the website -->
        <%
            if (session.getAttribute("customer") == null) {
        %>
        <jsp:include page="/template/headerUser.jsp" />
        <%} else {%>
        <jsp:include page="/template/headerLogIn.jsp" />
        <%}%>

        <main class="container-fluid row" style="padding: 0; margin: 0">
            <%
                if (session.getAttribute("customer") == null) {
            %>
            <jsp:include page="/template/sidebarUserExtra.jsp" />
            <%} else {%>
            <jsp:include page="/template/sidebarUserCus.jsp" />
            <%}%>
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