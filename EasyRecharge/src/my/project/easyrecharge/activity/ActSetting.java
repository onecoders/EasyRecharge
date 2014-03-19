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
import my.project.easyrecharge.view.CustomDialog.OnLeftBtnClickListener;

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

public class ActSetting extends ActBase implements ClickListener {

	private UITableView tableView;

	public ProgressDialog pBar;

	private int verCode, newVerCode = 0;
	private String verName, newVerName = "";

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
			switchActivity(ActFeedback.class);
			break;
		case ABOUT:
			switchActivity(ActAbout.class);
			break;
		case VERSION_CHECK:
			// checkUpdate();
			showToast(R.string.already_newest);
			break;
		default:
			break;
		}
	}

	private void checkUpdate() {
		new GetServerVersionTask().execute(F.APK_CHECK_VERSON_URL);
	}

	private void getVerCode() {
		try {
			verCode = VersionUtil.getVersionCode(this);
			verName = VersionUtil.getVersionName(this);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	class GetServerVersionTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressBar();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			getVerCode();
			return getServerVerCode(params[0]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			dismissProgressBar();
			if (result) {
				needUpdate = newVerCode > verCode;
				if (needUpdate) {
					doNewVersionUpdate();
				} else {
					notNewVersionShow();
				}
			}
		}

	}

	private boolean getServerVerCode(String url) {
		// [{"appname":"jtapp12","apkname":"jtapp-12-updateapksamples.apk","verName":1.0.1,"verCode":2}]
		try {
			String verjson = HttpUtil.getContent(url);
			JSONObject obj = new JSONObject(verjson);
			newVerCode = Integer.parseInt(obj.getString("verCode"));
			newVerName = obj.getString("verName");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			newVerCode = -1;
			return false;
		}
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
		StringBuffer sb = new StringBuffer();
		sb.append(getString(R.string.current_version));
		sb.append(verName);
		sb.append(getString(R.string.version_code));
		sb.append(verCode);
		if (needUpdate) {
			sb.append(getString(R.string.find_new_version));
			sb.append(newVerName);
			sb.append(getString(R.string.version_code));
			sb.append(newVerCode);
			sb.append(getString(R.string.update_or_not));
		} else {
			sb.append(getString(R.string.no_need_to_update));
		}
		return sb.toString();
	}

	private void doUpdate() {
		new DownloadFileAsyncTask().execute(F.APK_DOWNLOAD_URL);
	}

	class DownloadFileAsyncTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressBar();
		}

		@Override
		protected Void doInBackground(String... params) {
			downloadFile(params[0], F.UPDATE_SAVE_NAME);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dismissProgressBar();
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

	private void showProgressBar() {
		pBar = new ProgressDialog(this);
		pBar.setTitle(R.string.downloading);
		pBar.setMessage(getString(R.string.please_wait));
		pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pBar.show();
	}

	private void dismissProgressBar() {
		pBar.dismiss();
	}

}
