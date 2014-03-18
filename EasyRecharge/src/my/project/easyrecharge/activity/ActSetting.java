package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;
import br.com.dina.ui.model.BasicItem;
import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;

public class ActSetting extends ActBase implements ClickListener {

	private UITableView tableView;

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
		setAbTitle(R.string.title_setting);
		initUITableView();
	}

	private void initUITableView() {
		tableView = (UITableView) findViewById(R.id.tableView);
		createMenuList();
		tableView.commit();
		tableView.setClickListener(this);
	}

	private void createMenuList() {
		String[] menuTitles = getResources().getStringArray(
				R.array.setting_list);
		for (String string : menuTitles) {
			tableView.addBasicItem(new BasicItem(string));
		}
	}

	@Override
	public void onClick(int index) {
		switch (SetAct.values()[index]) {
		case FEEDBACK:
			startActivity(ActFeedback.class);
			break;
		case ABOUT:
			startActivity(ActAbout.class);
			break;
		case VERSION_CHECK:
			showToast(R.string.already_newest);
			break;
		default:
			break;
		}
	}

}
