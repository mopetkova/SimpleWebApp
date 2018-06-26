<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="form-group"><br>
        <form:form method="POST" action="/update" modelAttribute="employee">

            <label>Id:</label>
            <form:input path="employeeId" value="${employee.employeeId}"/><br>

            <label>Username:</label>
            <form:input path="username" value="${employee.username}"/><br>

            <label>Password:</label>
            <form:input path="password" value="${employee.password}"/><br>

            <label>Name:</label>
            <form:input path="name" value="${employee.name}"/><br>

            <label>Surname:</label>
            <form:input path="surname" value="${employee.surname}"/><br>

            <label>Country:</label>
            <form:input path="country" value="${employee.country}"/><br>


            <label>Birth Date:</label>
            <fmt:formatDate type="date" pattern="yyyy-MM-dd" var="dateString" value="${employee.birthDate}"/>
            <form:input path="birthDate" type="date" pattern="yyyy-MM-dd" value="${dateString}"/> <br>

            <label>Marital Status:</label><br>
            <c:forEach items="${maritalStatusList}" var="ms">
                <form:radiobutton modelAttribute="maritalStatus" path="maritalStatus" value="${ms}"/> ${ms.status}
            </c:forEach>

            <%--<form:form commandName="maritalStatus" name="formradioquest">--%>
                <%--<c:forEach var="ms" items="${maritalStatusList}">--%>
                    <%--<form:radiobutton path="maritalStatusId" value="${ms.maritalStatusId}" label="${ms.status}"/>--%>
                <%--</c:forEach>--%>
            <%--</form:form>--%>

            <br>

            <%--<lebel>Skills:</lebel><br>--%>
            <%--<c:forEach var="skill" items="${skills}">--%>
            <%--${skill.name}--%>
            <%--&lt;%&ndash;<form:checkbox modelAttribute="skill"&ndash;%&gt;--%>
            <%--&lt;%&ndash;path="skills" value="${skill.skillId}"> ${skill.name} </form:checkbox>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="checkbox" name="${skill.name}" value="${skill.skillId}"/> ${skill.name}&ndash;%&gt;--%>
            <%--</c:forEach>--%>

            <br>
            <%--<label>Skills:</label>--%>
            <%--<form:select path="skills">--%>
            <%--<c:forEach var="skill" items="${skills}">--%>
            <%--<form:option value="${skill}"> ${skill.skillId} - ${skill.name}</form:option>--%>
            <%--</c:forEach>--%>
            <%--</form:select>--%>


            <br>
            <input type="submit" value="Save"/>
        </form:form>
    </div>
</div>
</body>
</html>
