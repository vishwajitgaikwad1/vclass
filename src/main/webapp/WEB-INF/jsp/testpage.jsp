<%--
  Created by IntelliJ IDEA.
  User: vishwajit_gaikwad
  Date: 8/5/21
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <tr>
            <td>

                ${ROLE.equals("STUDENT")}
            </td>
        </tr>
                <c:forEach items="${TIMETABLE_MODEL}" var="v">
                    <tr>
                        <td>${v.TIME}</td>
                        <td>${v.MON}</td>
                        <td>${v.TUE}</td>
                        <td>${v.WED}</td>
                        <td>${v.THU}</td>
                        <td>${v.FRI}</td>
                        <td>${v.SAT}</td>
                    </tr>
                </c:forEach>

    </table>
</div>
</body>
</html>
