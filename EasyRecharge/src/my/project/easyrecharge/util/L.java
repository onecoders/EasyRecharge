package my.project.easyrecharge.util;

import android.util.Log;

public class L {

	private static volatile boolean DISABLED = false;

	private L() {

	}

	public static void enableLogging() {
		DISABLED = false;
	}

	public static void disableLogging() {
		DISABLED = true;
	}

	public static void d(String tag, String msg) {
		if (DISABLED)
			return;
		Log.d(tag, msg);
	}

	public static void i(String tag, String msg) {
		if (DISABLED)
			return;
		Log.i(tag, msg);
	}

}
