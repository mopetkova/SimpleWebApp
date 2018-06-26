<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>


<body>
<h2>Welcome</h2>
<div class="container">
    <div class="form-group">

        <form:form method="POST" action="/" modelAttribute="employee">
            <form:label path="username"> Username </form:label>
            <form:input path="username"/> <br>

            <form:label path="password">Password</form:label>
            <form:input path="password"/> <br>
            <br>
            <input class="btn btn-default" type="submit" value="Submit"/>
        </form:form>
        <br>
        <a class="btn btn-default" href="/registration">Registration</a>
    </div>

</div>
</body>
</html>