<%-- 
    Document   : manageServices
    Created on : Feb 25, 2024, 8:16:46 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <title>Manage Service Page</title>
    </head>
    <body style="margin: 0">

        <c:if test="${sessionScope.admin == null}">
            <c:redirect url="/" />
        </c:if>
        
        <jsp:useBean id="serviceInfor" class="dao.ServiceDAO" scope="page" />
        <jsp:useBean id="serviceStatus" class="dao.ServiceStatusTypeDAO" scope="page" />
        <jsp:useBean id="deviceList" class="dao.DeviceDAO" scope="page" />

        <c:set var="device" value="${deviceList.allDevice}"/>
        <c:set var="serStatus" value="${serviceStatus.allServiceStatusType}" scope="page"/>

        <c:choose>
            <c:when test="${requestScope.pageIndex == null && requestScope.pageValue == null}">
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="itemTable" value="${serviceInfor.getServiceByScope(beginCus, endCus)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex == null}" >
                <c:set var="pageIndex" value="1" scope="request"/>
                <c:set var="beginCus" value="1" scope="request"/>
                <c:set var="endCus" value="5" scope="request"/>
                <c:set var="pageAction" value="${requestScope.serviceNameSearch}" scope="request"/>
                <c:set var="itemTable" value="${serviceInfor.getScopeServiceByName(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:when test="${requestScope.pageValue eq 'Search' && requestScope.pageIndex != null}" >
                <c:set var="itemTable" value="${serviceInfor.getScopeServiceByName(beginCus, endCus, requestScope.pageAction)}" scope="request" />
            </c:when>
            <c:otherwise>
                <c:set var="itemTable" value="${serviceInfor.getServiceByScope(beginCus, endCus)}" scope="request" />
            </c:otherwise>
        </c:choose>
        <c:forEach var="service" items="${itemTable}"> 
            <div class= "modal fade" id="formSer-${service.serviceId}" tabindex="-1" aria-labelledby="formService" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="formService">Edit Form</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="/AD_Network/MainController" method="get">
                            <input type="hidden" name="typeEdit" value="updateService">
                            <div class="modal-body">
                                <!-- Your form goes here -->
                                <input type="hidden" name="action" value="updateService">
                                <div class="mb-3">
                                    <label for="selectStatus" class="form-label">Status</label>
                                    <select class="form-select" id="selectStatus" name="statusSer">
                                        <c:set var="thisStatus" value="${service.serviceStatus.typeName}"/>
                                        <option value="${ser.serviceStatus.serviceStatus}" selected>${thisStatus}</option>
                                        <c:forEach var="ser" items="${serStatus}">
                                            <c:if test="${ser.typeName != thisStatus}">
                                                <option value="${ser.serviceStatus}">${ser.typeName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <input type="hidden" name="serviceId" value="${service.serviceId}"
                                           <label for="servicePrice" class="form-label">Price</label>
                                    <input name="priceUpdate" type="number" class="form-control" id="servicePrice" placeholder="Ex: 200000" min="100000" step="1000">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" >Close</button>
                                <button type="submit" class="btn btn-primary" onclick="return window.confirm('Are you sure to save this change?')">Save changes</button>
                            </div>
                        </form>

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
                        <input type="text" class="form-control" placeholder="Service Name" name="txtServiceNameSearch">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success mb-3" name="action" value="searchServiceByName">Search</button>
                    </div>
                </form>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <h3 style="color: #1768b2; font-weight: bold">Manage Service</h3>
            </div>

            <div class="col-4 d-flex justify-content-center">
                <form class="row g-3" action="" method="get">
                    <div class="col-auto">
                        <select class="form-select">
                            <option selected disabled>Sort by ...</option>
                            <option value="1">Sort by Name Service</option>
                            <option value="2">Sort by Name Device</option>
                            <option value="2">Sort by Value</option>
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
                <tr class="fixed">
                    <th>ID</th>
                    <th>Service Name</th>
                    <th>Device</th>
                    <th>Service Type</th>
                    <th>Service Status</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody style="font-weight: bold;">
                <c:forEach var="service" items="${itemTable}">
                    <tr>
                        <td><c:out value="${service.serviceId}"/></td>
                        <td><c:out value="${service.serviceName}"/></td>
                        <td><c:out value="${service.deviceId.deviceName}"/></td>
                        <td><c:out value="${service.sTypeId.sTypeDesc}"/></td>
                        <c:choose>
                            <c:when test="${service.serviceStatus.serviceStatus == 1}">
                                <td style="color: green">In Stock</td>
                            </c:when>
                            <c:when test="${service.serviceStatus.serviceStatus == 0}">
                                <td style="color: red">Sold Out</td>
                            </c:when>
                            <c:when test="${service.serviceStatus.serviceStatus == 2}">
                                <td style="color: red">Disable</td>
                            </c:when>
                        </c:choose>
                        <td>
                            <c:set var="pr" value="${service.price}" />
                            <fmt:formatNumber var="price" value="${pr}" type="number" pattern="###,###"/>
                            <c:out value="${price} VNĐ"/>
                        </td>
                        <td>
                            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#formSer-${service.serviceId}">Update</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="d-flex justify-content-center">
            <div class="col-4 d-flex justify-content-around">
                <div class="modal fade" id="createService" aria-hidden="true" aria-labelledby="createService" tabindex="-1">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title fs-5" id="createService">Create Service</h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form action="/AD_Network/MainController" method="get" id="formCreateService">
                                <input type="hidden" name="action" value="createForServices">
                                <input type="hidden" name="typeEdit" value="createService">
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="serviceNameC" class="form-label">Name Service</label>
                                        <input name="serviceName" type="text" class="form-control" id="serviceNameC" placeholder="Service Name">
                                    </div>
                                    <div class="mb-3">
                                        <label for="serviceTypeI" class="form-label">Device</label>
                                        <select class="form-select" id="serviceTypeI" name="selectTypeI">
                                            <option value="1">Internet</option>
                                            <option value="2">Cloud</option>
                                            <option value="3">Camera</option>
                                            <option value="4">Television</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="deviceNameI" class="form-label">Device</label>
                                        <select class="form-select" id="deviceNameI" name="selectDevice">
                                            <c:forEach var="dev" items="${device}">
                                                <option value="${dev.deviceId}">${dev.deviceName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="servicePrice" class="form-label">Service Price</label>
                                        <input name="servicePrice" type="number" class="form-control" id="servicePrice" placeholder="Ex: 200000" min="100000" step="1000">
                                    </div>
                                </div>
                            </form>
                            <div class="modal-footer d-flex justify-content-between">
                                <div>
                                    <button class="btn btn-success" data-bs-target="#createDevice" data-bs-toggle="modal">
                                        Create New Device
                                    </button>
                                </div>
                                <div>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button  id="submitCreateService" class="btn btn-primary" data-bs-target="#createContract" data-bs-toggle="modal">Create</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary" data-bs-target="#createService" data-bs-toggle="modal">
                    Create Service
                </button>
            </div>
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
                                        <input type="hidden" name="pageManage" value="pageServices"/>
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
                                <input type="hidden" name="pageManage" value="pageServices"/>
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
                                <input type="hidden" name="pageManage" value="pageServices"/>
                                <button class="page-link" type="submit" name="action" value="pagination">
                                    Last Page
                                </button>
                            </form>
                        </li>
                        <c:choose>
                            <c:when test="${pageIndex == pagination.getIndex(serviceInfor.quantityService())}">
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
                                        <input type="hidden" name="pageManage" value="pageServices"/>
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
                <div class="modal fade" id="createDevice" aria-hidden="true" aria-labelledby="createDevice" tabindex="-1">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title fs-5" id="createDevice">Create Device</h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form action="/AD_Network/MainController" method="get">
                                <input type="hidden" name="action" value="createForServices">
                                <input type="hidden" name="typeEdit" value="createDevice">
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="deviceNameC" class="form-label">Name Device</label>
                                        <input name="deviceName" type="text" class="form-control" id="deviceNameC" placeholder="Device Name">
                                    </div>
                                    <div class="mb-3">
                                        <label for="deviceDesNameC" class="form-label">Device Description</label>
                                        <input name="devDetailDesc" type="text" class="form-control" id="deviceDesNameC" placeholder="Description about Device">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button class="btn btn-primary" data-bs-target="#createContract" data-bs-toggle="modal">Create</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <button class="btn btn-primary" data-bs-target="#createDevice" data-bs-toggle="modal">
                    Create Device
                </button>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <c:choose>
                <c:when test="${requestScope.typeEdit eq 'createDevice' && requestScope.result == 0}">
                    <h4 style="color: red">Create Device Failed</h4>
                </c:when>
                <c:when test="${requestScope.typeEdit eq 'createDevice' && requestScope.result == 1}">
                    <h4 style="color: green">Create Device Successfully</h4>
                </c:when>
                <c:when test="${requestScope.typeEdit eq 'createService' && requestScope.result == 0}">
                    <h4 style="color: red">Create Service Failed</h4>
                </c:when>
                <c:when test="${requestScope.typeEdit eq 'createService' && requestScope.result == 1}">
                    <h4 style="color: green">Create Service Successfully</h4>
                </c:when>
                <c:when test="${requestScope.typeEdit eq 'updateService' && requestScope.result == 0}">
                    <h4 style="color: red">Update Service Failed</h4>
                </c:when>
                <c:when test="${requestScope.typeEdit eq 'updateService' && requestScope.result == 1}">
                    <h4 style="color: green">Update Service Successfully</h4>
                </c:when>
            </c:choose>
        </div>     
        <script>
            const getElement = document.getElementById("submitCreateService");
            getElement.addEventListener("click", function () {
                document.getElementById("formCreateService").submit();
            });
        </script>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>