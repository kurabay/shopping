package bean;

public class StatusItem {
	
	//商品番号を格納する変数
	private String isbn;
	
	//商品名を格納する変数
	private String sales_id;
	
	//入金状況
	private String payment_status;
	
	//発送状況
	private String shipping_status;
		
	
	//コンストラクタ定義
	public StatusItem() {
		this.isbn = null;
		this.sales_id = null;
		this.payment_status = null;
		this.shipping_status = null;
	}
	
	//	各フィールド変数のアクセサメソッド
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getSalesId() {
		return sales_id;
	}
	public void setSalesId(String sales_id) {
		this.sales_id = sales_id;
	}
	
	public String getPaymentStatus() {
		return payment_status;
	}
	public void setPaymentStatus(String payment_status) {
		this.payment_status = payment_status;
	}
	
	public String getShippingStatus() {
		return shipping_status;
	}
	public void setShippingStatus(String shipping_status) {
		this.shipping_status = shipping_status;
	}


}