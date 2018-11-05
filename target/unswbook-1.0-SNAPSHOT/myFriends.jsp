<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="servlets.*, com.mycompany.unswbook.*, java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>my friends</title>
  <meta name="description" content="An very basic example of how to use the Wookmark jQuery plug-in.">
  <meta name="author" content="Christoph Ono">

  <meta name="viewport" content="width=device-width,initial-scale=1">

  <!-- CSS Reset -->
  <link rel="stylesheet" href="http://${ipaddress}:8080/unswbook/css/reset.css">

  <!-- Styling for your grid blocks -->
  <link rel="stylesheet" href="http://${ipaddress}:8080/unswbook/css/style.css">

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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>

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

<body style="height: 1000px;">
<!-- <link href="/templets/skins/style/yulan.css" rel="stylesheet"> -->
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
 

  <div class="container-fluid">
    <div id="main" role="main" style=" margin-top: -50px">
    <c:if test="${empty friends_list}">
      <p align="center" />
      <H3 style="font-family: verdana; color: #1B7FC3;" align="center">

          Sorry, your don't have any friends!
      </H3>
    </c:if>

    <c:if test="${not empty friends_list}">
      <ul id="tiles">
        <!-- These are our grid blocks -->
        <c:forEach var="user" items="${friends_list}">
        <li>
          <figure>
            <a href="friendPage_servlet?userID=${user.userID}&userName=${user.userName}" class="text-center"><img src="http://${ipaddress}:8080/unswbook${user.getPhoto()}" width="190" height="190"></a>
            <figcaption>
              <h2>${user.yourName}</h2>
              <c:if test="${user.gender=='male'}">
              <img src="http://${ipaddress}:8080/unswbook/icons/male.png" width="20" height="20" style="margin-left: auto; margin-right: 10%">
              </c:if>
              <c:if test="${user.gender=='female'}">
              <img src="http://${ipaddress}:8080/unswbook/icons/female.png" width="20" height="20" style="margin-left: auto; margin-right: 10%">
              </c:if>
              <span>DoB: ${user.getDoB()}</span><br>
              <span class="glyphicon glyphicon-envelope"> ${user.email}</span>
                <p></p>
            </figcaption>           
            </figure>
        </li>
        </c:forEach>
        <!-- End of grid blocks -->
      </ul>
     </c:if> 
    </div>
    <%--<footer>--%>

    <%--</footer>--%>
  </div>

  <!-- include jQuery -->
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>

  <!-- Include the plug-in -->
  <script src="http://${ipaddress}:8080/unswbook/libs/jquery.wookmark.js"></script>

  <!-- Once the page is loaded, initalize the plug-in. -->
  <script type="text/javascript">
    $(document).ready(new function() {
      // Prepare layout options.
      var options = {
        autoResize: true, // This will auto-update the layout when the browser window is resized.
        container: $('#main'), // Optional, used for some extra CSS styling
        offset: 2, // Optional, the distance between grid items
        itemWidth: 210 // Optional, the width of a grid item
      };

      // Get a reference to your grid items.
      var handler = $('#tiles li');

      // Call the layout function.
      handler.wookmark(options);

      // Capture clicks on grid items.
      handler.click(function(){
        // Randomize the height of the clicked item.
        var newHeight = $('img', this).height() + Math.round(Math.random()*300+30);
        $(this).css('height', newHeight+'px');

        // Update the layout.
        handler.wookmark();
      });
    });
  </script>

</body>
</html>
