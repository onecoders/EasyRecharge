package my.project.easyrecharge.model;

/**
 * 绑定用户充值订单信息
 * 
 * 用户绑定后，保存User属性
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class OrderWithBind extends Order {

	// 绑定用户
	private BindInfo bindInfo;

	public void setUser(BindInfo Binder) {
		this.bindInfo = Binder;
	}

	@Override
	public void setSchool(School school) {
		bindInfo.setSchool(school);
	}

	@Override
	public int getSchoolId() {
		return bindInfo.getSchoolId();
	}

	@Override
	public String getSchoolName() {
		return bindInfo.getSchoolName();
	}

	@Override
	public int getBuildingNo() {
		return bindInfo.getBuildingNo();
	}

	@Override
	public void setBuildingNo(int buildingNo) {
		bindInfo.setBuildingNo(buildingNo);
	}

	@Override
	public int getRoomNo() {
		return bindInfo.getRoomNo();
	}

	@Override
	public void setRoomNo(int roomNo) {
		bindInfo.setRoomNo(roomNo);
	}

	@Override
	public String getPartnerId() {
		return bindInfo.getPartnerId();
	}

	@Override
	public String getSellerAccount() {
		return bindInfo.getSellerAccount();
	}

	@Override
	public String getPrivateKey() {
		return bindInfo.getPrivateKey();
	}

	@Override
	public String getPublicKey() {
		return bindInfo.getPublicKey();
	}

}