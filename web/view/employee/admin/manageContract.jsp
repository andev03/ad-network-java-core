
<%-- 
    Document   : manageContract
    Created on : Feb 25, 2024, 8:15:59 PM
    Author     : user
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="dto.Contract"%>
<%@page import="dao.ContractDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/tableDataStyle.css" />
        <title>Manage Contract Page</title>
    </head>
    <body style="margin: 0">
        <jsp:useBean id="contractInfor" class="dao.ContractDAO" scope="page" />
        <jsp:useBean id="pagination" class="utils.Pagination" scope="page"/>

        <c:choose>
            <c:when test="${requestScope.pageIndex == null && requestScope.pageValue == null}">
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="itemTable" value="${contractInfor.getContractByScopeForViewContract(beginCus, endCus)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="pageAction" value="${requestScope.cusNameContractSearch}" scope="request"/>
                <c:set var="itemTable" value="${contractInfor.getScopeContractCustomerByName(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${contractInfor.getScopeContractCustomerByName(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:otherwise>
                <c:set var="itemTable" value="${contractInfor.getContractByScopeForViewContract(beginCus, endCus)}" scope="request" />
            </c:otherwise>
        </c:choose>
        <c:forEach var="contract" items="${itemTable}">
            <div class="modal fade" id="updateContract-${contract.contractId}" aria-hidden="true" aria-labelledby="updateContract" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title fs-5" id="updateContract">Update Contract</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="/AD_Network/MainController">
                            <input type="hidden" name="action" value="updateContract">
                            <input type="hidden" name="contractid" value="${contract.contractId}"/>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <input type="hidden" name="customerId" value="${contract.cusId.accId}">
                                    <label for="customerName" class="form-label">Customer Name</label>
                                    <input class="form-control" id="customerName" readonly value="${contract.cusId.userId.fullName}">
                                </div>
                                <div class="mb-3">
                                    <input type="hidden" name="employeeId" value="${sessionScope.admin.accId}">
                                    <label for="employeeName" class="form-label">Employee Name</label>
                                    <input class="form-control" id="employeeName" readonly value="${sessionScope.admin.userId.fullName}">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button class="btn btn-primary">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>

        <div>
            <div class="modal fade" id="createContract" aria-hidden="true" aria-labelledby="createContract" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title fs-5" id="createContract">Create Contract</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="/AD_Network/MainController" method="get">
                            <input type="hidden" name="action" value="createContract">
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="customerName" class="form-label">Customer Name</label>
                                    <select class="form-select" id="customerName" name="cusId">
                                        <c:forEach var="contractToCreate" items="${contractInfor.contractForCreate}">
                                            <option value="${contractToCreate.cusId.accId} ${contractToCreate.contractId}">${contractToCreate.cusId.userId.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <input type="hidden" name="employeeId" value="${sessionScope.admin.accId}">
                                    <label for="employeeName" class="form-label" >Employee Name</label>
                                    <div class="form-control" id="employeeName">${sessionScope.admin.userId.fullName}</div>
                                </div> 
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                <button class="btn btn-primary" data-bs-target="#createContract" data-bs-toggle="modal" onclick="return window.confirm('Are you sure create contract?')">Create</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/template/headerAdmin.jsp" />

        <div class="search d-flex container justify-content-center" style="margin-top: 20px">
            <div class="col-4">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                        <input type="text" class="form-control" placeholder="Customer Name" name="txtNameContractSearch">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success mb-3" name="action" value="searchContractCustomerByName">Search</button>
                    </div>
                </form>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <h3 style="color: #1768b2; font-weight: bold">Manage Contract</h3>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <select class="form-select">
                            <option selected disabled>Sort by ...</option>
                            <option value="1">Sort by Name Customer</option>
                            <option value="2">Sort by Name Employee</option>
                            <option value="2">Sort by Value</option>
                            <option value="2">Sort by Duration</option>
                            <option value="2">Sort by Status</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3" name="action" value="sortSubmit">GO</button>
                    </div>
                </form>
            </div>
        </div>
        <table class="table table-striped table-hover ">
            <thead class="table-dark">
                <tr class="fixed">
                    <th>ID</th>      
                    <th>Customer</th>
                    <th>Customer Phone</th>
                    <th>Employee</th>
                    <th>Value</th>
                    <th>Duration</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody style="font-weight: bold;">
                <c:forEach var="contract" items="${itemTable}">
                    <tr>
                        <td><c:out value="${contract.contractId}"/></td>      
                        <td><c:out value="${contract.cusId.userId.fullName}"/></td>
                        <td><c:out value="${contract.cusId.accTel}"/></td>
                        <td><c:out value="${contract.empId.userId.fullName}"/></td>
                        <c:set var="pr" value="${contract.transactionId.transAmount}" />
                        <fmt:formatNumber var="formattedValue" value="${pr}" type="number" pattern="###,###"/>
                        <td><c:out value="${formattedValue} VNĐ" /></td>
                        <td><c:out value="${contract.contractNo.typeName} month"/></td>
                        <td><c:out value="${contract.startDate}"/></td>
                        <td><c:out value="${contract.endDate}"/></td>
                        <c:choose>
                            <c:when test="${contract.statusNo.statusNo == 0}">
                                <td style="color: red;">Expired</td>
                                <td><button class="btn btn-secondary btn-sm">Update</button></td>
                            </c:when>
                            <c:when test="${contract.statusNo.statusNo == 1}">
                                <td style="color: green;">Valid</td>
                                <td><button class="btn btn-secondary btn-sm">Update</button></td>
                            </c:when>
                            <c:when test="${contract.statusNo.statusNo == 2}">
                                <td style="color: blueviolet;">Extend</td>
                                <td><button data-bs-toggle="modal" data-bs-target="#updateContract-${contract.contractId}" type="button" class="btn btn-primary btn-sm">Update</button></td>
                            </c:when>
                            <c:when test="${contract.statusNo.statusNo == 3}">
                                <td style="color: blue;">Extended</td>
                                <td><button class="btn btn-secondary btn-sm">Update</button></td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="d-flex justify-content-around">
            <div class="col-4 d-flex justify-content-around">
                <button class="btn btn-primary" data-bs-target="#createContract" data-bs-toggle="modal">
                    Create Contract
                </button>
            </div>
            <!--Kết thúc ở đây-->
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
                                        <input type="hidden" name="pageManage" value="pageContract"/>
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
                                <input type="hidden" name="pageManage" value="pageContract"/>
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
                                <input type="hidden" name="pageManage" value="pageContract"/>
                                <button class="page-link" type="submit" name="action" value="pagination">
                                    Last Page
                                </button>
                            </form>
                        </li>
                        <c:choose>
                            <c:when test="${pageIndex == pagination.getIndex(contractInfor.quantityContract())}">
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
                                        <input type="hidden" name="pageManage" value="pageContract"/>
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
            <div class="col-4 d-flex justify-content-around">
                <c:choose>
                    <c:when test="${requestScope.statusCreate eq 'Create Contract Successfully!'}">
                        <h4 style="color: green">${requestScope.statusCreate}</h4>
                    </c:when>
                    <c:when test="${param.result eq 'succesfull'}">
                        <h4 style="color: green">Update contract successfully!</h4>
                    </c:when>
                    <c:when test="${param.result eq 'failed'}">
                        <h4 style="color: red">Update contract failed!</h4>
                    </c:when>
                    <c:otherwise>
                        <h4 style="color: red">${requestScope.statusCreate}</h4>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>