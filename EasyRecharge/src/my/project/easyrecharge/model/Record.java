package my.project.easyrecharge.model;

import my.project.easyrecharge.util.Dateformat;

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

	//
	@SerializedName("SchoolID")
	private String schoolID;
	//
	@SerializedName("ApartID")
	private String apartID;
	//
	@SerializedName("ApartName")
	private String apartName;
	//
	@SerializedName("RoomName")
	private String roomName;
	//
	@SerializedName("PosMoney")
	private double posMoney;
	//
	@SerializedName("PosTime")
	private String posTime;
	//
	@SerializedName("StuNO")
	private String stuNO;
	//
	@SerializedName("StuName")
	private String stuName;
	//
	@SerializedName("StuTel")
	private String stuTel;
	//
	@SerializedName("Sstate")
	private int sstate;
	//
	@SerializedName("Rstate")
	private int rstate;
	//
	@SerializedName("CRCstr")
	private String crcstr;
	//
	@SerializedName("CreateDate")
	private String createDate;

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
		return posMoney + "";
	}

	public void setPosMoney(double posMoney) {
		this.posMoney = posMoney;
	}

	public String getPosTime() {
		String timestamp = posTime.substring(posTime.indexOf("(") + 1,
				posTime.indexOf(")"));
		return Dateformat.timestamp2DateStr(timestamp);
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

	public int getSstate() {
		return sstate;
	}

	public void setSstate(int sstate) {
		this.sstate = sstate;
	}

	public int getRstate() {
		return rstate;
	}

	public void setRstate(int rstate) {
		this.rstate = rstate;
	}

	public String getCrcstr() {
		return crcstr;
	}

	public void setCrcstr(String crcstr) {
		this.crcstr = crcstr;
	}

	public String getCreateDate() {
		String timestamp = createDate.substring(createDate.indexOf("(") + 1,
				createDate.indexOf(")"));
		return Dateformat.timestamp2DateStr(timestamp);
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTradeNo() {
		return crcstr.split("&")[3].split("=")[1];
	}
}
