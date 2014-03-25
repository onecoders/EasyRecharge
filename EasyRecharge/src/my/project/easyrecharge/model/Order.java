package my.project.easyrecharge.model;

/**
 * 充值订单抽象类
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public abstract class Order {

	protected static final String SUBJECT = "智电宝电费充值";

	// 充值金额
	protected int price;

	public abstract void setSchool(School school);

	// 获取学校id
	public abstract int getSchoolId();

	// 获取学校名称
	public abstract String getSchoolName();

	// 获取楼号
	public abstract int getBuildingNo();

	public abstract void setBuildingNo(int buildingNo);

	// 获取房间号
	public abstract int getRoomNo();

	public abstract void setRoomNo(int roomNo);

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
		return "电费充值详情" + " 学校：" + getSchoolName() + "，楼号：" + getBuildingNo()
				+ "，房间号：" + getRoomNo() + "，充值金额：" + this.price;
	}

	// 获取partner id
	public abstract String getPartnerId();

	// 获取支付宝帐号
	public abstract String getSellerAccount();

	// 获取private key
	public abstract String getPrivateKey();

}
