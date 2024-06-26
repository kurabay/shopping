<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.*,"%>
<%
	Items items = (Items) request.getAttribute("items");
    User user = (User) session.getAttribute("user");

%>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>商品詳細画面</title>
	    <link rel ="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
	<body>
	    <!--ヘッダー部分  -->
			<%@ include file="/common/header.jsp" %>
	    
	    <div id="menu">
				<div class="container">
					<!-- ナビゲーション  -->
					<div id="nav">
						<ul>
							<li><a href ="<%=request.getContextPath()%>/view/menu.jsp" >[メニュー]</a></li>
	    					<li><a href ="<%=request.getContextPath()%>/list">[商品一覧]</a></li>
						</ul>
					</div>

					<!-- ページタイトル -->
					<div id="page_title">
						<h2>商品詳細</h2>
					</div>
				</div>
			</div>
			<hr style="text-align:center; height:2px; background-color:blue">
	    
	    
	    <div class="container">
	        <div class="image-and-button">
	            <img src="images/sample2.jpg" width="270px">
	            <form class="button" style="text-align:center; margin-top:20px;" action="showcart.html">
	                <input type="submit" value="カートに追加">
	            </form>
	        </div>
	        <div class="details">
	            <td><h2><%=items.getItem_name() %></h2></td>
	            <h4><%=items.getItem_kana() %></h4>
	            <h2><%=items.getPrice() %></h2>
	            <h4><%=items.getType() %></h4>
	            <h3>商品説明</h3>
	            <p><%=items.getRemark() %></p>
	        </div>
	    </div>
	
	    <hr style="text-align:center; height:5px; background-color:blue; margin-top:350px;" class="footer">
	    <table style="margin:auto; border:0; width:950px; text-align:left;">
	        <tr>
	            <td>神田ITスクール</td>
	        </tr>
	    </table>
	</body>
</html>