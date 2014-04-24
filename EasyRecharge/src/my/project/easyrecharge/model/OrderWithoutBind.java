package my.project.easyrecharge.model;

/**
 * Recharge order Without Bind
 * 
 * 用户选择后，设置相应属性
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class OrderWithoutBind extends Order {

	// 学校
	private School school;
	// 公寓
	private Apart apart;
	// 房间号
	private String roomNum;

	public OrderWithoutBind() {

	}

	public OrderWithoutBind(School school, Apart apart, String roomNum) {
		this.school = school;
		this.apart = apart;
		this.roomNum = roomNum;
	}

	@Override
	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public School getSchool() {
		return this.school;
	}

	@Override
	public void setApart(Apart apart) {
		this.apart = apart;
	}

	@Override
	public Apart getApart() {
		return this.apart;
	}

	@Override
	public String getRoomNum() {
		return roomNum;
	}

	@Override
	public void setRoomNum(String roomNo) {
		this.roomNum = roomNo;
	}

	@Override
	public String getPartnerId() {
		return school.getPartnerId();
	}

	@Override
	public String getSellerAccount() {
		return school.getSellerAccount();
	}

	@Override
	public String getPrivateKey() {
		return school.getPrivateKey();
	}

}
