<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: vishwajit_gaikwad
  Date: 14/5/21
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Classroom | V - Class</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
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
        /*background-color: #000000;*/
    }

    .modal a:hover {
        color: #333333;
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

    /*.container a:hover code {*/
        /*background-color: transparent;*/
        /*color: #fff;*/
    /*}*/
    /*.container {*/
        /*width: 100%;*/
        /*max-width: 980px;*/
        /*margin: 0 auto;*/
        /*padding: auto;*/
        /*overflow: hidden;*/
    /*}*/

    .panel-title a:hover{
        color: black;
        font-size: 15px;
    }

    .panel-title{
        font-size: 14px;
    }

    .roomList, .roomList td, .roomList th {
        border: 1px solid black;
        padding: 8px;
    }

    .roomList {
        width: 100%;
        border-collapse: collapse;
    }

    td{
        padding: 4px;
    }

    input{
        width: 100%;
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

<body onload="onChangeUpdateMeeting(); onChangeDeleteMeeting() ">
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
                <li class="active">
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

<main id="main" style="display:block;">


    <div class="container">
        <h2>Classrooms</h2>
        <hr>


        <c:choose>
            <c:when test="${ROLE_MODEL.equals(\"STUDENT\")}">
                <div class="panel-group">
                    <div class="panel panel-default">


                            <%--FOR EACH SEM--%>
                        <c:forEach items="${SEM_VO_LIST_MODEL}" var="semList">
                            <div class="panel-heading" style="background-color: #333">
                                <h4 class="panel-title">
                                    <a style="color: white" data-toggle="collapse" href="#collapse${semList.sem}">SEM ${semList.sem}</a>
                                </h4>
                            </div>


                            <div id="collapse${semList.sem}" class="panel-collapse collapse">
                                    <%--FOR EACH SUBJECT--%>
                                <c:forEach var="subjectList" items="${semList.roomsVOList}">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse-subject${subjectList.subjectMstrSeq}">${subjectList.subject}</a>
                                        </h4>
                                    </div>
                                    <div id="collapse-subject${subjectList.subjectMstrSeq}" class="panel-collapse collapse">
                                        <ul class="list-group">
                                            <li class="list-group-item">
                                                <table class="roomList">
                                                    <tr>
                                                        <td>Classname</td>
                                                        <td>Date & Time (DD-MM-YYY HH.TT)</td>
                                                        <td>Classroom Link</td>
                                                        <td>Password</td>
                                                        <td>Lecturer</td>
                                                    </tr>
                                                    <c:forEach var="roomsList" items="${subjectList.rooms}">
                                                        <tr>
                                                            <td>${roomsList.className}</td>
                                                            <td>${roomsList.date}</td>
                                                            <td><a style="color: black" href="${roomsList.url}" target="_blank">${roomsList.url}</a></td>
                                                            <td>${roomsList.password}</td>
                                                            <td>Prof. ${roomsList.hostName}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </li>
                                        </ul>
                                    </div>
                                </c:forEach>
                                <div class="panel-footer" style="background-color: black;"></div>
                            </div>
                            <div class="panel-group" style="background-color:transparent;"></div>
                        </c:forEach>
                    </div>

                </div>
            </c:when>
            <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">
                <table class="roomList">
                    <th>COURSE</th>
                    <th>SEM</th>
                    <th>SUBJECT</th>
                    <th>CLASSNAME</th>
                    <th>DATE & TIME (DD-MM-YYYY HH.TT)</th>
                    <th>CLASSROOM LINK</th>
                    <th>PASSWORD</th>
                    <c:forEach items="${SEM_VO_LIST_MODEL}" var="semList">
                        <tr>
                            <td>${semList.courseName}</td>
                            <td>${semList.sem}</td>
                            <td>${semList.subject}</td>
                            <td>${semList.className}</td>
                            <td>${semList.date} ${semList.time}</td>
                            <td><a style="color: black" href="${semList.url}" target="_blank">${semList.url}</a></td>
                            <td>${semList.password}</td>
                        </tr>
                    </c:forEach>
                </table>

            </c:when>
        </c:choose>
        <hr>




        <c:choose>
            <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">
                <!-- Trigger the modal with a button -->
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Manage Classrooms</button>

                <!-- Modal -->
                <div id="myModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Manage Meetings</h4>
                            </div>
                            <div class="modal-body">
                                <%--TABBED PANE GOES HERE--%>
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a class="modal" data-toggle="tab" href="#createPanel">Create Meeting</a></li>
                                        <li><a class="modal" data-toggle="tab" href="#updatePanel">Update Meeting</a></li>
                                        <li><a class="modal" data-toggle="tab" href="#deletePanel">Delete Meeting</a></li>
                                    </ul>

                                    <div class="tab-content">
                                        <%--TABBED PANE BODY--%>
                                        <%--CREATE MEETING PANEl BODY--%>
                                            <div id="createPanel" class="tab-pane fade in active" style="padding: 8px;">
                                                <form:form action="/zoom/refreshtoken?action=createMeeting" modelAttribute="zoomCreateVO" >
                                                    <table style="width: 100%;">
                                                        <tr>
                                                            <td>
                                                                    <%--SELECT COURSE--%>
                                                                    <form:select name="course" id="course" path="course">
                                                                    <c:forEach items="${COURSE_MATRIX_MODEL}" var="cmm">
                                                                        <option value="${cmm.COURSE_MSTR_SEQ}">${cmm.COURSE_NAME}</option>
                                                                    </c:forEach>
                                                                </form:select>
                                                                    <%--SELECT SEM--%>
                                                                <form:select name="sem" id="sem" path="sem">
                                                                    <c:forEach items="${FACULTY_MATRIX_MODEL}" var="fmm">
                                                                        <option value="${fmm.SEM}">SEM ${fmm.SEM}</option>
                                                                    </c:forEach>
                                                                </form:select>
                                                                    <%--SELECT SUBJECT--%>
                                                                <form:select name="subject" id="subject" path="subject">
                                                                    <c:forEach items="${FACULTY_MATRIX_MODEL}" var="fmm">
                                                                        <option value="${fmm.SUBJECT_MSTR_SEQ}">${fmm.SUBJECT_NAME}</option>
                                                                    </c:forEach>
                                                                </form:select>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><form:input type="text" name="createMeetingName" placeholder="Enter Meeting Name" path="topic" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <form:input style="margin-bottom: 8px;" type="date" id="createMeetingDate" name="createMeetingDate" path="date"/>
                                                                <form:input type="time" id="createMeetingTime" name="createMeetingTime" path="time"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center; ">
                                                                <input style="background-color:#0AC4F6; border: black 1px solid; color:white" class="btn btn-default" type="submit" value="Create Meeting">
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form:form>
                                            </div>


                                                <%--UPDATE MEETING PANEl BODY--%>
                                            <div id="updatePanel" class="tab-pane fade" style="padding: 8px;">
                                                <%-- <form:form action="/zoom/refreshtoken?action=createMeeting" modelAttribute="zoomCreateVO" >--%>
                                                <form:form action="/zoom/refreshtoken?action=updateMeeting" modelAttribute="zoomCreateVO" >
                                                    <table style="width: 100%;">
                                                        <tr>
                                                            <td>
                                                                    <%--<form:select name="course" id="course" path="course">--%>
                                                                <form:select path="meetingId" name="updateMeeting" id="updateMeeting" onchange="onChangeUpdateMeeting()">
                                                                <c:forEach items="${SEM_VO_LIST_MODEL}" var = "updateList">
                                                                        <option value="${updateList.meetingId}">${updateList.className}</option>
                                                                </c:forEach>
                                                                </form:select>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <%--<form:input type="text" name="createMeetingName" placeholder="Enter Meeting Name" path="topic" />--%>
                                                                <form:input path="topic" type="text" id="updateMeetingName" name="updateMeetingName" placeholder="Enter New Meeting Name" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <form:input path="date" style="margin-bottom: 8px;" type="date" id="updateMeetingDate" name="updateMeetingDate" />
                                                                <form:input path="time" type="time" id="updateMeetingTime" name="updateMeetingTime" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center;">
                                                                <input style="background-color:#0AC4F6; border: black 1px solid; color:white" class="btn btn-default" type="submit" value="Update Meeting">
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form:form>
                                            </div>

                                        <%--DELETE MEETING PANEL BODY--%>
                                            <div id="deletePanel" class="tab-pane fade">
                                                <%--<form:form action="/zoom/refreshtoken?action=createMeeting" modelAttribute="zoomCreateVO" >--%>
                                                <form:form action="/zoom/refreshtoken?action=deleteMeeting" modelAttribute="zoomCreateVO" >
                                                    <table style="width: 100%;">
                                                        <tr>
                                                            <td>
                                                                <form:select name="deleteMeeting" id="deleteMeeting" path="meetingId" onchange="onChangeDeleteMeeting()" style="width: 100%; margin-bottom: 10px">
                                                                    <c:forEach items="${SEM_VO_LIST_MODEL}" var = "updateList">
                                                                        <option value="${updateList.meetingId}">${updateList.className} - ${updateList.date} - ${updateList.time}</option>
                                                                    </c:forEach>
                                                                </form:select>
                                                            </td>
                                                        </tr>
                                                        <%--<tr>
                                                            <td>
                                                                <input type="text" id="deleteMeetingName" name="deleteMeetingName" readonly="true">
                                                            </td>
                                                        </tr>--%>
                                                       <%-- <tr>
                                                            <td>
                                                                <input style="margin-bottom: 8px;" type="date" id="deleteMeetingDate" name="deleteMeetingDate" readonly="true">
                                                                <input type="time" id="deleteMeetingTime" name="deleteMeetingTime">
                                                            </td>
                                                        </tr>--%>
                                                        <tr>
                                                            <td style="text-align: center;">
                                                                <input style="background-color:#F6310A; border: black 1px solid; color:white" class="btn btn-default" type="submit" value="Delete Meeting">
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </form:form>
                                            </div>
                                    </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>

                    </div>
                </div>
            </c:when>
        </c:choose>
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


<script>
    function onChangeDeleteMeeting(){
        var e = document.getElementById("deleteMeeting");
        var result = e.options[e.selectedIndex].text;
        document.getElementById("deleteMeetingName").value=result;
    }

    function onChangeUpdateMeeting(){
        var e = document.getElementById("updateMeeting");
        var result = e.options[e.selectedIndex].text;
        document.getElementById("updateMeetingName").value=result;
    }
</script>
</body>
</html>
