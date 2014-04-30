package my.project.easyrecharge.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import my.project.easyrecharge.F;
import my.project.easyrecharge.model.ElecDetail;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

import de.timroes.axmlrpc.XMLRPCClient;

public class RequestUtil {

	private static final int TIME_OUT = 5 * 1000;

	public static ElecDetail xmlrpcRequest(String apiName, Object... arg1)
			throws Exception {
		XMLRPCClient client = new XMLRPCClient(new URL(F.XML_RPC_REQUEST_URL));
		client.setTimeout(5);
		HashMap<String, Object> result = (HashMap<String, Object>) client.call(
				apiName, arg1);
		ElecDetail detail = new ElecDetail();
		detail.setUsedScore((String) result.get("usedScore"));
		detail.setRemainScore((String) result.get("remainScore"));
		detail.setIsHave((Integer) result.get("isHave"));
		detail.setLastReadTime((String) result.get("lastReadTime"));
		detail.setResult((Integer) result.get("result"));
		detail.setDescription((String) result.get("description"));
		return detail;
	}

	public static String httpRequest(String url) throws Exception {
		StringBuilder sb = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		// 设置网络超时参数
		HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);
		HttpGet httpRequest = new HttpGet(url);
		// httpRequest.setHeader("Content-Type", "Application/json");
		// httpRequest.setHeader("AUTHORIZATION", "Owner Z2Nuc3Q=");
		HttpResponse response = client.execute(httpRequest);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"), 8192);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
		}
		return sb.toString();
	}

	public static void volleyRequest(String url, Listener<String> listener,
			ErrorListener errorListener) {
		StringRequest request = new StringRequest(url, listener, errorListener) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// HashMap<String, String> headers = new HashMap<String,
				// String>();
				// headers.put("CUSTOM_HEADER", "Yahoo");
				// headers.put("ANOTHER_CUSTOM_HEADER", "Google");
				// return headers;
				return super.getHeaders();
			}

		};
		request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, 1, 1.0f));
		F.add(request);
	}

}
