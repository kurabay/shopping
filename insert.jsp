<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*"%>
<%
User user = (User) session.getAttribute("user");
%>

<html>
<head>
<title>商品登録</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">

		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>

		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ナビゲーション  -->
				<div id="nav">
					<ul>
						<li><a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a></li>
						<li><a href="<%=request.getContextPath()%>/list">[出品一覧]</a></li>
						<li><a href="<%=request.getContextPath()%>/insertList">[登録商品一覧]</a></li>
					</ul>
				</div>

				<!-- ページタイトル -->
				<div id="page_title">
					<h2>商品登録</h2>
				</div>
			</div>
		</div>
		<hr style="text-align:center; height:2px; background-color:blue; margin-top:1%">
		
		<!-- 書籍登録コンテンツ部分 -->
		<div id="main" class="container">
			<!--  入力フォーム -->
			<form action="<%=request.getContextPath()%>/insert">
				<table class="input-table" align="center">
					<tr>
						<th style="background-color: #6666ff; width: 250px">ユーザーID</th>
						<td
							style="background-color: #ABE1FA; text-align: center; width: 50"
							name="user_id"><%=user.getUser_id()%></td>
					</tr>
					<tr>
						<th style="background-color: #6666ff; width: 250px">商品名</th>
						<td><input type="text" name="item_name"></td>
					</tr>
					<tr>
						<th style="background-color: #6666ff; width: 250px">商品名(かな)</th>
						<td><input type="text" name="item_kana"></td>
					</tr>
					<tr>
						<th style="background-color: #6666ff; width: 250px">種類</th>
						<td><input type="text" name="type"></td>
					</tr>
					<tr>
						<th style="background-color: #6666ff; width: 250px">価格</th>
						<td><input type="text" name="price"></td>
					</tr>
					<tr>
						<th style="background-color: #6666ff; width: 250px">備考</th>
						<td><textarea name="remark" rows="5" cols="30"></textarea>></td>
					</tr>
				</table>
				<input type="submit" value="登録">
			</form>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

	</div>
</body>
</html>