package my.project.easyrecharge.model;

import my.project.easyrecharge.F;

/**
 * Recharge order
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public abstract class Order {

	protected static final String SUBJECT = "智电宝电费充值";

	// 充值金额
	protected int price;

	public abstract void setSchool(School school);

	public abstract School getSchool();

	// 获取学校id
	public String getSchoolId() {
		return getSchool().getSchoolID();
	}

	// 获取学校名称
	public String getSchoolName() {
		return getSchool().getSchoolName();
	}

	public abstract void setApart(Apart apart);

	public abstract Apart getApart();

	// 获取公寓id
	public String getApartId() {
		return getApart().getApartID();
	}

	// 获取公寓名称
	public String getApartName() {
		return getApart().getApartName();
	}

	// 获取房间号
	public abstract String getRoomNum();

	public abstract void setRoomNum(String roomNum);

	// 设置充值金额
	public void setPrice(int price) {
		this.price = price;
	}

	// 获取充值金额
	public int getPrice() {
		return price;
	}

	// 支付主题（用于支付说明）
	public String getSubject() {
		return SUBJECT;
	}

	// 支付信息描述（用于支付说明）
	public String getBody() {
		OrderInfo info = getOrderInfo();
		return "电费充值详情" + " 学校：" + getSchoolName() + "，楼号：" + getApartName()
				+ "，房间号：" + getRoomNum() + "，充值金额：" + getPrice() + "，参数："
				+ F.toJson(info);
	}

	// 用于商户服务端解析
	private OrderInfo getOrderInfo() {
		OrderInfo info = new OrderInfo();
		info.setSchoolID(getSchoolId());
		info.setApartID(getApartId());
		info.setRoomNum(getRoomNum());
		info.setPrice(getPrice());
		return info;
	}

	// 获取partner id
	public abstract String getPartnerId();

	// 获取支付宝帐号
	public abstract String getSellerAccount();

	// 获取private key
	public abstract String getPrivateKey();

}
