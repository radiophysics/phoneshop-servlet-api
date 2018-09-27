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
<form method="post" action="<c:url value="/products/"/>${product.id} " class="product">
    <table>
        <tr>
            <td>Id</td>
            <td>Code</td>
            <td>Description</td>
            <td>Price</td>
            <td>Currency</td>
            <td>Stock</td>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr class="items">
                <td>${product.id}</td>
                <td>
                    <c:url var="productUrl" value="/products/${product.id}"/>
                    <a href="${productUrl}">${product.code}</a>
                </td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.currency}</td>
                <td>${product.stock}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>
