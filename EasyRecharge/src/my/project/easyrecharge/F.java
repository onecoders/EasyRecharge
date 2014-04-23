package my.project.easyrecharge;

import java.lang.reflect.Type;

import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.BindInfo;
import my.project.easyrecharge.util.L;
import my.project.easyrecharge.util.VersionUtil;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;

import com.google.gson.Gson;

public class F {

	public static final String TAG = "easy-recharge";

	private static final String BASIC_URL = "http://58.241.60.41:8080/";

	public static final String XML_RPC_REQUEST_URL = BASIC_URL + "api";

	public static final String HTTP_REQUEST_URL = BASIC_URL + "client/";

	public static final String NOTIFY_URL = BASIC_URL + "Alipay/PayResult";

	public static class METHOD {

		public static final String QUERY_SCORE = "query_score";

		public static final String QUERY_VERSION = "version";

		public static final String QUERY_SHCOOL = "school";

		public static final String QUERY_APART = "apart";

		public static final String QUERY_ANNOUNCEMENT = "announcement";

	}

	// 版本信息，进入应用时读取
	public static String VERSION_NAME;
	// 用户绑定信息，用户绑定时保存，进入应用时读取
	public static BindInfo mBindInfo;
	// 是否显示start_image_1
	public static boolean isShowImage1;

	// shared preferences for saving bind info
	private static SharedPreferences mPrefs;
	private static Editor mEditor;

	private static final String PREFS_NAME = "easy_recharge";
	private static Gson mGson;

	public static void init(Context context) {
		L.disableLogging();// enable log or disable log
		initPrefAndGson(context);
		initVersionInfo(context);
		loadShowImage1();
		loadBindInfo();
	}

	private static void initPrefAndGson(Context context) {
		mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		mEditor = mPrefs.edit();
		mGson = new Gson();
	}

	private static void initVersionInfo(Context context) {
		VERSION_NAME = getVersionName(context);
	}

	private static String getVersionName(Context context) {
		String versionName;
		try {
			versionName = VersionUtil.getVersionName(context);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			versionName = context.getString(R.string.default_version_name);
		}
		return versionName;
	}

	private static void loadShowImage1() {
		isShowImage1 = getBoolean(Key.SHOW_IMAGE_1, true);
	}

	public static void saveIsShowImage1() {
		putBoolean(Key.SHOW_IMAGE_1, !isShowImage1);
	}

	private static void loadBindInfo() {
		String json = getString(Key.BIND_INFO, toJson(new BindInfo()));
		mBindInfo = fromJson(json, BindInfo.class);
	}

	public static boolean isBind() {
		return mBindInfo.isBind();
	}

	public static void bind(BindInfo bindInfo) {
		saveBindInfo(bindInfo);
	}

	public static void unbind() {
		saveBindInfo(new BindInfo());
	}

	// 绑定或解绑时保存绑定信息
	private static void saveBindInfo(BindInfo bindInfo) {
		mBindInfo.updateBindInfo(bindInfo);
		String json = toJson(bindInfo);
		putString(Key.BIND_INFO, json);
	}

	public static void putString(String key, String value) {
		mEditor.putString(key, value).commit();
	}

	public static String getString(String key, String defValue) {
		return mPrefs.getString(key, defValue);
	}

	public static void putBoolean(String key, boolean value) {
		mEditor.putBoolean(key, value).commit();
	}

	public static boolean getBoolean(String key, boolean defValue) {
		return mPrefs.getBoolean(key, defValue);
	}

	public static String toJson(Object src) {
		return mGson.toJson(src);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return mGson.fromJson(json, classOfT);
	}

	public static <T> T fromJson(String json, Type typeOfT) {
		return mGson.fromJson(json, typeOfT);
	}

}
