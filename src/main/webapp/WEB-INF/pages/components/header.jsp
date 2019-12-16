<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<header>
    <div class="container-fluid">
        <div class="col-lg-2 col-sm-4 col-6 header-top-right">
            <a href="language?locale=en" class="text-uppercase">en</a>
            <a href="language?locale=ru" class="text-uppercase ml-2">ru</a>
        </div>
        <div class="col-lg-4 col-sm-2 col-2 header-top-right">
            <c:choose>
                <c:when test="${not empty user}">
                    <a href="logout" class="text-uppercase ml-4"><fmt:message key="header.logout"/></a>
                </c:when>
                <c:otherwise>
                    <a href="login" class="text-uppercase login"><fmt:message key="header.login"/></a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="logo">
            <fmt:message key="header.logo"/>
        </div>
        <nav>
            <ul class="menu row justify-content-center align-items-center no-gutters">
                <li class="col-lg-2">
                    <a href="/store/"><fmt:message key="header.home"/></a>
                </li>
                <li class="col-lg-2">
                    <a href="/store/cashier"><fmt:message key="header.cashier"/></a>
                </li>
                <li class="col-lg-2">
                    <a href="/store/chief"><fmt:message key="header.chief"/></a>
                </li>
                <li class="col-lg-2">
                    <a href="/store/loader"><fmt:message key="header.loader"/></a>
                </li>
            </ul>
        </nav>
    </div>
</header>