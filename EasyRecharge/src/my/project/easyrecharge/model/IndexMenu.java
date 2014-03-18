package my.project.easyrecharge.model;

public class IndexMenu {

	public enum Act {
		BIND, INQUIRY, RECHARGE, SETTING
	}

	private int iconId;
	private String title;
	private Act act;

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

	public Act getAct() {
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}

}
