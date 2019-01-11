<%--
  Created by IntelliJ IDEA.
  User: tarena
  Date: 2018/12/29
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>实时文件上传</title>
</head>
<body>
<form action="http://localhost:8091/fileDemo" method="post" enctype="multipart/form-data">
    <input name="image" type="file">
    <input type="submit" value="提交">

</form>

</body>
</html>
