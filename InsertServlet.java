package sevlet;

import java.io.IOException;

import bean.Items;
import dao.ItemsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		//		入力データの文字コードの指定
		request.setCharacterEncoding("UTF-8");

		//		ItemsDAOオブジェクト生成
		ItemsDAO ItemsDaoObj = new ItemsDAO();

		try {

			//			各データの入力パラメータを取得する
			String isbn = request.getParameter("isbn");
			String item_name = request.getParameter("item_name");
			String item_kana = request.getParameter("item_kana");
			String type = request.getParameter("type");
			String strPrice = request.getParameter("price");
			String remark = request.getParameter("remark");

			//			selectByIsbn()メソッドでデータの存在確認
			Items items = new Items();
			items = ItemsDaoObj.selectByIsbn(isbn);

			//			各エラーチェック
			if (isbn.equals("")) {
				error = "ISBNが未入力の為、商品登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			if (item_name.equals("")) {
				error = "商品名が未入力の為、商品登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			if (item_kana.equals("")) {
				error = "商品名(かな)が未入力の為、商品登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			if (type.equals("")) {
				error = "種類が未入力の為、商品登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			if (strPrice.equals("")) {
				error = "価格が未入力の為、商品登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			if (remark.equals("")) {
				error = "備考欄が未入力の為、商品登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			int price = Integer.parseInt(strPrice);
			if(price < 0) {
				error = "価格が不正の為、商品登録処理は行えませんでした。";
				cmd = "list";
				return;

			}

			//			ISBNの重複チェック
			if(items.getIsbn() != null) {
				error = "入力ISBNは既に登録済みの為、、商品登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			//			各setterメソッドを利用し、各データを設定する
			items.setIsbn(isbn);
			items.setItem_name(item_name);
			items.setItem_kana(item_kana);
			items.setType(type);
			items.setPrice(price);
			items.setRemark(remark);

			//			生成したItemsオブジェクトを引数として、関連メソッドを呼び出す
			ItemsDaoObj.insert(items);
		}
		catch ( NumberFormatException e) {
			error = "価格が不正の為、書籍登録処理は行えませんでした。";
			cmd = "list";
		}
		catch (Exception e) {
			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			cmd = "logout";
		}
		finally {
			if (error.equals("")) {
				// エラーが無い場合はListServletにフォワード
				request.getRequestDispatcher("/list").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
