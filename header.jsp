<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.*,dao.*"%>
User user = (User)session.getAttribute("user");


      <h1 style="text-align:center">フリマサイト</h1>
      <% if(user.getAuthority().equals("1")){%>
      <hr style="text-align:center; height:5px; background-color:red">
      <% }else if(user.getAuthority().equals("")){ %>
      <hr style="text-align:center; height:5px; background-color:blue">
      <% } %>
 