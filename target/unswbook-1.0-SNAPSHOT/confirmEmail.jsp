<%-- 
    Document   : confrimEmail
    Created on : 27/09/2017, 1:10:12 PM
    Author     : yiqunrong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.unswbook.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>confirmation Page</title>
    </head>
    <body>
<%
   String idString=request.getParameter("userID");
    if(idString!=null){
        int userID=Integer.valueOf(idString);
        if(database.userConfirm(userID)){
            database.addLog(userID, "user confirm!");
%>
    <h1>your email have been confirmed!</h1>
<%
        }
    }
else{  
%>
   <h1>error!</h1>
<%
    }
%>
    </body>
</html>
