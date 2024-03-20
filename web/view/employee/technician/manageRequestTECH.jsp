<%-- 
    Document   : manageRequestTECH
    Created on : Feb 26, 2024, 8:59:31 PM
    Author     : user
--%>


<%@page import="dao.RequestDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="AD_Network/assets/css/guest.css" />
        <title>Manage Request Page</title>
    </head>
    <body style="margin: 0">
        
        <c:if test="${sessionScope.technician == null}">
            <c:redirect url="/" />
        </c:if>

        <jsp:include page="/template/headerTechnician.jsp" />
        <jsp:useBean id="requestInfor" class="dao.RequestDAO" scope="page"/>
        <jsp:useBean id="pagination" class="utils.Pagination" scope="page"/>
        <jsp:useBean id="reStatus" class="dao.ReStatusDAO" scope="page"/>
        <c:choose>
            <c:when test="${requestScope.pageValue eq 'viewRequestByDate' && requestScope.date != null}" >
                <c:set var="itemTable" value="${requestInfor.requestTechniByDate(requestScope.date, sessionScope.technician.accId)}" scope="page" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'showFollow' && requestScope.showValue != null}" >
                <c:set var="itemTable" value="${requestInfor.requestShowByChoice(requestScope.showValue, sessionScope.technician.accId)}" scope="page" />
            </c:when>
            <c:otherwise>
                <c:set var="itemTable" value="${requestInfor.getRequestForView(sessionScope.technician.accId)}" scope="page" />
            </c:otherwise>
        </c:choose>
        <div class="search d-flex container justify-content-center" style="margin-top: 20px">
            <div class="col-4">
                <form action="/AD_Network/MainController" method="post" >
                    <input type="hidden" name="action" value="viewRequestByDateForTechni">
                    <input type="date" id="date" name="date">
                    <input type="submit" value="Submit">
                </form>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <h3 style="color: #1768b2; font-weight: bold">Request</h3>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <form class="row g-3" action="/AD_Network/MainController" method="get">
                    <input type="hidden" name="action" value="sortReqestForTechni"/>
                    <div class="col-auto">
                        <select class="form-select" name="showValue">
                            <option selected disabled>Home</option>
                            <option value="1">Đã xác nhận</option>
                            <option value="2">Đang xử lí</option>
                            <option value="3">Hoàn Thành</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success mb-3" name="action" value="sortAction">Refresh</button>
                    </div>
                </form>
            </div>
        </div>
        <table class="table">
            <thead class="table-dark">
                <tr>
                    <th >ID</th>
                    <th >Full Name</th>
                    <th >Phone Number</th>
                    <th >Location</th>
                    <th >Request Type</th>
                    <th >Request Date</th>
                    <th >Status</th>
                    <th >Request Content</th>
                    <th >Action</th>
                </tr>
            </thead>
            <tbody class="table-group-divider" style="font-weight: bold;">
                <c:forEach var="request" items="${itemTable}">
                    <!--/////////////////////Thêm set ở đây nữa để sẵn lấy accID////////////////////////////-->
                    <jsp:useBean id="locationInfor" class="dao.LocationDAO" scope="page" />
                    <c:set var="loca" value="${locationInfor.getLocationByUserId(request.cusId.userId.userId)}" scope="request"/>
                    <!--////////////////////////////////////////////////////////////////////////////////////-->
                <div class="modal fade" id="location-${request.cusId.userId.userId}" tabindex="-1" aria-labelledby="location" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="location">Location Detail</h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <!-- Your form goes here -->
                                <p>${loca.locationDetail}, ${loca.districtId.districtName}, ${loca.cityId.cityName}, ${loca.provinceId.provinceName}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <tr>
                    <td >${request.requestId}</td>
                    <td >${request.cusId.userId.fullName}</td>
                    <td >${request.cusId.userId.phoneNumber}</td>
                    <c:choose>
                        <c:when test="${loca != null}">
                            <td><a data-bs-toggle="modal" data-bs-target="#location-${request.cusId.userId.userId}" href="#">View</a></td>
                        </c:when>
                        <c:otherwise>
                            <td style="color: red"><c:out value="Empty"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td >${request.requestTypeId.typeName}</td>
                    <td >${request.requestDate}</td>
                    <c:choose>
                        <c:when test="${request.statusNo.statusName eq 'Đã xác nhận'}">
                            <td><button type="button" class="btn btn-info">${request.statusNo.statusName}</button></td>
                            </c:when>
                            <c:when test="${request.statusNo.statusName eq 'Đang xử lí'}">
                            <td><button type="button" class="btn btn-warning">${request.statusNo.statusName}</button></td>
                            </c:when>
                            <c:when test="${request.statusNo.statusName eq 'Hoàn thành'}">
                            <td><button type="button" class="btn btn-success">${request.statusNo.statusName}</button></td>
                            </c:when>
                            <c:when test="${request.statusNo.statusName eq 'Hủy'}">
                            <td><button type="button" class="btn btn-danger">${request.statusNo.statusName}</button></td>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${request.requestContent == null}">
                            <td style="color: red">None</td>
                        </c:when>
                        <c:otherwise>
                            <td>${request.requestContent}</td>
                        </c:otherwise>
                    </c:choose>

                    <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formRequest-${request.requestId}">
                            Edit
                        </button></td>
                </tr>
                <div class="modal fade" id="formRequest-${request.requestId}" tabindex="-1" aria-labelledby="formRequest" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="formRequest">Edit Form</h5>
                            </div>
                            <div class="modal-body">
                                <!-- Your form goes here -->
                                <form action="/AD_Network/MainController" method="get">
                                    <input type="hidden" name="action" value="updateStatusRequestOfTechni">
                                    <input type="hidden" name="requestId" value="${request.requestId}">
                                    <!-- Các trường khác của form -->
                                    <div class="mb-3">
                                        <label for="statusUpdate" class="form-label">Status</label>
                                        <select class="form-select" id="selectReason" name="statusUpdate">
                                            <c:forEach var="requestStatus" items="${reStatus.allReStatusForTechni}">
                                                <option value="${requestStatus.statusNo}">${requestStatus.statusName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" >Close</button>
                                        <button type="submit" class="btn btn-primary" onclick="return window.confirm('Are you sure to save this change?')">Save changes</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center">
        <c:choose>
            <c:when test="${requestScope.condition eq 0}">
                <h4 style="color: red">Update failed!</h4>
            </c:when>
            <c:when test="${requestScope.condition eq 1}">
                <h4 style="color: green">Update successful</h4>
            </c:when>
            <c:when test="${requestScope.condition eq 2}">
                <h4 style="color: red">System error</h4>
            </c:when>
        </c:choose>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
