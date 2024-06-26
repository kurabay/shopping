<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.*, java.util.*"%>

<%
ArrayList<StatusItem> buy_list = (ArrayList<StatusItem>) request.getAttribute("buyList");
User user = (User)session.getAttribute("user");

%>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset"UTF-8">
        <title>入金状況</title>
    </head>
    <body>
        <h1 style="text-align:center">フリマサイト</h1>
        <hr style="text-align:center; height:5px; background-color:blue">
        
<table  style="margin:auto; width:850px;">
<tr>
   <td style="text-align:center; width:80px">[<a href="<%=request.getContextPath()%>/view/menu.jsp">メニュー</a>]</td>
   <td style="text-align:center; width:500px; font-size:25px;">入金状況</td>
   <td style="width:80px">&nbsp;</td>
   <td style="width:80px">&nbsp;</td>
</tr>

</table>

<hr style="text-align:center; height:3px; background-color:black">


 
<div style="text-align: center;">
<div style="margin: 3% 60% 1%;">
<form method="get" >
<div style="display: flex;">

<div style="margin-right: 3%;">
<select name="payment">
<option value="">--select type--</option>
<option value="1">未入金</option>
<option value="2">入金済</option>
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
<form method="get">
<tr>
<th style="background-color:#6666ff; width:250px">商品番号</th>
<th style="background-color:#6666ff; width:250px">商品名</th>
<th style="background-color:#6666ff; width:250px">入金状況</th>
<th style="background-color:#6666ff; width:250px">振込</th>
</tr>
<%
int total = 0;
if (buy_list != null) {
	for (StatusItem items : buy_list) {

%>
<tr>
<td><%=items.getIsbn()%></td>

<td><%=items.getSalesId()%></td>

<% if (items.getPaymentStatus().equals("1")){ %>
<td>未入金</td>
<form class="inline-block" action="<%=request.getContextPath()%>/payment" >
<input type="hidden" name="sales_id" value="<%=items.getSalesId()%>">
<input type="hidden" name="payment_status" value="2">
<td><input type="submit"  value="入金"></td>
</form>
<%} else { %>
<td>入金済</td>
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
