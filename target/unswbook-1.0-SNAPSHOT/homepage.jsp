<%-- 
    Document   : homepage
    Created on : 25/09/2017, 8:07:27 PM
    Author     : yiqunrong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.unswbook.*"%>
<!DOCTYPE html>
<!doctype html>
<html>
<head>

    <meta charset="UTF-8">
    <title>homepage</title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script>
        $(document).ready(function () {
            setInterval("get_notification()",1000);

            $("#post").click(function(){
                $("#show_hide").fadeToggle(500);

            });

            $("#motification").mouseover(function(){
                $("#motification").css("color","blue");
            });
            $("#motification").mouseout(function(){
                $("#motification").css("color","#22262e");
            });


            <%--$("#heart${post.content},#names").mouseover(function(){--%>
                <%--$("#heart${post.content},#names").css("color","red");--%>
            <%--});--%>
            <%--$("#heart${post.content},#names").mouseout(function(){--%>
                <%--$("#heart${post.content},#names").css("color","#22262e");--%>
            <%--});--%>


//            $("#deletMoment").mouseover(function(){
//                $("#deletMoment").css("color","red");
//            });
//            $("#deletMoment").mouseout(function(){
//                $("#deletMoment").css("color","#22262e");
//            });


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
        function redirect() {
            location.href = "modification_servlet";
        };

        function post_moment() {
            alert("Post your moment sucessfully!");
        }


        function delet_confirm()
        {
            var r=confirm("Delet your moment?");
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
        .form-control{
            font-size: 1.3em;
            color: #080808;
        }
        textarea.form-control {
            height: 135px;
            /* margin-top: px;*/
        }

        .submit{
            font-size: 1.1em;
            float: right;
            width: 150px;
            background-color: transparent;
            color: #fff;

        }

        .colorgraph {
            height: 5px;
            border-top: 0;
            background: #c4e17f;
            border-radius: 5px;
            background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
            background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
            background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
            background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
        }
    </style>
    <style type="text/css">
        <!-- CSS styles for Topnavigation -->
        body {
            margin: 0;
            padding: 0;
            background: #ccc;
        }
        body
        {
            font-family: 'Open Sans', sans-serif;
        }

        .fb-profile img.fb-image-lg{
            z-index: 0;
            width: 100%;
            margin-bottom: 10px;
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
        .fb-image-profile
        {
            margin: -90px 10px 20px 80px;
            z-index: 9;
            width: 20%;
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
                <%--<li ><a id="notification" class="glyphicon glyphicon-envelope" href="notification_servlet"></a></li>--%>
                <%--<li ><a href="notification_servlet"><span class="glyphicon glyphicon-envelope"><i id="new_message"></i></span></a></li>--%>
                <%--<li ><a href="logout_servlet"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Logout</a></li>--%>
            </ul>
        </div>
        <span class="navbar-form navbar-right form-search form-horizontal navbar-header" style="margin-right: 60px;" role="search" >
            <div class="form-group">
            <button style="height: 34px; padding-top: 5px;" class="btn btn-default btn-lg">
                <a href="notification_servlet" style="color: inherit;" class="fa fa-envelope "><span ><i id="new_message"></i></span></a>
            </button>
            <button style="height: 34px;" class="btn btn-default" >
                <a href="logout_servlet" style="color: inherit;"><i class="fa fa-sign-out fa-lg" aria-hidden="true"></i></a>
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
<div class="container-fluid">
    <div class="row">
        <div class="fb-profile" style="    margin-left: 150px;margin-right: 150px;margin-top: -150px;">
            <img align="left" class="fb-image-lg" src="https://scontent-syd2-1.xx.fbcdn.net/v/t31.0-8/16903389_10158312656115644_210251690318066081_o.jpg?oh=2d8e48868a9f345b812ca1f8d8640dcb&oe=5A3F9027"  alt="Profile image example"/>
            <img align="left" class="fb-image-profile thumbnail" src="${userInfo.getPhoto()}" style="margin-top: -90px; margin-top: -90px;width: 250px;margin-right: 0;margin-left: 75px;height: 250px" alt="" class="img-circle">
            <div class="col-md-8 col-xs-12 col-sm-6 col-lg-8 fb-profile-text" style="padding: 0px ; padding-right: 21px;">
                <div class="container" style="border-bottom:3px solid grey;width: 100%; margin-top: -30px; ">
                    <h2 style="margin-left: 50px">${userInfo.getUserName()}</h2>
                </div>
                <div class="col-xs-8 col-md-offset-2" style="margin-top: 30px; color: #22262e" >
                    <div class="row">
                        <div class="col-md-7 ">
                            <ul >
                                <li><p><span class="glyphicon glyphicon-user one" style="width:50px;"></span>${userInfo.getYourName()}</p></li>
                                <li><p><span class="fa fa-venus-mars" style="width:50px;"></span>${userInfo.getGender()}</p></li>
                                <li><p><span class="glyphicon glyphicon-calendar one" style="width:50px;"></span>${userInfo.getDoB()}</p></li>
                                <li><p><span class="glyphicon glyphicon-envelope one" style="width:50px;"></span>${userInfo.getEmail()}</p></li>
                            </ul>
                        </div >
                        <div class="col-md-1 col-md-offset-4" style="margin-top: 67px;">
                            <button onclick="redirect()" style="border: hidden ;background-color: white">
                                <span id="motification">
                                <i  class="fa fa-cog fa-spin fa-3x fa-fw" ></i>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%--post_moment--%>

<div class="container-fluid" >
    <div class="row" >
        <div class="col-md-2 col-md-offset-2" >
            <P>&nbsp;</P>
            <p></p>
            <button type="button" id="post" class="btn btn-arrow btn-arrow-right btn-info">Post my moment<i class="glyphicon glyphicon-chevron-right"></i><i class="glyphicon glyphicon-chevron-right"></i><i class="glyphicon glyphicon-chevron-right"></i><i class="glyphicon glyphicon-chevron-right"></i><i class="glyphicon glyphicon-chevron-right"></i><i class="glyphicon glyphicon-chevron-right"></i></button>

        </div>

        <div class=" col-md-6 col-md-offset-0 " id="show_hide" style="display:none">
            <div class="panel panel-default  ">
                <div class="panel-body" style="background-color: #d5d9e5">
                    <form action="myPage_servlet" method="post" enctype="multipart/form-data">
                        <textarea class="form-control counted" name="postContent" style="height: 180px;width: 580px;" placeholder="Type in your comment:" style="margin-bottom:10px;"></textarea>
                        <br>
                        <input type="file" name="file">
                        <button class="btn btn-info submit" onclick="post_moment()" type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i>&nbsp;Post Comment</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<br><br>


<%--posted_moment--%>




<c:forEach var="post" items="${postList}">
<div class="container" style="margin-bottom: 25px">
    <div class="panel panel-primary col-xs-10 col-md-offset-1" style="padding: 0px;">
        <%--heading--%>
        <div class="panel-heading " style="padding-bottom: 30px;background-color:#616365">
            <h3 class="panel-title">
                <span class="fa fa-calendar col-md-offset-9">&nbsp;${post.time}</span>
            </h3>
        </div>
        <%--body--%>
        <div class="panel-body" style="background-color: #ffffff;padding: 30px">
            <p>${post.content}</p>
            <c:if test="${post.photo!=''}">
                <img src="http://${ipaddress}:8080/unswbook/${post.photo}" width="300" height="300" alt=""/>
            </c:if>
        </div>
        <%--footer--%>
        <div class="panel-footer" style="padding-bottom: 8px;">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-10">
                        <span id="heart"><i class="fa fa-heart-o fa-lg " style="color: red"></i></span>
                        <c:forEach var="yourName" items="${post.likesYourNameList}" >
                            <span id="names" >&nbsp;${yourName}&nbsp;&nbsp;</span>
                        </c:forEach>
                    </div>
                    <div class="col-md-2 ">
                        <form action="myPage_servlet" onsubmit="return delet_confirm();" method="post" >
                            <input type="hidden" name="postID" value="${post.postID}">
                            <span>
                                <button type="submit" class="btn btn-default"  style="border-color: transparent">
                                    <span id="deletMoment">
                                        <i class="fa fa-trash fa-lg"></i>
                                    </span>
                                </button>
                            </span>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</c:forEach>

</body>
</html>
