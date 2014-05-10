package my.project.easyrecharge.model;

/**
 * Electricity detail
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ElecDetail {

	private static final int ROOM_EXIST_FLAG = 1;

	private String usedScore;
	private String remainScore;
	private int isHave;
	private String lastReadTime;
	private int result;
	private String description;

	public String getUsedScore() {
		return usedScore;
	}

	public void setUsedScore(String usedScore) {
		this.usedScore = usedScore;
	}

	public String getRemainScore() {
		return remainScore;
	}

	public void setRemainScore(String remainScore) {
		this.remainScore = remainScore;
	}

	public int getIsHave() {
		return isHave;
	}

	public void setIsHave(int isHave) {
		this.isHave = isHave;
	}

	public String getLastReadTime() {
		return lastReadTime;
	}

	public void setLastReadTime(String lastReadTime) {
		this.lastReadTime = lastReadTime;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// exist ? 1 : 0
	public boolean isRoomExist() {
		return getIsHave() == ROOM_EXIST_FLAG;
	}

}
