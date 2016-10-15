<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This page is to test the approach to get the request, session and application</title>
</head>
<body>
    <s:form action="useRawServletReqSesAppAction1" method="POST">
        <table>
            <tr>
                <td>username</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>password</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><input type="reset" value="reset"></td>
                <td><input type="submit" value="submit"></td>
            </tr>
        </table>
    </s:form>
</body>
</html>
