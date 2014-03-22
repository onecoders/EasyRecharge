package my.project.easyrecharge;

import my.project.easyrecharge.util.VersionUtil;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class F {

	public static final String TAG = "easy-recharge";

	public static final String BASIC_PATH = "";

	public static final String APK_CHECK_VERSON_URL = "";
	public static final String APK_DOWNLOAD_URL = "";
	public static final String UPDATE_SAVE_NAME = "easyrecharge.apk";

	public static String VERSION_NAME;

	public static void init(Context context) {
		initVersionInfo(context);
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

}
