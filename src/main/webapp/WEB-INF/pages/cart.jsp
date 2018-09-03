<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>

<html>
<head>
    <title>Cart</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<p>
    Hello from cart!
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
                <c:url var="productUrl" value="/products/${cartItem.product.id}">
                </c:url>
                <a href="${productUrl}">${cartItem.product.code}</a>
            </td>
            <td>${cartItem.product.description}</td>
            <td>${cartItem.product.price}</td>
            <td>${cartItem.product.currency}</td>
            <td>${cartItem.quantity}</td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>