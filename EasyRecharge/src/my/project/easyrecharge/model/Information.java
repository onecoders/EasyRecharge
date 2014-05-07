package my.project.easyrecharge.model;

/**
 * Information from server
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

import my.project.easyrecharge.util.Dateformat;

import com.google.gson.annotations.SerializedName;

public class Information {

	@SerializedName("SchoolID")
	private String schoolID;
	@SerializedName("Message")
	private String message;
	@SerializedName("UpdateTime")
	private String updateTime;
	@SerializedName("NewFLag")
	private boolean newFlag;

	public String getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUpdateTime() {
		String timestamp = updateTime.substring(updateTime.indexOf("(") + 1,
				updateTime.indexOf(")"));
		return Dateformat.timestamp2DateStr(timestamp);
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isNewFlag() {
		return newFlag;
	}

	public void setNewFlag(boolean newFlag) {
		this.newFlag = newFlag;
	}

}
