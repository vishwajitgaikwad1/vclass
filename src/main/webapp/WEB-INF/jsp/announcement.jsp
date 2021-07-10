<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vishwajit_gaikwad
  Date: 14/5/21
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Announcement | V - Class</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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

        .annList a:hover {
            background-color: transparent;
            color: #000;
        }

        .annList, .annList td, .annList th {
            border: 1px solid black;
            padding: 8px;
        }

        .annList {
            width: 100%;
            border-collapse: collapse;
        }

        .selectCourse td{
            width: auto;
            border:none;
        }
        .selectCourse{
            border:none;
            border-bottom: solid;
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

        .model{
            width: 100%;
        }
        .model td{
            padding: 4px;
        }

        .model input{
            width: 100%;
        }

        /* The snackbar - position it at the bottom and in the middle of the screen */
        #snackbar {
            visibility: hidden; /* Hidden by default. Visible on click */
            min-width: 250px; /* Set a default minimum width */
            margin-left: -125px; /* Divide value of min-width by 2 */
            background-color: #333; /* Black background color */
            color: #fff; /* White text color */
            text-align: center; /* Centered text */
            border-radius: 2px; /* Rounded borders */
            padding: 16px; /* Padding */
            position: fixed; /* Sit on top of the screen */
            z-index: 1; /* Add a z-index if needed */
            left: 50%; /* Center the snackbar */
            bottom: 30px; /* 30px from the bottom */
        }

        /* Show the snackbar when clicking on a button (class added with JavaScript) */
        #snackbar.show {
            visibility: visible; /* Show the snackbar */
            /* Add animation: Take 0.5 seconds to fade in and out the snackbar.
            However, delay the fade out process for 2.5 seconds */
            -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
            animation: fadein 0.5s, fadeout 0.5s 2.5s;
        }

        /* Animations to fade the snackbar in and out */
        @-webkit-keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }

        @keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }

        @-webkit-keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }

        @keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
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
    </style>

</head>
<body onload="onloadSem() , showSnackBar()">
<header>
    <div class="container">
        <nav>
            <ul class="list-divider-pipe">
                <li>
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
                <li class="active">
                    <a href="announcement">Announcement</a>
                </li>
               <%-- <li>
                    <a target="_blank" href="https://www.google.com">Profile</a>
                </li>--%>
            </ul>
        </nav>
    </div>
    <li>
        <a href="logout">LOGOUT</a>
    </li>
</header>

<main id="main">

<c:choose>
    <c:when test="${ROLE_MODEL.equals(\"STUDENT\")}">
        <table class="annList">
                <%--<th>COURSE</th>--%>
            <th>SEM</th>
            <th>TITLE</th>
            <th>FILE</th>
            <c:forEach items="${ANNOUNCEMENT_VO_LIST_MODEL}" var="annList">
                <tr>
                        <%--<td>${annList.courseMstrSeq}</td>--%>
                    <td>${annList.sem}</td>
                    <td>${annList.name}</td>
                    <td><a href="${annList.filePath}" target="_blank">${annList.fileName}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>

    <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">

        <table class="selectCourse">
            <tr>
                <td> <label for="course">Select Course:</label></td>
                <td><form name="f1" method="GET" action="#">
                    <select name="course" id="course">
                        <option value="0">ALL</option>
                        <c:forEach items="${COURSE_MATRIX_MODEL}" var="cmm">
                            <option value="${cmm.COURSE_MSTR_SEQ}">${cmm.COURSE_NAME}</option>
                        </c:forEach>
                    </select>
                    <input id="submit" type="submit" value="GO"/>
                </form>
            </tr>
        </table>

        <table style="margin-top: 10px" class="annList">
            <br>
            <th>COURSE</th>
            <th>SEM</th>
            <th>TITLE</th>
            <th>FILE</th>
            <c:forEach items="${ANNOUNCEMENT_VO_LIST_MODEL}" var="annList">
                <tr>
                    <td>${annList.COURSE_NAME}</td>
                    <td>${annList.SEM}</td>
                    <td>${annList.NAME}</td>
                    <td><a href="${annList.FILE_PATH}" target="_blank">${annList.FILE_NAME}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
    <script>
        function onloadSem(){
            const params = new URLSearchParams(window.location.search)
            var val = params.get('course');
            if(val!=null){ document.getElementById("course").value = val;}
        }
    </script>

    <c:choose>
        <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">
            <!-- Trigger the modal with a button -->
            <button style="margin-top: 10px" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Upload File</button>

            <!-- Modal -->
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add Announcement</h4>
                        </div>
                        <div class="modal-body">

                            <form method="post" enctype="multipart/form-data" action="fileupload?action=announcement">
                                <table style="width: 100%;">
                                    <tr>
                                        <td>
                                                <%--SELECT COURSE--%>
                                            <select name="course" id="course">
                                                <c:forEach items="${COURSE_MATRIX_MODEL}" var="cmm">
                                                    <option value="${cmm.COURSE_MSTR_SEQ}">${cmm.COURSE_NAME}</option>
                                                </c:forEach>
                                            </select>
                                                <%--SELECT SEM--%>
                                            <select name="sem" id="sem">
                                                <c:forEach items="${FACULTY_MATRIX_MODEL}" var="fmm">
                                                    <option value="${fmm.SEM}">SEM ${fmm.SEM}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                                <hr>

                                <table class="model">
                                    <tr>
                                        <td><input type="text" name="announcementName" placeholder="Enter Title"></td>
                                    </tr>
                                    <tr>
                                        <td><input type="file" name="file"></td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right;"><input value="Upload" class="btn btn-default" type="submit"></td>
                                    </tr>
                                </table>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <h4 style="font-size: 12px; float: left">Use only .pdf files</h4>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
        </c:when>
    </c:choose>

    <div id="snackbar" style="background-color: snow; color: black;">${MESSAGE.title} : ${MESSAGE.message}</div>
    <script>
        function showSnackBar() {
            // Get the snackbar DIV
            var x = document.getElementById("snackbar");
            var y = document.getElementById("snackbar").textContent;
            if(y.length>3){
                // Add the "show" class to DIV
                x.className = "show";

                // After 3 seconds, remove the show class from DIV
                setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
            }
        }
    </script>






























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
