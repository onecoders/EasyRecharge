package my.project.easyrecharge.model;

import my.project.easyrecharge.alipay.Keys;

/**
 * Corresponding Alipay Info of School
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class AlipayInfo {

	// 合作身份者id，以2088开头的16位纯数字
	private String partnerId = Keys.DEFAULT_PARTNER;
	// 收款支付宝账号
	private String sellerAccount = Keys.DEFAULT_SELLER;
	// 商户私钥，自助生成
	private String privateKey = Keys.PRIVATE;

	public AlipayInfo() {
		// default constructor
	}

	public AlipayInfo(String partnerId, String sellerAccount, String privateKey) {
		this.partnerId = partnerId;
		this.sellerAccount = sellerAccount;
		this.privateKey = privateKey;
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
