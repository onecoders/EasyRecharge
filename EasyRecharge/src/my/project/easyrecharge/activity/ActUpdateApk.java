package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.util.UpdateApkUtil;
import android.content.Context;
import android.os.AsyncTask;

/**
 * 抽象类，用于应用更新时下载apk和完成后安装
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public abstract class ActUpdateApk extends ActBase {

	// 如果下载中取消，调用task.cancel(true)，并重写onCancelled()方法
	private UpdateAsyncTask task;

	// 更新操作，下载apk并安装
	protected void doUpdate() {
		task = new UpdateAsyncTask(this);
		if (isNetworkConnected()) {
			task.execute(F.APK_DOWNLOAD_URL, F.UPDATE_SAVE_NAME);
		} else {
			showToast(R.string.network_ungelivable);
		}
	}

	class UpdateAsyncTask extends AsyncTask<String, Integer, Boolean> implements
			UpdateApkUtil.OnUpdateProgressListener {

		private Context context;
		private UpdateApkUtil updateUtil;

		public UpdateAsyncTask(Context context) {
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
				updateUtil.installApk(context);
			}
		}

		@Override
		public void updateProgress(int progress) {
			publishProgress(progress);
		}

	}

}
