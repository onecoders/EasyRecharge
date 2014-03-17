package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaMenu;
import my.project.easyrecharge.model.IndexMenu;
import my.project.easyrecharge.view.MyDialog.OnOKClickListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ActMain extends ActBase implements OnClickListener,
		OnItemClickListener {

	private GridView gridMenu;
	private IndexMenu[] menus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		init();
	}

	private void init() {
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	@Override
	protected View loadABCustomView() {
		View abView = LayoutInflater.from(this).inflate(R.layout.ab_main, null);
		return abView;
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
