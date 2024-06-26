package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.StatusItem;
import bean.User;
import dao.ItemsDAO;
import dao.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			//http://localhost:8080/shopping/payment

			//ログイン者のセッション情報を取得
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			String user_id = user.getUser_id();

			//並び替え
			String sort = request.getParameter("payment");
			if (sort == null) {
				sort = "";
			}

			ArrayList<StatusItem> buyList = new ArrayList<StatusItem>();
			ItemsDAO itemsDao = new ItemsDAO();
			if(sort.equals("")) {
				buyList = itemsDao.selectItemUserAll(user_id);
			}else if(sort.equals("1") || sort.equals("2")) {

			//未入金、入金済によって一覧を変える
			buyList = itemsDao.selectUserIdAll(user_id, sort);
			}
			//入金ステータス
			String payment_status = request.getParameter("payment_status");
			String sales_id = request.getParameter("sales_id");
			if (payment_status == null) {
				payment_status = "";
			}
			if (sales_id == null) {
				sales_id = "";
			}

			//ログイン者のidを基に買った商品一覧を取得
			//itemdaoからログイン者のセッション情報を基に購入一覧を取得する
			//itemDao.selectItemUserAll(user.getUser_id());
			//入金ステータス変更が有る際に一覧を取得する前にステータスを変更する
			if (payment_status.equals("2") && !sales_id.equals("")) {
				SalesDAO salesDao = new SalesDAO();
				salesDao.payStatusUpdate(payment_status, sales_id);

				buyList = itemsDao.selectItemUserAll(user_id);
			}

			request.setAttribute("buyList", buyList);

		} catch (IllegalStateException e) {
			System.out.println(e);
			error = "DB接続エラーのため、ユーザー一覧表示はできません。";
			cmd = "logout";
		} catch (Exception e) {
			System.out.println(e);
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/payment.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}

}