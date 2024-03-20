<%-- 
    Document   : managePayment
    Created on : Feb 25, 2024, 8:16:26 PM
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
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/tableDataStyle.css" />
        <title>Manage Payment Page</title>
    </head>
    <body style="margin: 0"> 

        <c:if test="${sessionScope.admin == null}">
            <c:redirect url="/" />
        </c:if>

        <jsp:useBean id="transactionInfor" class="dao.TransactionDAO" scope="page" />
        <jsp:useBean id="pagination" class="utils.Pagination" scope="page"/>
        <c:choose>
            <c:when test="${requestScope.pageIndex == null && requestScope.pageValue == null}">
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="itemTable" value="${transactionInfor.getTransactionByScope(beginCus, endCus)}" scope="page" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'viewTransByDate' && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="pageAction" value="${requestScope.date}" scope="request"/>
                <c:set var="itemTable" value="${transactionInfor.getTransactionByDate(beginCus, endCus, requestScope.pageAction)}" scope="page" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'viewTransByDate' && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${transactionInfor.getTransactionByDate(beginCus, endCus, requestScope.pageAction)}" scope="page" />
            </c:when>
            <c:when test="${(requestScope.pageValue eq 'sortByForm' || requestScope.pageValue eq 'sortByStatus') && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="itemTable" value="${transactionInfor.sortByAction(beginCus, endCus, requestScope.pageValue)}" scope="page" />
            </c:when>
            <c:when test="${(requestScope.pageValue eq 'sortByForm' || requestScope.pageValue eq 'sortByStatus') && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${transactionInfor.sortByAction(beginCus, endCus, requestScope.pageValue)}" scope="page" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="pageAction" value="${requestScope.paymentNameSearch}" scope="request"/>
                <c:set var="itemTable" value="${transactionInfor.getScopePaymentByNameCustomer(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${transactionInfor.getScopePaymentByNameCustomer(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:otherwise>
                <c:set var="itemTable" value="${transactionInfor.getTransactionByScope(beginCus, endCus)}" scope="page" />
            </c:otherwise>
        </c:choose>

        <jsp:include page="/template/headerAdmin.jsp" />
        <div class="search d-flex container justify-content-center" style="margin-top: 20px">
            <div class="col-4">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                        <input type="text" class="form-control" placeholder="Name Customer" name="txtNameCusPaymentSearch">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success mb-3" name="action" value="searchCustomerByNamePayment">Search</button>
                    </div>
                </form>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <h3 style="color: #1768b2; font-weight: bold">Manage Payment</h3>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <form class="row g-3" action="/AD_Network/MainController" method="Get">
                    <input type="hidden" name="pageManage" value="pagePayment"/>
                    <div class="col-auto">
                        <select class="form-select" name="sortChoose">
                            <option selected disabled>Sort by ...</option>
                            <option value="sortByForm">Sort by Form</option>
                            <option value="sortByStatus">Sort by Status</option>
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
                    <th>Customer Name</th>
                    <th>Form of Payment</th>
                    <th>Duration</th>
                    <th>Value</th>
                    <th>Payment Time</th>
                    <th>Status</th>
                    <th>Contract</th>
                </tr>
            </thead>
            <tbody style="font-weight: bold;">
                <c:forEach var="transaction" items="${itemTable}">
                    <tr>
                        <td><c:out value="${transaction.transactionId}"/></td>      
                        <td>${transaction.cusId.userId.fullName}</td>
                        <c:choose>
                            <c:when test="${transaction.typeId.typeId == 1}">
                                <td><button class="btn btn-danger">Offline</button></td>
                            </c:when>
                            <c:when test="${transaction.typeId.typeId == 2}">
                                <td><button class="btn btn-success">Online</button></td>
                            </c:when>
                        </c:choose>
                        <td>${transaction.transCycle} month</td>
                        <c:set var="pr" value="${transaction.transAmount}" />
                        <fmt:formatNumber var="price" value="${pr}" type="number" pattern="###,###"/>
                        <td>${price} VNĐ</td>
                        <td>${transaction.transTime}</td>
                        <c:choose>
                            <c:when test="${transaction.statusNo.statusNo == 1}">
                                <td><button class="btn btn-success">Paid</button></td>
                            </c:when>
                            <c:when test="${transaction.statusNo.statusNo == 0}">
                                <td><button class="btn btn-danger">Unpaid</button></td>
                            </c:when>
                        </c:choose>
                        <td><a href="#">View</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="d-flex justify-content-center">
            <div class="col-4 d-flex justify-content-center">
            </div>
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
                                    <input type="hidden" name="pageManage" value="pagePayment"/>
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
                            <input type="hidden" name="pageAction" value="${pageAction}"/>
                            <input type="hidden" name="pageManage" value="pagePayment"/>
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
                            <input type="hidden" name="pageManage" value="pagePayment"/>
                            <button class="page-link" type="submit" name="action" value="pagination">
                                Last Page
                            </button>
                        </form>
                    </li>
                    <c:choose>
                        <c:when test="${pageIndex == pagination.getIndex(transactionInfor.quantityTracnsaction())}">
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
                                    <input type="hidden" name="pageManage" value="pagePayment"/>
                                    <button class="page-link" type="submit" name="action" value="pagination">
                                        Next
                                    </button>
                                </form>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
            <div class="col-4 d-flex justify-content-center">
                <form action="/AD_Network/MainController" method="get" >
                    <input type="hidden" name="action" value="viewPaymentByDate"> 
                    <input type="date" id="date" name="date">
                    <input type="submit" value="Submit">
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
