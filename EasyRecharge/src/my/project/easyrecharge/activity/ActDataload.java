package my.project.easyrecharge.activity;

import java.net.URL;
import java.util.HashMap;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.EResult;
import my.project.easyrecharge.util.HttpUtil;
import android.os.AsyncTask;
import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;
import de.timroes.axmlrpc.XMLRPCServerException;

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

	// asynctask,invoke request method in other thread
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
		EResult eResult = null;
		try {
			XMLRPCClient client = new XMLRPCClient(new URL(
					F.XML_RPC_REQUEST_URL));
			HashMap<String, String> result = (HashMap<String, String>) client
					.call(apiName, arg1);
			eResult = new EResult();
			eResult.setResult(result.get(Key.RESULT));
			eResult.setMessage(result.get(Key.MESSAGE));
			eResult.setDescription(result.get(Key.DESCRIPTION));
		} catch (XMLRPCServerException ex) {
			// The server throw an error.
		} catch (XMLRPCException ex) {
			// An error occured in the client.
		} catch (Exception ex) {
			// Any other exception
		}
		return eResult;
	}

	// subclass invoke this method to request data with http
	protected String loadDataHttp(String apiName, String params) {
		String url = getHttpRequestUrl(apiName, params);
		if (isNetworkConnected()) {
			return httpRequest(url);
		} else {
			showToast(R.string.network_ungelivable);
		}
		return null;
	}

	private String getHttpRequestUrl(String apiName, String params) {
		StringBuilder sb = new StringBuilder();
		sb.append(F.HTTP_REQUEST_URL);
		sb.append(apiName);
		sb.append(params);
		return sb.toString();
	}

	private String httpRequest(String url) {
		try {
			return HttpUtil.getContent(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// subclass invoke after respond OK, content is json string
	protected void disposeResult(String content) {

	}

}
