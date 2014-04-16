package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.model.EResult;
import my.project.easyrecharge.util.RequestUtil;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Data Load Activity
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 */

public class ActDataload extends ActBase {

	// subclass invoke this method to request data with xml rpc
	protected void loadDataXMLRPC(String apiName, Object... params) {
		if (isNetworkConnected()) {
			new XMLRPCRequestTask(apiName).execute(params);
		} else {
			showToast(R.string.network_ungelivable);
		}
	}

	// asynctask with xml rpc request
	class XMLRPCRequestTask extends AsyncTask<Object, Void, EResult> {

		private String apiName;

		public XMLRPCRequestTask(String apiName) {
			this.apiName = apiName;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressHUD();
		}

		@Override
		protected EResult doInBackground(Object... params) {
			return xmlrpcRequest(apiName, params);
		}

		@Override
		protected void onPostExecute(EResult result) {
			super.onPostExecute(result);
			dismissProgressHUD();
			if (result != null) {
				if (result.isSuccess()) {
					disposeResult(result.getMessage());
				} else {
					showToast(result.getDescription());
				}
			} else {
				showToast(R.string.request_failed);
			}
		}

	}

	// request with xml rpc
	private EResult xmlrpcRequest(String apiName, Object... arg1) {
		try {
			return RequestUtil.xmlrpcRequest(apiName, arg1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// subclass invoke this method to request data with http
	protected void loadDataHttp(boolean needHint, String apiName, String params) {
		String url = getHttpRequestUrl(apiName, params);
		if (isNetworkConnected()) {
			new HttpRequestTask(needHint).execute(url);
		} else {
			showToast(R.string.network_ungelivable);
		}
	}

	private String getHttpRequestUrl(String apiName, String params) {
		StringBuilder sb = new StringBuilder();
		sb.append(F.HTTP_REQUEST_URL);
		sb.append(apiName);
		sb.append(params);
		return sb.toString();
	}

	// asynctask with http request
	class HttpRequestTask extends AsyncTask<String, Void, String> {

		private boolean needHint;

		public HttpRequestTask(boolean needHint) {
			this.needHint = needHint;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (needHint) {
				showProgressHUD();
			}
		}

		@Override
		protected String doInBackground(String... params) {
			return httpRequest(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (needHint) {
				dismissProgressHUD();
			}
			if (result != null) {
				disposeResult(result);
			} else {
				showToast(R.string.request_failed);
			}
		}

	}

	// request with http
	private String httpRequest(String url) {
		try {
			return RequestUtil.httpRequest(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// subclass invoke after respond OK, content is json string
	protected void disposeResult(String content) {
		Log.d(F.TAG, content);
	}

}
