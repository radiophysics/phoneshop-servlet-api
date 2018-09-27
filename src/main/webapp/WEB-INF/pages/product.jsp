<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.Product" scope="request"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>${product}</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<p class="title_p">
    ${product.description}
</p>
<form method="post" action="<c:url value="/products/"/>${product.id} " class="product">
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
                <c:if test="${not empty param.addedQuantity}">
                    <label for="quantity" style="color: limegreen; display: block">
                        <fmt:message key="success"/>
                            ${param.addedQuantity}
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
            </td>
            <td>
                <input type="submit" value="Add to cart">
            </td>
        </tr>
    </table>
</form>
<input type="submit" name="compare" value="Compare">
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>