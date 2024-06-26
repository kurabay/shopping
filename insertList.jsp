<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*,util.MyFormat"%>

<%
ArrayList<Items> list = (ArrayList<Items>) request.getAttribute("insertList");
User user = (User)session.getAttribute("user");
%>

<html>
	<head>
		<title>登録商品一覧</title>
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/style.css">
	</head>
	<body>
		<!-- ブラウザ全体 -->
		<div id="wrap">

			<!--ヘッダー部分  -->
			<%@ include file="/common/header.jsp" %>

			<!-- メニュー部分 -->
			<div id="menu">
				<div class="container">
					<!-- ナビゲーション  -->
					<div id="nav">
						<ul>
							<li><a href ="<%=request.getContextPath()%>/view/menu.jsp" >[メニュー]</a></li>
							<li><a href ="<%=request.getContextPath()%>/view/insert.jsp">[商品登録]</a></li>
						</ul>
					</div>

					<!-- ページタイトル -->
					<div id="page_title">
						<h2>登録商品一覧</h2>
					</div>
				</div>
			</div>


			<hr style="text-align:center; height:2px; background-color:blue">
			<!-- 登録商品一覧のコンテンツ部分 -->
			<div id="main" class="container">


				<!-- 登録商品情報リスト -->
				<table-user class="list-table">
					<thead>
						<tr>
							<th style="background-color:#6666ff; width:250px">商品名</th>
							<th style="background-color:#6666ff; width:250px">種類</th>
							<th style="background-color:#6666ff; width:250px">価格</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (list != null) {
							for ( int i = 0; i < list.size(); i++) {
								Items items = (Items)list.get(i);
						%>
						<tr>
							<td><%=items.getItem_name() %></td>
							<td><%=items.getType() %></td>
							<td><%=items.getPrice() %></td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
				</table>
			</div>

			<!-- フッター部分 -->
			<%@ include file="/common/footer.jsp" %>

		</div>
	</body>
</html>