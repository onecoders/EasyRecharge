package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.model.ElecDetail;
import my.project.easyrecharge.util.L;
import my.project.easyrecharge.util.RequestUtil;
import android.os.AsyncTask;

import com.android.volley.Response;
import com.android.volley.VolleyError;

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
		L.d("XMLRPCRequest", apiName + params.toString());
		if (isNetworkConnected()) {
			new XMLRPCRequestTask(apiName).execute(params);
		} else {
			showToast(R.string.network_ungelivable);
		}
	}

	// asynctask with xml rpc request
	class XMLRPCRequestTask extends AsyncTask<Object, Void, ElecDetail> {

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
		protected ElecDetail doInBackground(Object... params) {
			return xmlrpcRequest(apiName, params);
		}

		@Override
		protected void onPostExecute(ElecDetail result) {
			super.onPostExecute(result);
			dismissProgressHUD();
			if (result != null) {
				if (result.isRoomExist()) {
					disposeResult(apiName, toJson(result));
				} else {
					showToast(R.string.room_not_exist);
				}
			} else {
				showToast(R.string.request_failed);
			}
		}

	}

	// request with xml rpc
	private ElecDetail xmlrpcRequest(String apiName, Object... arg1) {
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
		L.d("HttpRequest", url);
		if (isNetworkConnected()) {
			new HttpRequestTask(needHint, apiName).execute(url);
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
		private String apiName;

		public HttpRequestTask(boolean needHint, String apiName) {
			this.needHint = needHint;
			this.apiName = apiName;
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
				disposeResult(apiName, result);
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

	// subclass invoke this method to request data by volley
	protected void loadDataVolley(boolean needHint, String apiName,
			String params) {
		String url = getHttpRequestUrl(apiName, params);
		L.d("VolleyRequest", url);
		if (isNetworkConnected()) {
			volleyRequest(needHint, apiName, url);
		} else {
			showToast(R.string.network_ungelivable);
		}
	}

	private void volleyRequest(final boolean needHint, final String apiName,
			String url) {
		if (needHint) {
			showProgressHUD();
		}
		RequestUtil.volleyRequest(url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				if (needHint) {
					dismissProgressHUD();
				}
				disposeResult(apiName, response);
			}

		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				if (needHint) {
					dismissProgressHUD();
				}
				showToast(R.string.request_failed);
			}

		});
	}

	// subclass invoke after respond OK, content is json string
	protected void disposeResult(String apiName, String content) {
		L.d(apiName, content);
	}

}
