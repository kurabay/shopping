package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Items;

public class ItemsDAO {
	
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/mybookdb";
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
			String sql = "SELECT user_id,isbn,item_name,item_kana,type,price,remark FROM itemsinfo ORDER BY isbn";

			try {
				con = getConnection();
				smt = con.createStatement();

				//			SQL文を発行し結果セットを取得
				ResultSet rs = smt.executeQuery(sql);

				//			書籍データを検索件数分全て取り出し、AllayListオブジェクトにBookオブジェクトとして格納
				while(rs.next()) {
					Items itemsinfo = new Items();
					itemsinfo.setUser_id(rs.getString("user_id"));
					itemsinfo.setIsbn(rs.getString("isbn"));
					itemsinfo.setItem_name(rs.getString("items_name"));
					itemsinfo.setItem_kana(rs.getString("items_kana"));
					itemsinfo.setType(rs.getString("type"));
					itemsinfo.setPrice(rs.getInt("price"));
					itemsinfo.setRemark(rs.getString("remark"));
					itemsList.add(itemsinfo);
				}

			}catch(Exception e) {
				throw new IllegalStateException(e);
			}finally {
				if( smt != null) {
					try {smt.close();}catch(SQLException ignore) {}
				}
				if( con != null ) {
					try {con.close();}catch(SQLException ignore) {}
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
				String sql = "INSERT INTO itemsinfo VALUES('"+items.getUser_id()+"','"+items.getIsbn()+"','"+items.getItem_name()+"',"
						+ ""+items.getItem_kana()+", "+""+items.getItem_kana()+", "+""+items.getType()+","+""+items.getPrice()+","+""+items.getRemark()+" )";

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
				String sql = "DELETE FROM itemsinfo WHERE isbn = '"+ isbn +"'";
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
				String sql = "UPDATE itemsinfo SET item_name='"+items.getItem_name()+"',item_kana="+items.getItem_kana()+"',type="+items.getType()+"',"
						+ "price="+items.getPrice()+"',remark="+items.getRemark()+" WHERE isbn='"+items.getIsbn()+"'";

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
				String sql = "SELECT user_id,isbn,item_name,item_kana,type,price,remark FROM itemsinfo WHERE isbn = '" + isbn + "'";

				ResultSet rs = smt.executeQuery(sql);

				while (rs.next()) {
					items.setUser_id(rs.getString("user_id"));
					items.setIsbn(rs.getString("isbn"));
					items.setItem_name(rs.getString("item_name"));
					items.setItem_kana(rs.getString("item_kana"));
					items.setType(rs.getString("type"));
					items.setPrice(rs.getInt("price"));
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
		public ArrayList<Items> search(String user_id,String isbn, String item_name, String item_kana, String type, String price, String remark) {
			Connection con = null;
			Statement smt = null;
			ArrayList<Items> itemsList = new ArrayList<Items>();
			try {
				con = getConnection();
				smt = con.createStatement();

				String sql = "SELECT user_id,isbn,item_name,item_kana,type,price,remark FROM itemsinfo " +
						"WHERE user_id LIKE '%" + user_id + "%' AND isbn LIKE '%" + isbn + "%' AND item_name LIKE '%" + item_name + "%' AND item_kana LIKE '%" + item_kana + "%'"
								+ "AND type LIKE '%" + type + "%' AND price LIKE '%" + price + "%' AND remark LIKE '%" + remark + "%'";

				ResultSet rs = smt.executeQuery(sql);

				while (rs.next()) {
					Items items = new Items();
					items.setUser_id(rs.getString("user_id"));
					items.setIsbn(rs.getString("isbn"));
					items.setItem_name(rs.getString("item_name"));
					items.setItem_kana(rs.getString("item_kana"));
					items.setType(rs.getString("type"));
					items.setPrice(rs.getInt("price"));
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

		/*売上情報テーブルと商品情報テーブルをつなげ、
		引数isbn、戻り値はuser_id、item_name、priceが格納されたArrayList*/
  		public ArrayList<bean.Items>  search(String isbn){
     			Connection con = null;
        		Statement smt = null;
        		ArrayList<bean.Items> list = new ArrayList<bean.Items>();
			
        		try {
            		con = getConnection();
            		smt = con.createStatement();
            		String sql = "SELECT user_id FROM salesinfo INNER JOIN iteminfo ON salesinfo."+isbn+"=iteminfo.isbn";
            		ResultSet rs = smt.executeQuery(sql);
            		while(rs.next()) {
                		bean.Items items = new bean.Items();
                		items.setUser_id(rs.getString("user_id"));
                		items.setItem_name(rs.getString("item_name"));
                		items.setPrice(rs.getInt("price"));
                		list.add(items);
			} catch (Exception e) {
            		throw new IllegalStateException(e);
			} finally {
            		if (smt != null) {
                		try {
                    		smt.close();
				} catch (SQLException ignore) {}
				}if (con != null) {
               		 		try {
                    				con.close();} catch (SQLException ignore) {}}
					}return list;
			}

}
