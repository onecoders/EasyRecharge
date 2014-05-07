package my.project.easyrecharge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Recharge Record
 * 
 * 根据 学校 & 楼号 & 房间号 查询，以list数据返回，解析后以ListView显示
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class Record {

	// 充值日期
	@SerializedName("SchoolID")
	private String schoolID;
	@SerializedName("SchoolID")
	private String apartID;
	@SerializedName("SchoolID")
	private String apartName;
	@SerializedName("SchoolID")
	private String roomName;
	@SerializedName("SchoolID")
	private String posMoney;
	@SerializedName("SchoolID")
	private String posTime;
	@SerializedName("SchoolID")
	private String stuNO;
	@SerializedName("SchoolID")
	private String stuName;
	@SerializedName("SchoolID")
	private String stuTel;

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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getPosMoney() {
		return posMoney;
	}

	public void setPosMoney(String posMoney) {
		this.posMoney = posMoney;
	}

	public String getPosTime() {
		return posTime;
	}

	public void setPosTime(String posTime) {
		this.posTime = posTime;
	}

	public String getStuNO() {
		return stuNO;
	}

	public void setStuNO(String stuNO) {
		this.stuNO = stuNO;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuTel() {
		return stuTel;
	}

	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}

}
