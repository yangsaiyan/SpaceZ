<%@ page import="com.spacez.model.entity.Launch" %><%--
  Created by IntelliJ IDEA.
  User: ziyan
  Date: 5/8/2023
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpaceZ - Edit Launch</title>
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
        String t = "checked", f = "checked";
        Launch l = (Launch) request.getAttribute("Ln");
        if(l.getIsLaunch() == true){
            f = null;
        }
        else if(l.getIsLaunch() == false){
            t = null;
        }
    %>
    <div class="form-container">
    <form action="LaunchController" method="post">
        <label>Launch ID<input type="text" name="id" value="<%=l.getId()%>" readonly></label><br><br>
        <label>Mission Name<input type="text" name="missionName" value="<%=l.getMissionName()%>"></label><br><br>
        <label>Spacecraft Model<input type="text" name="model" value="<%=l.getSpacecraftModel()%>"></label><br><br>
        <label>Date<input type="text" name="date" value="<%=l.getDate()%>"></label><br><br>
        <label>Time<input type="text" name="time" value="<%=l.getTime()%>"></label><br><br>
        <label>Description<input type="text" name="description" value="<%=l.getDescription()%>"></label><br><br>
        <label>Is Launch?<label style="margin-left: 2vw">TRUE<input type="radio" name="is_launch" value="true" <%=t%>></label><label>False<input type="radio" name="is_launch" value="false" <%=f%>></label></label><br><br>
        <input type="submit" value="Update" name="UPDATE"><br><br>
        <input type="submit" value="Delete" name="DELETE">
    </form>
    </div>
</body>
</html>
