<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,bean.*,dao.*"%>

<html lang="ja">

<head>
<meta charset="UTF-8">

<title>MENU（一般）</title>
</head>

<body>

	<%
	User user = (User) session.getAttribute("user");

	//管理者かユーザーでメニューを切り替える
	if (user.getAuthority().equals("1")) {

		//管理者画面
	%>
	<h1 style="text-align: center">フリマサイト</h1>
	<hr style="text-align: center; height: 5px; background-color: red">

	<table style="margin: auto; width: 850px; margin-left:28%">
		<tr>
			<td style="text-align: center; width: 500px; font-size: 35px;">MENU</td>
			<td style="width: 80px">&nbsp;</td>
			<td style="width: 80px">&nbsp;</td>

		</tr>
	</table>


	<hr style="text-align: center; height: 3px; background-color: red">


	<div id="main" class="container" ;>
		<div style="text-align: center; margin: auto; width: 850px;">
			<a href="<%=request.getContextPath()%>/list">【出品一覧】</a><br> <a
				href="<%=request.getContextPath()%>/showSales">【売上状況】</a><br> <a
				href="<%=request.getContextPath()%>/userList">【ユーザー一覧】</a><br>
			<a href="<%=request.getContextPath()%>/view/insertUser.jsp">【アカウント作成】</a><br>
			<a href="<%=request.getContextPath()%>/logout">【ログアウト】</a><br>

		</div>
		
		<hr
			style="text-align: center; height: 5px; background-color: red; margin-top: 600px;">
		<table
			style="margin: auto; border: 0; width: 950px; text-align: left; margin-top: 30px;">
			<tr>
				<td>神田ITスクール</td>
			</tr>
		</table>


		<%
		} else {
		//それ以外ならユーザー画面
		%>
		<h1 style="text-align: center">フリマサイト</h1>
		<hr style="text-align: center; height: 5px; background-color: blue">

		<table style="margin: auto; width: 850px; margin-left:28%">
			<tr>
				<td style="text-align: center; width: 500px; font-size: 35px;">MENU</td>
				<td style="width: 80px">&nbsp;</td>
				<td style="width: 80px">&nbsp;</td>

			</tr>
		</table>

	<hr style="text-align: center; height: 3px; background-color: blue">

		<div style="text-align: center; margin: auto; width: 850px;">
			<a href="<%=request.getContextPath()%>/list">【出品一覧】</a><br> <a
				href="<%=request.getContextPath()%>/view/insert.jsp">【商品登録】</a><br>
			<a href="<%=request.getContextPath()%>/showCart">【カート表示】</a><br>
			<a href="<%=request.getContextPath()%>/payment">【入金状況】</a><br> <a
				href="<%=request.getContextPath()%>/ship">【発送状況】</a><br> <a
				href="<%=request.getContextPath()%>/ShowHistoryOrderedItemServlet">【購入履歴一覧】</a><br>
			<a href="<%=request.getContextPath()%>/view/insertUser.jsp">【アカウント作成】</a><br>
			<a href="<%=request.getContextPath()%>/logout">【ログアウト】</a><br>

		</div>

		<hr
			style="text-align: center; height: 5px; background-color: blue; margin-top: 600px;">
		<table
			style="margin: auto; border: 0; width: 950px; text-align: left; margin-top: 30px;">
			<tr>
				<td>神田ITスクール</td>
			</tr>
		</table>
		<%}
%>
</body>

</html>