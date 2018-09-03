<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>
    <c:url var="productUrl" value="/products">
    </c:url>
    <a href="${productUrl}">Expert Soft</a>
</p>
<p>
    <c:url var="productUrl" value="/cart">
    </c:url>
    <a href="${productUrl}">Cart Page</a>
</p>
