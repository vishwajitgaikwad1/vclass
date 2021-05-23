<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vishwajit_gaikwad
  Date: 5/5/21
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | V - Class</title>

    <style>
        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-direction: column;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            box-sizing: border-box;
            font: 400 20px/1.5 "Source Sans Pro", "Open Sans", Roboto, "San Francisco",
            Helvetica, Arial, sans-serif;
            color: #333;
            margin: 0;
            background-color: #fff;
        }
        html, body {
            height: 100%;
        }

        header, #main, footer {
            display: -ms-flexbox;
            display: flex;
        }

        header,
        footer {
            -ms-flex-negative: 0;
            flex-shrink: 0;
        }
        /* ========================================================
                                HEADER CSS
        =========================================================== */
        header{
            background-color: #333;
            text-align: center;
            position: relative;
        }

        header > .container {
            overflow: visible;
        }
        header li {
            position: relative;
        }
        header nav {
            cursor: default;
        }

        header li a,
        header li a:link,
        header li a:active{
            font-size:14px;
            margin-right:5px;
            color:#ccc !important;
            padding: 0 8px;
        }

        header li a:hover {
            background-color: transparent;
            text-decoration: underline;
        }

        header nav a,
        header nav a:link,
        header nav a:active {
            padding: 0 8px;
            text-transform: uppercase;
            font-size: 14px;
            color: #ccc !important;
        }
        header nav a:hover {
            background-color: transparent;
            text-decoration: underline;
        }

        @media screen and (min-width: 481px) {
            header li {
                padding-bottom: 12px;
            }
            header li.active::after {
                top: 100%;
                left: 50%;
                border: solid transparent;
                content: " ";
                height: 0;
                width: 0;
                position: absolute;
                pointer-events: none;
                border-top-color: #333;
                border-width: 14px;
                margin-left: -7px;
            }
            header li.active:first-child::after {
                margin-left: -14px;
            }
            header a,
            header a:link,
            header a:active {
                padding: 0 8px;
            }
        }
        @media screen and (max-width: 480px) {
            header li {
                width: 50%;
                float: left;
                padding: 0;
                margin: 0;
            }
            header nav {
                margin: 0 20px;
            }
            header a,
            header a:link,
            header a:active {
                padding: 0;
            }
        }

        .list-divider-pipe {
            margin: 0;
            padding: 0;
        }
        .list-divider-pipe li {
            display: inline-block;
        }
        .list-divider-pipe li + li::before {
            content: "|";
            padding: 0 0.3em 0 0.1em;
            color: #999;
        }
        @media screen and (max-width: 480px) {
            nav .list-divider-pipe {
                margin-bottom: 1rem;
                overflow: hidden;
            }
            nav .list-divider-pipe li + li::before {
                display: none;
            }
        }

        /* ========================================================
                            BODY ELEMENT CSS
        =========================================================== */

        #main {
            -ms-flex: 1 0 auto;
            flex: 1 0 auto;
            padding: 25px;
            flex-wrap: wrap;
            align-content: flex-start;
        }

        /* CREATING NEW LINE IN FLEX BOX */
        .break {
            flex-basis: 100%;
            height: 0;
        }


        a,
        a:link,
        a:active {
            color: #000000;
            text-decoration: none;
            border-radius: 2px;
        }

        a:hover {
            color: #fff;
            background-color: #000000;
        }

        a:hover code {
            background-color: transparent;
            color: #fff;
        }

        p a {
            padding-right: 2px;
            padding-left: 2px;
            margin-right: -2px;
            margin-left: -2px;
        }

        .container {
            width: 100%;
            max-width: 980px;
            margin: 0 auto;
            padding: auto;
            overflow: hidden;
        }

        /* ========================================================
                                FOOTER CSS
        =========================================================== */

        footer{
            padding:0 20px;
            position:absolute;
            bottom:0px;
        }

        footer{
            margin-top: 2em;
            background-color: #333;
            padding: 0;
            padding: 0 8px;
            padding-top:8px;
            text-transform: uppercase;
            font-size: 14px;
            text-align: center;
            position: relative;

        }

        footer .no-margin-top {
            margin-top: 0;
            margin-bottom: 0;
        }

        div{
            display: block;
        }

        footer a, footer a:link, footer a:active {
            color: #ccc;
        }


        /* ========================================================
                        TIMETABLE CSS
=========================================================== */
        table{
            font-family: arial, sans-serif;
            border-collapse: collapse;
            border: 1px solid black;
            /* margin: auto; */
            margin-top: 0px;
            /* margin-left: 40px; */
            font-size: 16px;
            height: 10px;
        }

        .selectSemester td{
            width: auto;
            border:none;
        }
        .selectSemester{
            border:none;
            border-bottom: solid;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            width: 40px;
            padding: 8px;
        }

        tr:nth-child(even) {
            /* background-color: #dddddd; */
            color: white;
            background-color: #333;
        }

        #timetable{
            width: 100%;
            margin-top: 20px;
        }
        #left{
            width: 70%;
            margin-right:auto;
            margin-left: auto;
        }

        .hide{
            display:none;
        }

        /* ========================================================
                                NEWS CSS
        =========================================================== */

        #newcontent{
            height: 300px;
            width: 310px;
            background-color: #333;
            margin-left: auto;
            margin-right: auto;
            /* margin-top: 30px;
            margin-right: 30px; */
            vertical-align: top;
            overflow: auto;
        }

        .news {
            margin: 0;
            width: 100%;
            color: white;
        }
        .news td{
            background-color: #333;
            color: white;
        }



    </style>
</head>
<body onload="onloadSem()">
<header>
    <div class="container">
        <nav>
            <ul class="list-divider-pipe">
                <li class="active">
                    <a href="home">Home</a>
                </li>
                <li>
                    <a href="files">Files</a>
                </li>
                <li>
                    <a href="classroom">Classroom</a>
                </li>
                <li>
                    <a href="assignment">Assignment</a>
                </li>
                <li>
                    <a href="announcement">Announcement</a>
                </li>
                <li>
                    <a target="_blank" href="https://www.google.com">Profile</a>
                </li>
            </ul>
        </nav>
    </div>
    <li>
        <a href="logout">LOGOUT</a>
    </li>
</header>

<main id="main">

    <div id="left">
        <p>Welcome ${FIRSTNAME}!</p>
        <hr>

        <c:choose>
            <c:when test="${ROLE_MODEL.equals(\"STUDENT\")}">
            <table class="selectSemester hide">
                <tr>
                    <td> <label for="cars">Select Course & Sem:</label></td>
                    <td><select name="cars" id="cars">
                        <option value="volvo">Volvo</option>
                        <option value="saab">Saab</option>
                        <option value="mercedes">Mercedes</option>
                        <option value="audi">Audi</option>
                    </select> </td>
                </tr>
            </table>
            </c:when>


            <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">
                <table class="selectSemester">
                    <tr>
                        <td> <label for="cars">Select Course & Sem:</label></td>
                        <td><form name="f1" method="GET" action="#">
                            <select name="course">
                                <c:forEach items="${FACULTY_MATRIX_MODEL}" var="fmm">
                                    <option value="${fmm.COURSE_MSTR_SEQ}">${fmm.COURSE_NAME}</option>
                                </c:forEach>
                            </select>
                            <select name="sem" id="sem">
                                <c:forEach items="${FACULTY_MATRIX_MODEL}" var="fmm">
                                    <option value="${fmm.SEM}">${fmm.SEM}</option>
                                </c:forEach>
                            </select>
                            <input id="submit" type="submit" value="GO"/>
                        </form>
                    </tr>
                </table>
            </c:when>
        </c:choose>
        <script>
            function onloadSem(){
                const params = new URLSearchParams(window.location.search)
                var val = params.get('sem');
                if(val!=null){ document.getElementById("sem").value = val;}
            }


        </script>

        <table id = "timetable">
            <th>Time</th>
            <th>Mon</th>
            <th>Tue</th>
            <th>Wed</th>
            <th>Thu</th>
            <th>Fri</th>
            <th>Sat</th>
            <c:forEach items="${TIMETABLE_MODEL}" var="t">
                <tr>
                    <td>${t.TIME}</td>
                    <td>${t.MON}</td>
                    <td>${t.TUE}</td>
                    <td>${t.WED}</td>
                    <td>${t.THU}</td>
                    <td>${t.FRI}</td>
                    <td>${t.SAT}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div id="right">
        <div id="newcontent">
            <table class="news">
                <th>What's New ?</th>
                <c:forEach items="${NEWS_MODEL}" var="n">
                    <tr>
                        <td>
                            <a style="color: white;" href="${n.newsUrl}" target="_blank">${n.newsLabel}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</main>

<footer class="no-margin-top">
    <div class="container">
        <ul class="list-divider-pipe">
            <li>
                <a href="">Get Help</a>
            </li>
            <li>
                <a href="">About Us</a>
            </li>
        </ul>
    </div>

</footer>
</body>
</html>
