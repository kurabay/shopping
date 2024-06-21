package servlet;

import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;

import bean.Sale;
import bean.Sales;
import bean.User;
import dao.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//注文登録機能
@WebServlet("/buyConfirm")
public class BuyConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";
		//		各クラスをインスタンス化
		Sales sales = new Sales();
		SalesDAO SalesDaoObj = new SalesDAO();
		
		

		try {
			//		セッションから"user"のUserオブジェクトを取得
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			//		セッション切れの場合はerrorに遷移
			if ( user.getUser_id() == null ) {
				error = "セッション切れの為、購入は出来ません。";
				cmd = "login";
				return;
			}

			//		セッションから"sale_list"を取得
			ArrayList<Sales> salesList = (ArrayList<Sales>)session.getAttribute("salesList");

			//		カートの中身がない場合はerrorに遷移
			if ( salesList == null ) {
				error = "カートの中に何もなかったので購入は出来ません。";
				cmd = "menu";
				return;
			}

			//		関連メソッドをorder_listの(カート追加データ分）だ
			for ( int i = 0; i < salesList.size(); i++) {
				sales = SalesDaoObj.selectByIsbn(salesList.get(i).getIsbn());
				sales.setIsbn(Book.getIsbn());
				sales.setQuantity(salesList.get(i).getQuantity());
				objOrderDao.insert(order);
			}

//			各書籍情報と購入数をSaleオブジェクト格納
			for ( int i = 0; i < order_list.size(); i++) {
				bookInfo = new Sale(Book, order_list.get(i).getQuantity());
				bookInfo.setIsbn(order_list.get(i).getIsbn());
				book_list.add(bookInfo);
			}

//			Listに追加し、リクエストスコープに"book_list"という名前で格納
			request.setAttribute("book_list",book_list);
			
			
			//		セッションの"order_list"情報をクリアする
			session.setAttribute("order_list", null);

		}catch( IllegalStateException e ) {
			error = "DB接続エラーの為、購入は出来ません。";
			cmd = "logout";

		}finally {
			if ( error.equals("")) {
				request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}

}
