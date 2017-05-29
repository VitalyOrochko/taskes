<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.epam.webapp.entity.Role" %>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="locale" />
<html>
<head>
    <title><fmt:message key="title.main"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontello.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript"></script>
</head>
<body>

    <c:choose>
        <c:when test="${empty person}">
            <%@ include file="/WEB-INF/jspf/guestMainHeader.jspf"%>
        </c:when>
        <c:when test="${not empty person and person.role eq Role.APPLICANT}">
            <%@ include file="/WEB-INF/jspf/applicantMainHeader.jspf"%>
        </c:when>
        <c:when test="${not empty person and person.role eq Role.HR}">
            <%@ include file="/WEB-INF/jspf/hrMainHeader.jspf"%>
        </c:when>
        <c:when test="${not empty person and person.role eq Role.ADMIN}">
            <%@ include file="/WEB-INF/jspf/adminMainHeader.jspf"%>
        </c:when>
    </c:choose>

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <div class="carousel-caption">
                    <h3 class="main-poster" style="color: white;"><fmt:message key="main.poster.first"/></h3>
                </div>
                <img src="${pageContext.request.contextPath}/img/main1.jpg" alt="Chania">
            </div>
            <div class="item">
                <div class="carousel-caption">
                    <h3 class="main-poster"><fmt:message key="main.poster.second"/></h3>
                </div>
                <img src="${pageContext.request.contextPath}/img/main2.jpg" alt="Chania">
            </div>
            <div class="item">
                <div class="carousel-caption">
                    <h3 class="main-poster"><fmt:message key="main.poster.third"/></h3>
                </div>
                <img src="${pageContext.request.contextPath}/img/main3.jpg" alt="Chania">
            </div>
        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel"  data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        </a>
    </div>
    <c:import url="/WEB-INF/jspf/footer.jspf" charEncoding="utf-8"/>
</body>
</html>
