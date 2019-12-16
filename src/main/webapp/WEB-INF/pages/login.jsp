<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <title>Store</title>
    <c:import url="components/head.jsp"/>
</head>
<body>
    <div id="wrapper">
        <c:import url="components/header.jsp"/>
        <main>
            <c:if test="${not empty error}">
                <div><fmt:message key = "${error}"/></div>
            </c:if>
            <fmt:message key="login.greeting"/>
            <form action="login" method="post">
                <label>
                    <input type="text" name="username" placeholder="<fmt:message key = "login.user"/>" required>
                </label>
                <label>
                    <input type="password" name="password" placeholder="<fmt:message key = "login.password"/>" required>
                </label>
                <button type="submit" class="btn"><fmt:message key = "login.submit"/></button>
            </form>
        </main>
    </div>
</body>
</html>