<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.Product" scope="request"/>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <title>${product}</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<div>
    <c:if test="${not empty param.addedQuantity}">
        Added ${param.addedQuantity} successfully.
    </c:if>
<form method="post" action="<c:url value="/products"/>${product.id} ">
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
            <input type="text" name="quantity" id="quantity" value="${empty param.quantity ? 1 : param.quantity}"
                   style="text-align: right">
            <c:if test="${param.error}">
                <label for="quantity" style="colore: red; display: block">
                        ${param.error}
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