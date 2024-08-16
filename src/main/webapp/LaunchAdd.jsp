<%@ page import="com.spacez.model.entity.Launch" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ziyan
  Date: 5/8/2023
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpaceZ - Add Launch</title>
    <style>
        body{
            background: url("addupdate-background.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            margin: 0;
        }
        .form-container{
            position: relative;
            padding: 5vh;
            background: rgb(245, 245, 245, 0.8);
            border: 0px;
            border-radius: 20px;
            margin-top: 20vh;
            margin-right: auto;
            margin-left: auto;
            width: fit-content;
            box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
        }
        .form-container > form > input{
            width: 20vw;
            height: 4vh;
        }
        label{
            font-weight: bold;
        }
        input{
            border: 0px;
            border-radius: 20px;
            background: whitesmoke;
            margin-left: 1.5em;
            padding: 10px;
            box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
        }
        input[name=id]{
            width: 3vw;
            text-align: center;
        }
        input[name=is_launch]{
            margin-right: 2vw;
        }
    </style>
</head>
<body>

<%
    int temp = 0;
    List<Launch> launches = (List<Launch>) request.getAttribute("launches");
    for (Launch ln : launches) {
        if (temp < ln.getId()) temp = ln.getId();
    }
    temp++;
%>
<div class="form-container">
<form action="LaunchController" method="post">
    <label>Launch ID<input type="text" name="id" value="<%=temp%>" readonly></label><br><br>
    <label>Mission Name<input type="text" name="missionName"></label><br><br>
    <label>Spacecraft Model<input type="text" name="model"></label><br><br>
    <label>Date<input type="text" name="date" placeholder="YYYY-MM-DD"></label><br><br>
    <label>Time<input type="text" name="time" placeholder="xx:xx:xx"></label><br><br>
    <label>Description<input type="text" name="description" placeholder="Can be empty"></label><br><br>
    <label>Is Launch?<label style="margin-left: 2vw">TRUE<input type="radio" name="is_launch" value="true"></label><label>False<input type="radio" name="is_launch" value="false"></label></label><br><br>
    <input type="submit" value="Add" name="ADD">
</form>
</div>

</body>
</html>
