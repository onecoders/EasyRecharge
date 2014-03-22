package my.project.easyrecharge.model;

public class School {

	// 学校id，唯一
	private int id;
	// 学校名称
	private String name;
	// 学校校徽图片地址
	private String icon;
	// 合作身份者id，以2088开头的16位纯数字
	private String partnerId;
	// 收款支付宝账号
	private String sellerAccount;
	// 商户私钥，自助生成
	private String privateKey;
	
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
