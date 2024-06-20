package bean;

public class Items {
	
	//ユーザーIDを格納する変数
	private String user_id;
	
	//商品番号を格納する変数
	private String isbn;
	
	//商品名を格納する変数
	private String item_name;
	
	//商品名(かな)を格納する変数
	private String item_kana;
	
	//種類を格納する変数
	private String type;
	
	//値段を格納する変数
	private int price;
	
	//備考を格納する変数
	private String remark;
	
	//コンストラクタ定義
	public Items() {
		this.user_id = null;
		this.isbn = null;
		this.item_name = null;
		this.item_kana = null;
		this.type = null;
		this.price = 0;
		this.remark = null;
	}
	
//	各フィールド変数のアクセサメソッド
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public String getItem_kana() {
		return item_kana;
	}
	public void setItem_kana(String item_kana) {
		this.item_kana = item_kana;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	

}
