<%--
  Created by IntelliJ IDEA.
  User: vishwajit_gaikwad
  Date: 26/4/21
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>V Class | Login</title>
    <style>
        .login-form {
            width: 300px;
            margin: 0 auto;
            font-family: Tahoma, Geneva, sans-serif;
        }
        .login-form h1 {
            text-align: center;
            color: #4d4d4d;
            font-size: 24px;
            padding: 20px 0 20px 0;
        }
        .login-form input[type="password"],
        .login-form input[type="text"] {
            width: 100%;
            padding: 15px;
            border: 1px solid #dddddd;
            margin-bottom: 15px;
            box-sizing:border-box;
        }
        .login-form input[type="submit"] {
            width: 100%;
            padding: 15px;
            background-color: #535b63;
            border: 0;
            box-sizing: border-box;
            cursor: pointer;
            font-weight: bold;
            color: #ffffff;
        }
    </style>
    <%--<link href="css/login.css" rel="stylesheet">--%>
</head>
<body>
<div class="login-form">
    <br>
    <h1>V - Class<hr></h1>
    <form:form action="authLogin" modelAttribute="loginVO">
       <form:input path="loginId" placeholder="Login ID"/>
       <form:password path="password" placeholder="Password" />
        <input type="submit" value="Submit" />
    </form:form>
    <br>
    <hr>

    <p style="text-align: center; color: red;" > ${ERROR_MESSAGE} </p>
</div>
</body>
</html>
