package my.project.easyrecharge.model;

/**
 * 相应学校支付宝信息
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class AlipayInfo {

	// 合作身份者id，以2088开头的16位纯数字
	private String partnerId;
	// 收款支付宝账号
	private String sellerAccount;
	// 商户私钥，自助生成
	private String privateKey;
	// 公钥
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

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

	public String getPublicKey() {
		return PUBLIC_KEY;
	}

}
