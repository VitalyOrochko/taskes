
<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.epam.webapp.entity.Role" %>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="locale" />
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<body>

<c:choose>
    <c:when test="${empty person}">
        <%@ include file="/WEB-INF/jspf/guestHeader.jspf"%>
    </c:when>
    <c:when test="${not empty person and person.role eq Role.APPLICANT}">
        <%@ include file="/WEB-INF/jspf/applicantHeader.jspf"%>
    </c:when>
    <c:when test="${not empty person and person.role eq Role.HR}">
        <%@ include file="/WEB-INF/jspf/hrHeader.jspf"%>
    </c:when>
    <c:when test="${not empty person and person.role eq Role.ADMIN}">
        <%@ include file="/WEB-INF/jspf/adminHeader.jspf"%>
    </c:when>
</c:choose>

<div class="container">
    <h2>${person.name} ${person.surname}</h2>
    <div class="row">
        <div class="col-lg-4">
            <img src="${pageContext.request.contextPath}/upload/${person.id}.jpg" alt="Фото не загружено" width="300" height="300"/>
            <form action="/Controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="upload_file"/>
                <input type="file" name="file" title="Выберите jpg файл"><br>
                <input type="submit" value="<fmt:message key="profile.upload.button"/>"><br>
            </form>
        </div>
        <div class="col-lg-8">
            <h3><fmt:message key="profile.birthDate"/>:${person.birthDate}</h3>
            <h3><fmt:message key="profile.education"/>:${person.education}</h3>
            <h3><fmt:message key="profile.phone"/>:${person.phone}</h3>
            <h3><fmt:message key="profile.email"/>:${person.email}</h3>
        </div>
    </div>
</div>
</body>
</html>
