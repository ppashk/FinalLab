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
                        <th></th>
                    </tr>
                </thead>
                <c:forEach items="${receipts}" var="receipt">
                    <tr class="bg-primary">
                        <td>${receipt.id}</td>
                        <td>${receipt.totalPrice}</td>
                        <td>${receipt.closed}</td>
                        <td></td>
                    </tr>
                    <tr class="bg-secondary">
                        <th><fmt:message key = "id"/></th>
                        <th><fmt:message key = "name"/></th>
                        <th><fmt:message key = "price"/></th>
                        <th><fmt:message key = "quantity"/></th>
                    </tr>
                    <c:forEach items="${receipt.lines}" var="line">
                        <tr class="bg-secondary">
                            <td>${line.id}</td>
                            <td>${line.name}</td>
                            <td>${line.price}</td>
                            <td>${line.quantity}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
            <c:if test="${not empty message}">
                <div><fmt:message key = "${message}"/></div>
            </c:if>
            <form action="chief" method="post">
                <label>
                    <input type="number" name="receiptId" placeholder="<fmt:message key = "chief.receipt.id"/>" required>
                </label>
                <button type="submit" name="action" value="cancelReceipt" class="btn"><fmt:message key = "chief.cancel"/></button>
            </form>
            <form action="chief" method="post">
                <label>
                    <input type="text" name="idOrName" placeholder="<fmt:message key = "chief.id.or.name"/>" required>
                </label>
                <button type="submit" name="action" value="cancelLine" class="btn"><fmt:message key = "chief.cancel.line"/></button>
            </form>
            <form action="chief" method="post">
                <button type="submit" name="action" value="xReport" class="btn"><fmt:message key = "chief.x.report"/></button>
            </form>
            <form action="chief" method="post">
                <button type="submit" name="action" value="zReport" class="btn"><fmt:message key = "chief.z.report"/></button>
            </form>
            <form action="chief" method="post">
                <button type="submit" name="action" value="createXReport" class="btn"><fmt:message key = "chief.create.report"/></button>
            </form>
        </main>
    </div>
</body>
</html>