<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.Product" scope="request"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <title>${product}</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<div>
    <form method="post" action="<c:url value="/products/"/>${product.id} ">
        <c:if test="${not empty param.addedQuantity}">
            <fmt:message key="success"/>
        </c:if>
        <table>
            <tr>
                <td>Id</td>
                <td>${product.id}</td>
            </tr>
            <tr>
                <td>Code</td>
                <td>${product.code}</td>
            </tr>
            <tr>
                <td>Description</td>
                <td>${product.description}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>${product.price}</td>
            </tr>
            <tr>
                <td>Currency</td>
                <td>${product.currency}</td>
            </tr>
            <tr>
                <td>Stock</td>
                <td>${product.stock}</td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="quantity" id="quantity"
                           value="${empty param.quantity ? 1 : param.quantity}"
                           style="text-align: right">
                    <c:if test="${not empty error.number.format}">
                        <label for="quantity" style="color: red; display: block">
                            <fmt:message key="error.number.format"/>
                        </label>
                    </c:if>
                    <c:if test="${not empty error.quantity.stock}">
                        <label for="quantity" style="color: red; display: block">
                            <fmt:message key="error.quantity.stock"/>
                        </label>
                    </c:if>
                </td>
                <td>
                    <input type="submit" value="Add to cart">
                </td>
            </tr>
        </table>
    </form>
</div>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>