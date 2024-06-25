package servlet;

import java.io.IOException;
import java.util.List;

import bean.Sales;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/showCart")
public class ShowCartServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    
        String error = "";
        String cmd = "";

        try {
            HttpSession session = request.getSession();
            List<Sales> cartItems = null;
            
            Object salesListObj = session.getAttribute("salesList");
            if (salesListObj instanceof List<?>) {
                cartItems = (List<Sales>) salesListObj;
            }

            // カートの商品のリストをリクエスト属性として設定する
            request.setAttribute("cartItems", cartItems);

        } catch (Exception e) {
            e.printStackTrace();
            error = "カートの表示中にエラーが発生しました。";
            cmd = "showCart";
        } finally {
            // エラーハンドリング
            if (error.isEmpty()) {
                // エラーがなければカート表示ページにフォワードする
                request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
            } else {
                // エラーがあればエラーページにフォワードする
                request.setAttribute("error", error);
                request.setAttribute("cmd", cmd);
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
            }
        }
    }
}
