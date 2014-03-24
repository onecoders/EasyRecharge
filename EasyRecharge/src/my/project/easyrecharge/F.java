package my.project.easyrecharge;

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

	private static final String PREFS_BIND_INFO = "prefs_bind_info";
	private static final String KEY_BIND_INFO = "key_bind_info";
	private static Gson mGson;

	public static void init(Context context) {
		initPrefAndGson(context);
		initVersionInfo(context);
		loadBindInfo();
	}

	private static void initPrefAndGson(Context context) {
		mPrefs = context.getSharedPreferences(PREFS_BIND_INFO,
				Context.MODE_PRIVATE);
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
		String json = getString(KEY_BIND_INFO);
		mBindInfo = mGson.fromJson(json, BindInfo.class);
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
		String json = mGson.toJson(bindInfo);
		saveString(KEY_BIND_INFO, json);
	}

	private static void saveString(String key, String value) {
		mEditor.putString(key, value).commit();
	}

	private static String getString(String key) {
		return mPrefs.getString(key, mGson.toJson(new BindInfo()));
	}

}
