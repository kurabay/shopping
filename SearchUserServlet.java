package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//ユーザー検索機能
@WebServlet("/searchUser")
public class SearchUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

			//入力パラメーターを取得
			String user_id = request.getParameter("user_id");
			String user_nickname = request.getParameter("user_nickname");
			String authority = request.getParameter("authority");

			//UserDAOをインスタンス化
			UserDAO UserDaoObj = new UserDAO();

			//関連メソッドを呼び出し、戻り値としてItemsオブジェクトのリストを取得する
			ArrayList<User> list = UserDaoObj.search(user_id, user_nickname, authority);

			//リクエストスコープに登録
			request.setAttribute("user_list", list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/userList.jsp").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}

	}

}