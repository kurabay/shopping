<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User"%>
<%@page import="java.util.,bean.,dao.*"%>
<% User user = (User)session.getAttribute("user");%>

<%
String user_id = "";
String password = "";

Cookie[] userCookie = request.getCookies(); //クッキーの取得
String error = null;
error = (String)request.getAttribute("message");

if(userCookie != null){    
	for(int i = 0; i<userCookie.length; i++){
       		if(userCookie[i].getName().equals("user_id")){
         			user_id = userCookie[i].getValue();
		}
		if(userCookie[i].getName().equals("password")){
         		password = userCookie[i].getValue();
		}
	}
}

%>

<html>
   <head>
      <title>フリマサイトログイン画面</title>
   </head>
   <body style="text-align:center">
      <h1 style="text-align:center">フリマサイト</h1>
      <hr style="text-align:center; height:5px; background-color:blue">
      <h2 style="text-align:center">ログイン画面</h2>
      <hr style="text-align:center; height:2px; background-color:black">
      
      <tr>
         <div style="margin-bottom:350px">
         <table border="0"; style="margin: 0 auto">
         <hr>
         <form action="<%=request.getContextPath() %>/login" method="post">
         <tr>
              <th style="background-color:#6666FF; width:200">ユーザーID</th>
              <th><input type="text" size="30" name="user_id" value=<%=user_id%>></input></th><br>
         </tr>    
         <tr>    
              <th style="background-color:#6666FF; width:200">パスワード</th>
              <th><input type="text" size="30" name="password" value=<%=password %>></input></th><br>
         </tr>
         </table>
         
         
               <th colspan="2"> <input type="submit" name="login" value="ログイン"></input></th><br>
         		<th><td style="text-align:center"><a href="<%=request.getContextPath() %>/view/insertUser.jsp">【アカウント作成】</a></td></th>
             
          </form>
          </div>
         </tr>
         
         	<%
         	if(error != null){
         	%>
         <h2 style="text-align:center"><%=error %></h2>
      <%
         	}
      %>
      <hr style="text-align:center; height:5px; background-color:blue">
      <table  style="margin:auto; border:0; width:950px; text-align:left">
			<tr><td>神田ITスクール</td></tr>
	</table>
   </body>
</html>
