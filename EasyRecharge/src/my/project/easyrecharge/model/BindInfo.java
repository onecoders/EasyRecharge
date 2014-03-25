package my.project.easyrecharge.model;

/**
 * 用户绑定类
 * 
 * 保存到本地，进入应用读取
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class BindInfo {

	// 学校
	private School school;
	// 楼号
	private int buildingNo;
	// 房间号
	private int roomNo;
	// 是否绑定
	private boolean isBind;

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

	public boolean isBind() {
		return isBind;
	}

	public void setBind(boolean isBind) {
		this.isBind = isBind;
	}

	public void updateBindInfo(BindInfo bindInfo) {
		setSchool(bindInfo.getSchool());
		setBuildingNo(bindInfo.getBuildingNo());
		setRoomNo(bindInfo.getRoomNo());
		setBind(bindInfo.isBind());
	}

}
