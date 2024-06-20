<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.*,dao.*,util.MyFormat"%>

<%
ArrayList<Items> list = (ArrayList<Items>) request.getAttribute("item_list");
User user = (User)session.getAttribute("user");
MyFormat format = new MyFormat();
%>

<html>
	<head>
		<title>出品一覧</title>
		<link rel="stylesheet"  href="<%=request.getContextPath()%>/css/style.css">
	</head>
	<body>
		<!-- ブラウザ全体 -->
		<div id="wrap">

			<!--ヘッダー部分  -->
			<%@ include file="/common/header.jsp" %>

			<% 
				if ( user.getAuthority().equals("1")){
			%>
			<!-- メニュー部分 -->
			<div id="menu">
				<div class="container">
					<!-- ナビゲーション  -->
					<div id="nav">
						<ul>
							<li><a href ="<%=request.getContextPath()%>/view/menu.jsp" >[メニュー]</a></li>
						</ul>
					</div>

					<!-- ページタイトル -->
					<div id="page_title">
						<h2>出品検索</h2>
					</div>
				</div>
			</div>

			<!-- 書籍一覧のコンテンツ部分 -->
			
			<div id="main" class="container">

				<!-- 検索フォーム -->
				<form class="inline-block" action="<%=request.getContextPath()%>/search" >
		 			ユーザーID <input type="text"  name="user_id">
		 			ISBN <input type="text"  name="isbn">
		 			商品名 <input type="text"  name="item_name">
					種類 <input type="text" name="type">
					価格 <input type="text" name="price">
					<input type="submit" value="検索">
				</form>
				<form class="inline-block" action="<%=request.getContextPath()%>/list" >
					<input type="submit" value="全件表示">
				</form>

				<!-- 管理者用書籍情報リスト -->
				<table class="list-table">
					<thead>
						<tr>
							<th>ユーザーID</th>
							<th>ISBN</th>
							<th>商品名</th>
							<th>種類</th>
							<th>価格</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (list != null) {
							for ( int i = 0; i < list.size(); i++) {
								Items items = (Items)list.get(i);
						%>
						<tr>
							<td><%=items.getUser_id() %></td>
							<td><a href="<%=request.getContextPath() %>/detail?isbn=<%=items.getIsbn() %>&cmd=detail"><%=items.getIsbn() %></a></td>
							<td><%=items.getItem_name() %></td>
							<td><%=items.getType() %></td>
							<td><%=format.moneyFormat(items.getPrice())%></td>
							<td>
								<a href="<%=request.getContextPath()%>/detail?isbn=<%=items.getIsbn()%>&cmd=update">変更</a>
								<a href="<%=request.getContextPath()%>/delete?isbn=<%=items.getIsbn()%>">削除</a>
							</td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
					
					<%}else{ %>	
					
			<div id="menu">
				<div class="container">
					<!-- ナビゲーション  -->
					<div id="nav">
						<ul>
							<li><a href ="<%=request.getContextPath()%>/view/menu.jsp" >[メニュー]</a></li>
							<li><a href ="<%=request.getContextPath()%>/view/insert.jsp">[出品登録]</a></li>
						</ul>
					</div>

					<!-- ページタイトル -->
					<div id="page_title">
						<h2>出品検索</h2>
					</div>
				</div>
			</div>
			
				<!-- 検索フォーム -->
				<form class="inline-block" action="<%=request.getContextPath()%>/search" >
					ユーザーID <input type="text"  name="user_id">
		 			ISBN <input type="text"  name="isbn">
		 			商品名 <input type="text"  name="item_name">
					種類 <input type="text" name="type">
					価格 <input type="text" name="price">
					<input type="submit" value="検索">
				</form>
				<form class="inline-block" action="<%=request.getContextPath()%>/list" >
					<input type="submit" value="全件表示">
				</form>

				<!-- ユーザー用書籍情報リスト -->
				<table class="list-table">
					<thead>
						<tr>
							<th>ユーザーID</th>
							<th>ISBN</th>
							<th>商品名</th>
							<th>種類</th>
							<th>価格</th>
							<th>カートに入れる</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (list != null) {
							for ( int i = 0; i < list.size(); i++) {
								Items items = (Items)list.get(i);
						%>
						<tr>
						
							<td><%=items.getUser_id() %></td>
							<td><a href="<%=request.getContextPath() %>/detail?isbn=<%=items.getIsbn() %>&cmd=detail"><%=itesm.getIsbn() %></a></td>
							<td><%=items.getItem_name() %></td>
							<td><%=items.getType() %></td>
							<td><%=format.moneyFormat(items.getPrice())%></td>
							<td>
								<form class="inline-block" action="<%=request.getContextPath()%>/insertIntoCart" >
									<input type="submit" value="カートに入れる">
									<input type="hidden" name="isbn" value="<%=items.getIsbn() %>">
								</form>
							</td>
						</tr>
						<%
							}
						}
						%>
					</tbody>
					
				</table>
				<%} %>
			</div>

			<!-- フッター部分 -->
			<%@ include file="/common/footer.jsp" %>

		</div>
	</body>
</html>