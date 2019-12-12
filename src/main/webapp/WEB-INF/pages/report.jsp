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
                </tr>
                </thead>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.quantity}</td>
                    </tr>
                </c:forEach>
            </table>
            <form action="loader" method="post">
                <label>
                    <input type="text" name="name" placeholder="<fmt:message key = "name"/>" required>
                </label>
                <label>
                    <input type="number" name="price" placeholder="<fmt:message key = "price"/>" required>
                </label>
                <label>
                    <input type="number" name="quantity" placeholder="<fmt:message key = "quantity"/>" required>
                </label>
                <button type="submit" name="action" value="createProduct" class="btn"><fmt:message key = "loader.create"/></button>
            </form>
            <form action="loader" method="post">
                <label>
                    <input type="number" name="id" placeholder="<fmt:message key = "id"/>" required>
                </label>
                <label>
                    <input type="number" name="quantity" placeholder="<fmt:message key = "quantity"/>" required>
                </label>
                <button type="submit" name="action" value="updateProduct" class="btn"><fmt:message key = "loader.create"/></button>
            </form>
        </main>
    </div>
</body>
</html>