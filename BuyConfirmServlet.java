package servlet;

import java.io.IOException;
import java.util.List;

import bean.Sales;
import dao.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/buyConfirm")
public class BuyConfirmServlet extends HttpServlet {
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
                throw new IllegalStateException("カートが空です。");
            }

            SalesDAO salesDao = new SalesDAO();
            for (Sales sales : cartItems) {
                salesDao.insert(sales);
            }

            cartItems.clear();
            session.setAttribute("salesList", cartItems);

        } catch (IllegalStateException e) {
            error = e.getMessage();
            cmd = "login";
        } catch (Exception e) {
            e.printStackTrace();
            error = "購入処理中にエラーが発生しました。";
            cmd = "showCart";
        } finally {
            if (error.isEmpty()) {
                request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
            } else {
                request.setAttribute("error", error);
                request.setAttribute("cmd", cmd);
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
            }
        }
    }
}
