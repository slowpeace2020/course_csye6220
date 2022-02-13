<%--
  Created by IntelliJ IDEA.
  User: jg
  Date: 2/12/22
  Time: 10:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>CSV Read</title>
</head>
<body>
<h1>here</h1>
<h2>${pageContext.request.contextPath}</h2>
<form id="upload_csv" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/part4">
    <input id="file_upload" name="csv_file" type="file">
    <input type="submit" value="submit">
</form>
</body>
</html>
