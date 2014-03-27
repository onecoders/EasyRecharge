package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.util.UpdateApkUtil;
import android.content.Context;
import android.os.AsyncTask;

/**
 * 更新类基类
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public abstract class ActUpdateApk extends ActBase {

	// 更新操作，下载apk并安装
	protected void doUpdate() {
		new UpdateAsyncTask(this).execute(F.APK_DOWNLOAD_URL,
				F.UPDATE_SAVE_NAME);
	}

	class UpdateAsyncTask extends AsyncTask<String, Void, Void> implements
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
		protected Void doInBackground(String... params) {
			updateUtil.downloadFile(params[0], params[1]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			setMessage(getString(R.string.download_finish));
			dismissProgressHUD();
			updateUtil.installApk(context);
		}

		@Override
		public void updateProgress(float progress) {
			updateMessage(progress);
		}

	}

}
