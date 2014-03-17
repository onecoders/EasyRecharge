package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaMenu;
import my.project.easyrecharge.model.IndexMenu;
import my.project.easyrecharge.view.MyDialog.OnOKClickListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ActMain extends ActBase implements OnClickListener,
		OnItemClickListener {

	private static final int INQUIRY = 0;
	private static final int RECHARGE = 1;
	private static final int FEEDBACK = 2;
	private static final int ABOUT = 3;

	private GridView gridMenu;
	private IndexMenu[] menus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		init();
	}

	private void init() {
		setAbTitle(R.string.app_name);
		abLeftBtn.setVisibility(View.GONE);
		initMenuList();
		initGridMenu();
	}

	private void initMenuList() {
		String[] menuTitles = getResources().getStringArray(R.array.menu_title);
		TypedArray iconArray = getResources().obtainTypedArray(
				R.array.menu_icon_id);
		menus = new IndexMenu[menuTitles.length];
		for (int i = 0; i < menus.length; i++) {
			menus[i] = new IndexMenu(iconArray.getResourceId(i, 0),
					menuTitles[i]);
		}
		iconArray.recycle();
	}

	private void initGridMenu() {
		gridMenu = (GridView) findViewById(R.id.grid_menu);
		gridMenu.setAdapter(new AdaMenu(this, menus));
		gridMenu.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		Class<?> cls = null;
		switch (position) {
		case INQUIRY:
			cls = ActInquiry.class;
			break;
		case RECHARGE:
			cls = ActRecharge.class;
			break;
		case FEEDBACK:
			cls = ActFeedback.class;
			break;
		case ABOUT:
			cls = ActAbout.class;
			break;
		default:
			break;
		}
		if (cls != null) {
			startActivity(cls);
		}
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onBackPressed() {
		showDialog(R.string.exit_dialog_title, R.string.exit_dialog_message,
				new OnOKClickListener() {

					@Override
					public void onOKClick() {
						exit();
					}
				});
	}

	private void exit() {
		finish();
		System.exit(0);
	}

}
