package util;

import java.text.DecimalFormat;

public class MyFormat {
	
	public String moneyFormat(int price) {
		String money = null;
		DecimalFormat format = new DecimalFormat();
		format.applyPattern("\\###,###,###,###;###,###,###,###");
		money = format.format(price);
		return money;
	}

}
