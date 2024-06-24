package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.User;

public class UserDAO {

	//データベース接続
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/shoppingdb";
	private static String USER = "root";
	private static String PASS = "root123";

	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//ユーザーIDが合致するユーザー情報を取得
	public User selectByUser(String user_id) {

		User user = new User();

		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();

			//検索用sql文
			String sql = "SELECT * FROM userinfo WHERE user_id ='" + user_id + "'";

			ResultSet rs = smt.executeQuery(sql);

			user.setUser_id(rs.getString("user_id"));

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignre) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return user;
	}

	//ユーザーIDとパスワードの条件に合致する情報を取得
	public User selectByUser(String user_id, String password) {
		User user = new User();

		Connection con = null;
		Statement smt = null;
		try {
			con = getConnection();
			smt = con.createStatement();

			//検索用sql文
			String sql = "SELECT * FROM userinfo WHERE user_id ='" + user_id + "' AND password='" + password + "'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUser_id(rs.getString("user_id"));
				user.setPassword(rs.getString("password"));
				user.setAuthority(rs.getString("authority"));

			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignre) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return user;
	}

	//新規登録
	public int insert(User user) {
		int count;

		Connection con = null;
		Statement smt = null;

		try {

			con = getConnection();
			smt = con.createStatement();

			String sql = "INSERT INTO userinfo VALUES('" + user.getUser_id() + "','" + user.getUser_name() + "','"
					+ user.getUser_nickname() + "','" + user.getUser_address() + "','" + user.getMail() + "','"
					+ user.getPhone_num() + "','" + user.getPassword() + "','"
					+ user.getAuthority() + "')";
			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return count;
	}

	//ユーザーデータの更新(件数)
	public int update(User user) {
		int count;

		Connection con = null;
		Statement smt = null;

		try {
			con = getConnection();
			smt = con.createStatement();

			//更新用SQL文
			String sql = "UPDATE userinfo SET user_name='" + user.getUser_name() + "',user_nickname='"
					+ user.getUser_nickname() + "',user_address='" + user.getUser_address() + "',mail='"
					+ user.getMail() + "',phone_num='" + user.getPhone_num() + "',password='" + user.getPassword() +
					"',authority='" + user.getAuthority() + "' WHERE user='" + user.getUser_id() + "'";

			count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignre) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return count;
	}
	
	//ユーザーデータ更新(更新データ表示）
		public ArrayList<User> search(User user) {
			ArrayList<User> userlist = new ArrayList<User>();

			Connection con = null;
			Statement smt = null;

			try {
				con = getConnection();
				smt = con.createStatement();

				//更新用SQL文
				String sql = "UPDATE userinfo SET user_name='" + user.getUser_name() + "',user_nickname='"
						+ user.getUser_nickname() + "',user_address='" + user.getUser_address() + "',mail='"
						+ user.getMail() + "',phone_num='" + user.getPhone_num() + "',password='" + user.getPassword() +
						"',authority='" + user.getAuthority() + "' WHERE user='" + user.getUser_id() + "'";
				ResultSet rs = smt.executeQuery(sql);

				while (rs.next()) {
					User userupdate = new User();

					userupdate.setUser_name(rs.getString("user_name"));
					userupdate.setUser_nickname(rs.getString("user_nickname"));
					userupdate.setUser_address(rs.getString("user_address"));
					userupdate.setMail(rs.getString("mail"));
					userupdate.setPhone_num(rs.getString("phone_num"));
					userupdate.setPassword(rs.getString("password"));
					userupdate.setAuthority(rs.getString("authority"));

					userlist.add(userupdate);

				}

			} catch (Exception e) {
				throw new IllegalStateException(e);
			} finally {
				if (smt != null) {
					try {
						smt.close();
					} catch (SQLException ignre) {
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException ignore) {
					}
				}
			}
			return userlist;
		}
		
	//全ユーザー情報を取得
        public ArrayList<User> selectAll() {
            ArrayList<User> userlist = new ArrayList<User>();

            Connection con = null;
            Statement smt = null;
            try {
                con = getConnection();
                smt = con.createStatement();

                //検索用sql文
                String sql = "SELECT * FROM userinfo";

                ResultSet rs = smt.executeQuery(sql);

                while (rs.next()) {
                    User user = new User();
                    user.setUser_id(rs.getString("user_id"));
                    user.setUser_nickname(rs.getString("user_nickname"));
                    user.setAuthority(rs.getString("authority"));

                    userlist.add(user);
                }

            } catch (Exception e) {
                throw new IllegalStateException(e);
            } finally {
                if (smt != null) {
                    try {
                        smt.close();
                    } catch (SQLException ignre) {
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ignore) {
                    }
                }
            }
            return userlist;
        }	
		
		//ユーザーデータ検索
		public ArrayList<User> search(String user_id) {
			ArrayList<User> list = new ArrayList<User>();

			Connection con = null;
			Statement smt = null;

			try {
				con = getConnection();
				smt = con.createStatement();

				//検索用SQL文
				String sql = "SELECT * FROM userinfo WHERE user_id LIKE '%" + user_id + "%'";
				ResultSet rs = smt.executeQuery(sql);

				while (rs.next()) {
					User user = new User();
					user.setUser_id(rs.getString("user_id"));

					list.add(user);
				}
			} catch (Exception e) {
				throw new IllegalStateException(e);
			} finally {
				if (smt != null) {
					try {
						smt.close();
					} catch (SQLException ignre) {
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
		
		//ユーザーデータ削除用
		public int delete(String user_id) {
			int count;
			
			Connection con = null;
			Statement smt = null;

			try {
				con = getConnection();
				smt = con.createStatement();

				//削除用SQL文
				String sql = "DELETE FROM userinfo WHERE user_id = '"+user_id+"'";

				count = smt.executeUpdate(sql);

			} catch (Exception e) {
				throw new IllegalStateException(e);
			} finally {
				if (smt != null) {
					try {
						smt.close();
					} catch (SQLException ignre) {
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException ignore) {
					}
				}
			}
			return count;
		}
		
		//ユーザーID、ニックネーム、権限の条件に合致する情報を取得
	    public ArrayList<User> search(String user_id, String user_nickname, String authority) {
	        ArrayList<User> list = new ArrayList<User>();

	        Connection con = null;
	        Statement smt = null;
	        try {
	            con = getConnection();
	            smt = con.createStatement();

	            //検索用sql文
	            String sql = "SELECT * FROM userinfo WHERE user_id ='" + user_id + "' AND user_nickname='" + user_nickname
	                    + "' AND authority='" + authority + "'";

	            ResultSet rs = smt.executeQuery(sql);

	            while (rs.next()) {
	                User user = new User();
	                user.setUser_id(rs.getString("user_id"));
	                user.setPassword(rs.getString("user_nickname"));
	                user.setAuthority(rs.getString("authority"));

	                list.add(user);
	            }
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        } finally {
	            if (smt != null) {
	                try {
	                    smt.close();
	                } catch (SQLException ignre) {
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

}