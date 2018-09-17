<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>

<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Cart</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<p class="title_p">
    Cart
</p>
<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>Code</td>
        <td>Description</td>
        <td>Price</td>
        <td>Currency</td>
        <td>Quantity</td>
    </tr>
    </thead>
    <c:forEach var="cartItem" items="${cart.cartItems}">
        <tr>
            <td>${cartItem.product.id}</td>
            <td>
                <c:url var="productUrl" value="/products/${cartItem.product.id}"/>
                <a href="${productUrl}">${cartItem.product.code}</a>
            </td>
            <td>${cartItem.product.description}</td>
            <td><fmt:formatNumber value="${cartItem.product.price}"/></td>
            <td>${cartItem.product.currency}</td>
            <td><fmt:formatNumber value="${cartItem.quantity}"/></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>