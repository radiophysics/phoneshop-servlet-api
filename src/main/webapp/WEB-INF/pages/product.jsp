<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.Product" scope="request"/>

<html>
<head>
    <title>${product}</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<table>
    <thead>
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
    </thead>
</table>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>