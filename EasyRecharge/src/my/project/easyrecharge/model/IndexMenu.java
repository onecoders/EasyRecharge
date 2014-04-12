package my.project.easyrecharge.model;

/**
 * Index Menu
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class IndexMenu {

	public enum ActName {
		INQUIRY, RECHARGE, RECORD, BIND, INFORMATION, SETTING
	}

	private int iconId;
	private String title;
	private ActName actName;

	public IndexMenu(int iconId, String title) {
		this.iconId = iconId;
		this.title = title;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ActName getAct() {
		return actName;
	}

	public void setAct(ActName act) {
		this.actName = act;
	}

}
