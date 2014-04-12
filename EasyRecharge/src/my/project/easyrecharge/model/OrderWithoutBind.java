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
	// 楼号
	private String buildingNo;
	// 房间号
	private String roomNo;

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
	public String getBuildingNo() {
		return buildingNo;
	}

	@Override
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	@Override
	public String getRoomNo() {
		return roomNo;
	}

	@Override
	public void setRoomNo(String roomNo) {
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
