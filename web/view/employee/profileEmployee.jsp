<%-- 
    Document   : profileEmployee
    Created on : Mar 9, 2024, 9:32:14 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Profile</title>
        <link rel="stylesheet" href="/assets/css/profileStyle.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    </head>
    <body>

        <c:choose>
            <c:when test="${sessionScope.admin == null && sessionScope.technician == null}">
                <c:redirect url="/" />
            </c:when>
        </c:choose>

        <jsp:useBean id="location" class="dao.LocationDAO" scope="page"/>
        <c:choose>
            <c:when test="${sessionScope.admin!=null}">
                <c:set var="emp" value="${sessionScope.admin}" scope="request"/>
                <c:set var="locationDetail" value="${location.getLocationByUserId(emp.userId.userId)}" scope="request"/>
                <jsp:include page="/template/headerAdmin.jsp" />
            </c:when>
            <c:when test="${sessionScope.technician!=null}">
                <c:set var="emp" value="${sessionScope.technician}" scope="request"/>
                <c:set var="locationDetail" value="${location.getLocationByUserId(emp.userId.userId)}" scope="request"/>
                <jsp:include page="/template/headerTechnician.jsp" />
            </c:when>
        </c:choose>
        

        <main class="container-fluid row" style="padding: 0; margin: 0">
            <div class="container py-4">
                <h2 class="mb-3 text-center fw-bold" style="font-size: 50px; color: #1768b2; ">Basic Information</h2>

                <div class="mb-3">
                    <label for="fullName" class="form-label fw-bold">Full Name</label>
                    <input type="text" readonly class="form-control" id="fullName" value="${emp.userId.fullName}">
                </div>
                <div class="mb-3">
                    <label for="locaDe" class="form-label fw-bold">Location Detail</label>
                    <c:choose>
                        <c:when test="${locationDetail.locationDetail==null}">
                            <div class="form-control" id="locaDe" style="color: red; font-weight: bold;">Empty</div>
                        </c:when>
                        <c:otherwise>
                            <input type="text" readonly class="form-control" id="locaDe" value="${locationDetail.locationDetail}">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="row mb-3">
                    <div class="col"> 
                        <label for="province" class="form-label fw-bold">Province</label>
                        <c:choose>
                            <c:when test="${locationDetail.provinceId==null}">
                                <div class="form-control" id="province" style="color: red; font-weight: bold;">Empty</div>
                            </c:when>
                            <c:otherwise>
                                <input type="text" readonly class="form-control" id="province" value="${locationDetail.provinceId.provinceName}">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col"> 
                        <label for="city" class="form-label fw-bold">City</label>
                        <c:choose>
                            <c:when test="${locationDetail.cityId==null}">
                                <div class="form-control" id="city" style="color: red; font-weight: bold;">Empty</div>
                            </c:when>
                            <c:otherwise>
                                <input type="text" readonly class="form-control" id="city" value="${locationDetail.cityId.cityName}">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col"> 
                        <label for="district" class="form-label fw-bold">District</label>
                        <c:choose>
                            <c:when test="${locationDetail.districtId==null}">
                                <div class="form-control" id="district" style="color: red; font-weight: bold;">Empty</div>
                            </c:when>
                            <c:otherwise>
                                <input type="text" readonly class="form-control" id="district" value="${locationDetail.districtId.districtName}">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="phoneNumContact" class="form-label fw-bold">Phone Number(Contact)</label>
                    <c:choose>
                        <c:when test="${emp.userId.phoneNumber==null}">
                            <div class="form-control" id="phoneNumContact" style="color: red; font-weight: bold;">Empty</div>
                        </c:when>
                        <c:otherwise>
                            <input type="tel" readonly class="form-control" id="phoneNumContact" value="${emp.userId.phoneNumber}">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="mb-3">
                    <label for="emailContact" class="form-label fw-bold">Email(Contact)</label>
                    <c:choose>
                        <c:when test="${emp.userId.email==null}">
                            <div class="form-control" id="emailContact" style="color: red; font-weight: bold;">Empty</div>
                        </c:when>
                        <c:otherwise>                  
                            <input type="email" readonly class="form-control" id="emailContact" value="${emp.userId.email}">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="mb-3">
                    <label for="IdentityNum" class="form-label fw-bold">Identity Number</label>
                    <c:choose>
                        <c:when test="${emp.userId.identityNo==null}">
                            <div class="form-control" id="IdentityNum" style="color: red; font-weight: bold;">Empty</div>
                        </c:when>
                        <c:otherwise>
                            <input type="text" readonly class="form-control" id="IdentityNum" value="${emp.userId.identityNo}">
                        </c:otherwise>
                    </c:choose>
                </div>

                <h3 class="mb-3 fw-bold justify-content-center text-center d-flex">Account Information</h3>
                <div class="mb-3">
                    <label for="emailRe" class="form-label fw-bold">Email(Register)</label>
                    <input type="email" readonly class="form-control" id="emailRe" value="${emp.accEmail}">
                </div>
                <div class="mb-3">
                    <label for="pass" class="form-label fw-bold">Password</label>
                    <input type="password" readonly class="form-control" id="pass" value="${emp.accPassEmp}">
                </div>
            </div>
            <div class="d-flex justify-content-center" style="padding: 15px;">
                <a href="/AD_Network/MainController?view=viewEditProfileEmployee" class="text-decoration-none btn btn-primary btn-success" style="width: 80%; ">Edit</a>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
