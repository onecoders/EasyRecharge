package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.util.HttpUtil;
import my.project.easyrecharge.util.UpdateApkUtil;
import my.project.easyrecharge.util.VersionUtil;
import my.project.easyrecharge.view.NewAlertDialog.OnDialogBtnClickListener;

import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;

/**
 * 抽象类，用于应用更新时下载apk和完成后安装
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActUpdateApk extends ActDataload {

	// cancel download task by invoking task.cancel(true)
	// and override onCancelled()
	private DownloadTask task;

	private int verCode, newVerCode = 0;

	private boolean needUpdate;

	// check update info from server
	protected void checkUpdate(boolean showProgressHUD) {
		new CheckVersionTask(showProgressHUD).execute(F.APK_CHECK_VERSON_URL);
	}

	class CheckVersionTask extends AsyncTask<String, Void, Boolean> {

		private boolean showProgressHUD;

		public CheckVersionTask(boolean showProgressHUD) {
			this.showProgressHUD = showProgressHUD;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (showProgressHUD) {
				showProgressHUD();
			}
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
			// return if check version succeed
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (showProgressHUD) {
				dismissProgressHUD();
			}
			if (result) {
				// needUpdate = newVerCode > verCode;
				// assume needUpdate is true
				needUpdate = true;
				if (needUpdate) {
					doNewVersionUpdate();
				} else {
					noNewVersion();
				}
			}
		}

	}

	private boolean checkVersion(String url) {
		return getVerCode() && getServerVerCode(url);
	}

	// get local app's version info
	private boolean getVerCode() {
		try {
			verCode = VersionUtil.getVersionCode(this);
			return true;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	// get version info from server
	private boolean getServerVerCode(String url) {
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
	private void noNewVersion() {
		String message = getUpdateInfo();
		showDialog(R.string.update_dialog_title, message, R.string.confirm,
				true, 0, null);
	}

	// 发现新版本
	private void doNewVersionUpdate() {
		String message = getUpdateInfo();
		showDialog(R.string.update_dialog_title, message, R.string.update_now,
				false, R.string.update_not_now, new OnDialogBtnClickListener() {

					@Override
					public void onLeftBtnClick() {
						doUpdate();
					}

					@Override
					public void onRightBtnClick() {

					}

				});
	}

	private String getUpdateInfo() {
		return getString(needUpdate ? R.string.find_new_version_message
				: R.string.already_newest_message);
	}

	// 更新操作，下载apk并安装
	protected void doUpdate() {
		task = new DownloadTask(this);
		if (isNetworkConnected()) {
			task.execute(F.APK_DOWNLOAD_URL, F.UPDATE_SAVE_NAME);
		} else {
			showToast(R.string.network_ungelivable);
		}
	}

	class DownloadTask extends AsyncTask<String, Integer, Boolean> implements
			UpdateApkUtil.OnUpdateProgressListener {

		private Context context;
		private UpdateApkUtil updateUtil;

		public DownloadTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			updateUtil = new UpdateApkUtil(this);
			showProgressHUD();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			return updateUtil.downloadFile(params[0], params[1]);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			updateMessage(values[0]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			setMessage(getString(result ? R.string.download_finish
					: R.string.download_failed));
			dismissProgressHUD();
			if (result) {
				// install apk
				updateUtil.installApk(context);
			}
		}

		@Override
		public void updateProgress(int progress) {
			publishProgress(progress);
		}

	}

}
