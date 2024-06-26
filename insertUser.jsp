<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>アカウント作成</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .error-message {
            display: none;
            color: #perple;
            background-color: #perple;
            border: 1px solid #f5c6cb;
            padding: 10px;
            margin-top: 10px;
            border-radius: 5px;
            position: relative;
            max-width: 400px;
            margin: auto;
            font-family: "Arial", sans-serif;
            font-size: 14px;
            text-align: left;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .error-message::after {
            content: '';
            position: absolute;
            top: -10px;
            left: 50%;
            margin-left: -10px;
            border-width: 10px;
            border-style: solid;
            border-color: transparent transparent #f8d7da transparent;
        }

        .error-message.show {
            display: block;
            animation: fadeIn 0.5s;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
    </style>
    <script>
        function showError(message) {
            const errorMessageElement = document.getElementById('error-message');
            errorMessageElement.textContent = message;
            errorMessageElement.classList.add('show');
        }

        window.onload = function() {
            const errorMessage = '${requestScope.errorMessage}';
            if (errorMessage) {
                showError(errorMessage);
            }
        }
    </script>
</head>
<body>
    <div id="wrap">
        <h1 style="text-align: center">フリマサイト</h1>
        <hr style="text-align: center; height: 5px; background-color: purple">

        <table style="margin: auto; width: 850px; margin-left:28%">
            <tr>
                <td style="text-align: center; width: 500px; font-size: 35px;">アカウント作成</td>
                <td style="width: 80px">&nbsp;</td>
                <td style="width: 80px">&nbsp;</td>
            </tr>
        </table>

        <hr style="text-align: center; height: 3px; background-color: purple">
        
        <div class="form-container" style="text-align: center; margin: auto;">
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
            
            <div id="error-message" class="error-message"></div>
        </div>
        
        <hr style="text-align: center; height: 5px; background-color: purple">
        <table style="width: 950px; text-align: left;">
            <tr>
                <td>神田ITスクール</td>
            </tr>
        </table>
    </div>
</body>
</html>
