package my.project.easyrecharge.util;

import android.content.Context;
import android.widget.Toast;

public class MToast {

	private static Toast toast = null;

	public static void showText(Context context, int resId) {
		if (toast == null) {
			toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
		} else {
			toast.setText(resId);
		}
		toast.show();
	}

}
