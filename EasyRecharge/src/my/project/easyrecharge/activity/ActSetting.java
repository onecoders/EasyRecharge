package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.util.HttpUtil;
import my.project.easyrecharge.util.VersionUtil;
import my.project.easyrecharge.view.NewAlertDialog.OnLeftBtnClickListener;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import br.com.dina.ui.model.BasicItem;
import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;

/**
 * 设置
 * 
 * 主要有检测版本操作
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActSetting extends ActUpdateApk implements ClickListener {

	private UITableView tableView;

	public ProgressDialog pBar;

	private int verCode, newVerCode = 0;

	private boolean needUpdate;

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
		setAbTitle(R.string.activity_title_setting);
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
			switchActivity(ActFeedback.class);
			break;
		case ABOUT:
			switchActivity(ActAbout.class);
			break;
		case VERSION_CHECK:
			checkUpdate();
			break;
		default:
			break;
		}
	}

	// check update info from server
	private void checkUpdate() {
		new CheckVersionTask().execute(F.APK_CHECK_VERSON_URL);
	}

	class CheckVersionTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressHUD();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			// simulation
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
			// do real things
			// return checkVersion(params[0]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			dismissProgressHUD();
			if (result) {
				// needUpdate = newVerCode > verCode;
				needUpdate = false;
				if (needUpdate) {
					doNewVersionUpdate();
				} else {
					notNewVersionShow();
				}
			}
		}

	}

	private boolean checkVersion(String url) {
		return getVerCode() && getServerVerCode(url);
	}

	// 获取本地安装包的版本号
	private boolean getVerCode() {
		try {
			verCode = VersionUtil.getVersionCode(this);
			return true;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 获取服务器端上安装包的版本号
	private boolean getServerVerCode(String url) {
		// [{"appname":"jtapp12","apkname":"jtapp-12-updateapksamples.apk","verName":1.0.1,"verCode":2}]
		try {
			String verjson = HttpUtil.getContent(url);
			JSONObject obj = new JSONObject(verjson);
			newVerCode = Integer.parseInt(obj.getString("verCode"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			newVerCode = -1;
		}
		return false;
	}

	// 没有发现新版本
	private void notNewVersionShow() {
		String message = getUpdateInfo();
		showDialog(R.string.update_dialog_title, message, R.string.confirm,
				true, 0, null);
	}

	// 发现新版本
	private void doNewVersionUpdate() {
		String message = getUpdateInfo();
		showDialog(R.string.update_dialog_title, message, R.string.update_now,
				false, R.string.update_not_now, new OnLeftBtnClickListener() {

					@Override
					public void onLeftBtnClick() {
						doUpdate();
					}

				});
	}

	private String getUpdateInfo() {
		return getString(needUpdate ? R.string.find_new_version_message
				: R.string.already_newest_message);
	}

}
