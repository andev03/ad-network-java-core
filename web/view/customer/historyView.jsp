<%-- 
    Document   : historyView
    Created on : Mar 19, 2024, 1:39:13 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History View Page</title>
    </head>
    <body>
        
        <c:if test="${sessionScope.customer == null}">
            <c:redirect url="/" />
        </c:if>

        <jsp:useBean id="requestDetail" class="dao.RequestDAO" scope="page"/>
        <c:set var="requestInfor" value="${requestDetail.getAllRequestByCusId(sessionScope.customer.accId)}" scope="page"/>

        <jsp:include page="/template/headerLogIn.jsp" />
        <main class="container-fluid row" style="padding: 0; margin: 0">
            <jsp:include page="/template/sidebarUserCus.jsp" />
            <div class="col-3"></div>
            <div class="col-9" data-bs-spy="scroll" data-bs-target="#list-example" data-bs-smooth-scroll="true" tabindex="0"
                 style="padding: 0;">
                <div class="container py-4 justify-content-center text-center">
                    <div class="text-center">
                        <h2 style="color: #1768b2; font-weight: bold">Request History</h2>
                    </div>
                    <div class="justify-content-center m-5">
                        <table class="table table-bordered table-hover table-striped">
                            <thead class="table-info">
                            <th>No</th>
                            <th>Request ID</th>
                            <th>Request Type</th>
                            <th>Request Date</th>
                            <th>Finish Date</th>
                            <th>Name Employee</th>
                            <th>Status</th>
                            <th>Content</th>
                            </thead>
                            <tbody>
                                <c:forEach var="requestView" items="${requestInfor}">
                                    <c:set var="i" value="${i+1}" scope="page"/> 
                                    <tr>
                                        <td>${i}</td>
                                        <td>${requestView.requestId}</td>
                                        <c:choose>
                                            <c:when test="${requestView.requestTypeId.typeName eq 'Lắp đặt'}">
                                                <td class="fw-bold" style="color: #FF4500;">${requestView.requestTypeId.typeName}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="fw-bold" style="color: #008000;">${requestView.requestTypeId.typeName}</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td>${requestView.requestDate}</td>
                                        <td>${requestView.finishDate}</td>
                                        <td>${requestView.empId.userId.fullName}</td>
                                        <td class="fw-bold">${requestView.statusNo.statusName}</td>
                                        <c:choose>
                                            <c:when test="${requestView.requestContent != null}">
                                                <td class="fw-bold">${requestView.requestContent}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="fw-bold" style="color: red;">Empty</td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
