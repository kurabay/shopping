<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.*,dao.*"%>
User user = (User)session.getAttribute("user");


<div style="position:relative; width: 100%; height: 200px;">
    <div style="position:absolute; left: 0; top: 0;">
        <img src="<%= request.getContextPath() %>/images/logo.webp" alt="サイトロゴ" height="180">
    </div>
    <div style="position: absolute; left: 50%; top: 50%; transform:translate(-50%, -50%);">
        <h1>フリマサイト</h1>
    </div>
</div>

<% if (user.getAuthority().equals("1")) { %> 
    <hr style="height: 5px; background-color:red;"> 
<% } else { %> 
    <hr style="height: 5px; background-color:blue;"> 
<% } %>
 