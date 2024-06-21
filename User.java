package bean;

public class User {
	//フィールド変数定義
		private String user_id; //ユーザー名
		private String user_name; //ユーザーの名前
		private String user_nickname; //ユーザーのニックネーム
		private String user_address; //ユーザーの住所
		private String mail; //メールアドレス
		private String phone_num; //電話番号
		private String password; //パスワード
		private String authority; //権限（1:一般ユーザー、2:管理者）

		//コンストラクタ定義
		public User() {
			this.user_id = null;
			this.user_name = null;
			this.user_nickname = null;
			this.user_address = null;
			this.mail = null;
			this.phone_num = null;
			this.password = null;
			this.authority = null;
		}
		
		public String getUser_id() {
			return user_id;
		}
		
		public String getUser_name() {
			return user_name;
		}
		
		public String getUser_nickname() {
			return user_nickname;
		}
		
		public String getUser_address() {
			return user_address;
		}
		
		public String getMail() {
			return mail;
		}
		
		public String getPhone_num() {
			return phone_num;
		}
		
		public String getPassword() {
			return password;
		}
		
		public String getAuthority() {
			return authority;
		}
		
		
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		
		public void setUser_nickname(String user_nickname) {
			this.user_nickname = user_nickname;
		}
		
		public void setUser_address(String user_address) {
			this.user_address = user_address;
		}
		
		public void setMail(String mail) {
			this.mail = mail;
		}
		
		public void setPhone_num(String phone_num) {
			this.phone_num = phone_num;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public void setAuthority(String authority) {
			this.authority = authority;
		}
		
		

}
