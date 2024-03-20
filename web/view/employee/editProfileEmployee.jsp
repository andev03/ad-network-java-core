<%-- 
    Document   : editProfileEmployee
    Created on : Mar 11, 2024, 11:19:34 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account Profile</title>
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
        <jsp:useBean id="prov" class="dao.ProvinceDAO" scope="page"/>
        <c:set var="province" value="${prov.allProvince}" scope="page"/>
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
                <h2 class="mb-3 text-center fw-bold" style="font-size: 50px; color: #1768b2; ">Change Information</h2>
                <form>
                    <div class="mb-3">
                        <label for="fullName" class="form-label fw-bold">Full Name</label>
                        <input type="text" class="form-control" id="fullName" placeholder="${emp.userId.fullName}">
                    </div>
                    <div class="mb-3">
                        <label for="locaDe" class="form-label fw-bold">Location Detail</label>
                        <input type="tetx" class="form-control" id="locaDe" placeholder="${locationDetail.locationDetail}">
                    </div>
                    <div class="row mb-3">   
                        <div class="col"> <label for="province" class="form-label fw-bold">Province</label>
                            <select class="form-select" id="province">
                                <c:forEach var="provinceSelect" items="${province}">
                                    <option value="${provinceSelect.provinceId}">${provinceSelect.provinceName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col"> <label for="city" class="form-label fw-bold">City</label>
                            <select class="form-select" id="city">

                            </select>
                        </div>
                        <div class="col"> <label for="district" class="form-label fw-bold">District</label>
                            <select class="form-select" id="district">

                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumContact" class="form-label fw-bold">Phone Number(Contact)</label>
                        <input type="tel" class="form-control" id="phoneNumContact" placeholder="${emp.userId.phoneNumber}">
                    </div>
                    <div class="mb-3">
                        <label for="emailContact" class="form-label fw-bold">Email(Contact)</label>
                        <input type="email" class="form-control" id="emailContact" placeholder="${emp.userId.email}">
                    </div>
                    <div class="mb-3">
                        <label for="IdentityNum" class="form-label fw-bold">Identity Number</label>
                        <input type="text" pattern="\d{11}" class="form-control" id="IdentityNum" placeholder="${emp.userId.identityNo}">
                    </div>
                    <h2 class="mb-3 fw-bold justify-content-center text-center d-flex">Account Information</h2>
                    <div class="mb-3">
                        <label for="phoneNumberRe" class="form-label fw-bold">Email(Register)</label>
                        <input type="tel" class="form-control" id="phoneNumberRe" placeholder="${emp.accEmail}">
                    </div>
                    <div class="mb-3">
                        <label for="pass" class="form-label fw-bold">Password</label>
                        <input type="email" class="form-control" id="pass" placeholder="${emp.accPassEmp}">
                    </div>
                </form>
            </div>
            <div class="d-flex justify-content-center justify-content-evenly" style="padding: 15px;">
                <a href="/AD_Network/MainController?view=viewProfileEmployee" class="text-decoration-none btn btn-secondary" style="width: 45%; ">Back</a>
                <button type="submit" class="btn btn-primary btn-success" style="width: 45%;">Change</button>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
