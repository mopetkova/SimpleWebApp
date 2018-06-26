<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Registration</h2>
<div class="container">
    <div clas="form-group">
        <form:form method="POST" action="/registration" modelAttribute="employee">

            <label>Username:</label>
            <form:input path="username"/><br>

            <label>Password:</label>
            <form:input path="password"/><br><br>

            <label>Name:</label>
            <form:input path="name"/><br>

            <label>Surname:</label>
            <form:input path="surname"/><br>

            <label>Country:</label>
            <form:input path="country"/><br>

            <label>Birth Date:</label>
            <form:input path="birthDate" type="date" pattern="yyyy-MM-dd"/><br>


            <%--<label>Marital Status:</label>--%>
            <%--<form:input path="maritalStatusId"/><br>--%>

            <%--<label>Marital Status: </label>--%>
            <%--<div>--%>
            <%--<form:select path="maritalStatusId">--%>
            <%--&lt;%&ndash;<form:option value="-" label="Marital Status"/>&ndash;%&gt;--%>
            <%--<form:options var="maritalStatus" items="${maritalStatusList}"/>--%>
            <%--<form:options "${maritalStatusList.}"/>--%>
            <%--</form:select>--%>
            <%--</div>--%>


            <%--<option value="${maritalStatus.maritalStatusId}"--%>
            <%--${employee.maritalStatus.status eq maritalStatus.status ? 'selected':''} >--%>
            <%--${maritalStatus.maritalStatusId} - ${maritalStatus.status}--%>
            <%--</option>--%>


            <label>Marital Status:</label>
            <form:select path="maritalStatus">
                <c:forEach var="maritalStatus" items="${maritalStatusList}">
                    <form:option value="${maritalStatus}">${maritalStatus.maritalStatusId} - ${maritalStatus.status}</form:option>
                </c:forEach>
            </form:select>

            <br>

            <%--<label>--%>
            <%--<span>Skills to add:</span>--%>
            <%--<select id="skills" name="skillsId">--%>
            <%--<c:forEach var="skill" items="${skills}">--%>
            <%--<option>${skill.name}</option>--%>
            <%--&lt;%&ndash;<option value="${skill.skillId}-${skill.name}">${skill.name}</option>&ndash;%&gt;--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</label>--%>

            <%--<label>Skills: </label>--%>
            <%--<form:options items="${skills}" itemValue="skillId" itemLabel="name" />--%>

            <%--<input class="upsert__input" name="birthDate" type="date" placeholder="yyyy-MM-dd"--%>
            <%--value="<fmt:formatDate pattern="yyyy-MM-dd" value = "${employee.birthDate}" />">--%>
            <br>

            <input class="btn btn-default" type="submit" value="Save"/>
        </form:form>
    </div>
</div>
</body>
</html>
