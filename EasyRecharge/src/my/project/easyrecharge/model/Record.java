package my.project.easyrecharge.model;

/**
 * Recharge Record
 * 
 * 根据 学校 & 楼号 & 房间号 查询，以list数据返回，解析后以ListView显示
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class Record {

	// 充值日期
	private String date;
	// 充值用户名
	private String userName;
	// 充值金额
	private String price;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
