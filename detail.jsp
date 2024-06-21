<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Items,util.MyFormat"%>
<%
	Items items = (Items) request.getAttribute("items");
	MyFormat format = new MyFormat();
%>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>商品詳細画面</title>
	    <link rel ="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<!-- 
	    <style>
	        .centered {
	            text-align: center;
	        }
	
	        .container {
	            display: flex;
	            justify-content: center;
	            align-items: flex-start;
	            margin-top: 20px;
	        }
	
	        .image-and-button {
	            display: flex;
	            flex-direction: column;
	            align-items: center;
	        }
	
	        .image-and-button img {
	            border: 2px solid black;
	            padding: 10px;
	        }
	
	        .button {
	            margin-top: 20px; /* ボタンの上に余白を追加 */
	        }
	
	        .details {
	            margin-left: 20px;
	        }
	
	        .product-details h3,
	        .product-details h2 {
	            margin: 10px 0;
	        }
	
	        .footer {
	            margin-top: 50px;
	        }
	    </style>
	</head>
 -->
	<body>
	    <h1 style="text-align:center">フリマサイト</h1>
	    <hr style="text-align:center; height:5px; background-color:blue">
	
	    <div class="centered">
	    	<ul>
	    	<li><a href ="<%=request.getContextPath()%>/view/menu.jsp" >[メニュー]</a></li>
	    	<li><a href ="<%=request.getContextPath()%>/list">[商品一覧]</a></li>
	    	</ul>
	        <h2>商品詳細</h2>
	        
	    </div>
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
	            <td>ロゴ的なもの</td>
	        </tr>
	    </table>
	</body>
</html>