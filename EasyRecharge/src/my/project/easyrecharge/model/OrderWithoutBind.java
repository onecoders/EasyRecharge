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
	private String roomNo;

	@Override
	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String getSchoolId() {
		return school.getSchoolID();
	}

	@Override
	public String getSchoolName() {
		return school.getSchoolName();
	}

	@Override
	public void setApart(Apart apart) {
		this.apart = apart;
	}

	@Override
	public String getApartId() {
		return apart.getApartID();
	}

	@Override
	public String getApartName() {
		return apart.getApartName();
	}

	@Override
	public String getRoomNum() {
		return roomNo;
	}

	@Override
	public void setRoomNum(String roomNo) {
		this.roomNo = roomNo;
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
