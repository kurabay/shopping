package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//カート登録機能に関する処理をおこなう
@WebServlet("/insertIntoCart")
public class InsertIntoCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{

		String error = "";
		String cmd = "";
		
		//1	セッションから"user"のUserオブジェクトを取得
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			// セッション切れの場合はエラー
			if (user == null) {
				error = "セッション切れの為、カートに追加できません";
				cmd = "logout";
				return;
			}


		//2 isbnのパラメータを取得
			String isbn = (String) request.getParameter("isbn");

		//3	BookDAOをインスタンス化し、書籍情報の検索		
			BookDAO BookDaoObj = new BookDAO();
			Book book = BookDaoObj.selectByIsbn(isbn);

		//4 リクエストスコープに"book"という名前で格納
			request.setAttribute("book",book);
			
		//5 Orderのインスタンスを生成し、各setterメソッドを利用
			Order order = new Order();
			order.setUserid(user.getUserid());
			order.setIsbn(book.getIsbn());
			order.setQuantity(1);

		//6	セッションからorder_listのList配列を取得	
			ArrayList<Order> order_list = (ArrayList<Order>) session.getAttribute("order_list");

			if(order_list == null){
				order_list = new ArrayList<Order>();
			}
			
		//7 OrderオブジェクトをList配列に追加し、セッションスコープに"order_list"という名前で登録
			order_list.add(order);
			session.setAttribute("order_list", order_list);
			
		}catch(IllegalStateException e) {
			error = "DB接続エラーの為、カートに追加は出来ません。";
			cmd = "logout";
			
		//8 エラーの有無でフォワード先を呼び別け
		} finally {
			// � エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// エラーが無い場合はinsertIntoCart.jspにフォワード
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}


//s17
//①セッションから"user"のUserオブジェクトを取得する(セッション切れの場合はエラー)。
//例) User user = (User)session.getAttribute("user");
//※取得したuser変数自身がnullならセッション切れになっていると判断できる

//②isbnのパラメータを取得する。
//例）String isbn = request.getParameter("isbn");
//・jsp画面(Web)からのフォームデータを取得する

//③BookDAOをインスタンス化し、関連メソッドを呼び出す。
//例）Book Book = BookDaoObj.selectByIsbn(isbn);
//・データベースのbookinfoより引数のISBNデータを検索するメソッド

//④③で取得したBookオブジェクトをリクエストスコープに"Book"という名前で格納する。
//例）request.setAttribute("Book",Book);

//⑤Orderのインスタンスを生成し、各setterメソッドを利用し、isbn、userid(ログイン者)、数量(1固定)を設定する。
//例）Order order = new Order();
// order.setIsbn(isbn);

//⑥セッションから"order_list"のList配列を取得する。(取得出来なかった場合はArrayList<Order>配列を新規で作成する)
//例) list = (ArrayList<Order>)session.getAttribute("order_list");
//※取得出来なかった場合とはorder_listがnullの場合
//if(list == null){
//list = new ArrayList<Order>();
//}

//⑦OrderオブジェクトをList配列に追加し、セッションスコープに"order_list"という名前で登録する。
//例）list.add(order);
//session.setAttribute("order_list",list)

//⑧エラーの有無でフォワード先を呼び別ける。
//・エラーが無い場合(正常ルート)はスコープデータに②で取得したBookをinsertIntoCart.jspにフォワードする
//例）request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response)
//・エラーが有る場合(異常ルート)はerror.jspにフォワードする
//例）request.getRequestDispatcher("/view/error.jsp").forward(request, response)