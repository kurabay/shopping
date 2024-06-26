package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insertUser")
public class InsertUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // insertUser.jspにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/insertUser.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // フォームからのデータを取得
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        String user_nickname = request.getParameter("user_nickname");
        String user_address = request.getParameter("user_address");
        String mail = request.getParameter("mail");
        String phone_num = request.getParameter("phone_num");
        String password = request.getParameter("password");
        String authority = "";

        UserDAO userDAO = new UserDAO();

        // ユーザーIDの重複を確認
        if (userDAO.isUserIdExists(user_id)) {
            request.setAttribute("errorMessage", "ユーザーIDは既に存在します。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/insertUser.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // ユーザーオブジェクトを作成
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_name(user_name);
        user.setUser_nickname(user_nickname);
        user.setUser_address(user_address);
        user.setMail(mail);
        user.setPhone_num(phone_num);
        user.setPassword(password);
        user.setAuthority(authority);

        // データベースに接続してユーザー情報を保存
        int result = userDAO.insert(user);

        if (result > 0) {
            response.sendRedirect("view/login.jsp");
        } else {
            request.setAttribute("errorMessage", "ユーザーの作成に失敗しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/insertUser.jsp");
            dispatcher.forward(request, response);
        }
    }
}
