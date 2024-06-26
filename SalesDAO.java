package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesDAO{

	//JDBCドライバ内部のDriverクラスパス
	private static final String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	// 接続するMySQLデータベースパス
	private static final String URL = "jdbc:mariadb://localhost/shoppingdb";
	// データベースのユーザー名
	private static final String USER = "root";
	// データベースのパスワード
	private static final String PASSWD = "root123";

	
	// フィールド変数の情報を基に、DB接続をおこなうメソッド
	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(RDB_DRIVE);
			con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	
	//全売上情報のリスト
	public ArrayList<bean.Sales> selectAll(){
		Connection con = null;
		Statement smt = null;
		ArrayList<bean.Sales>salesList = new ArrayList<bean.Sales>();
		
		try {
			con = getConnection();
			smt = con.createStatement();
			
			String sql = "SELECT * FROM salesinfo ORDER BY salesId";
			ResultSet rs = smt.executeQuery(sql);
			
			while(rs.next()) {
				bean.Sales sales = new bean.Sales();
				sales.setSalesId(rs.getInt("salesId"));
				sales.setSalesDate(rs.getString("salesDate"));
				sales.setIsbn(rs.getString("isbn"));
				sales.setShippinStatus(rs.getString("shippingStatus"));
				sales.setPaymentStatus(rs.getString("paymentStatus"));
				salesList.add(sales);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return salesList;
	}
	
	//売上情報をsalesinfoテーブルへ格納する
	public void insert(bean.Sales sales) {
		String sql = "INSERT INTO salesinfo VALUES('" + sales.getSalesId() + "','" + sales.getSalesDate() +"','" + sales.getIsbn() + "','" + sales.getShippingStatus() + "',"
				+ sales.getPaymentStatus() + ")";
		Connection con = null;
		Statement smt = null;
		
		try {
			con = getConnection();
			smt = con.createStatement();
			
			smt.executeUpdate(sql);
		
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		}
		
		//buy_idと、入金・発送状況をsalesinfoテーブルへ格納する
	public void insert(String isbn) {
		String sql = "";
		Connection con = null;
		Statement smt = null;
		
		try {
			con = getConnection();
			smt = con.createStatement();
			
			sql = "INSERT INTO salesinfo VALUES('', CURRENT_DATE ,'" + isbn + "','1', '1')";
			smt.executeUpdate(sql);
		
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}

	

	}
	//引数の商品データを元にDBのsalesinfoテーブルから該当商品データの入金状況更新処理を行うメソッド
	public void payStatusUpdate (String payment_status, String isbn ) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "UPDATE salesinfo SET payment_status='" + payment_status + "' WHERE sales_id = '" + isbn + "'";

			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
//
//	
//	// ログインしているユーザーが発送した商品情報を全て取得するメソッド
//	public ArrayList<StatusItem> selectItemShipUserAll(String user_id) {
//		
//		Connection con = null;
//		Statement smt = null;
//		
//		ArrayList<StatusItem> itemsList = new ArrayList<StatusItem>();
//		
//		//			SQL文を文字列として定義
//		String sql = "SELECT salesinfo.*, itemsinfo.* FROM `itemsinfo` INNER JOIN salesinfo on itemsinfo.isbn = salesinfo.isbn WHERE itemsinfo.user_id = '" + user_id + "'";
//		
//		try {
//			con = getConnection();
//			smt = con.createStatement();
//			
//			//			SQL文を発行し結果セットを取得
//			ResultSet rs = smt.executeQuery(sql);
//			
//			//			書籍データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
//			while(rs.next()) {
//				StatusItem status_item = new StatusItem();
//				status_item.setIsbn(rs.getString("user_id"));
//				status_item.setSalesId(rs.getString("sales_id"));
//				status_item.setShippingStatus(rs.getString("shipping_status"));
//				itemsList.add(status_item);
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e);
//			throw new IllegalStateException(e);
//		}finally {
//			if( smt != null) {
//				try {smt.close();}catch(SQLException ignore) {}
//			}
//			if( con != null ) {
//				try {con.close();}catch(SQLException ignore) {}
//			}
//		}
//		return itemsList;
//	}
//	
//	// ログインしているユーザーと配送するユーザーが同じ時に商品情報の全て取得するメソッド(ShipServlet)
//	public ArrayList<StatusItem> selectShipUserIdAll(String user_id, String shipping_status) {
//		
//		Connection con = null;
//		Statement smt = null;
//		
//		ArrayList<StatusItem> itemsList = new ArrayList<StatusItem>();
//		
//		//			SQL文を文字列として定義
//		String sql = "SELECT salesinfo.*, itemsinfo.* FROM `itemsinfo` INNER JOIN salesinfo on itemsinfo.isbn = salesinfo.isbn WHERE itemsinfo.buy_id = '" + user_id + "' AND shipping_status = '" + shipping_status + "'";
//		
//		try {
//			con = getConnection();
//			smt = con.createStatement();
//			
//			//			SQL文を発行し結果セットを取得
//			ResultSet rs = smt.executeQuery(sql);
//			
//			//			書籍データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
//			while(rs.next()) {
//				StatusItem status_item = new StatusItem();
//				status_item.setIsbn(rs.getString("user_id"));
//				status_item.setSalesId(rs.getString("sales_id"));
//				status_item.setShippingStatus(rs.getString("shipping_status"));
//				itemsList.add(status_item);
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e);
//			throw new IllegalStateException(e);
//		}finally {
//			if( smt != null) {
//				try {smt.close();}catch(SQLException ignore) {}
//			}
//			if( con != null ) {
//				try {con.close();}catch(SQLException ignore) {}
//			}
//		}
//		return itemsList;
//	}
//
//	
	//引数の商品データを元にDBのsalesinfoテーブルから該当商品データの発送状況の更新処理を行うメソッド
	public void shipStatusUpdate (String shipping_status, String sales_id ) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "UPDATE salesinfo SET shipping_status='" + shipping_status + "' WHERE sales_id = '" + sales_id + "'";

			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
		
	//絞り込み検索処理
	public ArrayList<bean.Sales> search(int salesId,  String salesDate, String isbn, String shippingStatus, String paymentStatus){
		Connection con = null;
		Statement smt = null;
		ArrayList<bean.Sales> salesList = new ArrayList<bean.Sales>();
		
		try {
			con = getConnection();
			smt = con.createStatement();
			
			String sql = "SELECT * FROM salesinfo WHERE salesId LIKE '%" + salesId
					+ "%' AND salesDate LIKE '%" + salesDate + "%' AND isbn LIKE '%" + isbn + "%' AND shippingStatus LIKE '%"
					+ shippingStatus + "%'AND paymentStatus LIKE '%" + paymentStatus + "%";
			
			ResultSet rs = smt.executeQuery(sql);
			
			while(rs.next()) {
				bean.Sales sales = new bean.Sales();
				sales.setSalesId(rs.getInt(salesId));
				sales.setSalesDate(rs.getString("salesDate"));
				sales.setIsbn(rs.getString("isbn"));
				sales.setShippinStatus(rs.getString("shippingStatus"));
				sales.setPaymentStatus(rs.getString("paymentStatus"));
				salesList.add(sales);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return salesList;
	}

}
	
	
