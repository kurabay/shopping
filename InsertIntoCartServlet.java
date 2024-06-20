package servlet;

import java.io.IOException;
import java.util.ArrayList;

import dao.ItemsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/insertIntoCart")
public class InsertIntoCartServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";
		
		try {
			//セッションからUserオブジェクトを取得
			HttpSession session = request.getSession();
			bean.User user = (bean.User)session.getAttribute("user");
			
			//セッション切れの場合はエラー
			if(user == null) {
				error = "セッション切れの為、カートに追加できませんでした。";
				cmd = "logout";
				return;
			}
			//isbnのパラメータを取得
			String isbn = (String) request.getParameter("isbn");
			
			//ItemDAOをインスタンス化し、関連メソッドを呼び出す
			ItemsDAO itemObjDao = new ItemsDAO();
			bean.Items item = itemObjDao.selectByIsbn(isbn);
			
			//リクエストスコープに名前itemで格納
			request.setAttribute("item",item);
			
			//Salesをオブジェクト化し、各setterメソッドを利用
			bean.Sales sales = new bean.Sales();
			sales.setSalesId(sales.getSalesId());
			sales.setIsbn(sales.getIsbn());

			
			//セッションからsalesListの配列を取得する
			ArrayList<bean.Sales> salesList = (ArrayList<bean.Sales>)session.getAttribute("salesList");
			if(salesList == null) {
				salesList = new ArrayList<bean.Sales>();
			}
			
			//セッションスコープに登録
			salesList.add(sales);
			session.setAttribute("salesList", salesList);
			
		}catch(IllegalStateException e) {
			error = "DB接続エラーの為、カートに追加できません。";
			cmd = "logout";
		}finally {
			//エラーの有無でフォワード先を分ける
			if(error.equals("")) {
				//エラーが無い場合はshowCart.jspにフォワード
				request.getRequestDispatcher("/view/showCart.jsp").forward(request,response);
			}else {
				//エラーがある場合はerror.jspへフォワード
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}