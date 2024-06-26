<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.*,dao.*"%>
<%@page import="java.util.ArrayList,util.MyFormat"%>

<%
User user = (User) session.getAttribute("user");
%>



<html>
   <head>
      <title>ShowOrderedItem</title>
      <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/style.css">
   </head>
   <body>
   		<!--ヘッダー部分  -->
        <%@ include file="/common/header.jsp" %>
      
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
						<h2>購入状況</h2>
					</div>
				</div>
			</div>
      
      
      <hr style="text-align:center; height:2px; background-color:red">
      
      <div style="margin-bottom:250px; text-align:center;">
				<form class="inline-block" action="<%=request.getContextPath()%>/searchSales" >
		 			ユーザーID<input type="text"  name="user_id">
		 			商品名 <input type="text"  name="item_name">
					<input type="submit" value="検索"></input>
				</form>
				<form class="inline-block" action="<%=request.getContextPath()%>/showSales" >
					<input type="submit" value="全件表示">
				</form>
				
     　 <table style="margin:auto">
				<tr>
					<th style="background-color:#ff6347; width:250px">日付</th>
					<th style="background-color:#ff6347; width:250px">ユーザーID</th>
					<th style="background-color:#ff6347; width:250px">商品名</th>
					<th style="background-color:#ff6347; width:250px">価格</th>
				</tr>
				<%
				ArrayList<Items> itemlist =(ArrayList<Items>)request.getAttribute("itemlist");
				if(itemlist != null){
					for(int i=0;i<itemlist.size();i++){
						Items item = itemlist.get(i);
				%>
				<tr>
					<td style="text-align:center; width:200px"><%=item.getSalesDate()%></td>
					<td style="text-align:center; width:200px"><%=item.getUser_id() %></td>
					<td style="text-align:center; width:200px"><%=item.getItem_name()%></td>
					<td style="text-align:center; width:200px"><%=item.getPrice()%></td>
				</tr>
				<%
					}
				}else{
				%>
				<tr>
					<td style="text-align:center; width:200px">&nbsp;</td>
					<td style="text-align:center; width:200px">&nbsp;</td>
					<td style="text-align:center; width:200px">&nbsp;</td>
					<td style="text-align:center; width:200px">&nbsp;</td>
				</tr>
				<%
				}
				%>
			</table>
	    </div>
       <%@ include file="/common/footer.jsp" %>
   </body>
</html>