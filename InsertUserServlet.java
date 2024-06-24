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
        String authority = request.getParameter("authority");

        // デバッグのためにフォームデータを出力
        System.out.println("user_id: " + user_id);
        System.out.println("user_name: " + user_name);
        System.out.println("user_nickname: " + user_nickname);
        System.out.println("user_address: " + user_address);
        System.out.println("mail: " + mail);
        System.out.println("phone_num: " + phone_num);
        System.out.println("password: " + password);
        System.out.println("authority: " + authority);

        // フォームデータの検証
        if (user_id == null || user_id.isEmpty()) {
            response.sendRedirect("insertUser.jsp?error=User ID is required.");
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
        UserDAO userDAO = new UserDAO();
        int result = userDAO.insert(user);

        if (result > 0) {
            response.sendRedirect("view/login.jsp");
        } else {
            response.sendRedirect("insertUser.jsp?error=Failed to create user.");
        }
    }
}
