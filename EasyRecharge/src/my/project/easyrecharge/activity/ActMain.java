package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaMenu;
import my.project.easyrecharge.model.IndexMenu;
import my.project.easyrecharge.model.IndexMenu.ActName;
import my.project.easyrecharge.view.NewAlertDialog.OnDialogBtnClickListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * 应用首页（导航页：电费查询/快捷充值/充值记录/用户绑定/通知信息/设置）
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActMain extends ActUpdateApk implements OnItemClickListener {

	private GridView gridMenu;
	private IndexMenu[] menus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		init();
	}

	private void init() {
		checkVersion();
		initActionBar();
		initMenuList();
		initGridMenu();
	}

	private void checkVersion() {
		if (isNetworkConnected()) {
			checkUpdate();
		}
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.app_name);
		setAbLeftBtnText(R.string.txt_exit_btn);
	}

	private void initMenuList() {
		String[] menuTitles = getResources()
				.getStringArray(R.array.menu_titles);
		TypedArray iconArray = getResources().obtainTypedArray(
				R.array.menu_icon_ids);
		int menuCount = menuTitles.length;
		menus = new IndexMenu[menuCount];
		for (int i = 0; i < menuCount; i++) {
			menus[i] = new IndexMenu(iconArray.getResourceId(i, 0),
					menuTitles[i]);
			menus[i].setAct(ActName.values()[i]);
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
		switch (menus[position].getAct()) {
		case INQUIRY:
			cls = ActInquiry.class;
			break;
		case RECHARGE:
			cls = ActRecharge.class;
			break;
		case RECORD:
			cls = ActRecord.class;
			break;
		case BIND:
			cls = ActBind.class;
			break;
		case INFORMATION:
			cls = ActInformation.class;
			break;
		case SETTING:
			cls = ActSetting.class;
			break;
		default:
			break;
		}
		if (cls != null) {
			switchActivity(cls);
		}
	}

	@Override
	public void onBackPressed() {
		showDialog(R.string.exit_dialog_title, R.string.exit_dialog_message,
				R.string.confirm, R.string.cancel,
				new OnDialogBtnClickListener() {

					@Override
					public void onLeftBtnClick() {
						exit();
					}

					@Override
					public void onRightBtnClick() {

					}
				});
	}

	private void exit() {
		finish();
		System.exit(0);
	}

}
