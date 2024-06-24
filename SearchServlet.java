package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Items;
import dao.ItemsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//検索機能
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

			//			入力パラメーターを取得
			String user_id = request.getParameter("user_id");
			String isbn = request.getParameter("isbn");
			String item_name = request.getParameter("item_name");
			String type = request.getParameter("type");
			String price = request.getParameter("price");

			//			ItemsDAOをインスタンス化
			ItemsDAO ItemsDaoObj = new ItemsDAO();

			//			関連メソッドを呼び出し、戻り値としてItemsオブジェクトのリストを取得する
			ArrayList<Items> list = ItemsDaoObj.search(user_id, isbn, item_name, type, price);

			//			リクエストスコープに登録
			request.setAttribute("item_list", list);

		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}

	}

}
