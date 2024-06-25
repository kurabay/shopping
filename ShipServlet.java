package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Items;
import bean.StatusItem;
import dao.ItemsDAO;
import dao.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.SendMail;

@WebServlet("/ship")
public class ShipServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
//			http://localhost:8080/shopping/ship
			
			//ログイン者のセッション情報を取得
//			HttpSession session = request.getSession();
//			User user = (User)session.getAttribute("user");
			String sample_id = "0001";
			
//			並び替え
			String sort = request.getParameter("ship");
			if (sort == null) {
				sort = "";
			}
			
//			発送ステータス
			String shipping_status = request.getParameter("shipping_status");
			String sales_id = request.getParameter("sales_id");
			if (shipping_status == null) {
				shipping_status = "";
			}
			if (sales_id == null) {
				sales_id = "";
			}
			
			ItemsDAO itemsDao = new ItemsDAO();
			ArrayList<StatusItem> salesList = new ArrayList<StatusItem>();
			
			if (sort.equals("1") || sort.equals("2")) {
//				未発送、発送済によって一覧を変える
				salesList = itemsDao.selectShipUserIdAll(sample_id, sort);				
			}else {
				//ログイン者のidを基に買った商品一覧を取得	
				//itemdaoからログイン者のセッション情報を基に購入一覧を取得する
//			itemDao.selectItemUserAll(user.getUser_id());
//				発送ステータス変更が有る際に一覧を取得する前にステータスを変更する
				if(shipping_status.equals("2") && !sales_id.equals("")) {
					SalesDAO salesDao = new SalesDAO();
					salesDao.shipStatusUpdate(shipping_status, sales_id);
				}
				salesList = itemsDao.selectItemShipUserAll(sample_id);
				
				//発送ステータスが発送済に変更されたらメールを送信する
				Items items = (Items)session.getAttribute("items");
				SendMail sendMail = new SendMail();
				sendMail.setCodeType("iso-2022-jp");
				sendMail.setFromInfo("test.receiver@kanda-it-school-system.com", "test.sender@kanda-it-school-system.com");
				sendMail.setRecipients("test.receiver@kanda-it-school-system.com");
				sendMail.setSubject("商品が発送されました。/ 神田雑貨店");
				sendMail.setText(items.getBuyId() + "様\n\n");
				sendMail.setText("いつも神田雑貨店をご利用いただきありがとうございます。\n");
				sendMail.setText("以下の商品が発送されましたので、ご連絡いたします。\n");
				sendMail.setText(items.getIsbn() + " " + items.getItem_name() + " " + items.getPrice() + "円\n");
				sendMail.setText("商品の到着までしばらくお待ちください。");
				sendMail.forwardMail();
				
			}
			
			request.setAttribute("salesList",salesList);

			} catch (IllegalStateException e) {
			System.out.println(e);
			error = "DB接続エラーのため、ユーザー一覧表示はできません。";
			cmd = "logout";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/ship.jsp").forward(request, response);
			} 
			else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}

}
