<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 20-Jun-18
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form method="POST" modelAttribute="user">


    <label>Name</label>
    <form:input type="text" path="name" id="name"/>
    <label>Surname</label>
    <form:input type="text" path="surname" id="surname"/>
    <input type="submit" value="Register">
</form:form>

</body>
</html>
