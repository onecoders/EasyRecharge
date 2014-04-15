package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.METHOD;
import my.project.easyrecharge.R;
import my.project.easyrecharge.model.VersionServer;
import my.project.easyrecharge.util.UpdateApkUtil;
import my.project.easyrecharge.util.VersionUtil;
import my.project.easyrecharge.view.NewAlertDialog.OnDialogBtnClickListener;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Update app(Download and install)
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
	private String apkDownloadUrl;

	// check update info from server
	protected void checkUpdate(boolean showProgressHUD) {
		if (isNetworkConnected()) {
			new CheckVersionTask(showProgressHUD).execute(METHOD.QUERY_VERSION,
					"");
		} else {
			// hint in dataload method instead
			showToast(R.string.network_ungelivable);
		}
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
			// return if check version succeed
			return checkVersion(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (showProgressHUD) {
				dismissProgressHUD();
			}
			if (result) {
				needUpdate = newVerCode > verCode;
				if (needUpdate) {
					doNewVersionUpdate();
				} else {
					if (showProgressHUD) {
						noNewVersion();
					}
				}
			}
		}

	}

	// do real check
	private boolean checkVersion(String apiName, String params) {
		return getVerCode() && getServerVerCode(apiName, params);
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
	private boolean getServerVerCode(String apiName, String params) {
		String verJson = loadDataHttp(apiName, params);
		Log.d(F.TAG, verJson);
		if (verJson != null) {
			VersionServer version = F.fromJson(verJson, VersionServer.class);
			newVerCode = version.getIinfo();
			apkDownloadUrl = version.getCinfo();
			return true;
		}
		return false;
	}

	// no new version available
	private void noNewVersion() {
		String message = getUpdateInfo();
		showDialog(R.string.update_dialog_title, message, R.string.confirm,
				true, 0, null);
	}

	// new version available on server
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

	// update app, download apk and install
	protected void doUpdate() {
		task = new DownloadTask(this);
		if (isNetworkConnected()) {
			task.execute(apkDownloadUrl, F.UPDATE_SAVE_NAME);
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
			this.updateUtil = new UpdateApkUtil(this);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
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
