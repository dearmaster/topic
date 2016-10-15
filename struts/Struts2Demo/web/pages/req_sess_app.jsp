<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display the result from request, session and application</title>
</head>
<body>
    <h2><%= request.getAttribute("greeting")%></h2>
    <h2>${sessionScope.password}</h2>
    <h2>${applicationScope.counter}</h2>
</body>
</html>
