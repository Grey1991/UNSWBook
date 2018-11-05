<%-- 
    Document   : registration
    Created on : 25/09/2017, 9:07:43 PM
    Author     : yiqunrong
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.jsp.*" %>
<%@ page import="com.mycompany.*" %>
<%@ page import="servlets.*, com.mycompany.unswbook.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>modification Page</title>


        <style type="text/css">
            <!-- CSS styles for Topnavigation -->
            body {
                margin: 0;
                padding: 0;
                background: #ccc;
            }
            #new_message{

                display:block;
                background:inherit;
                border-radius:50%;
                width:6px;
                height:6px;
                top:-3px;
                right:-6px;
                position:absolute;
            }
            .nav ul {
                list-style: none;
                background-color: #444;
                text-align: center;
                padding: 0;
                margin: 0;
            }
            .nav li {
                font-family: 'Oswald', sans-serif;
                font-size: 1.2em;
                line-height: 40px;
                height: 40px;
                border-bottom: 1px solid #888;
            }
            .nav a {
                text-decoration: none;
                color: #fff;
                display: block;
                transition: .3s background-color;
            }
            .nav a:hover {
                background-color: #005f5f;
            }
            .nav a.active {
                background-color: #fff;
                color: #444;
                cursor: default;
            }
            @media screen and (min-width: 600px) {
                .nav li {
                    width: 120px;
                    border-bottom: none;
                    height: 50px;
                    line-height: 50px;
                    font-size: 1.4em;
                }

                /* Option 1 - Display Inline */
                .nav li {
                    display: inline-block;
                    margin-right: -4px;
                }

            }

            <!-- CSS styles for mytables -->
            @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100);

            body {
                background-color: #3e94ec;
                font-family: "Roboto", helvetica, arial, sans-serif;
                font-size: 16px;
                font-weight: 400;
                text-rendering: optimizeLegibility;
            }

            div.table-title {
                display: block;
                margin: auto;
                max-width: 600px;
                padding:5px;
                width: 100%;
            }

            .table-title h3 {
                color: #fafafa;
                font-size: 30px;
                font-weight: 400;
                font-style:normal;
                font-family: "Roboto", helvetica, arial, sans-serif;
                text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
                text-transform:uppercase;
            }


            /*** Table Styles **/

            .table-fill {
                background: white;
                border-radius:3px;
                border-collapse: collapse;
                height: 320px;
                margin: auto;
                padding:5px;
                width: 100%;
                box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
                animation: float 5s infinite;
            }

            th {
                color:#D5DDE5;;
                background:#1b1e24;
                border-bottom:4px solid #9ea7af;
                border-right: 1px solid #343a45;
                font-size:20px;
                font-weight: 100;
                padding:10px;
                text-align:left;
                text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
                vertical-align:middle;
            }

            th:first-child {
                border-top-left-radius:3px;
            }

            th:last-child {
                border-top-right-radius:3px;
                border-right:none;
            }

            tr {
                border-top: 1px solid #C1C3D1;
                border-bottom-: 1px solid #C1C3D1;
                color:#666B85;
                font-size:8px;
                font-weight:normal;
                text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
            }

            tr:hover td {
                background:#4E5066;
                color:#FFFFFF;
                border-top: 1px solid #22262e;
                border-bottom: 1px solid #22262e;
            }

            tr:first-child {
                border-top:none;
            }

            tr:last-child {
                border-bottom:none;
            }

            tr:nth-child(odd) td {
                background:#EBEBEB;
            }

            tr:nth-child(odd):hover td {
                background:#4E5066;
            }

            tr:last-child td:first-child {
                border-bottom-left-radius:3px;
            }

            tr:last-child td:last-child {
                border-bottom-right-radius:3px;
            }

            td {
                background:#FFFFFF;
                padding:3px;
                text-align:left;
                vertical-align:middle;
                font-weight:300;
                font-size:14px;
                text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
                border-right: 1px solid #C1C3D1;
            }

            td:last-child {
                border-right: 0px;
            }

            th.text-left {
                text-align: left;
            }

            th.text-center {
                text-align: center;
            }

            th.text-right {
                text-align: right;
            }

            td.text-left {
                text-align: left;
            }

            td.text-center {
                text-align: center;
            }

            td.text-right {
                text-align: right;
            }

        </style>
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>

        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script>
            $(document).ready(function () {
                setInterval("get_notification()",1000);
            });
            function get_notification() {
                var flag = $("notification").val();
                $.get('checkNotification_servlet', {
                    flag: flag
                }, function (responseText) {
                    if(responseText == "new notification"){
//                        document.getElementById("notification").style.color = "#FA1506";
                        document.getElementById("new_message").style.background = "#FA1506";

                    }
                })
            }


            function modify_detail_comfirm()
            {
                var r=confirm("Modify your detail?");
                if (r==true)
                {
                    return true
                }
                else
                {
                    return false
                }
            }


        </script>

        <style>
            .form_hover {
                padding: 0px;
                position: relative;
                overflow: hidden;
                height: 390px;
            }
            #new_message{

                display:block;
                background:inherit;
                border-radius:50%;
                width:6px;
                height:6px;
                top: 15px;
                right: 132px;
                position:absolute;
            }
            .form_hover:hover .header {
                opacity: 1;
                transform: translateY(-250px);
                -webkit-transform: translateY(-250px);
                -moz-transform: translateY(-250px);
                -ms-transform: translateY(-250px);
                -o-transform: translateY(-250px);
            }

            .form_hover img {
                z-index: 4;
            }

            .form_hover .header {
                position: absolute;
                top: 250px;
                -webkit-transition: all 0.3s ease;
                -moz-transition: all 0.3s ease;
                -o-transition: all 0.3s ease;
                -ms-transition: all 0.3s ease;
                transition: all 0.3s ease;
                width: 100%;
            }

            .form_hover .blur {
                height: 300px;
                z-index: 5;
                position: absolute;
                width: 100%;
            }

            .form_hover .header-text {
                z-index: 10;
                color: #fff;
                position: absolute;
                height: 300px;
                text-align: center;
                top: -20px;
                width: 100%;
            }
        </style>
    </head>
    <body>

    <%--nav--%>
    <nav class="navbar navbar-default navbar-inverse"  role="navigation">
        <div class="container-fluid">
            <div class="navbar-header " style="margin-left: 5%">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="myPage_servlet"><i class="fa fa-home fa-fw" style="font-size:20px"></i>&nbsp;Home</a></li>
                    <li ><a href="search.jsp" class="fa fa-search fa-fw" style="font-size:20px" >&nbsp;Search</a></li>
                    <li ><a href="friendsList_servlet">MyFriends</a></li>
                    <li ><a href="moment_servlet">Moments</a></li>
                    <li ><a href="visualisation.jsp">Visualisation</a></li>
                    <%--<li ><a id="notification" href="notification_servlet"><span class="glyphicon glyphicon-envelope"><i id="new_message"></i></span></a></li>--%>
                <%--<li ><a id="notification" class="glyphicon glyphicon-envelope" href="notification_servlet"></a></li>--%>
                    <%--<li ><a href="logout_servlet"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Logout</a></li>--%>
                </ul>
            </div>
            <span class="navbar-form navbar-right form-search form-horizontal navbar-header" style="margin-right: 60px;" role="search" >
            <div class="form-group">
            <button style="height: 34px; padding-top: 8px;" class="btn btn-default btn-lg">
                <a href="notification_servlet" class="fa fa-envelope " style="color: inherit;"><span ><i id="new_message"></i></span></a>
            </button>
            <button style="height: 34px; padding-top: 9px" class="btn btn-default" >
                <a href="logout_servlet" style="color: inherit;"><i class="fa fa-sign-out fa-lg "  aria-hidden="true"></i></a>
            </button>
            </div>
        </span>
            <form class="navbar-form navbar-right form-search form-horizontal" style="margin-right: 5px;" role="search" method="post" action="advancedSearch_servlet">
                <div class="form-group">
                    <input type="hidden" name="action" value="simple_search">
                    <input type="text" name="yourName" class="form-control" placeholder="Search by Name">
                    <button type="submit" style="height: 34px;" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                </div>
            </form>
        </div>
    </nav>



    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <div class="container" >
        <div class="row">
            <div class="col-lg-12">
                <div class="form_hover " style="background-color: #ffffff;margin-top: 20px;">
                    <p style="text-align: center; margin-top: 20px;">
                        <i >
                            <c:if test="${userInfo.getPhoto()!=''}">
                                <img style="width: 200px;height: 200px" src="${userInfo.getPhoto()}">
                            </c:if>
                        </i>
                    </p>
                    <div class="header">
                        <div class="blur"></div>
                        <div class="header-text">
                            <div class="panel panel-primary" style="height:410px;">
                                <div class="panel-heading">
                                    <h3 style="background-color:#428BCA; color:white;"><i class="fa fa-gears" style="font-size:50px"></i> </h3>
                                </div>
                                <div class="panel-body">
                                    <form action="modification_servlet" onsubmit=" return modify_detail_comfirm()" method="post" enctype="multipart/form-data">

                                        <div class="form-group">
                                            <div class="input-group" style="width: 100%">
                                                <span class="input-group-addon" style="width: 52px;height: 45px"><i class="fa fa-user fa-lg" aria-hidden="true"></i></span>
                                                <input type="text" name="yourName" value="${userInfo.getYourName()}" class="form-control input-lg">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group" style="width: 100%">
                                                <span class="input-group-addon " style="width: 52px;height: 45px"><i class="fa fa-calendar fa-lg" aria-hidden="true"></i><span class="text-danger"> </span></span>
                                                <input type="date" name="DoB" value="${userInfo.getDoB()}"  class="form-control input-lg">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group" style="width: 100%">
                                                <span class="input-group-addon " style="width: 52px;height: 45px"><i class="fa fa-venus-mars fa-lg" aria-hidden="true"></i><span class="text-danger"> </span></span>

                                                <select name="gender" class="form-control input-lg">
                                                    <option value="male" ${userInfo.gender == "male" ? 'selected' : ''} >male</option>
                                                    <option value="female" ${userInfo.gender == "female" ? 'selected' : ''} >female</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-1 col-sm-offset-5">
                                                <span class="input-group" style="width: 100%">
                                                <label class="btn-bs-file btn " style="color: #555;background-color: #eeeeee;border-color: #eeeeee;">
                                                    <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>
                                                </label>
                                                </span>
                                            </div>
                                            <div class="col-sm-2 col-sm-offset-0 " style="margin-top: 10px;margin-bottom: 20px">
                                                <input type="file" name="file">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <button class="btn btn-primary btn-lg btn-block" ><i class="fa fa-arrows-v">&nbsp;&nbsp;&nbsp;Modify&nbsp;&nbsp;&nbsp;</i><i class="fa fa-arrows-v"></i></button>
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
    </body>
</html>
