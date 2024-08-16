<%@ page import="com.spacez.model.entity.Launch" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: ziyan
  Date: 26/7/2023
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpaceZ - Launch Table</title>
    <style>
        body {
            background-image: url("table-background.png");
            background-attachment: fixed;
            background-size: cover;
            background-repeat: no-repeat;
            margin: 0;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        table{
            position: fixed;
            background: rgba(192,192,192, 0.75);
            border-collapse: collapse;
            border-radius: 1em;
            overflow: hidden;
            margin-left: auto;
            margin-right: auto;
            width: 70%;
            height: 70%;
        }
        th, td{
            padding: 4px;
            border-top: none;
            border-right: none;
            border-left: none;
        }
        .page-item{
            background: lightblue ;
            text-decoration: none;
            display: inline;
            border-radius: 20px;
            margin-left: 10px;
            margin-right: 10px;
            padding: 10px;
        }
        pagination {
            list-style-type: none;
            justify-content: center;
        }
        nav{
            margin-top: 3vh;
            text-align: center;
        }
        .pageref{
            font-weight: bold;
            font-size: 150%;
            color: black;
            text-align: center;
        }
        table,nav,.pageref{
            position: relative;
            top: 8vh;
        }
        input[name=ADD]{
            border-radius: 20px;
            border: 0px;
            width: 15vw;
            height: 3vh;
            box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 2px 6px 2px;
        }

        .addForm-container{
            position: fixed;
            bottom: 0;
            right: 0;
            padding: 1em;
        }
    </style>
</head>
<body>

<%
    int currentPage = (int) request.getAttribute("currentPage");
    int recordsPerPage = (int) request.getAttribute("recordsPerPage");
    int nOfPages = (int) request.getAttribute("nOfPages");
    String keyword = (String) request.getAttribute("keyword");
    String direction = (String) request.getAttribute("direction");
    String searchType = (String) request.getAttribute("searchType");
    String asc = "selected", desc = "selected";
    if("ASC".equals(direction)){
        desc = "";
    }else asc = "";

%>

<form class="form-inline md-form mr-auto mb-4" action="PaginationServlet" method="get">
    <select name="searchType">
        <option value="id">Launch ID</option>
        <option value="name">Mission Name</option>
        <option value="model">SpaceCraft Model</option>
        <option value="date">Date</option>
        <option value="time">Time</option>
        <option value="description">Description</option>
        <option value="isLaunch">Is Launch?</option>
    </select>
    <input class="form-control mr-sm-2" type="text" aria-label="Search" name="keyword">
    <button class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info" type="submit">Search</button>
    <input type="hidden" name="currentPage" value="<%=currentPage%>">
    <input type="hidden" name="direction" value="<%=direction%>">
</form>

<form class="form-inline md-form mr-auto mb-4" action="PaginationServlet" method="get">
    <select class="form-control" id="direction" name="direction">
        <option value="ASC" <%=asc%>>Ascending</option>
        <option value="DESC" <%=desc%>>Descending</option>
    </select>
    <button class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info" type="submit">Sorting</button>
    <input type="hidden" name="currentPage" value="<%=currentPage%>">
    <input type="hidden" name="keyword" value="<%=keyword%>">
</form>


<table border="1">
    <thead>

    <tr>
        <th>Launch ID</th>
        <th>Mission Name</th>
        <th>Spacecraft Model</th>
        <th>Launch Date</th>
        <th>Launch Time</th>
        <th>Description</th>
        <th>Is Launched</th>
        <th>Update/Delete</th>
    </tr>
    </thead>
    <tbody>
<%
    List<Launch> launches = (List<Launch>) request.getAttribute("launches");
    String print = "";
    if (launches.size() != 0){

    for (Launch l : launches) {
        if(l.getIsLaunch() == true){
            print = "<span>&#10003;</span>";
        }
        else if (l.getIsLaunch() == false){
            print = "X";
        }
%>
    <tr>
        <th><%= l.getId() %></th>
        <th><%= l.getMissionName() %></th>
        <th><%= l.getSpacecraftModel() %></th>
        <th><%= l.getDate() %></th>
        <th><%= l.getTime() %></th>
        <td><%= l.getDescription() %></td>
        <th><%= print %></th>
        <th><a href="/SpaceZWeb-1.0-SNAPSHOT/LaunchController?id=<%= l.getId() %>">Edit</a></th>;
    </tr>
<%
        }
    }
    else{
            out.println("<tr>");
            String status = "No records found!";
            for (int i = 0; i < 8; i++) {
                out.println("<td>" + status + "</td>");
            }
            out.println("</tr>");
        }
%>
    </tbody>
</table>
<nav aria-label="Navigation for staffs">
    <div class="pagination">

        <%
            if (currentPage != 1 && nOfPages != 0) {
        %>


        <%
            out.println("<div class=\"page-item\">");
            out.println("<a style=\"text-decoration: none;\" class=\"page-link\" href=\"" + "PaginationServlet?recordsPerPage=" + recordsPerPage + "&currentPage=1" + "&keyword=" + keyword + "&direction=" + direction + "\">First</a>");
            out.println("</div>");
        %>


        <div class="page-item">
            <%
                out.println("<a style=\"text-decoration: none;\" class=\"page-link\" href=\"" + "PaginationServlet?recordsPerPage=" + recordsPerPage + "&currentPage=" + (currentPage - 1) + "&keyword=" + keyword + "&direction=" + direction + "\">Previous</a>");
            %>
        </div>
        <%
            }
        %>
        <%

        %>
        <%
            if (currentPage < nOfPages) {
                out.println("<div class=\"page-item\">");
                out.println("<a style=\"text-decoration: none;\" class=\"page-link\" href=\"" + "PaginationServlet?recordsPerPage=" + recordsPerPage + "&currentPage=" + (currentPage + 1) + "&keyword=" + keyword +  "&direction=" + direction + "\">Next</a>");
                out.println("</div>");

                out.println("<div class=\"page-item\">");
                out.println("<a style=\"text-decoration: none;\" class=\"page-link\" href=\"" + "PaginationServlet?recordsPerPage=" + recordsPerPage + "&currentPage=" + nOfPages  + "&keyword=" + keyword +  "&direction=" + direction + "\">Last</a>");
                out.println("</div>");
            }
        %>

    </div>
</nav>
<%
    if (nOfPages != 0) {
        out.println("<p class=\"pageref\">");
        out.println(currentPage + " of " + nOfPages);
        out.println("</p>");
    }
%>
<div class="addForm-container">
<form action="/SpaceZWeb-1.0-SNAPSHOT/AddLaunchServlet" class="addForm">
    <input type="submit" value="Add" name="ADD">
</form>
</div>
</body>
</html>
