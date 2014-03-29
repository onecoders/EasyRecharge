package my.project.easyrecharge;

import java.lang.reflect.Type;

import my.project.easyrecharge.model.BindInfo;
import my.project.easyrecharge.util.VersionUtil;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;

import com.google.gson.Gson;

public class F {

	public static final String TAG = "easy-recharge";

	public static final String BASIC_PATH = "";

	public static final String APK_CHECK_VERSON_URL = "";
	public static final String APK_DOWNLOAD_URL = "";
	public static final String UPDATE_SAVE_NAME = "easyrecharge.apk";

	// 版本信息，进入应用时读取
	public static String VERSION_NAME;
	// 用户绑定信息，用户绑定时保存，进入应用时读取
	public static BindInfo mBindInfo;

	// shared preferences for saving bind info
	private static SharedPreferences mPrefs;
	private static Editor mEditor;

	private static final String PREFS_NAME = "easy_recharge";
	private static final String KEY_BIND_INFO = "key_bind_info";
	private static Gson mGson;

	public static void init(Context context) {
		initPrefAndGson(context);
		initVersionInfo(context);
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

	private static void loadBindInfo() {
		String json = getString(KEY_BIND_INFO, toJson(new BindInfo()));
		mBindInfo = fromJson(json, BindInfo.class);
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
		putString(KEY_BIND_INFO, json);
	}

	public static void putString(String key, String value) {
		mEditor.putString(key, value).commit();
	}

	public static String getString(String key, String defValue) {
		return mPrefs.getString(key, defValue);
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
