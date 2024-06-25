package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Items;
import bean.User;
import dao.ItemsDAO;
import dao.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/buyConfirm")
public class BuyConfirmServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        String cmd = "";
        User user = new User();

        try {
        	
//        	セッション情報の取り出し
            HttpSession session = request.getSession();
            user = (User)session.getAttribute("user");
            ArrayList<Items> itemsList = (ArrayList<Items>)session.getAttribute("itemsList");           
            
            if ( user.getUser_id() == null) {
                throw new IllegalStateException("セッションが存在しません。もう一度ログインしてください。");
            }
                        
            if (itemsList.size() == 0) {
                throw new IllegalStateException("カートが空です。");
            }

            SalesDAO salesDao = new SalesDAO();
            ItemsDAO itemsDao = new ItemsDAO();
            
            request.setAttribute("oldList", itemsList);
            
            for (Items item : itemsList) {
                salesDao.insert(item.getIsbn());
                itemsDao.buyUserUpdate(user.getUser_id(), item.getIsbn());
            }
            
            
//            itemsList.clear();
            session.setAttribute("itemsList", null);
            
            

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
