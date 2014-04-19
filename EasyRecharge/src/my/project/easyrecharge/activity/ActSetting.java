package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import br.com.dina.ui.model.BasicItem;
import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;

/**
 * Setting Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActSetting extends ActUpdateApk implements ClickListener {

	private UITableView tableView;

	public ProgressDialog pBar;

	private enum SetAct {
		FEEDBACK, ABOUT, VERSION_CHECK
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_setting);
		init();
	}

	private void init() {
		initActionBar();
		initUITableView();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_setting);
	}

	private void initUITableView() {
		tableView = (UITableView) findViewById(R.id.tableView);
		createMenuList();
		tableView.commit();
		tableView.setClickListener(this);
	}

	private void createMenuList() {
		String[] menuTitles = getResources().getStringArray(
				R.array.setting_title_list);
		TypedArray iconArray = getResources().obtainTypedArray(
				R.array.setting_icon_list);
		int count = menuTitles.length;
		for (int i = 0; i < count; i++) {
			BasicItem item = new BasicItem(menuTitles[i]);
			item.setDrawable(iconArray.getResourceId(i, 0));
			if (i == count - 1) {
				item.setSubtitle(getString(R.string.current_version)
						+ F.VERSION_NAME);
			}
			tableView.addBasicItem(item);
		}
		iconArray.recycle();
	}

	@Override
	public void onClick(int index) {
		switch (SetAct.values()[index]) {
		case FEEDBACK:
			switchActivity(ActFeedback.class, null);
			break;
		case ABOUT:
			switchActivity(ActAbout.class, null);
			break;
		case VERSION_CHECK:
			checkUpdate(true);
			break;
		default:
			break;
		}
	}

}
