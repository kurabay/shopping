<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User,dao.UserDAO"%>
    
<%
//セッションからユーザー情報を取得
User user = (User)session.getAttribute("user");

//セッション切れか確認
if(user == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd","logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
String authority = "";
if(user.getAuthority().equals("")) {
	authority = "一般ユーザー";	
}
if(user.getAuthority().equals("1")) {
	authority = "管理者";
	
}
%>
<!--
else if(user.getAuthority().equals("") {
	request.setAttribute("message","入力データが間違っています。");
	request.getRequestDispatcher("/view/login.jsp").forward(request, response);
	return;
			
}
-->

   
<html>
	<head>

		<title>userInfo</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

	</head>
<body>
	<!-- 名前記入部分 -->
			
			<div id="menu">
				<div class="table_right">
						<th><h4>名前：<%=user.getUserid() %></h4></th>
						<th><h4>権限：<%=authority %></h4></th>
				</div>
			</div>
</body>
</html>