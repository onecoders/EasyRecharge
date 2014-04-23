package my.project.easyrecharge.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.Method;
import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaMenu;
import my.project.easyrecharge.model.IndexMenu;
import my.project.easyrecharge.model.IndexMenu.ActName;
import my.project.easyrecharge.model.Information;
import my.project.easyrecharge.view.NewAlertDialog.OnDialogBtnClickListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.google.gson.reflect.TypeToken;

/**
 * Index Page
 * 
 * @include Inquiry/Recharges/Record/Bind/Information/Setting
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
		checkUpdate(false);
		loadInforData();
		initActionBar();
		initMenuList();
		initGridMenu();
	}

	private void loadInforData() {
		loadDataHttp(false, Method.QUERY_ANNOUNCEMENT, "?schoolId=1");
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (!apiName.equals(Method.QUERY_ANNOUNCEMENT))
			return;
		try {
			Type collectionType = new TypeToken<List<Information>>() {
			}.getType();
			List<Information> infoList = F.fromJson(content, collectionType);
			initInfoDialog(infoList);
		} catch (Exception e) {
			showToast(R.string.error_data);
		}
	}

	private void initInfoDialog(List<Information> infoList) {
		List<Information> newInfos = new ArrayList<Information>();
		for (Information info : infoList) {
			if (info.isNewFlag())
				newInfos.add(info);
		}
		if (newInfos.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("----------------------\n");
			for (Information info : newInfos)
				sb.append(info.getMessage() + "\n");
			sb.append("----------------------");
			showDialog(R.string.info_dialog_title, sb.toString(),
					R.string.confirm, true, 0, null);
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
			switchActivity(cls, null);
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
