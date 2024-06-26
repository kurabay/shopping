<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Sales"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="bean.Items"%>
<%@ page import="bean.User"%>
<%@page import="java.util.ArrayList,util.MyFormat"%>
<%
MyFormat myformat = new MyFormat();
User user = (User) session.getAttribute("user");
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
 <!--ヘッダー部分  -->
			<%@ include file="/common/header.jsp" %>

<div id="wrap">
<div id="menu">
<ul id="nav">
<li><a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a></li>
<li><a href="<%=request.getContextPath()%>/list">出品一覧</a></li>
</ul>
</div>

<div id="main">
<div id="page_title">
<h2 id="page_title">カート表示</h2>
</div>
<%
ArrayList<Items> itemsList = (ArrayList<Items>) session.getAttribute("itemsList");
if (itemsList != null) {
%>
<table class="list-table">
<thead>
<tr>
<th style="background-color: blue; width: 250px">商品名</th>
<th style="background-color: blue; width: 250px">種類</th>
<th style="background-color: blue; width: 250px">価格</th>
<th style="background-color: blue; width: 250px">削除</th>

</tr>
</thead>
<tbody>
<%
int count = 0;
for (Items item : itemsList) {
	if (item != null) {
%>
<tr>
<td><%=item.getItem_name()%></td>
<td><%=item.getType()%></td>
<td><%=myformat.moneyFormat(item.getPrice())%></td>
<td><a href="<%=request.getContextPath()%>/showCart?delno=<%= count %>">削除</a></td>
</tr>
<%
	count++;
}
}
%>
</tbody>
</table>
<form action="<%=request.getContextPath()%>/buyConfirm" method="post">
<button type="submit">購入</button>
</form>
<%
} else {
%>
<p>カートに商品がありません。</p>
<%
}
%>
</div>
<hr style="text-align: center; height: 5px; background-color: blue">
<table style="margin: auto; border: 0; width: 950px; text-align: left">
<tr>
<td>神田ITスクール</td>
</tr>
</table>
</div>
</body>
</html>
