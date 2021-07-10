<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vishwajit_gaikwad
  Date: 14/5/21
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assignment | V - Class</title>
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

        .panel-title a:hover{
            color: black;
            background: transparent;
            font-size: 15px;
        }

        .panel-title{
            font-size: 14px;
        }
        .assignment table{
            width: 100%;
            border: 0px solid black;
            padding: 0px;
        }

        .exact table, .exact td, .exact th {
            border: 1px solid black;
            padding: 8px;
        }

        .whitetb table, .whitetb td, .whitetb th {
            border: 1px solid whitesmoke;
            padding: 8px;
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
<body onload="showSnackBar()">
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
                <li class="active">
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

    <div class="container">
    <c:choose>
        <c:when test="${ROLE_MODEL.equals(\"STUDENT\")}">
        <div class="panel-group">
            <div class="panel panel-default">

                <%--FOR EACH SUBJECT--%>
                <c:forEach items="${STUDENT_ASSIGNMENT_MODEL}" var="subjectList">
                <div class="panel-heading" style="background-color: #333">
                    <h4 class="panel-title">
                        <a style="color: white" data-toggle="collapse" href="#collapseSubject${subjectList.subjectMstrSeq}">${subjectList.subject}</a>
                    </h4>
                </div>
                    <div id="collapseSubject${subjectList.subjectMstrSeq}" class="panel-collapse collapse">
                        <ul class="list-group">

                            <c:forEach items="${subjectList.assignmentList}" var="assignmentList">
                                <li class="list-group-item">
                                    <div class="panel-heading" style="padding: 0px">
                                        <h4 class="panel-title">
                                            <table class="exact" style="border: 0px; width: 100%">
                                                <tr>
                                                    <td style="width: 75%">
                                                        <a style="font-size: 15px;" data-toggle="collapse" href="#collapseAssignment${assignmentList.assignmentMstrSeq}">${assignmentList.assignmentName} | Max Marks : ${assignmentList.marks}</a>
                                                    </td>
                                                    <td style="text-align: right;">
                                                        <a target="_blank" style="font-size: 14px" href="${assignmentList.path}">View Assignment</a>
                                                    </td>
                                                    <td>
                                                        <button id="${subjectList.subjectMstrSeq}|${assignmentList.assignmentMstrSeq}" onclick="reply_click(this.id)" type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">Upload</button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </h4>
                                    </div>

                                    <div id="collapseAssignment${assignmentList.assignmentMstrSeq}" class="panel-collapse collapse">
                                        <hr>
                                        <table class="exact" style="width: 100%">
                                            <th>Status</th>
                                            <th>Marks</th>
                                            <th>Submission Date(YYYY-MM-DD)</th>
                                            <th>File</th>

                                            <c:forEach items="${assignmentList.submittedFilesList}" var="submissionList">
                                                <tr>
                                                    <td>${submissionList.status}</td>
                                                    <td>${submissionList.marks}</td>
                                                    <td>${submissionList.date}</td>
                                                    <td><a href="${submissionList.filePath}">View</a></td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>

                                </li>
                            </c:forEach>
                        </ul>
                        <div class="panel-footer" style="background-color: black;"></div>
                    </div>
                    <div class="panel-footer" style="background-color: white;"></div>
                </c:forEach>
            </div>
        </div>
            <script>
                function reply_click(clicked_id)
                {
                    var str = clicked_id;
                    const myArr = str.split("|");
                    console.log(myArr);
                    var subjectMstrSeq = myArr[0];
                    var assignmentMstrSeq = myArr[1];
                    document.getElementById("subject").value=subjectMstrSeq;
                    document.getElementById("assignmentSeq").value = assignmentMstrSeq;
                }
            </script>

            <%--STUDENT MODAL BOX--%>
            <!-- Modal -->
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Upload Assignment</h4>
                        </div>
                        <div class="modal-body">

                            <form method="post" enctype="multipart/form-data" action="fileupload?action=assignment">
                                <table style="width: 100%;border: 0px">
                                    <tr>
                                        <td style="border: 0px">
                                            <input style="width: 100%; display: none;" type="text" id="subject" name="subject" readonly="true">
                                            <input style="width: 100%; display: none;" type="text" id="assignmentSeq" name="assignment" readonly="true">
                                        </td>
                                    </tr>
                                </table>

                                <table style="border: 0px; width: 100%;">
                                    <tr>
                                        <td style="border: 0px; width: 100%"><input type="file" name="file"></td>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <td style="text-align: center;border: 0px"><input value="Upload" class="btn btn-default" type="submit"></td>
                                    </tr>
                                </table>
                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

        </c:when>
        <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">
            <div class="panel-group">
                <div class="panel panel-default">
                        <%--FOR EACH COURSE--%>
                    <c:forEach items="${FACULTY_ASSIGNMENT_MODEL}" var="courseList">
                        <div class="panel-heading" style="background-color: #333">
                            <h4 class="panel-title">
                                <a style="color: white" data-toggle="collapse" href="#collapseCourse${courseList.courseMstrSeq}">${courseList.courseName}</a>
                            </h4>
                        </div>

                        <div id="collapseCourse${courseList.courseMstrSeq}" class="panel-collapse collapse">
                                <%--FOR EACH SEM--%>
                            <c:forEach items="${courseList.semVOList}" var="semList">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" href="#collapseSem${semList.sem}">SEM ${semList.sem}</a>
                                    </h4>
                                </div>

                                <div id="collapseSem${semList.sem}" class="panel-collapse collapse">
                                    <ul class="list-group">
                                            <%--FOR EACH SUBJECT--%>
                                        <c:forEach items="${semList.subjectList}" var="subjectList">
                                            <li class="list-group-item">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title assignment">
                                                        <table class="exact">
                                                            <tr>
                                                                <td style="width: 90%">
                                                                    <a style="font-size: 15px;" data-toggle="collapse" href="#collapseSubject${subjectList.subjectMstrSeq}">${subjectList.subject}</a>
                                                                </td>
                                                                <td style="text-align: right">
                                                                    <button id="${courseList.courseName}|${courseList.courseMstrSeq}|${semList.sem}|${subjectList.subject}|${subjectList.subjectMstrSeq}" onclick="reply_click_faculty(this.id)" type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">Upload</button>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                    </h4>
                                                </div>

                                                <div id="collapseSubject${subjectList.subjectMstrSeq}" class="panel-collapse collapse">
                                                    <ul class="list-group">

                                                        <c:forEach items="${subjectList.assignmentList}" var="assignmentList">
                                                            <li class="list-group-item">
                                                                <div class="panel-heading" style="padding: 0px">
                                                                    <h4 class="panel-title">
                                                                        <table class="exact" style="border: 0px solid black; width: 100%">
                                                                            <tr>
                                                                                <td style="width: 75%">
                                                                                    <a style="font-size: 15px;" data-toggle="collapse" href="#collapseAssignment${assignmentList.assignmentMstrSeq}">${assignmentList.assignmentName} | Max Marks : ${assignmentList.marks}</a>
                                                                                </td>
                                                                                <td style="text-align: right;">
                                                                                    <a target="_blank" style="font-size: 14px" href="${assignmentList.path}">View Assignment</a>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </h4>
                                                                </div>

                                                                <div id="collapseAssignment${assignmentList.assignmentMstrSeq}" class="panel-collapse collapse">
                                                                    <hr>
                                                                    <table class="exact" style="width: 100%">
                                                                        <th>Roll No</th>
                                                                        <th>Status</th>
                                                                        <th>Marks</th>
                                                                        <th>Submission Date(YYYY-MM-DD)</th>
                                                                        <th>File</th>

                                                                        <c:forEach items="${assignmentList.submittedFilesList}" var="submissionList">
                                                                            <tr>
                                                                                <td><input type="checkbox" value="${submissionList.userMstrSeq}"> ${submissionList.userMstrSeq}</td>
                                                                                <td>${submissionList.status}</td>
                                                                                <td>${submissionList.marks}</td>
                                                                                <td>${submissionList.date}</td>
                                                                                <td><a href="${submissionList.filePath}">View</a></td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </table>
                                                                </div>

                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <%--<div class="panel-footer" style="background-color: black;"></div>--%>
                                </div>
                            </c:forEach>
                                    <div class="panel-footer" style="background-color: black;"></div>
                        </div>
                        <%--<div class="panel-footer" style="background-color: white;"></div>--%>
                    </c:forEach>
                </div>
            </div>

            <script>
                function reply_click_faculty(clicked_id)
                {
                    var str = clicked_id;
                    const myArr = str.split("|");
                    console.log(myArr);
                    var courseName = myArr[0];
                    var courseSeq = myArr[1]
                    var sem = myArr[2];
                    var subjectName = myArr[3];
                    var subjectSeq = myArr[4]
                    document.getElementById("facultyCourseSeq").value = courseSeq;
                    document.getElementById("facultyCourseName").value = courseName;
                    document.getElementById("facultySemSeq").value = sem;
                    document.getElementById("facultySubjectSeq").value = subjectSeq;
                    document.getElementById("facultySubject").value = subjectName;
                }
            </script>

            <%--FACULTY MODAL BOX--%>
            <!-- Modal -->
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Upload Assignment</h4>
                        </div>
                        <div class="modal-body">

                            <form method="post" enctype="multipart/form-data" action="fileupload?action=assignment">
                                <table class="whitetb">
                                    <tr>
                                        <td>
                                            <label for="facultyCourseSeq" >Course:</label>
                                            <input type="text" id="facultyCourseName"readonly="true">
                                            <input style="display: none" type="text" id="facultyCourseSeq" name="course" readonly="true">
                                        </td>
                                        <td>
                                            <label for="facultySemSeq" >Sem:</label>
                                            <input type="text" id="facultySemSeq" name="sem" readonly="true">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <label for="facultySubject" >Subject:</label>
                                            <input style="width: 100%" type="text" id="facultySubject" name="subjectName" readonly="true">
                                            <input style="display: none" type="text" id="facultySubjectSeq" name="subject" readonly="true">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <label for="facultyAssignmentName" >Assignment Name:</label>
                                            <input style="width: 100%;" type="text" id="facultyAssignmentName" name="assignmentName">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <label for="facultyMarks" >Marks:</label>
                                            <input style="width:100%;" type="text" id="facultyMarks" name="marks">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"  style="width: 100%;">
                                            <input type="file" name="file">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="text-align: center;"><input value="Upload" class="btn btn-default" type="submit"></td>
                                    </tr>
                                </table>
                            </form>

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
