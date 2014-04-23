package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.Method;
import my.project.easyrecharge.R;
import my.project.easyrecharge.model.VersionServer;
import my.project.easyrecharge.util.UpdateApkUtil;
import my.project.easyrecharge.util.VersionUtil;
import my.project.easyrecharge.view.NewAlertDialog.OnDialogBtnClickListener;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;

/**
 * Update app(Download and install)
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActUpdateApk extends ActDataload {

	public static final String APK_SAVE_NAME = "easyrecharge.apk";

	private boolean needUpdate;
	private VersionServer version;
	private boolean needHint;

	// cancel download task by invoking task.cancel(true)
	// and override onCancelled()
	private DownloadTask task;

	// check update info from server
	protected void checkUpdate(boolean needHint) {
		this.needHint = needHint;
		loadDataHttp(needHint, Method.QUERY_VERSION, "");
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (!apiName.equals(Method.QUERY_VERSION))
			return;
		try {
			version = F.fromJson(content, VersionServer.class);
			needUpdate = version.getIinfo() > getVerCode();
			if (needUpdate) {
				doNewVersionUpdate();
			} else {
				if (needHint)
					noNewVersion();
			}
		} catch (Exception e) {
			showToast(R.string.error_data);
		}
	}

	// get local app's version info
	private int getVerCode() {
		try {
			return VersionUtil.getVersionCode(this);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
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
			task.execute(version.getCinfo(), APK_SAVE_NAME);
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
