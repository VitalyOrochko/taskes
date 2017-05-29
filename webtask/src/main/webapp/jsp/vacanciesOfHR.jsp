
<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<%@ include file="/WEB-INF/jspf/hrHeader.jspf"%>

<div class="container">

    <form class="form-reg" action="/Controller" method="post">
        <h3 class="head"><fmt:message key="vacancy.add"/></h3>
            <input type="hidden" name="command" value="add_vacancy"/>
            <label for="job_title"><fmt:message key="vacancy.add.jobtitle"/></label>
            <input type="text" id="job_title" name="job_title" required class="form-control" pattern="[A-zA-я ,:.;'']{4,}" title="Four or more characters like letters and ,:.;''"/>
            <br/>
            <label for="description"><fmt:message key="vacancy.add.description"/></label>
            <input type="text" id="description" name="description" required class="form-control" pattern=".{6,}" title="Six or more characters"/>
            <br/>
            <label for="salary"><fmt:message key="vacancy.add.salary"/></label>
            <input type="number" id="salary" name="salary" min="0" required class="form-control"/>
            <br/>
            <div class="form-group-inline">
                <label for="it_level"><fmt:message key="vacancy.add.it.level"/><span class="star">*</span></label>
                <select id="it_level" class="form-control input" name="it_level">
                    <option value="beginning"><fmt:message key="vacancy.add.it.level.beginning"/></option>
                    <option value="middle"><fmt:message key="vacancy.add.it.level.middle"/></option>
                    <option value="advanced"><fmt:message key="vacancy.add.it.level.high"/></option>
                </select>
            </div>
            <div class="form-group-inline">
                <label for="english_level"><fmt:message key="vacancy.add.it.level"/><span class="star">*</span></label>
                <select id="english_level" class="form-control input" name="english_level">
                    <option value="elementary"><fmt:message key="vacancy.add.english.level.elementary"/></option>
                    <option value="pre-intermediate"><fmt:message key="vacancy.add.english.level.pre"/></option>
                    <option value="intermediate"><fmt:message key="vacancy.add.english.level.intermediate"/></option>
                    <option value="upper-intermediate"><fmt:message key="vacancy.add.english.level.upper"/></option>
                    <option value="advanced"><fmt:message key="vacancy.add.english.level.advanced"/></option>
                </select>
            </div>
            <input type="submit" value="<fmt:message key="vacancy.add.button"/>" class="btn btn-login-submit"/>
            <br/>
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

            </div>
        </div>
        <hr>
    </c:forEach>
</div>

</body>
</html>
