<%--
  Created by IntelliJ IDEA.
  User: grey1
  Date: 2017/9/26
  Time: 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>visualization</title>
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

        text {
          font-weight: 300;
          font-family: "Helvetica Neue", Helvetica, Arial, sans-serf;
          font-size: 14px;
        }

        .node rect {
          stroke: #333;
          fill: #fff;
          stroke-width: 1.5px;
        }

        .edgePath path.path {
          stroke: #333;
          fill: none;
          stroke-width: 1.5px;
        }

        .arrowhead {
         stroke: blue;
         fill: blue;
         stroke-width: 1.5px;
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

    </style>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
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
<nav class="navbar navbar-default navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header " style="margin-left: 5%">
            <ul class="nav navbar-nav">
                <li class="active"><a href="myPage_servlet"><i class="fa fa-home fa-fw" style="font-size:20px"></i>&nbsp;Home</a></li>
                <li><a href="search.jsp" class="fa fa-search fa-fw" style="font-size:20px">&nbsp;Search</a></li>
                <li><a href="friendsList_servlet">MyFriends</a></li>
                <li><a href="moment_servlet">Moments</a></li>
                <li><a href="visualisation.jsp">visualisations</a></li>
                <%--<li ><a id="notification" href="notification_servlet"><span class="glyphicon glyphicon-envelope"><i id="new_message"></i></span></a></li>--%>
            <%--<li><a id="notification" class="glyphicon glyphicon-envelope" href="notification_servlet"></a></li>--%>
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

<p></p>
<p></p>
<p></p>
<p></p>
<div id="graph">
</div>

<script type="text/javascript">

    var w = 1000*1.5;
    var h = 600*1.5;
    var linkDistance=200;

    var colors = d3.scale.category10();

    var dataset = {
    
    nodes: [
    <c:forEach var="pn" items="${peopleNmessage}">
    {name: "${pn.get(0)}", type: "${pn.get(1)}"},
    </c:forEach>
    ],
    edges: [
    <c:forEach var="pn" items="${edgeReturn}">
    {source: ${pn.get(0)}, target: ${pn.get(1)}, name: "${pn.get(2)}" },
    </c:forEach>
    ]
    };

 
    var svg = d3.select("#graph").append("svg").attr({"width":w,"height":h});

    var force = d3.layout.force()
        .nodes(dataset.nodes)
        .links(dataset.edges)
        .size([w,h])
        .linkDistance([linkDistance])
        .charge([-500])
        .theta(0.1)
        .gravity(0.05)
        .start();


    var node = svg.selectAll("node")
    .data(dataset.nodes)
    .enter()
    .append("g")
    .attr("class", function (d) {
        if (d.type === "0") {
           return "user";
        } else if(d.type === "1") {
           return "post";
        }else if(d.type=="2"){
           return "unsw";
        }else if(d.type=="3"){
           return "me";
        }else if(d.type=="4"){
           return "myfirend";
        }
    })
    .call(force.drag);
 
    
    var nodec = svg.selectAll(".user")
      .append("image")
      .attr("xlink:href", "http://${ipaddress}:8080/unswbook/icons/user.png")
      .attr("width", 30)
      .attr("height",30)
      .call(force.drag);

    var noder = svg.selectAll(".post")
      .append("image")
      .attr("xlink:href", "http://${ipaddress}:8080/unswbook/icons/post.png")
      .attr("width", 30)
      .attr("height",30)
      .call(force.drag);
  
     var nodeu = svg.selectAll(".unsw")
      .append("image")
      .attr("xlink:href", "http://${ipaddress}:8080/unswbook/icons/unsw.png")
      .attr("width", 30)
      .attr("height",30)
      .call(force.drag);
  
    var nodem = svg.selectAll(".me")
      .append("image")
      .attr("xlink:href", "http://${ipaddress}:8080/unswbook/icons/me.png")
      .attr("width", 40)
      .attr("height",40)
      .call(force.drag);
  
   var nodef = svg.selectAll(".myfirend")
      .append("image")
      .attr("xlink:href", "http://${ipaddress}:8080/unswbook/icons/myfriend.png")
      .attr("width", 30)
      .attr("height",30)
      .call(force.drag);

    var edges = svg.selectAll("line")
      .data(dataset.edges)
      .enter()
      .append("line")
      .attr("id",function(d,i) {return 'edge'+i})
      .attr('marker-end','url(#arrowhead)')
      .style("stroke","#ccc")
      .style("pointer-events", "none");


    var nodelabels = svg.selectAll(".nodelabel") 
       .data(dataset.nodes)
       .enter()
       .append("text")
       .attr({"x":function(d){return d.x;},
              "y":function(d){return d.y;},
              "class":"nodelabel",
              "stroke":"black"})
       .text(function(d){return d.name;});


    var edgepaths = svg.selectAll(".edgepath")
        .data(dataset.edges)
        .enter()
        .append('path')
        .attr({'d': function(d) {return 'M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y},
               'class':'edgepath',
               'fill-opacity':0,
               'stroke-opacity':0,
               'fill':'blue',
               'stroke':'red',
               'id':function(d,i) {return 'edgepath'+i}})
        .style("pointer-events", "none");

    var edgelabels = svg.selectAll(".edgelabel")
        .data(dataset.edges)
        .enter()
        .append('text')
        .style("pointer-events", "none")
        .attr({'class':'edgelabel',
               'id':function(d,i){return 'edgelabel'+i},
               'dx':80,
               'dy':0,
               'font-size':10,
               'fill':'#aaa'});

    edgelabels.append('textPath')
        .attr('xlink:href',function(d,i) {return '#edgepath'+i})
        .style("pointer-events", "none")
        .text(function(d){return d.name;});


    svg.append('defs').append('marker')
        .attr({'id':'arrowhead',
               'viewBox':'-0 -5 10 10',
               'refX':25,
               'refY':0,
               //'markerUnits':'strokeWidth',
               'orient':'auto',
               'markerWidth':10,
               'markerHeight':10,
               'xoverflow':'visible'})
        .append('svg:path')
            .attr('d', 'M 0,-5 L 10 ,0 L 0,5')
            .attr('fill', '#ccc')
            .attr('stroke','#ccc');
     

    force.on("tick", function(){

        edges.attr({"x1": function(d){return d.source.x;},
                    "y1": function(d){return d.source.y;},
                    "x2": function(d){return d.target.x;},
                    "y2": function(d){return d.target.y;}
        });

        nodec.attr({"x":function(d){return d.x;},
                    "y":function(d){return d.y;}
        });

        noder.attr({"x":function(d){return d.x;},
                    "y":function(d){return d.y;}
        });
        
        nodeu.attr({"x":function(d){return d.x;},
                    "y":function(d){return d.y;}
        });
        
        nodem.attr({"x":function(d){return d.x;},
                    "y":function(d){return d.y;}
        });        

        nodef.attr({"x":function(d){return d.x;},
                    "y":function(d){return d.y;}
        });   

        nodelabels.attr("x", function(d) { return d.x; }) 
                  .attr("y", function(d) { return d.y; });

        edgepaths.attr('d', function(d) { var path='M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y;
                                           //console.log(d)
                                           return path});       

        edgelabels.attr('transform',function(d,i){
            if (d.target.x<d.source.x){
                bbox = this.getBBox();
                rx = bbox.x+bbox.width/2;
                ry = bbox.y+bbox.height/2;
                return 'rotate(180 '+rx+' '+ry+')';
                }
            else {
                return 'rotate(0)';
                }
        });
    });
    </script>
</body>
</html>