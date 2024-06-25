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
import util.SendMail; // インポートが正しいか確認

@WebServlet("/buyConfirm")
public class BuyConfirmServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        String cmd = "";
        User user = new User();

        try {
            // セッション情報の取り出し
            HttpSession session = request.getSession();
            user = (User)session.getAttribute("user");
            ArrayList<Items> itemsList = (ArrayList<Items>)session.getAttribute("itemsList");

            if (user.getUser_id() == null) {
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

            // メール送信
            StringBuilder content = new StringBuilder();
            content.append(user.getUser_nickname()).append("様,\n\n以下の商品の購入が確定しました。\n\n");
            for (Items item : itemsList) {
                content.append("ISBN: ").append(item.getIsbn()).append("\n");
                content.append("商品名: ").append(item.getItem_name()).append("\n");
                content.append("価格: ").append(item.getPrice()).append("\n\n");
            }
            content.append("ご利用ありがとうございました。");

            SendMail sendMail = new SendMail();
            sendMail.setCodeType("UTF-8");
            sendMail.setFromInfo("test.receiver@kanda-it-school-system.com", "神田雑貨店");
            sendMail.setRecipients(user.getMail());
            sendMail.setSubject("購入確認");
            sendMail.setText(content.toString());
            sendMail.forwardMail();

            // カートをクリア
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
