package servlet;

import java.io.IOException;

import bean.Items;
import dao.ItemsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 *詳細表示処理用サーブレットクラス
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";
		
		try {
			//入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");
			
			//①isbn、cmdの入力パラメータを取得する
			String isbn = request.getParameter("isbn");
			cmd = request.getParameter("cmd");
			
			//ItemsDAOをインスタンス化する
			ItemsDAO itemsDao = new ItemsDAO();
			
			//結果の戻り値として、Itemsオブジェクトを取得する
			Items items = itemsDao.selectByIsbn(isbn);
			
			//詳細情報のエラーチェック
			if (item.getIsbn() == null) {
				if (cmd.equals("detail")) {
					error = "表示対象の商品が存在しない為、詳細情報は表示できませんでした。";
				}
				cmd = "list";
				return;
			}
		}catch(IllegalStateException e)	{	//DB接続エラー
			if (cmd.equals("detail")) {
				error = "DB接続エラーの為、商品の詳細は表示できませんでした。";
			}
			cmd = "logout";
			
		}finally {
			//エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) { //エラーがない場合
				//cmdの値でフォワード先を呼び分ける
				if (cmd.equals("detail")) {
					request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
				}	
			} else { //エラーがある場合「error.jsp」にフォワード
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
			
			
		}
	}

}
