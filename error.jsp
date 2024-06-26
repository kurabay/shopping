<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.User"%>


<%
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
User user = (User)session.getAttribute("user");
%>

<html>
	<head>
		<title>Error</title>
	</head>
	<body>
		<h1 style="text-align:center">エラー表示</h1>
      <hr style="text-align:center; height:5px; background-color:blue">
     	<div style="margin-bottom:350px; text-align:center">
     		●●エラー●●<br><br>
     		<%= error %><br><br>	
     		
     	<%
     	if(cmd == "menu"){
     	%>
     	<a href="<%=request.getContextPath() %>/view/menu.jsp">メニューへ戻る</a>
     	<%
     	}else if(cmd == "list"){
     	%>
     	<a href="<%=request.getContextPath() %>/list">一覧表示に戻る</a>
     	<%
     	}else {
     	%>
     	<a href="<%=request.getContextPath() %>/logout">ログイン画面へ戻る</a>
     	<%
     	}
     	%>
     	</div>
     
    
      <hr style="text-align:center; height:5px; background-color:blue">
      
	 <table  style="margin:auto; border:0; width:950px; text-align:left">
			<tr><td>神田ITスクール</td></tr>
	</table>
	</body>
</html>