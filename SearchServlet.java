package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest request ,HttpServletResponse response) 
			throws ServletException ,IOException{
		String error = "";
		String cmd = "";

		try {
			// 文字コードを設定
			request.setCharacterEncoding("UTF-8");
		
			//1 isbn,title,price等の入力パラメータを取得
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");
	
			//2
			BookDAO objDao = new BookDAO();
	
			//3 書籍を検索するメソッドを呼び出し、Bookオブジェクトのリストを取得
			ArrayList<Book> bookList = objDao.search(isbn, title, price);
	
			//4 取得したリストをリクエストスコープに"book_list"という名前で格納
			request.setAttribute("book_list", bookList);
			
		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "menu";
			
		//5	list.jspにフォワード
		}finally {
			// エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// エラーが無い場合はlist.jspにフォワード
				request.getRequestDispatcher("/view/list.jsp").forward(request,response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}

//s13
//①isbn、title、price等の入力パラメータを取得する。
//例）String isbn = request.getParameter("isbn");
//・jsp画面(Web)からのフォームデータを取得する

//②BookDAOをインスタンス化し、関連メソッドを呼び出す。

//③②の結果の戻り値として、Bookオブジェクトのリストを取得する。
//例）ArrayList<Book> list = BookDaoObj.search(isbn, title, price);
//・データベースより引数の条件でbookinfoテーブルのデータを取得するメソッド
//※メソッドの引数が3つの理由は色々なパターンを学ぶためです(Book型のオブジェクトを渡しても構いません)

//④③で取得したListをリクエストスコープに"book_list"という名前で格納する。
//例）request.setAttribute("book_list",list);

//⑤list.jspにフォワードする。
//例）request.getRequestDispatcher("/view/list.jsp").forward(request, response);