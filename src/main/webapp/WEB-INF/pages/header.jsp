<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="custom_navigation">
    <c:url var="productUrl" value="/products"/>
    <a class="nav_button" href="${productUrl}" style="text-align: left">Phoneshop</a>
    <c:url var="productUrl" value="/cart"/>
    <a class="cart_button" href="${productUrl}" style="text-align: right">Cart</a>
</nav>
