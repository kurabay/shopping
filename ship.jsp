<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.*, java.util.*"%>

<%
ArrayList<StatusItem> sales_list = (ArrayList<StatusItem>) request.getAttribute("salesList");
%>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset"UTF-8">
        <title>発送状況</title>
    </head>
    <body>
        <h1 style="text-align:center">フリマサイト</h1>
        <hr style="text-align:center; height:5px; background-color:blue">
        
		<table  style="margin:auto; width:850px;">
		<tr>
		   	<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]</td>
		   	<td style="text-align:center; width:500px; font-size:25px;">発送状況</td>
		   	<td style="width:80px">&nbsp;</td>
	   	<td style="width:80px">&nbsp;</td>
		</tr>
		
		</table>
		
		<hr style="text-align:center; height:3px; background-color:black">
		
	<div style="text-align: center;">
		<div style="margin: 3% 60% 1%;">
			<form action="<%=request.getContextPath()%>/ship" method="get" >
				<div style="display: flex;">
				
					<div style="margin-right: 3%;">
						<select name="ship">
							<option>--select type--</option>
							<option value="1">未発送</option>
							<option value="2">発送済</option>
						</select>
					</div>
					
					<div>
						<input type="submit" value="並び替え">
					</div>
					
				</div>
			</form>
		</div>
	</div>		

		         
<table style="margin:0 auto 20%; text-align: center;">
	<form action="<%=request.getContextPath()%>/ship" method="get">
		<tr>
			<th style="background-color:#6666ff; width:250px">ISBN</th>
			<th style="background-color:#6666ff; width:250px">売り上げ番号</th>
			<th style="background-color:#6666ff; width:250px">発送状況</th>
			<th style="background-color:#6666ff; width:250px">発送</th>
		</tr>
		<%
		int total = 0;
			if (sales_list != null) {
				for (StatusItem items : sales_list) {
		%>
					<tr>
						<td><%=items.getIsbn()%></td>
						
						<td><%=items.getSalesId()%></td>
						
						<% if (items.getShippingStatus().equals("1")){ %>
							<td>未発送</td>
							<input type="hidden" name="sales_id" value="<%=items.getSalesId()%>">
							<input type="hidden" name="shipping_status" value="2">
							<td><input type="submit"  value="発送"></td>
						<%} else { %>
							<td>発送済</td>
							<td>ー</td>
						<% } %>
					</tr>
		<%
				}
			}
		%>
	</form>
</table>
			
        <hr style="text-align:center; height:5px; background-color:blue">
             <table  style="margin:auto; border:0; width:950px; text-align:left">
                <tr><td></td></tr>
            </table>
    </body>
</html>