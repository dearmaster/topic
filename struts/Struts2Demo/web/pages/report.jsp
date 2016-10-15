<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is report page</title>
</head>
<body>
    <div>
        <s:form action="uploadReport" method="POST">
            <table>
                <tr>
                    <td>name</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td><input type="reset" value="reset"></td>
                    <td><input type="submit" value="submit"></td>
                </tr>
            </table>
        </s:form>
    </div>
</body>
</html>
