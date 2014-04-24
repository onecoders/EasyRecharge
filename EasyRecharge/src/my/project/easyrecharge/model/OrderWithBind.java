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

	public OrderWithBind() {

	}

	public OrderWithBind(BindInfo bindInfo) {
		this.bindInfo = bindInfo;
	}

	public void setBindInfo(BindInfo bindInfo) {
		this.bindInfo = bindInfo;
	}

	@Override
	public void setSchool(School school) {
		bindInfo.setSchool(school);
	}

	@Override
	public School getSchool() {
		return bindInfo.getSchool();
	}

	@Override
	public void setApart(Apart apart) {
		bindInfo.setApart(apart);
	}

	@Override
	public Apart getApart() {
		return bindInfo.getApart();
	}

	@Override
	public String getRoomNum() {
		return bindInfo.getRoomNum();
	}

	@Override
	public void setRoomNum(String roomNum) {
		bindInfo.setRoomNum(roomNum);
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
