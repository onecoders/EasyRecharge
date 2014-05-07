package my.project.easyrecharge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Apart
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class Apart {

	// 学校id
	@SerializedName("SchoolID")
	private String schoolID;
	// 公寓id
	@SerializedName("ApartID")
	private String apartID;
	// 公寓名称
	@SerializedName("ApartName")
	private String apartName;

	public String getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public String getApartID() {
		return apartID;
	}

	public void setApartID(String apartID) {
		this.apartID = apartID;
	}

	public String getApartName() {
		return apartName;
	}

	public void setApartName(String apartName) {
		this.apartName = apartName;
	}

}
