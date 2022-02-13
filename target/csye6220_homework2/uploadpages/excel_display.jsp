<%--
  Created by IntelliJ IDEA.
  User: jg
  Date: 2/12/22
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Excel Info Display</title>
</head>
<body>
<div id="peopleTable">
        <h3 style="text-align: center">people info list</h3>
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>Id</th>
                <th>Name</th>
                <th>Region</th>
            </tr>

            <c:forEach items="${people}" var="person" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${person.name}</td>
                    <td>${person.region}</td>
                </tr>

            </c:forEach>

        </table>
</div>
<div id="orderTable"></div>
    <h3 style="text-align: center">order info list</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>Row Id</th>
            <th>Order Id</th>
            <th>Order Date</th>
            <th>Ship Date</th>
            <th>Ship Mode</th>
            <th>Customer Id</th>
            <th>Customer Name</th>
            <th>Segment</th>
            <th>Country</th>
            <th>City</th>
            <th>State</th>
            <th>Postal Code</th>
            <th>Region</th>
            <th>Product Id</th>
            <th>Category</th>
            <th>Sub-Category</th>
            <th>Product Name</th>
            <th>Sales</th>
            <th>Quality</th>
            <th>Discount</th>
            <th>Profit</th>
        </tr>

        <c:forEach items="${orders}" var="user" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${user.orderID}</td>
                <td>${user.orderDate}</td>
                <td>${user.shipDate}</td>
                <td>${user.shipMode}</td>
                <td>${user.customerID}</td>
                <td>${user.customerName}</td>
                <td>${user.segment}</td>
                <td>${user.country}</td>
                <td>${user.city}</td>
                <td>${user.state}</td>
                <td>${user.postalCode}</td>
                <td>${user.region}</td>
                <td>${user.productID}</td>
                <td>${user.category}</td>
                <td>${user.subCategory}</td>
                <td>${user.productName}</td>
                <td>${user.sales}</td>
                <td>${user.quantity}</td>
                <td>${user.discount}</td>
                <td>${user.profit}</td>
            </tr>

        </c:forEach>

    </table>
</div>
<div id="returnTable">
    <h3 style="text-align: center">return info list</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>Id</th>
            <th>Name</th>
            <th>Region</th>
        </tr>

        <c:forEach items="${returns}" var="backInfo" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${backInfo.orderId}</td>
                <td>${backInfo.returned}</td>
            </tr>

        </c:forEach>
    </table>
</div>
</body>
</html>
