<%--
  Created by IntelliJ IDEA.
  User: jg
  Date: 2/12/22
  Time: 10:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Excel Read</title>
</head>
<body>
<h1>Excel Upload</h1>
<form id="upload_csv" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/part5">
    <input id="file_upload" name="excel_file" type="file">
    <input type="submit" value="submit">
</form>
</body>
</html>
