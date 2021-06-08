<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Files | V - Class</title>
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

<body>
<header>
    <div class="container">
        <nav>
            <ul class="list-divider-pipe">
                <li>
                    <a href="home">Home</a>
                </li>
                <li class="active">
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

<main id="main" style="display:block;">


    <div class="container">
        <h2>Course Study Materials</h2>


        <c:choose>
            <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">
                <table>
                    <tr>
                        <td>Select Course: </td>
                        <td style="padding-left: 20px;">

                            <form name="f1" method="GET" action="#">
                                <select name="course" id="course">
                                    <c:forEach items="${COURSE_MATRIX_MODEL}" var="cmm">
                                        <option value="${cmm.COURSE_MSTR_SEQ}">${cmm.COURSE_NAME}</option>
                                    </c:forEach>
                                </select>

                                <input id="submit" type="submit" value="GO"/>
                            </form>

                        </td>
                    </tr>
                </table>
            </c:when>
        </c:choose>
        <hr>


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
                                <c:forEach var="subjectList" items="${semList.filesVO}">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse-subject${subjectList.subjectMstrSeq}">${subjectList.subject}</a>
                                        </h4>
                                    </div>
                                    <div id="collapse-subject${subjectList.subjectMstrSeq}" class="panel-collapse collapse">
                                        <ul class="list-group">
                                            <c:forEach var="filesList" items="${subjectList.files}">
                                                <li class="list-group-item">
                                                    <a style="color: black" target="_blank" data-toggle="collapse" href="file://${filesList.filePath}">${filesList.fileName}</a>
                                                </li>

                                            </c:forEach>
                                        </ul>
                                    </div>
                                </c:forEach>
                                <div class="panel-footer" style="background-color: black;"></div>
                        </div>
                        <div class="panel-group" style="background-color:transparent;"></div>
                </c:forEach>
            </div>

        </div>

        <c:choose>
            <c:when test="${ROLE_MODEL.equals(\"FACULTY\")}">
                <!-- Trigger the modal with a button -->
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Upload File</button>

                <!-- Modal -->
                <div id="myModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Upload File</h4>
                            </div>
                            <div class="modal-body">

                                <form method="post" enctype="multipart/form-data" action="fileupload">
                                    <table>
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
                                                <%--SELECT SUBJECT--%>
                                                    <select name="subject" id="subject">
                                                        <c:forEach items="${FACULTY_MATRIX_MODEL}" var="fmm">
                                                            <option value="${fmm.SUBJECT_MSTR_SEQ}">${fmm.SUBJECT_NAME}</option>
                                                        </c:forEach>
                                                    </select>
                                            </td>
                                        </tr>
                                    </table>
                                    <hr>

                                    <table>
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
