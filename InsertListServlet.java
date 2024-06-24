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

@WebServlet("/insertList")
public class InsertListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";
		
		try {
		
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");
			String item_kana = request.getParameter("item_kana");
			String item_name = request.getParameter("item_name");
			String user_id = request.getParameter("user_id");
//		セッションから"user"を取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		//			セッション切れの場合errorに遷移
		if(user == null) {
			error = "セッション切れの為、パスワード変更は行えません。";
			cmd = "logout";
			return;
		}
		
		
//		ItemsDAOをインスタンス化
		ItemsDAO ItemsDaoObj = new ItemsDAO();
		
		ArrayList<Items> list = new ArrayList<Items>();
		
		Items items = new Items();
		items.setIsbn(isbn);
		items.setType(title);
		items.setType(price);
		items.setItem_kana(item_kana);
		items.setItem_name(item_name);
		items.setRemark(user_id);
		items.setUser_id(user.getUser_id());
		
//		メソッド呼び出し
//		list = ItemsDaoObj.insert(items);
		
		
//		リクエストスコープに登録
		request.setAttribute("insertList", list);
		
		}catch (IllegalStateException e ) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
			
		}finally {
			if(error.equals("")){
				request.getRequestDispatcher("/view/insertList.jsp").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
		
	}

}
