package my.project.easyrecharge.model;

public class Order {

	private static final String SUBJECT = "智电宝电费充值";

	// 学校
	private School school;
	// 楼号
	private int buildingNo;
	// 房间号
	private int roomNo;
	// 充值金额
	private int price;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(int buildingNo) {
		this.buildingNo = buildingNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// 支付主题（用于支付说明）
	public String getSubject() {
		return SUBJECT;
	}

	// 支付信息描述（用于支付说明）
	public String getBody() {
		return "电费充值详情" + " 学校：" + this.school.getName() + "，楼号："
				+ this.buildingNo + "，房间号：" + this.roomNo + "，充值金额："
				+ this.price;
	}

}
