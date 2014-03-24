package my.project.easyrecharge.model;

/**
 * 用户绑定类
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class User {

	// 学校
	private School school;
	// 楼号
	private int buildingNo;
	// 房间号
	private int roomNo;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getSchoolId() {
		return school.getId();
	}

	public String getSchoolName() {
		return school.getName();
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

	// 获取partner id
	public String getPartnerId() {
		return school.getPartnerId();
	}

	// 获取支付宝帐号
	public String getSellerAccount() {
		return school.getSellerAccount();
	}

	// 获取private key
	public String getPrivateKey() {
		return school.getPrivateKey();
	}

	// 获取公钥
	public String getPublicKey() {
		return school.getPublicKey();
	}

}
