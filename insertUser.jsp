<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント作成</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<style>
.error-message {
	display: none;
	color: #purple;
	background-color: #f8d7da;
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

@
keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
.login-link-container {
	text-align: left;
	margin: 20px 0;
}

.form-container {
	text-align: center;
	margin: auto;
	width: 300px;
}

.form-group {
	margin: 10px 0;
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
<body style="text-align: center">

	<div style="position: relative; width: 100%; height: 200px;">
		<div style="position: absolute; left: 0; top: 0;">
			<img src="<%=request.getContextPath()%>/images/logo.webp" alt="サイトロゴ"
				height="180">
		</div>
		<div
			style="position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);">
			<h1>フリマサイト</h1>
		</div>
	</div>
	<hr style="text-align: center; height: 5px; background-color: #ba55d3">

	<div class="login-link-container">
		<ul>
			<li><a href="<%=request.getContextPath()%>/view/login.jsp">[ログイン画面へ]</a></li>
		</ul>
	</div>


	<div style="text-align: center; margin-top:-40px;">
		<h2>アカウント作成</h2>
	</div>
	<hr style="text-align: center; height: 3px; background-color: #ba55d3">
	<div class="form-container">
		<form action="${pageContext.request.contextPath}/insertUser"
			method="post">
			<div class="form-group">
				<label for="user_id">ユーザー名:</label> <input type="text" id="user_id"
					name="user_id" required>
			</div>
			<div class="form-group">
				<label for="password">パスワード:</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="form-group">
				<label for="user_name">名前:</label> <input type="text" id="user_name"
					name="user_name" required>
			</div>
			<div class="form-group">
				<label for="user_nickname">ニックネーム:</label> <input type="text"
					id="user_nickname" name="user_nickname" required>
			</div>
			<div class="form-group">
				<label for="user_address">住所:</label> <input type="text"
					id="user_address" name="user_address">
			</div>
			<div class="form-group">
				<label for="mail">メールアドレス:</label> <input type="email" id="mail"
					name="mail" required>
			</div>
			<div class="form-group">
				<label for="phone_num">電話番号:</label> <input type="text"
					id="phone_num" name="phone_num">
			</div>
			<button type="submit">登録</button>
		</form>

		<div id="error-message" class="error-message"></div>
	</div>

	<hr style="text-align: center; height: 5px; background-color: #ba55d3">
	<table style="width: 950px; text-align: left;">
		<tr>
			<td>神田ITスクール</td>
		</tr>
	</table>
</body>
</html>
