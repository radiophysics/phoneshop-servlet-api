<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>
    <c:url var="productUrl" value="/products"/>
    <a href="${productUrl}" style="text-align: left">Expert Soft</a>
    <c:url var="productUrl" value="/cart"/>
    <a href="${productUrl}" style="text-align: right">Cart Page</a>
</p>
