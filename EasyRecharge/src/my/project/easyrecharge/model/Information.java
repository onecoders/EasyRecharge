package my.project.easyrecharge.model;

/**
 * Information from server
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

import my.project.easyrecharge.util.Dateformat;

public class Information {

	private String SchoolID;

	private String Message;

	private String UpdateTime;

	private boolean NewFLag;

	public String getSchoolID() {
		return SchoolID;
	}

	public void setSchoolID(String schoolID) {
		SchoolID = schoolID;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getUpdateTime() {
		String timestamp = UpdateTime.substring(UpdateTime.indexOf("(") + 1,
				UpdateTime.indexOf(")"));
		return Dateformat.timestamp2DateStr(timestamp);
	}

	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}

	public boolean isNewFlag() {
		return NewFLag;
	}

	public void setNewFlag(boolean newFlag) {
		NewFLag = newFlag;
	}

}
