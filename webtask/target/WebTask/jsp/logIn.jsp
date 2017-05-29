<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="locale" />
<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="login.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontello.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<%@ include file="/WEB-INF/jspf/guestHeader.jspf"%>
<div class="content">
    ${registrationMessage}
    <div class = "container">
        <form name="loginForm" class="form-reg" id="form-login" action="/Controller" method="post">
            <h3 class="head"><fmt:message key="login.title"/></h3>
            <div class="login-content">
                <input type="hidden" name="command" value="log_in"/>
                <label for="email1"><fmt:message key="login.mail"/></label>
                <input type="email" id="email1" name="email" required class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}">
                <br/>
                <label for="password1"><fmt:message key="login.password"/></label>
                <input type="password" id="password1" name="password" required class="form-control" pattern=".{6,}" title="Six or more characters">
                <p class="error-message">${LogInMessage}</p>
                <input type="submit" value="<fmt:message key="login.button.authorization"/>" class="btn btn-login-submit"/>
                <p id="reg-link" class="link"><fmt:message key="login.registration"/></p>
                <br/>
            </div>
        </form>
        <form name="regForm" class="form-reg" id="form-register" action="/Controller" method="post">
            <h3 class="head"></h3>
            <div class="login-content">
                <input type="hidden" name="command" value="registration_applicant"/>
                <div class="form-group-inline">
                    <label for="first-name"><fmt:message key="login.name"/><span class="star">*</span></label>
                    <input type="text" id="first-name" name="first-name" class="form-control input" pattern="[^\s\d]{2,}" title = "Two or more letters" required>
                </div>
                <div class="form-group-inline">
                    <label for="last-name"><fmt:message key="login.surname"/><span class="star">*</span></label>
                    <input type="text" id="last-name" name="last-name" class="form-control input" pattern="[^\s\d]{2,}" title = "Two or more letters" required>
                </div>
                <div class="form-group-inline">
                    <label for="birth-date"><fmt:message key="login.birth.date"/><span class="star">*</span></label>
                    <input type="date" id="birth-date" name="birth-date" class="form-control input" required>
                </div>
                <div class="form-group-inline">
                    <label for="phone"><fmt:message key="login.phone"/><span class="star">*</span></label>
                    <input type="text" id="phone" name="phone" class="form-control input" pattern="(\+)?[0-9]{7,13}" required>
                </div>
                <div class="form-group-inline">
                    <label for="email2"><fmt:message key="login.mail"/><span class="star">*</span></label>
                    <input type="email" id="email2" name="email" class="form-control input"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                </div>
                <div class="form-group-inline">
                    <label for="password2"><fmt:message key="login.password"/><span class="star">*</span></label>
                    <input type="password" id="password2" name="password" class="form-control input" pattern=".{6,}" title="Six or more characters"  required>
                </div>
                <div class="form-group-inline">
                    <label for="education"><fmt:message key="login.education"/><span class="star">*</span></label>
                    <select id="education" class="form-control input" name="education">
                        <option value="middle"><fmt:message key="login.education.middle"/></option>
                        <option value="high"><fmt:message key="login.education.high"/></option>
                        <option value="magistr"><fmt:message key="login.education.magistr"/></option>
                        <option value="candidate"><fmt:message key="login.education.candidate"/></option>
                        <option value="doctor"><fmt:message key="login.education.doctor"/></option>
                    </select>
                </div>
                <input type="submit" value="<fmt:message key="login.button.registration"/>" class="btn btn-login-submit"/>
                <p id="login-link" class="link"><fmt:message key="login.title"/></p>
                <br/>
            </div>
        </form>
    </div>
</div>

<c:import url="/WEB-INF/jspf/footer.jspf" charEncoding="utf-8"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/script-login.js"></script>

</body>
</html>