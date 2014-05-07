package my.project.easyrecharge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Version info from server
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class VersionServer {

	@SerializedName("VersionCode")
	private int versionCode;
	@SerializedName("VersionName")
	private String versionName;
	@SerializedName("CanUse")
	private boolean canUse;
	@SerializedName("Description")
	private String description;

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public boolean isCanUse() {
		return canUse;
	}

	public void setCanUse(boolean canUse) {
		this.canUse = canUse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
