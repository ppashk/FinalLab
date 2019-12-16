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
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th><fmt:message key = "id"/></th>
                    <th><fmt:message key = "total.price"/></th>
                    <th><fmt:message key = "isClosed"/></th>
                    <th><fmt:message key = "login.user"/></th>
                </tr>
                </thead>
                <c:forEach items="${reports}" var="report">
                    <tr class="bg-primary">
                        <td>${report.id}</td>
                        <td>${report.totalPrice}</td>
                        <td>${report.closed}</td>
                        <td>${report.username}</td>
                    </tr>
                    <tr class="bg-secondary">
                        <th><fmt:message key = "id"/></th>
                        <th><fmt:message key = "name"/></th>
                        <th><fmt:message key = "price"/></th>
                        <th><fmt:message key = "quantity"/></th>
                    </tr>
                    <c:forEach items="${report.reportLines}" var="reportLine">
                        <tr class="bg-secondary">
                            <td>${reportLine.id}</td>
                            <td>${reportLine.name}</td>
                            <td>${reportLine.price}</td>
                            <td>${reportLine.quantity}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </main>
    </div>
</body>
</html>