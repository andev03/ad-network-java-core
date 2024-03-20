<%-- 
    Document   : manageRequest
    Created on : Feb 25, 2024, 8:17:17 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <title>Manage Request Page</title>
    </head>
    <body style="margin: 0">

        <c:if test="${sessionScope.admin == null}">
            <c:redirect url="/" />
        </c:if>

        <jsp:useBean id="requestInfor" class="dao.RequestDAO" scope="page"/>
        <jsp:useBean id="pagination" class="utils.Pagination" scope="page"/>
        <jsp:useBean id="locationInfor" class="dao.LocationDAO" scope="page" />
        <jsp:useBean id="employeeAll" class="dao.EmployeeDAO" scope="page" />
        <c:set var="employee" value="${employeeAll.allTechnicianActive}" scope="page"/>


        <c:choose>
            <c:when test="${requestScope.pageIndex == null && requestScope.pageValue == null}">
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="itemTable" value="${requestInfor.requestForManageRequestOfAdmin(beginCus, endCus)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="pageAction" value="${requestScope.requestNameSearch}" scope="request"/>
                <c:set var="itemTable" value="${requestInfor.getScopeRequestByNameCustomer(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${requestInfor.getScopeRequestByNameCustomer(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:otherwise>
                <c:set var="itemTable" value="${requestInfor.requestForManageRequestOfAdmin(beginCus, endCus)}" scope="request" />
            </c:otherwise>
        </c:choose>

        <c:forEach var="request" items="${itemTable}">
            <div class="modal fade" id="formRequest-${request.requestId}" tabindex="-1" aria-labelledby="formRequest" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="formRequest">Edit Form</h5>
                        </div>
                        <div class="modal-body">
                            <!-- Your form goes here -->
                            <form action="/AD_Network/MainController" method="get">
                                <input type="hidden" name="action" value="updateStatusRequestOfAdmin">
                                <input type="hidden" name="requestId" value="${request.requestId}">
                                <input type="hidden" name="statusFirst" value="${request.statusNo.statusName}"/>
                                <!-- Các trường khác của form -->
                                <div class="mb-3">
                                    <label for="statusUpdate" class="form-label">Status</label>
                                    <select class="form-select" id="statusUpdate" name="statusUpdate">
                                        <option value=""></option>
                                        <option value="1">Đã xác nhận</option>
                                        <option value="4">Hủy</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="job" class="form-label">Employee</label>
                                    <select class="form-select" id="job" name="employeeId">
                                        <c:forEach var="emp" items="${employee}">
                                            <option value="${emp.accId}">${emp.userId.fullName}</option>
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
            <!--/////////////////////Thêm set ở đây nữa để sẵn lấy accID////////////////////////////-->
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
        </c:forEach>


        <jsp:include page="/template/headerAdmin.jsp" />
        <div class="search d-flex container justify-content-center" style="margin-top: 20px">
            <div class="col-4">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                        <input type="text" class="form-control" placeholder="Name Customer" name="txtRequestNameCusSearch">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success mb-3" name="action" value="searchRequestCustomerByName">Search</button>
                    </div>
                </form>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <h3 style="color: #1768b2; font-weight: bold">Manage Request</h3>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <select class="form-select">
                            <option selected disabled>Sort by ...</option>
                            <option value="1">Sort by Name Customer</option>
                            <option value="2">Sort by Name Employee</option>
                            <option value="2">Sort by Request Type</option>
                            <option value="2">Sort by Status</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3" name="action" value="sortSubmit">Refresh</button>
                    </div>
                </form>
            </div>
        </div>
        <table class="table table-striped table-hover ">
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
            <tbody style="font-weight: bold;">
                <c:forEach var="request" items="${itemTable}">
                    <tr>
                        <td>${request.requestId}</td>
                        <td>${request.cusId.userId.fullName}</td>
                        <td >${request.cusId.accTel}</td>
                        <c:choose>
                            <c:when test="${loca != null}">
                                <td><button data-bs-toggle="modal" data-bs-target="#location-${request.cusId.userId.userId}" class="btn btn-primary">View</button></td>
                            </c:when>
                            <c:otherwise>
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:otherwise>
                        </c:choose>
                        <td>${request.requestTypeId.typeName}</td>
                        <td>${request.requestDate}</td>
                        <c:choose>
                            <c:when test="${request.statusNo.statusName eq 'Đã xác nhận'}">
                                <td><button type="button" class="btn btn-primary">${request.statusNo.statusName}</button></td>
                                </c:when>
                                <c:when test="${request.statusNo.statusName eq 'Chưa xác nhận'}">
                                <td><button type="button" class="btn btn-info">${request.statusNo.statusName}</button></td>
                                </c:when>
                                <c:when test="${request.statusNo.statusName eq 'Hủy'}">
                                <td><button type="button" class="btn btn-warning">${request.statusNo.statusName}</button></td>
                                </c:when>
                                <c:when test="${request.statusNo.statusName eq 'Hoàn thành'}">
                                <td><button type="button" class="btn btn-success">${request.statusNo.statusName}</button></td>
                                </c:when>
                                <c:when test="${request.statusNo.statusName eq 'Đang xử lí'}">
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
                        <c:choose>
                            <c:when test="${request.statusNo.statusName eq 'Đã xác nhận' || request.statusNo.statusName eq 'Hủy'}">
                                <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formRequest-${request.requestId}">
                                        Edit
                                    </button></td>
                                </c:when>
                                <c:otherwise>
                                <td><button type="button" class="btn btn-secondary">
                                        Edit
                                    </button></td>
                                </c:otherwise>
                            </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="d-flex justify-content-center">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${pageIndex == 1}">
                            <li class="page-item disabled">
                                <button class="page-link" type="submit">
                                    Previous
                                </button>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item disabled">
                                <form action="/AD_Network/MainController" method="post">
                                    <input type="hidden" name="pageChoose" value="Previous"/>
                                    <input type="hidden" name="pageValue" value="${pageValue}"/>
                                    <input type="hidden" name="beginCus" value="${beginCus}"/>
                                    <input type="hidden" name="endCus" value="${endCus}"/>
                                    <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                                    <input type="hidden" name="pageManage" value="pageRequest"/>
                                    <button class="page-link" type="submit" name="action" value="pagination">
                                        Previous
                                    </button>
                                </form>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <li class="page-item">
                        <form action="/AD_Network/MainController" method="post">
                            <input type="hidden" name="pageChoose" value="First Page"/>
                            <input type="hidden" name="pageValue" value="${pageValue}"/>
                            <input type="hidden" name="beginCus" value="${beginCus}"/>
                            <input type="hidden" name="endCus" value="${endCus}"/>
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <input type="hidden" name="pageManage" value="pageRequest"/>
                            <button class="page-link" type="submit" name="action" value="pagination">
                                First Page
                            </button>
                        </form>
                    </li>
                    <li class="page-item">
                        <button class="page-link">
                            ${requestScope.pageIndex}
                        </button>
                    </li>

                    <li class="page-item">
                        <form action="/AD_Network/MainController" method="post">
                            <input type="hidden" name="pageChoose" value="Last Page"/>
                            <input type="hidden" name="pageValue" value="${pageValue}"/>
                            <input type="hidden" name="beginCus" value="${beginCus}"/>
                            <input type="hidden" name="endCus" value="${endCus}"/>
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <input type="hidden" name="pageManage" value="pageRequest"/>
                            <button class="page-link" type="submit" name="action" value="pagination">
                                Last Page
                            </button>
                        </form>
                    </li>
                    <c:choose>
                        <c:when test="${pageIndex == pagination.getIndex(requestInfor.quantityRequest())}">
                            <li class="page-item disabled">
                                <button class="page-link">
                                    Next
                                </button>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <form action="/AD_Network/MainController" method="post" >
                                    <input type="hidden" name="pageChoose" value="Next"/>
                                    <input type="hidden" name="pageValue" value="${pageValue}"/>
                                    <input type="hidden" name="beginCus" value="${beginCus}"/>
                                    <input type="hidden" name="endCus" value="${endCus}"/>
                                    <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                                    <input type="hidden" name="pageManage" value="pageRequest"/>
                                    <button class="page-link" type="submit" name="action" value="pagination">
                                        Next
                                    </button>
                                </form>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
