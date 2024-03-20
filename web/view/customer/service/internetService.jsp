<%-- 
    Document   : internetService
    Created on : Feb 12, 2024, 3:51:59 PM
    Author     : user
--%>

<%@page import="java.sql.Array"%>
<%@page import="dto.Service"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/AD_Network/assets/css/guest.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/deviceSerStyle.css" />
        <link rel="stylesheet" href="/AD_Network/assets/css/tableDataStyle.css" />
        <title>Internet Service</title>
    </head>
    <body>
        <c:choose>
            <c:when  test="${sessionScope.customer == null}">
                <jsp:include page="/template/headerUser.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="/template/headerLogIn.jsp"/>
            </c:otherwise>
        </c:choose>
        <main class="container-fluid row" style="padding: 0; margin: 0">
            <jsp:include page="/template/sidebarUserExtra.jsp"/>
            <jsp:useBean id="serviceInfor" class="dao.ServiceDAO" scope="page" />
            <jsp:useBean id="locationInfor" class="dao.LocationDAO" scope="page" />
            <c:set var="loca" value="${locationInfor.getLocationByUserId(sessionScope.customer.userId.userId)}" scope="request"/>
            <div class="col-3"></div>
            <div class="col-9" data-bs-spy="scroll" data-bs-target="#list-example" data-bs-smooth-scroll="true" tabindex="0"
                 style="padding: 0;">
                <img src="/AD_Network/assets/img/banner_service/bs_internet.png" style="width: 100%">
                <div class="cartModel">
                    <div class="d-flex justify-content-center" style="margin-top: 60px">
                        <c:choose>
                            <c:when test="${sessionScope.customer == null}">
                                <button type="button" class="btn btn-primary position-relative" style="width: 100px; height: 50px" onclick="return window.confirm('You must be LOGGED in to perform this task!')">
                                    Cart
                                </button>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${loca != null}">
                                        <button type="button" class="btn btn-primary position-relative" style="width: 100px; height: 50px" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                            Cart
                                            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" >
                                                ${fn:length(sessionScope.cart)}
                                            </span>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-primary position-relative" style="width: 100px; height: 50px" onclick="return window.confirm('You need to FILL in your location details or your identity number!')">
                                            Cart
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <!--đang-->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content" style="width: 40em">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Cart</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form action="/AD_Network/MainController" method="post">
                                        <input type="hidden" name="action" value="buyAction">
                                        <input type="hidden" name="typeBuy" value="buyByCart"/>
                                        <table class="table table-striped table-hover ">
                                            <thead class="table-dark">
                                                <tr >
                                                    <th>Number</th>
                                                    <th>Service</th>
                                                    <th>Price</th>  
                                                    <th>Type</th>
                                                    <th style="font-size: 14px">Total Service</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="cartInfor" items="${sessionScope.cart}">
                                                    <c:set var="i" value="${i+1}" scope="page"/> 
                                                    <tr>
                                                        <td>${i}</td>
                                                        <td>${cartInfor.key}</td>
                                                        <c:set var="pr" value="${serviceInfor.getServiceForCartByName(cartInfor.key).price}" />
                                                        <fmt:formatNumber var="priceService" value="${pr}" type="number" pattern="###,###"/>
                                                        <td>${priceService}đ/tháng</td>  
                                                        <td>${serviceInfor.getServiceForCartByName(cartInfor.key).sTypeId.sTypeDesc}</td>
                                                        <td><input type="number" value="${cartInfor.value}"  min="1"  max="3" name="txtquantity-${i}"></td>
                                                <input type="hidden" name="txtQuantityNumber-${i}" value="${cartInfor.key}"/>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="modal-footer">
                                            <input type="hidden" name="numberServiceInCart" value="${i}">
                                            <select name="transCycle">
                                                <option value="1">1 month</option>
                                                <option value="6">6 months</option>
                                                <option value="12">12 months</option>
                                            </select>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Buy</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="service" class="container" style="padding: 70px 0 55px 0px;">
                        <div class="row justify-content-around" style="margin: 0">
                            <c:forEach var="internet" items="${serviceInfor.getServiceByType(1)}">
                                <div id="internet" class="col-lg-5 service" style="margin-bottom: 3em;">
                                    <img src="/AD_Network/assets/img/banner_device/bd_internet.png" alt="" style="width: 100%;">
                                    <h2 class="fw-normal" style="color:#ff0000">${internet.serviceName}</h2>
                                    <h5 style="color: green">${internet.deviceId.deviceName}</h5>
                                    <p>Tốc độ: ${internet.deviceId.deviceDetailDesc}</p>
                                    <div style="color: #0000ff">
                                        <c:set var="pr" value="${internet.price}" />
                                        <fmt:formatNumber var="price" value="${pr}" type="number" pattern="###,###"/>
                                        <h4>${price}đ/tháng</h4>
                                    </div>
                                    <c:choose>
                                        <c:when test="${internet.serviceStatus.serviceStatus == 0}">
                                            <div style="width: 100%; display: flex; justify-content: flex-end;">
                                                <button class="CartBtn" style="margin-right: 20px; background-color: #ff3333; color: white">Sold Out</button>
                                                <button class="CartBtn" style="color: white">Sold Out</button>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when  test="${sessionScope.customer == null}">
                                                    <div style="width: 100%; display: flex; justify-content: flex-end;">           
                                                        <button class="CartBtn" style="margin-right: 20px; background-color: #ff3333 " onclick="return window.confirm('You must be LOGGED in to perform this task!')"><span class="IconContainer"><p class="fa fa-shopping-cart"></p></span><p class="text">BUY NOW </p></button>
                                                        <button class="CartBtn" onclick="return window.confirm('You must be LOGGED in to perform this task!')"><span class="IconContainer"><p class="fa fa-shopping-cart"></p></span><p class="text">Add to Cart</p></button>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${loca != null}">
                                                            <div style="width: 100%; display: flex; justify-content: flex-end;">
                                                                <form action="/AD_Network/MainController" method="post">
                                                                    <input type="hidden" name="action" value="buyAction">
                                                                    <input type="hidden" name="typeBuy" value="buyNow"/>
                                                                    <input type="hidden" name="service" value="${internet.serviceId}">
                                                                    <button class="CartBtn" style="margin-right: 20px; background-color: #ff3333 "><span class="IconContainer"><p class="fa fa-shopping-cart"></p></span><p class="text">BUY NOW </p></button>        
                                                                    <select name="transCycleBuyNow" class="form-select text-center" style="width:87%; margin:5px 0;">
                                                                        <option value="1">1 month</option>
                                                                        <option value="6">6 months</option>
                                                                        <option value="12">12 months</option>
                                                                    </select>
                                                                </form>
                                                                <form action="/AD_Network/MainController" method="get">
                                                                    <input type="hidden" name="action" value="addToCart">
                                                                    <input type="hidden" name="pageAction" value="internet">
                                                                    <input type="hidden" name="service" value="${internet.serviceId}">
                                                                    <button class="CartBtn"><span class="IconContainer"><p class="fa fa-shopping-cart"></p></span><p class="text">Add to Cart</p></button>
                                                                </form>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div style="width: 100%; display: flex; justify-content: flex-end;">           
                                                                <button class="CartBtn" style="margin-right: 20px; background-color: #ff3333 " onclick="return window.confirm('You need to FILL in your location details or your identity number!')"><span class="IconContainer"><p class="fa fa-shopping-cart"></p></span><p class="text">BUY NOW </p></button>
                                                                <button class="CartBtn" onclick="return window.confirm('You need to FILL in your location details or your identity number!')"><span class="IconContainer"><p class="fa fa-shopping-cart"></p></span><p class="text">Add to Cart</p></button>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
