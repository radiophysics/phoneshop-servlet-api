<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Checkout</title>
</head>

<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<p class="title_p">
    Checkout
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
    <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="status">
        <tr>
            <td>${cartItem.product.id}</td>
            <td>
                <c:url var="productUrl" value="/products/${cartItem.product.id}"/>
                <a href="${productUrl}">${cartItem.product.code}</a>
            </td>
            <td>${cartItem.product.description}</td>
            <td><fmt:formatNumber value="${cartItem.product.price}"/></td>
            <td>${cartItem.product.currency}</td>
            <td>${cartItem.quantity}</td>
        </tr>
    </c:forEach>
</table>
<form method="post">
    <p>
        <label for="name">Name</label>
        <input name="Name" id="name">
    </p>
    <p>
        <label for="phone">Phone</label>
        <input name="Phone" id="phone">
    </p>
    <p>
        <label for="address">Address</label>
        <input name="Address" id="address">
    </p>
    <input value="Make order" type="submit">
</form>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
