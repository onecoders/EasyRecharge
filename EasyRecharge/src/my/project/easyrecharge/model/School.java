package my.project.easyrecharge.model;

import java.util.regex.Pattern;

import my.project.easyrecharge.util.LetterUtil;

import com.google.gson.annotations.SerializedName;

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
	@SerializedName("SchoolID")
	private String schoolID;
	// 学校名称
	@SerializedName("SchoolName")
	private String schoolName;
	//
	@SerializedName("ZFBAccount")
	private String zfbAccount;
	// 学校对应的支付宝信息
	private AlipayInfo alipayInfo = new AlipayInfo();
	// 电费单价
	@SerializedName("Price")
	private double unitPrice;
	// 首字母
	private String alpha;

	public String getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getZFBAccount() {
		return zfbAccount;
	}

	public void setZFBAccount(String zfbAccount) {
		this.zfbAccount = zfbAccount;
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

	public String getAlpha() {
		return formatAlpha(schoolName);
	}

	private String formatAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		String letter = LetterUtil.getFirstLetter(str.trim().substring(0, 1));
		char c = letter.charAt(0);
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else {
			return "#";
		}
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getPriceStr() {
		return getUnitPrice() + "";
	}

}
