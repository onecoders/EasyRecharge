package my.project.easyrecharge.model;

public class IndexMenu {

	private int iconId;
	private String title;

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

}
