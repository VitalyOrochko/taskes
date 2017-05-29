
<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.epam.webapp.entity.Role" %>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="locale" />
<html>
<head>
    <title><fmt:message key="vacancies.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontello.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main-style.css"/>
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
</c:choose>

<div class="container">

    <form action="/Controller" method="post">
        <input type="hidden" name="command" value="add_vacancy"/>
        <label for="title"><fmt:message key="vacancies.select.title"/></label>
        <input type="text" id="title" name="title" class="form-control input" pattern="[A-zА-я]{2,}" value="<fmt:message key="vacancies.select.title.value"/>" title = "Two or more letters" required/>
        <label for="salary"><fmt:message key="vacancies.select.salary"/></label>
        <input type="number" id="salary" name="salary" min="0" class="form-control input"/>
        <input type="submit" value="<fmt:message key="vacancies.select.submit"/>"/>
    </form>

    <c:forEach var="elem" items="${vacancies}">
        <div class="row">
            <div class="vacancyItem">
                <blockquote>
                    <h3 >${elem.jobTitle}</h3>
                    <p><fmt:message key="vacancies.item.description"/>:${elem.description}</p>
                    <p><fmt:message key="vacancies.item.salary"/>:${elem.salary}</p>
                    <p><fmt:message key="vacancies.item.english"/>:${elem.englishLevel}</p>
                    <p><fmt:message key="vacancies.item.it"/>:${elem.itLevel}</p>
                </blockquote>
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="register_interview"/>
                    <input type="hidden" name="id" value="${elem.id}"/>
                    <input type="submit" value="<fmt:message key="vacancies.button.register"/>" class="btn btn-login-submit"/>
                </form>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>

</body>
</html>
