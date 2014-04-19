package my.project.easyrecharge.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import my.project.easyrecharge.F;
import my.project.easyrecharge.model.ElecDetail;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import de.timroes.axmlrpc.XMLRPCClient;

public class RequestUtil {

	public static ElecDetail xmlrpcRequest(String apiName, Object... arg1)
			throws Exception {
		XMLRPCClient client = new XMLRPCClient(new URL(F.XML_RPC_REQUEST_URL));
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
		HttpConnectionParams.setSoTimeout(httpParams, 5000);
		HttpResponse response = client.execute(new HttpGet(url));
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

}
