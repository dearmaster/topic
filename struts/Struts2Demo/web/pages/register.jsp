<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is register page</title>
</head>
<body>
        <s:form action="register!register" method="post">

            <table>
                <tr>
                    <td>username</td>
                    <td><input type="text" name="user.username"></td>
                </tr>
                <tr>
                    <td>password</td>
                    <td><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td>gender</td>
                    <td><input type="radio" name="user.gender" value="男">男 &nbsp;&nbsp;&nbsp;<input type="radio" name="user.gender" value="女">女</td>
                </tr>
<%--                <tr>
                    <td>birthday</td>
                    <td><input type="text" name="user.birthday"></td>
                </tr>--%>
                <tr>
                    <td>email</td>
                    <td><input type="text" name="user.email"></td>
                </tr>
                <tr>
                    <td>address</td>
                    <td><input type="text" name="user.address"></td>
                </tr>
                <tr>
                    <td><input type="reset" value="reset"></td>
                    <td><input type="submit" value="submit"></td>
                </tr>
            </table>

        </s:form>
</body>
</html>
