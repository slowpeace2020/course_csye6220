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
    <title>CSV Info Display</title>
</head>
<body>
<div id="csvTable">
        <h1 style="text-align: center">CSV info list</h1>
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
</body>
</html>
