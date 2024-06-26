<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.User"%>
<%
	//セッションからユーザー情報を取得
	User user = (User)session.getAttribute("user");
	%>


<html>
   <head>
      <title>ユーザー一覧画面</title>
   </head>
   <body>
      <!--ヘッダー部分  -->
			<%@ include file="/common/header.jsp" %>
      <table  style="margin:auto; width:850px;">
		<tr>
		   <td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
		   <td style="text-align:center; width:500px; font-size:25px;">ユーザー一覧</td>
		   <td style="width:80px">&nbsp;</td>
		   <td style="width:80px">&nbsp;</td>
		</tr>
      </table>
      
      <hr style="text-align:center; height:3px; background-color:red">
      <div style="margin-bottom:250px">
         <table style="margin:auto">
            <tr>
               <td>
                 <form action="<%=request.getContextPath() %>/searchUser">
                    ユーザーID:<input type="text" size="30" name="user_id"></input>
                    ニックネーム:<input type="text" size="30" name="user_nickname"></input>
                    権限:<input type="text" size="30" name="authority"></input>
                    <input type="submit" name="serch" value="検索"></input>
                 </form>   
               </td>
               <td>
                 <form action="<%=request.getContextPath()%>/userList" method="get">
                    <input type="submit" name="searchall" value="全件表示"></input>
                 </form>
               </td>
            </tr>
         </table>
         
         <table style="margin:auto">
				<tr>
					<th style="background-color:#ff6347; width:250px">ユーザーID</th>
					<th style="background-color:#ff6347; width:250px">ニックネーム</th>
					<th style="background-color:#ff6347; width:250px">権限</th>
				</tr>
				<%
				ArrayList<User> userList =(ArrayList<User>)request.getAttribute("user_list");
				if(userList != null){
					for(int i=0;i<userList.size();i++){
						User users = userList.get(i);
				%>
				<tr>
					<td style="text-align:center; width:200px"><%=users.getUser_id()%></td>
					<td style="text-align:center; width:200px"><%=users.getUser_nickname()%></td>
					<td style="text-align:center; width:200px">
					<%
					if(users.getAuthority().equals("1")){
					%>
					一般ユーザー
					<%
					}else if(users.getAuthority().equals("2")){
					%>
					管理者
					<%
					}
					%>
					</td>
				</tr>
				<%
					}
				}else{
				%>
				<tr>
					<td style="text-align:center; width:200px">&nbsp;</td>
					<td style="text-align:center; width:200px">&nbsp;</td>
					<td style="text-align:center; width:200px">&nbsp;</td>
				</tr>
				<%
				}
				%>
				
				
			</table>
	    </div>
	    
	   <hr style="text-align:center; height:5px; background-color:red">
	 <table  style="margin:auto; border:0; width:950px; text-align:left">
			<tr><td>神田IDスクール</td></tr>
	</table>
   </body>
</html>