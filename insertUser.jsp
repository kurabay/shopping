<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.,bean.,dao.*"%>
<% User user = (User)session.getAttribute("user");%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アカウント作成</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div id="wrap">
        <header>
            <h1>フリマサイト</h1>
        </header>
        <div id="menu"></div>
        <div id="main">
            <h1>アカウント作成</h1>
            <hr>
            <div class="form-container">
                <form action="${pageContext.request.contextPath}/insertUser" method="post">
                    <div class="form-group">
                        <label for="user_id">ユーザー名:</label>
                        <input type="text" id="user_id" name="user_id" required>
                    </div>
                    <div class="form-group">
                        <label for="password">パスワード:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="user_name">名前:</label>
                        <input type="text" id="user_name" name="user_name" required>
                    </div>
                    <div class="form-group">
                        <label for="user_nickname">ニックネーム:</label>
                        <input type="text" id="user_nickname" name="user_nickname" required>
                    </div>
                    <div class="form-group">
                        <label for="user_address">住所:</label>
                        <input type="text" id="user_address" name="user_address">
                    </div>
                    <div class="form-group">
                        <label for="mail">メールアドレス:</label>
                        <input type="email" id="mail" name="mail" required>
                    </div>
                    <div class="form-group">
                        <label for="phone_num">電話番号:</label>
                        <input type="text" id="phone_num" name="phone_num">
                    </div>
                    <button type="submit">登録</button>
                </form>
            </div>
            <c:if test="${param.error != null}">
                <p class="error">${param.error}</p>
            </c:if>
        </div>
        <footer>
            神田ITスクール
        </footer>
    </div>
</body>
</html>
