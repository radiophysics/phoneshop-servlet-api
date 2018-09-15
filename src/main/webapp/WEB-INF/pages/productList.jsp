<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>

<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
  <title>Product List</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<p>
  Hello from product list!
</p>
<table>
  <thead>
    <tr>
      <td>Id</td>
      <td>Code</td>
      <td>Description</td>
      <td>Price</td>
      <td>Currency</td>
      <td>Stock</td>
    </tr>
  </thead>
  <c:forEach var="product" items="${products}">
    <tr>
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
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>