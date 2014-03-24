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
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setSchool(School school) {
		user.setSchool(school);
	}

	@Override
	public int getSchoolId() {
		return user.getSchoolId();
	}

	@Override
	public String getSchoolName() {
		return user.getSchoolName();
	}

	@Override
	public int getBuildingNo() {
		return user.getBuildingNo();
	}

	@Override
	public void setBuildingNo(int buildingNo) {
		user.setBuildingNo(buildingNo);
	}

	@Override
	public int getRoomNo() {
		return user.getRoomNo();
	}

	@Override
	public void setRoomNo(int roomNo) {
		user.setRoomNo(roomNo);
	}

	@Override
	public String getPartnerId() {
		return user.getPartnerId();
	}

	@Override
	public String getSellerAccount() {
		return user.getSellerAccount();
	}

	@Override
	public String getPrivateKey() {
		return user.getPrivateKey();
	}

	@Override
	public String getPublicKey() {
		return user.getPublicKey();
	}

}
