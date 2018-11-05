<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <%--<link rel="stylesheet" href="styles/main.css" type="text/css"/>--%>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Website CSS style -->
    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="style.css">--%>
    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            padding-top: 90px;
        }
        body, html{
            height: 100%;
            background-repeat: no-repeat;
            /*background:url(https://i.ytimg.com/vi/4kfXjatgeEU/maxresdefault.jpg);*/
            font-family: 'Oxygen', sans-serif;
            background-size: cover;
            background-image: url(https://scontent-syd2-1.xx.fbcdn.net/v/t31.0-8/16903389_10158312656115644_210251690318066081_o.jpg?oh=2d8e48868a9f345b812ca1f8d8640dcb&oe=5A3F9027);


        }
        .header-content {
            padding: 50px 0;
        }
        .header-title {
            color: #fd1628;
            display: block;
            font-size: 48px;
            font-weight: 600;
            line-height: 50px;
            text-transform: uppercase;
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
        .panel-login {
            border-color: #ccc;
            -webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
            -moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
            box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
        }
        .panel-login>.panel-heading {
            color: #00415d;
            background-color: #fff;
            border-color: #fff;
            text-align:center;
        }
        .panel-login>.panel-heading a{
            text-decoration: none;
            color: #666;
            font-weight: bold;
            font-size: 15px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }
        .panel-login>.panel-heading a.active{
            color: #029f5b;
            font-size: 18px;
        }
        .panel-login>.panel-heading hr{
            margin-top: 10px;
            margin-bottom: 0px;
            clear: both;
            border: 0;
            height: 1px;
            background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
            background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
            background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
            background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
        }
        .panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"] {
            height: 45px;
            border: 1px solid #ddd;
            font-size: 16px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }
        .panel-login input:hover,
        .panel-login input:focus {
            outline:none;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            border-color: #ccc;
        }
        .btn-login {
            background-color: #59B2E0;
            outline: none;
            color: #fff;
            font-size: 14px;
            height: auto;
            font-weight: normal;
            padding: 14px 0;
            text-transform: uppercase;
            border-color: #59B2E6;
        }
        .btn-login:hover,
        .btn-login:focus {
            color: #fff;
            background-color: #53A3CD;
            border-color: #53A3CD;
        }
        .forgot-password {
            text-decoration: underline;
            color: #888;
        }
        .forgot-password:hover,
        .forgot-password:focus {
            text-decoration: underline;
            color: #666;
        }

        .btn-register {
            background-color: #1CB94E;
            outline: none;
            color: #fff;
            font-size: 14px;
            height: auto;
            font-weight: normal;
            padding: 14px 0;
            text-transform: uppercase;
            border-color: #1CB94A;
        }
        .btn-register:hover,
        .btn-register:focus {
            color: #fff;
            background-color: #1CA347;
            border-color: #1CA347;
        }

    </style>
    <script type="text/javascript">

        $(function() {
            $('#login-form-link').click(function(e) {
                $("#login-form").delay(100).fadeIn(100);
                $("#register-form").fadeOut(100);
                $('#register-form-link').removeClass('active');
                $(this).addClass('active');
                e.preventDefault();
            });
            $('#register-form-link').click(function(e) {
                $("#register-form").delay(100).fadeIn(100);
                $("#login-form").fadeOut(100);
                $('#login-form-link').removeClass('active');
                $(this).addClass('active');
                e.preventDefault();
            });
        });

        function validateEmail(email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        $(document).ready(function() {
            $('#username').blur(function(event) {
                var name = $('#username').val();
                $.get('login_servlet', {
                    username : name
                }, function(responseText) {
                    $('#response_username').text(responseText);
                });
            });

            $('#email').blur(function(event) {
                var email = $('#email').val();
                $.get('login_servlet', {
                    email : email
                }, function(responseText) {
                    $('#response_email').text(responseText);
                    if (responseText==""){
                        if(!validateEmail(email)){
                            $('#response_email').text("incorrect format of email");
                        }
                    }

                });
            });

            $('#password').blur(function(event) {
                var password = document.getElementById("password").value;
                if(password.length<6){
                    $('#response_password').text("password must at least has 6 charater long");
                }else{
                    $('#response_password').text("");
                }
            });

            $('#realName').blur(function(event) {
                var realName = document.getElementById("realName").value;
                if(realName.length==0){
                    $('#response_realName').text("realName cannot be empty ");
                }else{
                    $('#response_realName').text("");
                }
            });

        });

        function validateForm() {
            var ok=true;
            if(document.getElementById("response_username").textContent!=""){
                ok=false;
            }

            if(document.getElementById("response_email").textContent!=""){
                ok=false;
            }

            if(document.getElementById("response_password").textContent!=""){
                ok=false;
            }

            if($('#realName').val()==""){
                ok=false;
            }
            if(!ok){
                alert("The fields marked with * must be filled correctly");
            }
            return ok;
        }


    </script>
</head>
<body style="padding: 70px">

    <% 
        String me=(String)request.getAttribute("message");
        if (me==null){
            me="";
        }
    %>
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-login">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-6">
                                <a href="#" class="active" id="login-form-link">Login</a>
                            </div>
                            <div class="col-xs-6">
                                <a href="#" id="register-form-link">Register</a>
                            </div>
                        </div>
                        <hr>
                    </div>
                    <div class="panel-body" >
                        <div class="row">
                                <%--login_form--%>
                            <div style="padding-left: 40px;padding-right:40px;">
                                <p style="color: red;"><%=me%></p>
                                <form id="login-form" action="login_servlet" method="post" role="form" style="display: block;">
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" name="username" value="" id="username0" tabindex="1" class="form-control" placeholder="Username" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" name="password" id="password0" tabindex="2" class="form-control" placeholder="Password">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="3" class="form-control btn btn-login" value="Log In">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="admin_login.jsp" tabindex="4" class="forgot-password">Admin_login</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </form>
                                <%--register_form--%>
                                <hr class="colorgraph">
                                <div  style="padding-left: 35px;padding-right: 35px;">
                                    <form id="register-form" action="registration_servlet" method="post" enctype="multipart/form-data"  role="form" style="display: none;"  onsubmit="return validateForm()">
                                        <div class="form-group">
                                            <div class="input-group" style="width: 100%;">
                                                <span class="input-group-addon" style="width: 52px;height: 45px"><i class="fa fa-user fa-lg" aria-hidden="true"></i><span class="text-danger">*</span></span>
                                                <input type="text" id="username" name="username" value="" tabindex="1" class="form-control"  placeholder="*Username:" >
                                            </div>
                                            <div id="response_username" style="color: red"></div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group" style="width: 100%;">
                                                <span class="input-group-addon" style="width: 52px;height: 45px"><i class="fa fa-lock fa-lg" aria-hidden="true"></i><span class="text-danger">*</span></span>
                                                <input type="password" id="password" name="password" value="" tabindex="2" class="form-control" placeholder="*Password:">
                                            </div>
                                            <div id="response_password" style="color: red"></div>
                                        </div>

                                        <div class="form-group ">
                                            <div class="input-group" style="width: 100%">
                                                <span class="input-group-addon" style="width: 52px;height: 45px"><i class="fa fa-envelope fa-lg" aria-hidden="true"></i><span class="text-danger">*</span></span>
                                                <input type="email" name="email" value="" id="email" tabindex="3" class="form-control" placeholder="*Email Address:" >
                                            </div>
                                            <div id="response_email" style="color: red"></div>
                                        </div>

                                        <div class="form-group" >
                                            <div class="input-group" style="width: 100%">
                                                <span class="input-group-addon" style="width: 52px;height: 45px"><i class="fa fa-pencil fa-lg" aria-hidden="true"></i><span class="text-danger">*</span></span>
                                                <input type="text" name="yourName" id="realName" value="" tabindex="4" class="form-control" placeholder="*RealName:" >
                                            </div>
                                            <div id="response_realName" style="color: red"></div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group"style="width: 100%">
                                                <span class="input-group-addon " style="width: 52px;height: 45px"><i class="fa fa-calendar fa-lg" aria-hidden="true"></i><span class="text-danger"> </span></span>
                                                <input type="date" name="DoB" style="height: 45px" value="" tabindex="5" class="form-control" placeholder="Date of birth:" >
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="control-label col-sm-3"></label>
                                            <div class="col-md-8 col-sm-9">
                                                <label><input name="gender" type="radio" value="male" checked>Male </label>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label><input name="gender" type="radio" value="female" >Female </label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label class="pad_top"><span class="fa fa-photo fa-lg" aria-hidden="true"></span>
                                                    <input type = "file" name = "file" tabindex="6" style="margin-left: 12%;margin-top: -7%"></label>
                                            </div>
                                        </div>
                                        <label>&nbsp;</label>

                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" value="Register Now" tabindex="7" id="enter" class=" form-control btn btn-register margin_left">
                                                </div>
                                            </div>
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