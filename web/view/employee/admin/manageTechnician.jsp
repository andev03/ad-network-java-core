<%-- 
    Document   : manageTechnician
    Created on : Feb 25, 2024, 8:17:36 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/tableDataStyle.css" />
        <title>Manage Technician Page</title>
    </head>
    <body style="margin: 0">
        
        <c:if test="${sessionScope.admin == null}">
            <c:redirect url="/" />
        </c:if>
        
        <jsp:useBean id="technicianInfor" class="dao.EmployeeDAO" scope="page" />
        <jsp:useBean id="pagination" class="utils.Pagination" scope="page"/>
        <jsp:useBean id="locationInfor" class="dao.LocationDAO" scope="page" />
        <c:set var="data" value="${logErrorData.allLogErrorDetai}" scope="page"/>
        <c:choose>
            <c:when test="${requestScope.pageIndex == null && requestScope.pageValue == null}">
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="itemTable" value="${technicianInfor.getTechnicianByScope(beginCus, endCus)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="pageAction" value="${requestScope.emailSearch}" scope="request"/>
                <c:set var="itemTable" value="${technicianInfor.getScopeEmployeeByEmail(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${technicianInfor.getScopeEmployeeByEmail(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${(requestScope.pageValue eq 'sortByNameEmp' || requestScope.pageValue eq 'sortByStatusEmp') && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/> 
                <c:set var="itemTable" value="${technicianInfor.sortEmployeeByAction(beginCus, endCus, requestScope.pageValue)}" scope="request" />
            </c:when>
            <c:when test="${(requestScope.pageValue eq 'sortByNameEmp' || requestScope.pageValue eq 'sortByStatusEmp') && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${technicianInfor.sortEmployeeByAction(beginCus, endCus, requestScope.pageValue)}" scope="request" />
            </c:when>
            <c:otherwise>
                <c:set var="itemTable" value="${technicianInfor.getTechnicianByScope(beginCus, endCus)}" scope="request" />
            </c:otherwise>
        </c:choose>
        <c:forEach var="technician" items="${itemTable}"> 
            <div class="modal fade" id="formTech-${technician.accId}" tabindex="-1" aria-labelledby="formTechnician" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="formTechnician">Edit Form</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="/AD_Network/MainController" method="get">
                            <input type="hidden" name="action" value="updateStatusTechni">
                            <div class="modal-body">
                                <!-- Your form goes here -->
                                <div class="mb-3">
                                    <label for="TechnicianStatus" class="form-label">Status</label>
                                    <div class="radio-input">
                                        <label>
                                            <input type="radio" name="statusUpdate"  class="status-unblock" value="0" ${technician.statusNo.statusName == 'Unblock' ? 'checked' : ''}>
                                            <span>Unblock</span>
                                        </label>
                                        <label>
                                            <input type="radio" name="statusUpdate"  class="status-block" value="1" ${technician.statusNo.statusName == 'Block' ? 'checked' : ''}>
                                            <span>Block</span>
                                        </label>
                                        <span class="selection"></span>

                                    </div>
                                </div>
                                <!-- Các trường khác của form -->
                                <c:if test="${technician.statusNo.statusName == 'Block'}"> 
                                    <div class="mb-3">
                                        <label for="banReason" class="form-label">Block Reason</label>
                                        <p style="color: red;">Employee have received reason why they were Blocked</p>
                                    </div>
                                </c:if> 
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="techniUpdate" value="${technician.accId}">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" >Close</button>
                                <button type="submit" class="btn btn-primary" onclick="return window.confirm('Are you sure to save the changes?')">Save changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--/////////////////////Thêm set ở đây nữa để sẵn lấy accID////////////////////////////-->
            <c:set var="local" value="${locationInfor.getLocationByUserId(technician.userId.userId)}" scope="request"/>
            <!--////////////////////////////////////////////////////////////////////////////////////-->
            <div class="modal fade" id="location-${technician.accId}" tabindex="-1" aria-labelledby="location" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="location">Location Detail</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Your form goes here -->
                            <p>${local.locationDetail}, ${local.districtId.districtName}, ${local.cityId.cityName}, ${local.provinceId.provinceName}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <%@include file="/template/headerAdmin.jsp" %>

        <div class="search d-flex container justify-content-center" style="margin-top: 20px">
            <div class="col-4">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                        <input type="text" class="form-control" placeholder="Email" name="txtEmailSearch">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success mb-3" name="action" value="searchEmployeeByEmail">Search</button>
                    </div>
                </form>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <h3 style="color: #1768b2; font-weight: bold">Manage Technician</h3>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <form class="row g-3" action="/AD_Network/MainController" method="get">
                    <input type="hidden" name="pageManage" value="pageTechnician"/>
                    <div class="col-auto">
                        <select class="form-select" name="sortChoose">
                            <option selected disabled>Sort by ...</option>
                            <option value="sortByNameEmp">Sort by Name</option>
                            <option value="sortByStatusEmp">Sort by Status</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3" name="action" value="sortAction">Refresh</button>
                    </div>
                </form>
            </div>
        </div>
        <table class="table table-striped table-hover ">
            <thead class="table-dark">
                <tr class="fixed">
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Email(Register)</th>
                    <th>Phone Number(Contact)</th>
                    <th>Email(Contact)</th>
                    <th>Location</th>
                    <th>Identity Number</th>
                    <th>Status</th>
                    <th>Request</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody  style="font-weight: bold;">
                <c:forEach var="technician" items="${itemTable}">
                    <c:set var="location" value="${loca}"/>
                    <tr>
                        <td>${technician.accId}</td>
                        <td>${technician.userId.fullName}</td>
                        <td>${technician.accEmail}</td>
                        <c:choose>
                            <c:when test="${technician.userId.phoneNumber == null}"> 
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td><c:out value="${technician.userId.phoneNumber}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${technician.userId.email == null}"> 
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td><c:out value="${technician.userId.email}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${local != null}">
                                <td><button data-bs-toggle="modal" data-bs-target="#location-${technician.accId}" class="btn btn-primary">View</button></td>
                            </c:when>
                            <c:otherwise>
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${technician.userId.identityNo == null}"> 
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td><c:out value="${technician.userId.identityNo}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${technician.statusNo.statusName == 'Block'}"> 
                                <td style="color: red"><c:out value="${technician.statusNo.statusName}"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td style="color: green;"><c:out value="${technician.statusNo.statusName}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <a href="#">View</a>
                        </td>
                        <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formTech-${technician.accId}">
                                Edit
                            </button></td>
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
                                    <input type="hidden" name="beginCus" value="${beginCus}"/>
                                    <input type="hidden" name="endCus" value="${endCus}"/>
                                    <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                                    <input type="hidden" name="pageValue" value="${pageValue}"/>
                                    <input type="hidden" name="pageAction" value="${pageAction}" />
                                    <input type="hidden" name="pageManage" value="pageTechnician"/>
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
                            <input type="hidden" name="beginCus" value="${beginCus}"/>
                            <input type="hidden" name="endCus" value="${endCus}"/>
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <input type="hidden" name="pageValue" value="${pageValue}"/>
                            <input type="hidden" name="pageAction" value="${pageAction}" />
                            <input type="hidden" name="pageManage" value="pageTechnician"/>
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
                            <input type="hidden" name="beginCus" value="${beginCus}"/>
                            <input type="hidden" name="endCus" value="${endCus}"/>
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <input type="hidden" name="pageValue" value="${pageValue}"/>
                            <input type="hidden" name="pageAction" value="${pageAction}" />
                            <input type="hidden" name="pageManage" value="pageTechnician"/>
                            <button class="page-link" type="submit" name="action" value="pagination">
                                Last Page
                            </button>
                        </form>
                    </li>
                    <c:choose>
                        <c:when test="${pageIndex == pagination.getIndex(technicianInfor.quantityTechnician())}">
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
                                    <input type="hidden" name="beginCus" value="${beginCus}"/>
                                    <input type="hidden" name="endCus" value="${endCus}"/>
                                    <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                                    <input type="hidden" name="pageValue" value="${pageValue}"/>
                                    <input type="hidden" name="pageAction" value="${pageAction}" />
                                    <input type="hidden" name="pageManage" value="pageTechnician"/>
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
        <div class="d-flex justify-content-center">
            <c:choose>
                <c:when test="${requestScope.resultUpdate eq 'Update status successfully!'}">
                    <h4 style="color: green">${requestScope.resultUpdate}</h4>
                </c:when>
                <c:otherwise>
                    <h4 style="color: red">${requestScope.resultUpdate}</h4>
                </c:otherwise>
            </c:choose>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="/AD_Network/assets/js/tableRadio.js"></script>
    </body>
</html>
