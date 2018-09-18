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
<form method="post">
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
                <td>
                    <input type="text" name="quantity" id="quantity"
                           value="${quantity}"
                           style="text-align: right">
                    <c:if test="${not empty param.updatedQuantity}">
                    <label for="quantity" style="color: limegreen; display: block">
                        <fmt:message key="update"/>
                    </label>
                    </c:if>
                    <c:if test="${errorNumberFormat}">
                    <label for="quantity" style="color: red; display: block">
                        <fmt:message key="errorNumberFormat"/>
                    </label>
                    </c:if>
                    <c:if test="${errorQuantityStock}">
                    <label for="quantity" style="color: red; display: block">
                        <fmt:message key="errorQuantityStock"/>
                    </label>
                    </c:if>
                    <c:if test="${errorNegativeNumber}">
                    <label for="quantity" style="color: red; display: block">
                        <fmt:message key="errorNegativeNumber"/>
                    </label>
                    </c:if>
                <td>
                    <input type="submit" name="delete" value="X"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Update">
</form>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>