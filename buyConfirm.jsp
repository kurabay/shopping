<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*"%>

<%
ArrayList<Items> itemslist = (ArrayList<Items>) request.getAttribute("oldList");
User user = (User) session.getAttribute("user");
%>

<html>
	<head>
		<title>購入品確認</title>
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
							<li><a href ="<%=request.getContextPath()%>/list">[出品一覧]</a></li>
						</ul>
					</div>

					<!-- ページタイトル -->
					<div id="page_title">
						<h2>購入品確認</h2>
					</div>
				</div>
			</div>
			<hr style="text-align:center; height:2px; background-color:blue">
			
			<!-- 商品一覧のコンテンツ部分 -->
			<div id="main" class="container">
			<p>下記の商品を購入しました。<br>ご利用ありがとうございました。</p>
				<!-- カートの書籍情報リスト -->
				<table class="list-table">
					<thead>
						<tr>
							<th style="background-color:blue; width:250px">商品名</th>
							<th style="background-color:blue; width:250px">種類</th>
							<th style="background-color:blue; width:250px">価格</th>
						</tr>
					</thead>
					<tbody>
						<%
						int total = 0;
						if (itemslist != null) {
							for ( int i = 0; i < itemslist.size(); i++) {
								Items items = (Items)itemslist.get(i);
								total += items.getPrice();
						%>
						<tr>
							<td><%=items.getItem_name()%></td>
							<td><%=items.getType() %></td>
							<td><%=items.getPrice()%></td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
				</table>
				
				<div class="table_line"></div>
				<table class="sum-table">
							<tr>
								<th style="background-color:blue; width:250px">合計</th>
								<td><%=total%></td>
							</tr>
						</table>
			</div>

			<!-- フッター部分 -->
			<%@ include file="/common/footer.jsp" %>

		</div>
	</body>
</html>
