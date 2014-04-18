package my.project.easyrecharge.model;

/**
 * Bind Info
 * 
 * 保存到本地，进入应用读取
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class BindInfo {

	// 学校
	private School school;
	// 公寓
	private Apart apart;
	// 房间号
	private String roomNo;
	// 是否绑定
	private boolean isBind;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getSchoolId() {
		return school.getSchoolID();
	}

	public String getSchoolName() {
		return school.getSchoolName();
	}

	public Apart getApart() {
		return apart;
	}

	public void setApart(Apart apart) {
		this.apart = apart;
	}

	public String getApartId() {
		return apart.getApartID();
	}

	public String getApartName() {
		return apart.getApartName();
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
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
		setApart(bindInfo.getApart());
		setRoomNo(bindInfo.getRoomNo());
		setBind(bindInfo.isBind());
	}

}
