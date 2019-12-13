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
                <tr class="">
                    <th><fmt:message key = "id"/></th>
                    <th><fmt:message key = "name"/></th>
                    <th><fmt:message key = "price"/></th>
                    <th><fmt:message key = "quantity"/></th>
                    <th><fmt:message key = "total.price"/></th>
                </tr>
                </thead>
                <c:forEach items="${lines}" var="line">
                    <tr>
                        <td>${line.id}</td>
                        <td>${line.name}</td>
                        <td>${line.price}</td>
                        <td>${line.quantity}</td>
                        <td></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>${receipt.totalPrice}</td>
                </tr>
            </table>
            <form action="cashier" method="post">
                <label>
                    <input type="text" name="idOrName" placeholder="<fmt:message key = "cashier.id.or.name"/>" required>
                </label>
                <label>
                    <input type="number" name="quantity" placeholder="<fmt:message key = "cashier.quantity"/>" required>
                </label>
                <button type="submit" name="action" value="createLine" class="btn"><fmt:message key = "cashier.create"/></button>
            </form>
            <form action="cashier" method="post">
                <button type="submit" name="action" value="closeReceipt" class="btn"><fmt:message key = "cashier.close"/></button>
            </form>
        </main>
    </div>
</body>
</html>