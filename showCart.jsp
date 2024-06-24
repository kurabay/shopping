<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Sales"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="bean.Items"%>

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
<%@ include file="/common/header.jsp"%>
</head>
<body>
<div id = "menu">
	<h2 id="page_title">カート表示</h2>
		<ul id="nav">
			<li><a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a></li>
			<li><a href="<%=request.getContextPath()%>/list">出品一覧</a></li>
		</ul>
	</div>
	<div id="wrap">
		<div id="main">
			<%
			ArrayList<Sales> salesList = (ArrayList<Sales>) request.getAttribute("salesList");
			if (salesList != null && !salesList.isEmpty()) {
				ItemsDAO itemsDAO = new ItemsDAO();
			%>
			<table class="list-table">
				<thead>
					<tr>
						<th>商品名</th>
						<th>種類</th>
						<th>価格</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Sales sales : salesList) {
						String isbn = sales.getIsbn();
						Items item = itemsDAO.selectByIsbn(isbn);
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
		<%@ include file="/common/footer.jsp"%>
	</div>
</body>
</html>
