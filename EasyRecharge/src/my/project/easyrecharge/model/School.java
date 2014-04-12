package my.project.easyrecharge.model;

/**
 * School
 * 
 * 包括学校基本信息和学校支付宝信息，从服务器端获取数据
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

	// 获取partner id
	public String getPartnerId() {
		return alipayInfo.getPartnerId();
	}

	// 获取支付宝帐号
	public String getSellerAccount() {
		return alipayInfo.getSellerAccount();
	}

	// 获取私钥
	public String getPrivateKey() {
		return alipayInfo.getPrivateKey();
	}

}
