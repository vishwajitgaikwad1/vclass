<%--
  Created by IntelliJ IDEA.
  User: vishwajit_gaikwad
  Date: 8/5/21
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TEST PAGE</title>

</head>
<body>
Welcome!
<div class="container">
    <table class="table table-striped">
        <tr>
            <td>
                ${RESPONSE.RESPONSE}
            </td>
        </tr>
        <tr>
            <td>
                ${RESPONSE.LOGIN_ID}
            </td>
        </tr>
        <tr>
            <td>
                ${RESPONSE.USER_MSTR_SEQ}
            </td>
        </tr>
    </table>
</div>
</body>
</html>
