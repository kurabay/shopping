<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User,dao.UserDAO"%>
User user = (User)session.getAttribute("user");

<% if(user.getAuthority().equals("1")){%>
<hr style="text-align:center; height:5px; background-color:red">
<% }else{ %>
<hr style="text-align:center; height:5px; background-color:blue">
<%} %>
	 <table  style="margin:auto; border:0; width:950px; text-align:left">
			<tr><td>神田ITスクール</td></tr>
	</table>
