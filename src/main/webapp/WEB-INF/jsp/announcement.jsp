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
    <title>Announcement | V - Class</title>
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
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            /* background-color: #dddddd; */
            color: white;
            background-color: #333;
        }

        #timetable{
            width: 70%;
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
        }


    </style>

</head>
<body>
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
            </ul>
        </nav>
    </div>
</header>

<main id="main">
    <p>Hey man this is announcment page!</p>
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
