package my.project.easyrecharge.model;

/**
 * Recharge order with Bind
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
	public String getSchoolId() {
		return bindInfo.getSchoolId();
	}

	@Override
	public String getSchoolName() {
		return bindInfo.getSchoolName();
	}

	@Override
	public void setApart(Apart apart) {
		bindInfo.setApart(apart);
	}

	@Override
	public String getApartId() {
		return bindInfo.getApartId();
	}

	@Override
	public String getApartName() {
		return bindInfo.getApartName();
	}

	@Override
	public String getRoomNum() {
		return bindInfo.getRoomNo();
	}

	@Override
	public void setRoomNum(String roomNo) {
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

}
