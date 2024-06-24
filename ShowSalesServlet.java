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

@WebServlet("/showSales")
public class ShowSalesServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			HttpSession session = request.getSession();
			
			User user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れです。";
				cmd = "logout";
				return;
			}
			
			
			ItemsDAO objitemsDao = new ItemsDAO();
			ArrayList<Items> itemlist = objitemsDao.connect();

			
			request.setAttribute("itemlist", itemlist);

		} catch (IllegalStateException e) {
			error = "DB接続エラーのため、売り上げ状況の確認はできません。";
			cmd = "logout";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/showSales.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
