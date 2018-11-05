<%--
  Created by IntelliJ IDEA.
  User: grey1
  Date: 2017/9/27
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="servlets.*, com.mycompany.unswbook.*, java.util.*" %>
<html>
<head>
    <title>friendPage</title>

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
            top: 15px;
            right: 132px;
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
//                    document.getElementById("notification").style.color = "#FA1506";
                    document.getElementById("new_message").style.background = "#FA1506";

                }
            })
        }
    </script>
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



<%--profile--%>
    <%userBean user = (userBean) request.getAttribute("user");%>
    <div class="container" >
        <div class="row" >
            <div class="col-xs-4" style="padding-left: 60px">
                <img src="http://${ipaddress}:8080/unswbook${user.getPhoto()}"  width="300" height="300" alt="" class="img-circle">
            </div>

            <div class="col-md-8 col-xs-12 col-sm-6 col-lg-8" style="padding: 0px">
                <div class="container" style="border-bottom:3px solid grey;width: 100%;  ">
                    <h2 style="margin-left: 50px"><%=user.userName%></h2>
                </div>
                <hr>
                <div class="col-xs-8 col-md-offset-3" style="margin-top: 20px;" >
                    <ul class="container details">
                        <li><p><span class="glyphicon glyphicon-user one" style="width:50px;"></span><%=user.yourName%></p></li>
                        <li><p><span class="fa fa-venus-mars" style="width:50px;"></span><%=user.gender%></p></li>
                        <li><p><span class="glyphicon glyphicon-calendar one" style="width:50px;"></span><%=user.DoB%></p></li>
                        <li><p><span class="glyphicon glyphicon-envelope one" style="width:50px;"></span><%=user.email%></p></li>
                    </ul>
                </div>
            </div>
        </div>
        <br>

    <%--her_post--%>
    <%ArrayList<postBean> post_list = (ArrayList<postBean>) request.getAttribute("post_list"); %>
    <%for (postBean post : post_list) {%>
    <div class="container" style="margin-bottom: 25px">
        <div class="panel panel-primary col-xs-10 col-md-offset-1" style="padding: 0px;">
            <div class="panel-heading" style="background-color:#616365">
                <h3 class="panel-title">
                    <span class="fa fa-calendar col-md-offset-9">&nbsp;<%=post.time%></span>
                </h3>
            </div>

            <div class="panel-body" style="background-color: #ffffff;padding: 30px">
                <p><%=post.content%></p>
                <%if (!post.photo.equals("")) {%>
                <p><img src="http://${ipaddress}:8080/unswbook/<%=post.photo%>"  width="300" height="300" alt=""></p>
                <%}%>
            </div>
            <div class="panel-footer" style="padding-bottom: 3px;padding-left: 30px">


                <p><span id="heart"><i class="fa fa-heart-o fa-lg " style="color: red"></i>&nbsp;&nbsp;</span><%for (String like_name : post.likesYourNameList) { out.println(like_name + "      "); };%> </p>
            </div>
         </div>
    </div>
    <%}%>

</body>
</html>
