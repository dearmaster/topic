<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is login page</title>
</head>
<body>
    <s:form action="login" method="post">

        <table>
            <tr>
                <td>username:</td>
                <td><input type="text" name="user.username"></td>
            </tr>
            <tr>
                <td>password:</td>
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
