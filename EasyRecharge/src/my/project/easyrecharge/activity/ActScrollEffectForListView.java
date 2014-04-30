package my.project.easyrecharge.activity;

import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ActScrollEffectForListView extends ActDataload {

	protected void setListViewHeightBasedOnChildren(ListView lv) {
		ListAdapter listAdapter = lv.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, lv);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = lv.getLayoutParams();
		params.height = totalHeight
				+ (lv.getDividerHeight() * (listAdapter.getCount() - 1))
				+ (isTablet() ? 100 : 50);
		lv.setLayoutParams(params);
	}

	private boolean isTablet() {
		Configuration config = getResources().getConfiguration();
		return (config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

}
