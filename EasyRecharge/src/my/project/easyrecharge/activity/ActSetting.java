package my.project.easyrecharge.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.util.ApkUtil;
import my.project.easyrecharge.util.HttpUtil;
import my.project.easyrecharge.util.VersionUtil;
import my.project.easyrecharge.view.NewAlertDialog.OnLeftBtnClickListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import br.com.dina.ui.model.BasicItem;
import br.com.dina.ui.widget.UITableView;
import br.com.dina.ui.widget.UITableView.ClickListener;

/**
 * 设置
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActSetting extends ActBase implements ClickListener {

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
				R.array.setting_list);
		for (String string : menuTitles) {
			tableView.addBasicItem(new BasicItem(string));
		}
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
		new GetServerVersionTask().execute(F.APK_CHECK_VERSON_URL);
	}

	class GetServerVersionTask extends AsyncTask<String, Void, Boolean> {

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

	private boolean getVerCode() {
		try {
			verCode = VersionUtil.getVersionCode(this);
			return true;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

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

	private void notNewVersionShow() {
		String message = getUpdateInfo();
		showDialog(R.string.update_dialog_title, message, R.string.confirm,
				true, 0, null);
	}

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

	private void doUpdate() {
		new DownloadFileAsyncTask().execute(F.APK_DOWNLOAD_URL);
	}

	class DownloadFileAsyncTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressHUD();
		}

		@Override
		protected Void doInBackground(String... params) {
			downloadFile(params[0], F.UPDATE_SAVE_NAME);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dismissProgressHUD();
			update();
		}

	}

	private void downloadFile(String url, String savePath) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			FileOutputStream fileOutputStream = null;
			if (is != null) {
				File file = new File(Environment.getExternalStorageDirectory(),
						savePath);
				fileOutputStream = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				int ch = -1;
				while ((ch = is.read(buf)) != -1) {
					fileOutputStream.write(buf, 0, ch);
				}
			}
			fileOutputStream.flush();
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		File apkFile = new File(Environment.getExternalStorageDirectory(),
				F.UPDATE_SAVE_NAME);
		ApkUtil.installApk(this, apkFile);
	}

}
