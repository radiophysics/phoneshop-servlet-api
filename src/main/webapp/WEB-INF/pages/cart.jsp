<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>
<fmt:setBundle basename="i18n.messages"/>

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
<form method="post" action="<c:url value="/cart"/> " class="cart">
    <c:if test="${not empty success}">
        ${successMsg}
    </c:if>
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
                <td>
                    <input type="hidden" name="productId" value="${cartItem.product.id}">
                    <input type="text" id="quantity${status.index}" name="quantity"
                           value="${quantities[status.index] != null ? quantities[status.index] : cartItem.quantity}">
                    <c:if test="${not empty errors && not empty errors[status.index]}">
                        <label for="quantity${status.index}">${errors[status.index]}</label>
                    </c:if>
                </td>
                <td>
                    <button type="submit" name="delete" value="${status.index}">X</button>
                </td>
            </tr>
        </c:forEach>
    </table>
<input type="submit" value="Update">
</form>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
