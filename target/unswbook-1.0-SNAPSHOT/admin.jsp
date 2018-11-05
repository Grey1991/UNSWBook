<%-- 
    Document   : admin
    Created on : 2017-9-25, 17:27:28
    Author     : shownwang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin</title>
        <style type="text/css">
            <!-- CSS styles for Topnavigation -->
            body {
                margin: 0;
                padding: 0;
                background: #ccc;
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

            <!-- CSS styles for search -->

            @import url(https://fonts.googleapis.com/css?family=Open+Sans);

                body{
                    background: #f2f2f2;
                    font-family: 'Open Sans', sans-serif;
                }

                .search {
                    width: 100%;
                    position: relative
                }

                .searchTerm {
                    float: left;
                    width: 100%;
                    border: 3px solid #333;
                    padding: 5px;
                    height: auto;
                    border-radius: 5px;
                    outline: none;
                    color: #9DBFAF;
                }

                .searchTerm:focus{
                    color: #00B4CC;
                }

                .searchButton {
                    right: -50px;
                    width: 110px;
                    height: 36px;
                    border: 1px solid #333;
                    background: #333;
                    text-align: center;
                    color: #fff;
                    border-radius: 5px;
                    cursor: pointer;
                    font-size: 20px;
                }

                /*Resize the wrap to see the search bar change!*/
                .wrap{
                    width: 30%;
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
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
                    <li class="active"><a href="admin.jsp"><i class="fa fa-home fa-fw" style="font-size:20px"></i>&nbsp;Home</a></li>
                    <li ><a href="logout_servlet"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>



    <%--<center>--%>
        <form method="post" action="adminsearch_servlet">
            <div class="" style="width: 40%;margin-left: auto;margin-right: auto;">
                <div class="search" style="margin-top: 50px;margin-bottom: 10px;">
                    <!-- hidden input tag to identify this form action as search -->
                    <input type="hidden" name="action" value="search">
                    <b>UserName:</b><br>
                    <input type="text" name="userName" class="searchTerm" size="100" maxlength="100"><br><br>
                    <b>Name:</b><br>
                    <input type="text" name="yourName" class="searchTerm" size="100" maxlength="100"><br><br>
                    <b>Email:</b><br>
                    <input type="text" name="email" class="searchTerm" size="100" maxlength="100"><br><br>
                    <b>DoB:</b><br>
                    <input type="date" name="DoB" value="" tabindex="5" class="searchTerm" placeholder="Date of birth:" >

                    <div class="form-group">
                        <label class="control-label col-sm-3"></label>
                        <div class="col-md-8 col-sm-9" style="margin-top: 10px; margin-bottom: 10px">
                            <label><input name="gender" type="radio" value="male" >Male </label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <label><input name="gender" type="radio" value="female" >Female </label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <label><input name="gender" type="radio" value="" checked>All </label>
                        </div>
                    </div>
                    <br><br>
                    <button type="submit" value="search" class="searchButton" style="margin-left: 77%"><span class="glyphicon glyphicon-search"></span> Search</button>
                </div>
            </div>


        </form>
        <div class="searchclear"></div>

    <div class="" style="width: 40%;margin-left: auto;margin-right: auto;">
        <div class="search" style="margin-top: 10px;margin-bottom: 100px;">
            <form method="post" action="adminsearch_servlet">
                <input type="hidden" name="action" value="show_all"></input>
                <input type="submit" value="show all" class="searchButton" style="margin-left: 77%"></input>
            </form>
        </div>
    </div>
    <%--</center>--%>
    </body>
</html>
