<%-- 
    Document   : admin_login
    Created on : 2017-9-25, 16:37:38
    Author     : shownwang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
        <title>admin_login</title>

        <style>
            @import url(http://fonts.googleapis.com/css?family=Roboto:400);
            body {
                background-color:#fff;
                -webkit-font-smoothing: antialiased;
                font: normal 14px Roboto,arial,sans-serif;
            }

            .container {
                padding: 25px;
                position: fixed;
            }

            .form-login {
                background-color: #EDEDED;
                padding-top: 10px;
                padding-bottom: 20px;
                padding-left: 20px;
                padding-right: 20px;
                border-radius: 15px;
                border-color:#d2d2d2;
                border-width: 10px;
                box-shadow:0 1px 0 #cfcfcf;
            }

            h4 {
                border:0 solid #9ac2fd;
                border-bottom-width:1px;
                padding-bottom:10px;
                text-align: center;
            }

            .form-control {
                border-radius: 10px;
            }

            .wrapper {
                text-align: center;
            }

        </style>
    </head>
    <body>
        <% String message = (String) request.getAttribute("message");
            if (message == null){
                message = "";
            }
        %>
    <center>
        <div class="container">
            <div class="col-md-offset-3">
                <div class="form-login" style="width: 450px;margin-top: 80px;">
                    <h4 >Admin Login</h4>
                    <p style="color: red;font-size: 12px"><%=message%></p>
                    <form id="newsearch" method="post" action="admin_login_servlet">
                        <br>
                        <input type="hidden" name="action" value="login"></input>
                        <input type="text" name="name" class="form-control input-sm chat-input"  placeholder="Input name..."></input>
                        <br>
                        <input type="text" name="password" class="form-control input-sm chat-input"  placeholder="Input password..."></input>
                        <br>
                        <div class="wrapper">
                        <span class="group-btn">
                        <input type="submit" value="login" class="btn btn-primary btn-md"></input>
                        </span>
                        </div>
                    </form>
                    <div class="searchclear"></div>
                    <div class="form-group">
                        <div class="text-center">
                            <p>&nbsp;</p>
                            <a href="index.jsp" class="forgot-password">User Login</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </center>
    </body>
</html>
