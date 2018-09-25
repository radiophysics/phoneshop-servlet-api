<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Order overview</title>
</head>
<body>
<jsp:include page="/WEB-INF/pages/header.jsp"/>
<p class="title_p">
    Your order is placed.
</p>
<table>
    <tr>
        <td>Name</td>
        <td><c:out value="${order.getName()}"/></td>
    </tr>
    <tr>
        <td>Phone</td>
        <td><c:out value="${order.getPhone()}"/></td>
    </tr>
    <tr>
        <td>Address</td>
        <td><c:out value="${order.getAddress()}"/></td>
    </tr>
    <tr>
        <td>Total cost</td>
        <td><fmt:formatNumber value = "${order.getTotalCost()}"/></td>
    </tr>
</table>
<jsp:include page="/WEB-INF/pages/footer.jsp"/>
</body>
</html>