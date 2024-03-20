<%-- 
    Document   : contractView
    Created on : Mar 18, 2024, 9:29:47 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <title>Contract View Page</title>
    </head>
    <body>

        <c:if test="${sessionScope.customer == null}">
            <c:redirect url="/" />
        </c:if>

        <!--Lấy dữ liệu bên header-->
        <c:set var="contract" value="${param.contractId}" />
        <c:set var="contractId" value="${contract}" />
        <jsp:useBean id="contractDetail" class="dao.ContractDAO" scope="page"/>
        <c:set var="contractInfor" value="${contractDetail.getContract(contractId)}" scope="page"/>
        <jsp:useBean id="contractServiceDetail" class="dao.ContractServiceDAO" scope="page"/>
        <c:set var="contractServiceInfor" value="${contractServiceDetail.getContractServiceByContract(contractId)}" scope="page"/>

        <jsp:include page="/template/headerLogIn.jsp" />
        <main class="container-fluid row" style="padding: 0; margin: 0">
            <jsp:include page="/template/sidebarUserCus.jsp" />
            <div class="col-3"></div>
            <div class="col-9" data-bs-spy="scroll" data-bs-target="#list-example" data-bs-smooth-scroll="true" tabindex="0"
                 style="padding: 0;">
                <div class="container py-4 justify-content-center text-center">
                    <div class="text-center">
                        <c:choose>
                            <c:when test="${param.result eq 'successfull'}">
                                <h4 style="color: green">Extend contract Successfully! 
                                    Please wait for our confirmation.</h4>
                                </c:when>
                                <c:when test="${param.result eq 'failed'}">
                                <h4 style="color: red">Extend contract failed!</h4>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="text-center">
                        <h2 style="color: #1768b2; font-weight: bold">Contract</h2>
                    </div>
                    <div class="m-3">
                        <label class="form-label fw-bold" for="employeeName">Employee Name</label>
                        <input type="text" readonly class="form-control" id="employeeName" value="${contractInfor.empId.userId.fullName}">
                    </div>
                    <div class="row m-3">
                        <div class="col">
                            <label class="form-label fw-bold" for="startDate">Start Date</label>
                            <input type="text" readonly class="form-control" id="startDate" value="${contractInfor.startDate}">
                        </div>
                        <div class="col">
                            <label class="form-label fw-bold" for="endDate">End Date</label>
                            <input type="text" readonly class="form-control" id="endDate" value="${contractInfor.endDate}">
                        </div>
                    </div>
                    <div class="row m-3">
                        <div class="col">
                            <label class="form-label fw-bold" for="price">Price</label>
                            <c:set var="pr" value="${contractInfor.transactionId.transAmount}"/>
                            <fmt:formatNumber var="price" value="${pr}" type="number" pattern="###,###"/>
                            <input type="text" readonly class="form-control" id="price" value="${price}">
                        </div>
                        <div class="col">
                            <label class="form-label fw-bold" for="cycle">Cycle</label>
                            <input type="text" readonly class="form-control" id="cycle" value="${contractInfor.contractNo.typeName} month">
                        </div>
                        <div class="col">
                            <label class="form-label fw-bold" for="status">Status</label>
                            <c:choose>
                                <c:when test="${contractInfor.statusNo.statusName eq 'Đã hết hạn' || contractInfor.statusNo.statusName eq 'Cần gia hạn'}">
                                    <input style="color: red;" type="text" readonly class="form-control fw-bold" id="status" value="${'Đã hết hạn'}">
                                </c:when>
                                <c:when test="${contractInfor.statusNo.statusName eq 'Còn hiệu lực'}">
                                    <input style="color: green;" type="text" readonly class="form-control fw-bold" id="status" value="${contractInfor.statusNo.statusName}">
                                </c:when>
                                <c:when test="${contractInfor.statusNo.statusName eq 'Đã gia hạn'}">
                                    <input style="color: blue;" type="text" readonly class="form-control fw-bold" id="status" value="${contractInfor.statusNo.statusName}">
                                </c:when>
                                <c:when test="${contractInfor.statusNo.statusName eq 'Chưa xác nhận'}">
                                    <input style="color: rgb(173, 216, 230);" type="text" readonly class="form-control fw-bold" id="status" value="${contractInfor.statusNo.statusName}">
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <div class="justify-content-center m-5">
                        <h4 class="fw-bold" style="color: #1768b2">Detail</h4>
                        <table class="table table-bordered table-hover table-striped">
                            <thead class="table-info">
                            <th>Service Name</th>
                            <th>Device Name</th>
                            <th>Price (per month)</th>
                            </thead>
                            <tbody>
                                <c:forEach var="contractServiceView" items="${contractServiceInfor}">
                                    <tr>
                                        <td>${contractServiceView.serviceId.serviceName}</td>
                                        <td>${contractServiceView.serviceId.deviceId.deviceName}</td>
                                        <c:set var="pr" value="${contractServiceView.serviceId.price}" />
                                        <fmt:formatNumber var="price" value="${pr}" type="number" pattern="###,###"/>
                                        <td>${price}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <c:choose>
                        <c:when test="${contractInfor.statusNo.statusName eq 'Đã hết hạn'}">
                            <form action="/AD_Network/MainController" class="d-flex justify-content-center" style="padding: 15px;">
                                <input type="hidden" name="action" value="extendContract">
                                <input type="hidden" name="contractId" value="${contractId}">
                                <input type="hidden" name="contractService" value="${contractServiceInfor}">
                                <input type="hidden" name="typeAction" value="sendRequest">
                                <button type="submit" href="" class="text-decoration-none btn btn-primary" style="width: 80%; ">Gia hạn</button>
                            </form>
                        </c:when>
                        <c:when test="${contractInfor.statusNo.statusName eq 'Cần gia hạn' || contractInfor.statusNo.statusName eq 'Chưa xác nhận'}">
                            <div class="d-flex justify-content-center" style="padding: 15px;">
                                <a onclick="return window.confirm('Hợp đồng của bạn đang chờ xử lí!')" class="text-decoration-none btn btn-secondary" style="width: 80%; ">Gia hạn</a>
                            </div>
                        </c:when>
                        <c:when test="${contractInfor.statusNo.statusName eq 'Đã gia hạn'}">
                            <div class="d-flex justify-content-center" style="padding: 15px;">
                                <a onclick="return window.confirm('Hợp đồng của bạn đã được xử lí thành công! Vui lòng kiểm tra lại ở mục Contract!')" class="text-decoration-none btn btn-secondary" style="width: 80%; ">Gia hạn</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="d-flex justify-content-center" style="padding: 15px;">
                                <a onclick="return window.confirm('Hợp đồng của bạn vẫn còn hiệu lực!')" class="text-decoration-none btn btn-secondary" style="width: 80%; ">Gia hạn</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>