package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.StatusItem;
import dao.ItemsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			
			//ログイン者のセッション情報を取得
//			HttpSession session = request.getSession();
//			User user = (User)session.getAttribute("user");
			String sample_id = "1001";
			
			// ログイン者のidを基に買った商品一覧を取得
			ItemsDAO itemDao = new ItemsDAO();
			
			//itemdaoからログイン者のセッション情報を基に購入一覧を取得する
//			itemDao.selectItemUserAll(user.getUser_id());
			ArrayList<StatusItem> buyList = itemDao.selectItemUserAll(sample_id);
			
			request.setAttribute("buyList", buyList);

		} catch (IllegalStateException e) {
			System.out.println(e);
			error = "DB接続エラーのため、ユーザー一覧表示はできません。";
			cmd = "logout";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/payment.jsp").forward(request, response);
			} 
//			else {
//				request.setAttribute("cmd", cmd);
//				request.setAttribute("error", error);
//				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
//			}

		}
	}

}
