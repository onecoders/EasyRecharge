package my.project.easyrecharge.model;

/**
 * 用户选择学校信息
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class School {

	// 学校id，唯一
	private int id;
	// 学校名称
	private String name;
	// 学校校徽图片地址
	private String icon;
	// 学校对应的支付宝信息
	private AlipayInfo alipayInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public AlipayInfo getAlipayInfo() {
		return alipayInfo;
	}

	public void setAlipayInfo(AlipayInfo alipayInfo) {
		this.alipayInfo = alipayInfo;
	}

	public String getPartnerId() {
		return alipayInfo.getPartnerId();
	}

	public void setPartnerId(String partnerId) {
		alipayInfo.setPartnerId(partnerId);
	}

	public String getSellerAccount() {
		return alipayInfo.getSellerAccount();
	}

	public void setSellerAccount(String sellerAccount) {
		alipayInfo.setSellerAccount(sellerAccount);
	}

	public String getPrivateKey() {
		return alipayInfo.getPrivateKey();
	}

	public void setPrivateKey(String privateKey) {
		alipayInfo.setPrivateKey(privateKey);
	}

	public String getPublicKey() {
		return alipayInfo.getPublicKey();
	}

}
