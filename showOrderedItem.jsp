<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,bean.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Sales"%>
<%@ page import="dao.ItemsDAO"%>
<%@ page import="bean.Items"%>

<%
	ArrayList<bean.Sales> sales_list = (ArrayList<bean.Sales>) request.getAttribute("sales_list");
	User user = (User)session.getAttribute("user");
%>
<html>
	<head>
		<title>フリマサイト</title>
	</head>
	<body>
		<!--ヘッダー部分  -->
			<%@ include file="/common/header.jsp" %>
		<h1 style="text-align:center">購入履歴</h1>

		
		<table style="margin:auto; width:850px">
			<tr>
				<td style="text-align:center; width:100">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</table>
			
		<hr style="height:2; background-color:blue" />
		<div style="margin:auto">
		
			<table style="margin:auto; margin-top:1%; margin-bottom:10%">
				
							<tr>
								<th style="text-align:center; background-color:#6666ff; width:250px">商品名</th>
								<th style="text-align:center; background-color:#6666ff; width:250px">種類</th>
								<th style="text-align:center; background-color:#6666ff; width:250px">価格</th>
								<th style="text-align:center; background-color:#6666ff; width:250px">注文日</th>
							</tr>
									<%
					if (sales_list != null) {
						for (Sales sales : sales_list) {
					%>
							<tr>
								<td><%=sales.getSalesItemName() %></td>
								<td><%=sales.getIsbn() %></td>
								<td><%=sales.getSalesPrice() %>
								<td><%=sales.getSalesDate().replace("-","/") %></td>
							</tr>
					<%
						}
					}
					%>
				

			</table>
		</div>
		<hr style="text-align:center; height:5px; background-color:blue">
	 <table  style="margin:auto; border:0; width:950px; text-align:left">
			<tr><td>神田ITスクール</td></tr>
	</table>
		
	</body>
</html>
