<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.*,dao.*"%>
<%@page import="java.util.ArrayList,util.MyFormat"%>
<%@page import="java.util.,bean.,dao.*"%>

<%User user = (User) session.getAttribute("user");%>



<html>
   <head>
      <title>ShowOrderedItem</title>
   </head>
   <body>
      <%@ include file="/common/header.jsp" %>
      <table  style="margin:auto; width:850px;">
		<tr>
		   <td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
		   <td style="text-align:center; width:500px; font-size:25px;">購入状況</td>
		   <td style="width:80px">&nbsp;</td>
		   <td style="width:80px">&nbsp;</td>
		</tr>
      </table>
      
      <hr style="text-align:center; height:3px; background-color:red">
      
      <div style="margin-bottom:250px">
				<form class="inline-block" action="<%=request.getContextPath()%>/searchSales" >
		 			ユーザーID<input type="text"  name="user_id">
		 			商品名 <input type="text"  name="item_name">
					<input type="submit" value="検索">
				</form>
     　 <table style="margin:auto">
				<tr>
					<th style="background-color:red; width:250px">日付</th>
					<th style="background-color:red; width:250px">ユーザーID</th>
					<th style="background-color:red; width:250px">商品名</th>
					<th style="background-color:red; width:250px">価格</th>
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
