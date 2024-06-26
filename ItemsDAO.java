package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Items;
import bean.StatusItem;

public class ItemsDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/shoppingdb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	//フィールド変数の情報を基に、DB接続をおこなうメソッド
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

	// DBの商品情報を格納するitemsinfoテーブルから全商品情報を取得するメソッド
	public ArrayList<Items> selectAll() {

		Connection con = null;
		Statement smt = null;

		ArrayList<Items> itemsList = new ArrayList<Items>();

		//			SQL文を文字列として定義
		String sql = "SELECT * FROM itemsinfo ORDER BY isbn";

		try {
			con = getConnection();
			smt = con.createStatement();

			//			SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//			書籍データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
			while (rs.next()) {
				Items itemsinfo = new Items();
				itemsinfo.setUser_id(rs.getString("user_id"));
				itemsinfo.setIsbn(rs.getString("isbn"));
				itemsinfo.setItem_name(rs.getString("item_name"));
				itemsinfo.setItem_kana(rs.getString("item_kana"));
				itemsinfo.setType(rs.getString("type"));
				itemsinfo.setPrice(rs.getInt("price"));
				itemsinfo.setBuy_id(rs.getString("buy_id"));
				itemsinfo.setRemark(rs.getString("remark"));
				itemsList.add(itemsinfo);
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
		return itemsList;
	}

	//引数で与えられた商品情報を、商品データを格納するitemsinfoテーブルへ登録するメソッド
	public void insert(Items items) {

		Connection con = null;
		Statement smt = null;

		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "INSERT INTO itemsinfo VALUES('" + items.getUser_id() + "','" + items.getIsbn() + "','"
					+ items.getItem_name() + "','" + items.getItem_kana() + "',' "
					+ items.getType() + "'," + items.getPrice() + ",'" + items.getBuy_id() + "','"
					+ items.getRemark() + " ')";

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

	//商品情報を格納するitemsinfoテーブルから、引数で与えられたISBNを持つ書籍データの削除をおこなうメソッド
	public void delete(String isbn) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "DELETE FROM itemsinfo WHERE isbn = '" + isbn + "'";
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

	//引数の商品データを元にDBのitemsinfoテーブルから該当商品データの更新処理を行うメソッド
	public void update(Items items) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "UPDATE itemsinfo SET item_name='" + items.getItem_name() + "',item_kana='"
					+ items.getItem_kana() + "',type=" + items.getType() + "',"
					+ "price=" + items.getPrice() + ",buy_id='" + items.getBuy_id() + "',remark=" + items.getRemark()
					+ " WHERE isbn='" + items.getIsbn()
					+ "'";

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

	//引数のISBNを基にDBの商品情報を格納するitemsinfoテーブルから該当書籍データの検索をおこなうメソッド
	public Items selectByIsbn(String isbn) {

		Items items = new Items();
		Connection con = null;
		Statement smt = null;

		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT user_id,isbn,item_name,item_kana,type,price,buy_id,remark FROM itemsinfo WHERE isbn = '"
					+ isbn + "'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				items.setUser_id(rs.getString("user_id"));
				items.setIsbn(rs.getString("isbn"));
				items.setItem_name(rs.getString("item_name"));
				items.setItem_kana(rs.getString("item_kana"));
				items.setType(rs.getString("type"));
				items.setPrice(rs.getInt("price"));
				items.setBuy_id(rs.getString("buy_id"));
				items.setRemark(rs.getString("remark"));
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
		return items;
	}

	// 引数の各データを基にDBの商品情報を格納するitemsinfoテーブルから該当書籍データの絞込み検索処理をおこなうメソッド
	public ArrayList<Items> search(String user_id, String isbn, String item_name, String item_kana, String type,
			int price, String buy_id, String remark) {
		Connection con = null;
		Statement smt = null;
		ArrayList<Items> itemsList = new ArrayList<Items>();
		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT * FROM itemsinfo WHERE user_id LIKE '%" + user_id + "%' AND isbn LIKE '%" + isbn
					+ "%' AND item_name LIKE '%"
					+ item_name + "%' AND item_kana LIKE '%" + item_kana + "%' AND type LIKE '%" + type
					+ "%' AND price >=" + price + " AND buy_id LIKE '%" + buy_id
					+ "%' AND remark LIKE '%" + remark
					+ "%'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Items items = new Items();
				items.setUser_id(rs.getString("user_id"));
				items.setIsbn(rs.getString("isbn"));
				items.setItem_name(rs.getString("item_name"));
				items.setItem_kana(rs.getString("item_kana"));
				items.setType(rs.getString("type"));
				items.setPrice(rs.getInt("price"));
				items.setBuy_id(rs.getString("buy_id"));
				items.setRemark(rs.getString("remark"));
				itemsList.add(items);
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
		return itemsList;
	}

	/*
	 * 売上情報テーブルと商品情報テーブルをつなげ、
	 * 戻り値はuser_id、item_name、priceが格納されたArrayList
	 */
	public ArrayList<bean.Items> connect() {
		Connection con = null;
		Statement smt = null;
		ArrayList<bean.Items> list = new ArrayList<bean.Items>();

		try {
			con = getConnection();
			smt = con.createStatement();

			//ISBNでitemsinfoとsalesinfoを接続、
			String sql = "SELECT * FROM salesinfo INNER JOIN itemsinfo ON salesinfo.isbn=itemsinfo.isbn";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				bean.Items items = new bean.Items();
				items.setSalesDate(rs.getString("sales_date"));
				items.setUser_id(rs.getString("user_id"));
				items.setItem_name(rs.getString("item_name"));
				items.setPrice(rs.getInt("price"));
				list.add(items);
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
		return list;
	}

	//引数のISBNを基にDBのユーザーが登録した情報を格納するitemsinfoテーブルから該当書籍データの検索をおこなうメソッド
	public ArrayList<Items> selectByUser_id(String user_id) {

		Items items = new Items();
		Connection con = null;
		Statement smt = null;

		ArrayList<Items> itemsList = new ArrayList<Items>();

		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT item_name,type,price FROM itemsinfo WHERE user_id = '" + user_id + "'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				items.setItem_name(rs.getString("item_name"));
				items.setType(rs.getString("type"));
				items.setPrice(rs.getInt("price"));
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
		return itemsList;
	}

	public void buyUserUpdate(String buy_id, String isbn) {
		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "UPDATE itemsinfo SET buy_id='" + buy_id + "' WHERE isbn = '" + isbn + "'";

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

	//ユーザーIDと商品名で検索
	//引数のISBNを基にDBのユーザーが登録した情報を格納するitemsinfoテーブルから該当書籍データの検索をおこなうメソッド
	public ArrayList<Items> search(String user_id, String item_name) {

		Connection con = null;
		Statement smt = null;

		ArrayList<Items> list = new ArrayList<Items>();

		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "SELECT * FROM salesinfo INNER JOIN itemsinfo ON salesinfo.isbn=itemsinfo.isbn WHERE itemsinfo.user_id LIKE '%"
					+ user_id + "%' AND itemsinfo.item_name LIKE '%" + item_name + "%'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				bean.Items items = new bean.Items();
				items.setSalesDate(rs.getString("sales_date"));
				items.setUser_id(rs.getString("user_id"));
				items.setItem_name(rs.getString("item_name"));
				items.setPrice(rs.getInt("price"));
				list.add(items);
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
		return list;
	}

	//userが購入した商品情報を取得
    public ArrayList<StatusItem> selectItemUserAll(String user_id) {
        Connection con = null;
        Statement smt = null;

        ArrayList<StatusItem> itemsList = new ArrayList<StatusItem>();

        //            SQL文を文字列として定義
        String sql = "SELECT * FROM itemsinfo INNER JOIN salesinfo on itemsinfo.isbn = salesinfo.isbn WHERE itemsinfo.buy_id = '"
                + user_id + "' ";
        try {
            con = getConnection();
            smt = con.createStatement();

            //            SQL文を発行し結果セットを取得
            ResultSet rs = smt.executeQuery(sql);

            //            書籍データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
            while (rs.next()) {
                StatusItem status_item = new StatusItem();
                status_item.setIsbn(rs.getString("isbn"));
                status_item.setSalesId(rs.getString("item_name"));
                status_item.setPaymentStatus(rs.getString("payment_status"));
                itemsList.add(status_item);
            }

        } catch (Exception e) {
            System.out.println(e);
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
        return itemsList;
    }




	

	// ログインしているユーザーと配送するユーザーが同じ時に商品情報の全て取得するメソッド(ShipServlet)
	public ArrayList<StatusItem> selectShipUserIdAll(String user_id, String shipping_status) {

		Connection con = null;
		Statement smt = null;

		ArrayList<StatusItem> itemsList = new ArrayList<StatusItem>();

		//			SQL文を文字列として定義
		String sql = "SELECT salesinfo.*, itemsinfo.* FROM `itemsinfo` INNER JOIN salesinfo on itemsinfo.isbn = salesinfo.isbn WHERE itemsinfo.user_id = '"
				+ user_id + "' AND salesinfo.shipping_status = '" + shipping_status + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//			SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//			書籍データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
			while (rs.next()) {
				StatusItem status_item = new StatusItem();
				status_item.setIsbn(rs.getString("isbn"));
				status_item.setSalesId(rs.getString("sales_id"));
				status_item.setShippingStatus(rs.getString("shipping_status"));
				itemsList.add(status_item);
			}

		} catch (Exception e) {
			System.out.println(e);
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
		return itemsList;
	}

	// ログインしているユーザーが発送した商品情報を全て取得するメソッド
	public ArrayList<StatusItem> selectItemShipUserAll(String user_id) {

		Connection con = null;
		Statement smt = null;

		ArrayList<StatusItem> itemsList = new ArrayList<StatusItem>();

		//			SQL文を文字列として定義
		String sql = "SELECT salesinfo.*, itemsinfo.* FROM `itemsinfo` INNER JOIN salesinfo on itemsinfo.isbn = salesinfo.isbn WHERE itemsinfo.user_id = '"
				+ user_id + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//			SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//			書籍データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
			while (rs.next()) {
				StatusItem status_item = new StatusItem();
				status_item.setIsbn(rs.getString("isbn"));
				status_item.setSalesId(rs.getString("sales_id"));
				status_item.setShippingStatus(rs.getString("shipping_status"));
				itemsList.add(status_item);
			}

		} catch (Exception e) {
			System.out.println(e);
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
		return itemsList;
	}

	// ログインしているユーザーと入金するユーザーが同じ時に商品情報の全て取得するメソッド
	public ArrayList<StatusItem> selectUserIdAll(String user_id, String payment_status) {

		Connection con = null;
		Statement smt = null;

		ArrayList<StatusItem> itemsList = new ArrayList<StatusItem>();

		//			SQL文を文字列として定義
		String sql = "SELECT salesinfo.*, itemsinfo.* FROM `itemsinfo` INNER JOIN salesinfo on itemsinfo.isbn = salesinfo.isbn WHERE itemsinfo.buy_id = '"
				+ user_id + "' AND payment_status = '" + payment_status + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			//			SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			//商品データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
			while (rs.next()) {
				StatusItem status_item = new StatusItem();
				status_item.setIsbn(rs.getString("isbn"));
				status_item.setSalesId(rs.getString("item_name"));
				status_item.setPaymentStatus(rs.getString("payment_status"));
				itemsList.add(status_item);
			}

		} catch (Exception e) {
			System.out.println(e);
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
		return itemsList;
	}

}
