package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Items;
import bean.User;
import dao.ItemsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/showCart")
public class ShowCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		ItemsDAO itemDao = new ItemsDAO();
		ArrayList<Items> list = new ArrayList<Items>();

		try {

			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			//delno(削除対象の配列要素番号)の入力パラメータを取得
			String delno = request.getParameter("delno");

			HttpSession session = request.getSession();

			User user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れです。";
				cmd = "logout";
				return;
			}
			ArrayList<Items> cartItems = (ArrayList<Items>) session.getAttribute("itemsList");

			if (cartItems == null) {
				cartItems = new ArrayList<Items>();
			}
			if (delno != null) {
				cartItems.remove(Integer.parseInt(delno));
			}

			Items item = null;
			for (int i = 0; i < cartItems.size(); i++) {
				item = itemDao.selectByIsbn(cartItems.get(i).getIsbn());
				list.add(item);

			}

			// カートの商品のリストをリクエスト属性として設定する
			session.setAttribute("itemsList", list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カート表示は行えませんでした。";
			cmd = "logout";
		} catch (Exception e) {
			error = "カートの表示中にエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			// エラーハンドリング
			if (error.isEmpty()) {
				request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
			} else {
				// エラーがあればエラーページにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
