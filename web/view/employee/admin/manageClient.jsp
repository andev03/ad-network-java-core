<%-- 
    Document   : manageClient
    Created on : Feb 26, 2024, 7:48:42 PM
    Author     : user
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.LogErrorDetailDAO"%>
<%@page import="dto.LogErrorDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/tableDataStyle.css" />
        <title>Manage Client Page</title>
    </head>
    <body style="margin: 0">
        <c:if test="${sessionScope.admin == null}">
            <c:redirect url="/" />
        </c:if>
        <jsp:useBean id="customerInfor" class="dao.CustomerDAO" scope="page"/>
        <jsp:useBean id="pagination" class="utils.Pagination" scope="page"/>
        <jsp:useBean id="logErrorData" class="dao.LogErrorDetailDAO" scope="page"/>
        <jsp:useBean id="logError" class="dao.LogErrorDAO" scope="page"/>
        <jsp:useBean id="locationIn" class="dao.LocationDAO" scope="page" />
        <c:set var="data" value="${logErrorData.allLogErrorDetai}" scope="page"/>
        <c:choose>
            <c:when test="${requestScope.pageIndex == null && requestScope.pageValue == null}">
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="itemTable" value="${customerInfor.getCustomerByScope(beginCus, endCus)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="pageAction" value="${requestScope.telephoneSearch}" scope="request"/>
                <c:set var="itemTable" value="${customerInfor.getScopeCustomerByTelephone(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${customerInfor.getScopeCustomerByTelephone(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${(requestScope.pageValue eq 'sortByName' || requestScope.pageValue eq 'sortByStatus') && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/> 
                <c:set var="itemTable" value="${customerInfor.sortCustomerByAction(beginCus, endCus, requestScope.pageValue)}" scope="request" />
            </c:when>
            <c:when test="${(requestScope.pageValue eq 'sortByName' || requestScope.pageValue eq 'sortByStatus') && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${customerInfor.sortCustomerByAction(beginCus, endCus, requestScope.pageValue)}" scope="request" />
            </c:when>
            <c:otherwise>
                <c:set var="itemTable" value="${customerInfor.getCustomerByScope(beginCus, endCus)}" scope="request" />
            </c:otherwise>
        </c:choose>

        <c:forEach var="customer" items="${itemTable}"> 
            <div class="modal fade" id="formCus-${customer.accId}" tabindex="-1" aria-labelledby="formCustomer" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="formCustomer">Edit Form</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Your form goes here -->
                            <form action="/AD_Network/MainController" method="get">
                                <input type="hidden" name="action" value="editCustomer">
                                <div class="mb-3">
                                    <label for="customerStatus" class="form-label">Status</label>
                                    <div class="radio-input">
                                        <label>
                                            <c:if test="${customer.statusNo.statusName == 'Unblock'}">
                                                <input type="hidden" name="cusIdEdit" value="${customer.accId}"/>
                                                <input type="radio" name="status" class="status-unblock" value="0" checked>
                                            </c:if>
                                            <c:if test="${customer.statusNo.statusName != 'Unblock'}">
                                                <input type="hidden" name="cusIdEdit" value="${customer.accId}"/>
                                                <input type="radio" name="status" class="status-unblock" value="0">
                                            </c:if>
                                            <span>Unblock</span>
                                        </label>
                                        <label>
                                            <c:if test="${customer.statusNo.statusName == 'Block'}">
                                                <input type="hidden" name="cusIdEdit" value="${customer.accId}"/>
                                                <input type="radio" name="status" class="status-block" value="1" checked>
                                            </c:if>
                                            <c:if test="${customer.statusNo.statusName != 'Block'}">
                                                <input type="hidden" name="cusIdEdit" value="${customer.accId}"/>
                                                <input type="radio" name="status" class="status-block" value="1">
                                            </c:if>
                                            <span>Block</span>
                                        </label>
                                        <span class="selection"></span>
                                    </div>
                                </div>
                                <!-- Các trường khác của form -->
                                <div class="mb-3">
                                    <label for="banReason" class="form-label">Block Reason</label>
                                    <select class="form-select" id="selectReason" name="banReason">
                                        <c:set var="isError" value="${logError.getLogError(customer.accId).logDetailId}" />
                                        <option value="${isError.logDetailId}" selected>${isError.logDetail}</option>
                                        <c:forEach var="logErrorDetail" items="${data}">
                                            <c:if test="${logErrorDetail.logDetail != isError.logDetail}">
                                                <option value="${logErrorDetail.logDetailId}">${logErrorDetail.logDetail}</option>
                                            </c:if>
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

            <c:set var="local" value="${locationIn.getLocationByUserId(customer.userId.userId)}" scope="request"/>
            <!--////////////////////////////////////////////////////////////////////////////////////-->

            <div class="modal fade" id="location-${customer.accId}" tabindex="-1" aria-labelledby="location" aria-hidden="true">
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

        <jsp:include page="/template/headerAdmin.jsp" />

        <div class="search d-flex container justify-content-center" style="margin-top: 20px">
            <div class="col-4">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                        <input type="text" class="form-control" placeholder="Phone Number" name="txtPhoneSearch">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success mb-3" name="action" value="searchCustomerByPhone">Search</button>
                    </div>
                </form>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <h3 style="color: #1768b2; font-weight: bold">Manage Client</h3>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <form class="row g-3" action="/AD_Network/MainController" method="Get">
                    <input type="hidden" name="pageManage" value="pageClient"/>
                    <div class="col-auto">
                        <select class="form-select" name="sortChoose">
                            <option selected disabled>Sort by ...</option>
                            <option value="sortByName">Sort by Name</option>
                            <option value="sortByStatus">Sort by Status</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3" name="action" value="sortAction">Refresh</button>
                    </div>
                </form>
            </div>
        </div>
        <!--Bắt đầu từ đây-->
        <table class="table table-striped table-hover ">
            <thead class="table-dark">
                <tr class="fixed">
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Phone Number(Register)</th>
                    <th>Phone Number(Contact)</th>
                    <th>Email(Contact)</th>
                    <th>Location</th>
                    <th>Identity Number</th>
                    <th>Status</th>
                    <th>Contract</th>
                    <th>Request</th>
                    <th>Payment</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody style="font-weight: bold;">
                <c:forEach var="customer" items="${itemTable}">
                    <c:set var="local" value="${locationIn.getLocationByUserId(customer.userId.userId)}" scope="request"/>
                    <tr>
                        <td><c:out value="${customer.accId}"/></td>
                        <td><c:out value="${customer.userId.fullName}"/></td>
                        <td><c:out value="${customer.accTel}"/></td>
                        <c:choose>
                            <c:when test="${customer.userId.phoneNumber == null}"> 
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td><c:out value="${customer.userId.phoneNumber}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${customer.userId.email == null}"> 
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td><c:out value="${customer.userId.email}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${local != null}">
                                <td><button data-bs-toggle="modal" data-bs-target="#location-${customer.accId}" class="btn btn-primary">View</button></td>
                            </c:when>
                            <c:otherwise>
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${customer.userId.identityNo == null}"> 
                                <td style="color: red"><c:out value="Empty"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td><c:out value="${customer.userId.identityNo}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${customer.statusNo.statusName == 'Block'}"> 
                                <td style="color: red"><c:out value="${customer.statusNo.statusName}"/></td>
                            </c:when> 
                            <c:otherwise>
                                <td style="color: green;"><c:out value="${customer.statusNo.statusName}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <td><a href="#">View</a></td>
                        <td><a href="#">View</a></td>
                        <td><a href="#">View</a></td>

                        <c:choose>
                            <c:when test="${customer.statusNo.statusName == 'Block'}"> 
                                <td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#formCus-${customer.accId}">Edit</button></td>
                            </c:when> 
                            <c:otherwise>
                                <td><button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#formCus-${customer.accId}">Edit</button></td>
                            </c:otherwise>
                        </c:choose>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--Kết thúc ở đây-->

        <!-- Phân chia trang-->
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
                                    <input type="hidden" name="pageManage" value="pageClient"/>
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
                            <input type="hidden" name="pageManage" value="pageClient"/>
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
                            <input type="hidden" name="pageManage" value="pageClient"/>
                            <button class="page-link" type="submit" name="action" value="pagination">
                                Last Page
                            </button>
                        </form>
                    </li>
                    <c:choose>
                        <c:when test="${pageIndex == pagination.getIndex(customerInfor.quantityCustomer())}">
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
                                    <input type="hidden" name="pageManage" value="pageClient"/>
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
        <script src="/AD_Network/assets/js/tableRadio.js"></script><!--Dùng để thay đổi dropdown khi lựa chọn radio-->
    </body>
</html>
