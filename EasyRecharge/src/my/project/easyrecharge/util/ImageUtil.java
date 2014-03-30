package my.project.easyrecharge.util;

import my.project.easyrecharge.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageUtil {

	private static DisplayImageOptions options;

	public static void display(Context context, String url,
			ImageView imageView) {
		ImageLoader.getInstance().displayImage(url, imageView,
				getDisplayImageOptions());
	}

	private static synchronized DisplayImageOptions getDisplayImageOptions() {
		if (null == options) {
			options = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.ic_stub)
					.showImageForEmptyUri(R.drawable.ic_empty)
					.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
					.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565)
					.build();
		}
		return options;
	}

}
