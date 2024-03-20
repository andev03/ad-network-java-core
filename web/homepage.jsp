<%-- 
    Document   : homepage
    Created on : Jan 28, 2024, 9:34:53 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="assets/css/guest.css" />
        <title>A&D Network</title>
    </head>

    <body style="margin: 0">

        <%@include file="template/headerUser.jsp" %>
        <main class="container-fluid row" style="padding: 0; margin: 0">

            <%@include file="template/sidebarUser.jsp" %>
            <div class="col-3"></div>

            <div class="col-9" data-bs-spy="scroll" data-bs-target="#list-example" data-bs-smooth-scroll="true" tabindex="0"
                 style="padding: 0;">
                <div class="bannerHead">
                    <img src="assets/img/banner_content/header_content_banner.png" alt="" style="width: 100%;">
                </div>
                <div class="textTitle" style="padding: 30px 0 30px 0;">Home</div>
                <div data-bs-spy="scroll" data-bs-target="#navbar-example3" data-bs-smooth-scroll="true"
                     class="scrollspy-example-2" tabindex="0">
                    <div id="home">
                        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel" data-bs-interval="1500">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                                        aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                                        aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                                        aria-label="Slide 3"></button>
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                                        aria-label="Slide 4"></button>
                                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4"
                                        aria-label="Slide 5"></button>
                            </div>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="assets/img/banner_content/bc01.png" class="d-block w-100" alt="bestService">
                                </div>
                                <div class="carousel-item">
                                    <img src="assets/img/banner_content/bc02.png" class="d-block w-100" alt="Internet_Service">
                                </div>
                                <div class="carousel-item">
                                    <img src="assets/img/banner_content/bc03.png" class="d-block w-100" alt="Cloud_Service">
                                </div>
                                <div class="carousel-item">
                                    <img src="assets/img/banner_content/bc04.png" class="d-block w-100" alt="Television_Service">
                                </div>
                                <div class="carousel-item">
                                    <img src="assets/img/banner_content/bc05.png" class="d-block w-100" alt="Camera_Service">
                                </div>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                                    data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                                    data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>
                    <div id="about" style="padding: 65px 0 90px 0;">
                        <div class="wrapper">
                            <div class="firstText">SOME FEATURES ABOUT</div>
                            <div class="textTitle">A&D Network</div>
                            <div class="transAbout" style="padding: 15px;">
                                <p>A&D Network is a pioneering company founded by Nguyen Hoang An and Truong Tan Dung. With a vision of
                                    innovation and commitment
                                    to providing the best customer experience, A&D Network is quickly becoming a trusted name in the
                                    telecommunications and technology
                                    sectors.</p>
                                <p>We specialize in providing modern and diverse services such as high-speed internet, security camera
                                    solutions, cloud storage,
                                    and television services. Our team of leading experts ensures stability and high quality in the provision
                                    of these services,
                                    continuously researching and developing to meet the increasing requirements of the market.</p>
                                <p>With an expanding vision and constant creativity, A&D Network is not only a business unit but also a
                                    reliable partner for customers and businesses.</p>
                            </div>
                            <div class="container row d-flex justify-content-center justify-content-evenly"
                                 style="margin-bottom: 50px;">

                                <img src="assets/img/banner_founder/bf01.png" alt="Nguyen Hoang An" class="col-5">
                                <img src="assets/img/banner_founder/bf02.png" alt="Truong Tan Dung" class="col-5">

                            </div>
                            <div class="d-flex justify-content-center text-decoration-none thirdText">
                                <a class="btn btn-success buto" href="view/platform/comingsoon.jsp"
                                   style="text-decoration: none; color: white; width: 280px;">FIND OUT MORE</a>
                            </div>
                        </div>
                    </div>
                    <div class="textTitle" style="padding: 30px 0 30px 0;">Service</div>
                    <div id="service" class="d-flex justify-content-around" style="padding: 70px 0 55px 0px;">

                        <div class="col-2 service">
                            <img src="assets/img/ads/InternetAds.png" alt="" style="width: 100%;">
                            <h2 class="fw-normal">Internet</h2>
                            <p>Advancements in internet infrastructure further bolster this trend, facilitating faster connections 
                                and enhancing user experiences online.</p>
                            <a href="#"><p class="btn btn-danger button-sm"><span>See more</span></p></a>
                        </div>
                        <div class="col-2 service">
                            <img src="assets/img/ads/CloudAds.png" alt="" style="width: 100%;">
                            <h2 class="fw-normal">Cloud</h2>
                            <p>Cloud storage offers scalable, flexible, and secure data storage solutions, revolutionizing how
                                information
                                is stored, accessed, and managed.</p>
                            <a href="#"><p class="btn btn-danger button-sm"><span>See more</span></p></a>
                        </div>
                        <div class="col-2 service">
                            <img src="assets/img/ads/televisionAds.png" alt="" style="width: 100%;">
                            <h2 class="fw-normal">Television</h2>
                            <p>Television services provide a diverse array of entertainment, news, and educational content, shaping
                                global
                                culture and communication dynamics.</p>
                            <a href="#"><p class="btn btn-danger button-sm"><span>See more</span></p></a>
                        </div>
                        <div class="col-2 service">
                            <img src="assets/img/ads/CameraAds.png" alt="" style="width: 100%;">
                            <h2 class="fw-normal">Camera</h2>
                            <p>Camera capture moments, preserve memories, and offer a unique perspective on the world, enabling
                                creativity
                                and storytelling across diverse mediums.</p>
                            <a href="#"><p class="btn btn-danger button-sm"><span>See more</span></p></a>
                        </div>
                    </div>
                </div>
                <%@include file="template/footer.jsp" %>
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/AccountDropDown.js"></script>
    </body>

</html>
