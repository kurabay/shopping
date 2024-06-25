<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Sales"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="bean.Items"%>
<%@ page import="bean.User"%>
<%
User user = (User) session.getAttribute("user");
ArrayList<Items> itemsList = (ArrayList<Items>) session.getAttribute("itemsList");
%>

<!DOCTYPE html>
<html>
<head>
<title>ショッピングカート</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<style>
.list-table {
	width: 50%;
	table-layout: fixed;
}
</style>
</head>
<body>
	<!-- 共通ヘッダーのインクルード -->
	<%@ include file="/common/header.jsp"%>

	<div id="wrap">

		<ul id="nav">
			<li><a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a></li>
			<li><a href="<%=request.getContextPath()%>/list">出品一覧</a></li>
		</ul>

		<div id="main">
			<div id="page_title">
				<h2 id="page_title">カート表示</h2>
			</div>
			<%
			if (itemsList != null && itemsList.size() != 0) {
			%>

			<table class="list-table">
				<thead>
					<tr>
						<th style="background-color: blue; width: 250px">商品名</th>
						<th style="background-color: blue; width: 250px">種類</th>
						<th style="background-color: blue; width: 250px">価格</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Items item : itemsList) {
						if (item != null) {
					%>
					<tr>
						<td><%=item.getItem_name()%></td>
						<td><%=item.getType()%></td>
						<td><%=item.getPrice()%></td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
			<form action="<%=request.getContextPath()%>/buyConfirm" method="post">
				<button type="submit">購入確認</button>
			</form>
			<%
			} else {
			%>
			<p>カートに商品がありません。</p>
			<%
			}
			%>
		</div>
	</div>

	<!-- 共通フッターのインクルード -->
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
