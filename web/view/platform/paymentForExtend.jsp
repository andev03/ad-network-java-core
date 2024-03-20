<%-- 
    Document   : payment
    Created on : Mar 7, 2024, 2:48:30 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="/AD_Network/assets/css/payment.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/tableDataStyle.css" />
    </head>
    <body>
        <c:choose>
            <c:when  test="${sessionScope.customer == null}">
                <c:redirect url="/homepage.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="/template/headerLogIn.jsp"/>
            </c:otherwise>
        </c:choose>

        <main class="container-fluid row" style="padding: 0; margin: 0">

            <jsp:include page="/template/sidebarUserCus.jsp"/>
            <jsp:useBean id="serviceInfor" class="dao.ServiceDAO" scope="page" />
            <jsp:useBean id="locationInfor" class="dao.LocationDAO" scope="page" />
            <c:set var="loca" value="${locationInfor.getLocationByUserId(sessionScope.customer.userId.userId)}" scope="request"/>
            <div class="col-3"></div>
            <div class="col-9" data-bs-spy="scroll" data-bs-target="#list-example" data-bs-smooth-scroll="true" tabindex="0"
                 style="padding: 0;">
                <div class="textTitle" style="padding: 0 0 30px 0;">Payment</div>
                <div data-bs-spy="scroll" data-bs-target="#navbar-example3" data-bs-smooth-scroll="true"
                     class="scrollspy-example-2" tabindex="0">
                    <!-- Button trigger modal -->
                    <div class="container row d-flex justify-content-center">
                        <form id="formForEndPayment" action="/AD_Network/MainController" method="get">
                            <input type="hidden" name="action" value="paymentExtend"/>

                            <div class="container row d-flex justify-content-center">
                                <div class="col-7 sub-container d-flex flex-column justify-content-between" style="padding: 5px 0px;">
                                    <div>
                                        <table class="table table-striped table-hover text-center">
                                            <thead class="table-dark">
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Name Service</th>
                                                    <th>Price</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${requestScope.serviceExtend != null}">
                                                        <c:forEach var="cartInfor" items="${requestScope.serviceExtend}">
                                                            <c:set var="i" value="${i+1}" scope="page"/> 
                                                            <c:set var="totalAmount" value="${i}"/>
                                                            <tr>
                                                                <td>${i}</td>
                                                                <td>${cartInfor.key}</td>
                                                                <c:set var="price" value="${serviceInfor.getServiceForCartByName(cartInfor.key).price}" />
                                                                <fmt:formatNumber var="price" value="${price}" type="number" pattern="###,###"/>
                                                                <td>${price}đ/tháng</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                    <input type="hidden" name="serviceExtend" value="${requestScope.serviceExtend}"/>
                                    <input type="hidden" name="contractId" value="${param.contractId}"/>
                                    <input type="hidden" name="transCycle" value="${requestScope.transCycle}"/>
                                    <input type="hidden" name="totalPrice" value="${requestScope.totalPrice}"/>
                                    <!--Phần đuôi-->
                                    <div class="row mb3 text-center" style="padding: 12px; font-weight: bold">
                                        <div class="col">
                                            <c:set var="pr" value="${totalPrice}" />
                                            <fmt:formatNumber var="priceTotal" value="${pr}" type="number" pattern="###,###"/>
                                            <label for="totalPrice" class="form-label align-items-center">Total Price: <fmt:formatNumber value="${requestScope.totalPrice}" type="number" pattern="###,###"/></label>đ
                                        </div>
                                        <div class="col">
                                            <label for="transCycle" class="form-label align-items-center">Cycle: ${requestScope.transCycle}</label>
                                        </div>
                                        <div class="col">
                                            <label for="totalAmount" class="form-label">Total Amount: ${totalAmount}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3 sub-container">
                                    <!-- Nội dung phần tử 2 -->
                                    <h3 class="text-center" style="color: #1768b2;">Other Details</h3>
                                    <div class="mb-3">
                                        <label for="paymentType" class="form-label" style="font-weight: bold;">Payment Type</label>
                                        <div class="radio-input">
                                            <label>
                                                <input type="radio" name="paymentType" class="status-offline" value="2" checked>
                                                <span>Online</span>
                                            </label>
                                            <label>
                                                <input type="radio" name="paymentType" class="status-online" value="1">
                                                <span>Offline</span>
                                            </label>
                                            <span class="selection"></span>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="location" class="form-label" style="font-weight: bold;">Location Detail</label>
                                        <div class="form-control" id="location">${loca.locationDetail}, ${loca.districtId.districtName}, ${loca.cityId.cityName}, ${loca.provinceId.provinceName}</div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="d-flex col-12 justify-content-center" style="margin: 30px"> 
                            <form action="/AD_Network/MainController" method="post" style="padding-right: 10px">
                                <input type="hidden" name="view" value="viewHomePage">
                                <button type="submit" class="btn btn-secondary">Close</button>
                            </form>

                            <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                Confirm
                            </button>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5 text-center" id="staticBackdropLabel">Terms and Conditions</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h6>1. Điều khoản sử dụng</h6>
                                        <p>Khách hàng phải tuân thủ tất cả các quy định về sử dụng dịch vụ.</p>
                                        <h6>2. Chính sách bảo mật</h6>
                                        <p>Công ty sẽ tuân thủ tất cả các quy định về bảo mật và bảo vệ dữ liệu cá nhân của khách hàng.</p>
                                        <h6>3. Chính sách thanh toán</h6>
                                        <p>Khách hàng phải thanh toán đầy đủ và đúng hạn cho tất cả các dịch vụ đã sử dụng.</p>
                                        <h6>4. Chính sách hủy dịch vụ</h6>
                                        <p>Khách hàng không thể hủy bỏ dịch vụ sau khi mua hàng.</p>
                                        <h6>5. Chính sách bảo hành và hỗ trợ</h6>
                                        <p>Công ty sẽ cung cấp hỗ trợ kỹ thuật và bảo hành cho các sản phẩm và dịch vụ theo các điều khoản đã quy định trong hợp đồng.</p><hr>
                                        <div class="mb-3">
                                            <p style="display: inline;">Để biết thêm chi tiết, vui lòng đọc</p>
                                            <a href="https://drive.google.com/file/d/1izmtEm6QYWeXtt1Kc3WBbrwlJVrwnvw6/view?usp=drive_link">Điều khoản và dịch vụ</a><br>
                                        </div>
                                        <div class="mb-3">
                                            <input type="checkbox" id="agreeCheck" name="agreeTerm" value="Agree" >
                                            <label for="agreeCheck">Tôi đã đọc và đồng ý với các Điều khoản và Chính sách</label>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button id="submitForm" type="submit" class="btn btn-primary" id="understoodButton">Understood</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="/AD_Network/assets/js/termAgree.js"></script>
        <script>
            document.getElementById("submitForm").addEventListener("click", function () {
                document.getElementById("formForEndPayment").submit();
            });

            window.onload = function () {
                document.getElementById('submitForm').disabled = true;
                document.getElementById('agreeCheck').addEventListener('change', function () {
                    document.getElementById('submitForm').disabled = !this.checked;
                });
            };
        </script>
    </body>
</html>
