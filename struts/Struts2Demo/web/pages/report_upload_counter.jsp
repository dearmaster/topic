<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is report upload counter page</title>
</head>
<body>
    <h2><%= request.getAttribute("greeting")%></h2>
    <h2>${requestScope.greeting}</h2>
    <h2>${requestScope.slogan}</h2>
    <h2>You uploaded ${sessionScope.report.name}</h2>
    <h2>The upload process has been invoked ${applicationScope.counter}</h2>
</body>
</html>
