package my.project.easyrecharge.model;

/**
 * 未绑定用户充值订单信息
 * 
 * 用户选择后，设置相应属性
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class OrderWithoutBind extends Order {

	// 学校
	private School school;
	// 楼号
	private int buildingNo;
	// 房间号
	private int roomNo;

	@Override
	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public int getSchoolId() {
		return school.getId();
	}

	@Override
	public String getSchoolName() {
		return school.getName();
	}

	@Override
	public int getBuildingNo() {
		return buildingNo;
	}

	@Override
	public void setBuildingNo(int buildingNo) {
		this.buildingNo = buildingNo;
	}

	@Override
	public int getRoomNo() {
		return roomNo;
	}

	@Override
	public void setRoomNo(int roomNo) {
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
