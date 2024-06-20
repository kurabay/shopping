package sevlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Items;
import dao.ItemsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//出品商品一覧機能
@WebServlet("/list")
public class ListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";
		
		try {
//			ItmesDAOのオブジェクト作成
			ItemsDAO ItemsDaoObj = new ItemsDAO();
			
//			検索した書籍情報を格納するAllayListオブジェクトを生成
//		ItemsDAOクラスに定義した、selectAll()メソッドを利用して書籍情報を取得
			
			ArrayList<Items> list = ItemsDaoObj.selectAll();
			 
//			所得した商品情報を「item_list」という名前でリクエストスコープに登録
			request.setAttribute("item_list", list);
			
		}catch (IllegalStateException e ) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
			
		}finally {
			if(error.equals("")){
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
		
	}

}
