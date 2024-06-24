package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Sales;
import dao.ItemsDAO;
import dao.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShowHistoryOrderedItemServlet")
public class ShowHistoryOrderedItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        String cmd = "";

        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                throw new IllegalStateException("セッションが存在しません。もう一度ログインしてください。");
            }

            @SuppressWarnings("unchecked")
            List<Sales> cartItems = (List<Sales>) session.getAttribute("salesList");
            if (cartItems == null || cartItems.isEmpty()) {
                throw new IllegalStateException("購入したアイテムはありません。");
            }
            
          //DAOをインスタンス化し、関連メソッドをsales_listのカート追加データ分だけ呼び出す
			SalesDAO salesDaoObj = new SalesDAO();
			ItemsDAO itemsDaoObj = new ItemsDAO();

			ArrayList<Sales> sales_list = new ArrayList<Sales>();

			sales_list = itemsDaoObj.selectAllSales();
		
			
			//取得した各ItemをListに追加し、リクエストスコープに"sales_list"という名前で格納
			request.setAttribute("sales_list", sales_list);

        } catch (IllegalStateException e) {
            error = e.getMessage();
            cmd = "login";
        } finally {
            if (error.isEmpty()) {
                request.getRequestDispatcher("/view/ShowHistoryOrderedItemServlet.jsp").forward(request, response);
            } else {
                request.setAttribute("error", error);
                request.setAttribute("cmd", cmd);
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
            }
        }
    }
}

//cartItems.clear();
//session.setAttribute("salesList", cartItems);