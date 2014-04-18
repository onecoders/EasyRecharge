package my.project.easyrecharge.model;

import java.util.regex.Pattern;

import my.project.easyrecharge.util.LetterUtil;

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
	private String SchoolID;
	// 学校名称
	private String SchoolName;
	//
	private String ZFBAccount;
	// 学校对应的支付宝信息
	private AlipayInfo alipayInfo;
	// 首字母
	private String alpha;

	public String getSchoolID() {
		return SchoolID;
	}

	public void setSchoolID(String schoolID) {
		SchoolID = schoolID;
	}

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public String getZFBAccount() {
		return ZFBAccount;
	}

	public void setZFBAccount(String zFBAccount) {
		ZFBAccount = zFBAccount;
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
		return formatAlpha(SchoolName);
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

}
