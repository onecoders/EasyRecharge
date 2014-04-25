package my.project.easyrecharge.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.Method;
import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaInfo;
import my.project.easyrecharge.model.Information;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

/**
 * Information Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActInformation extends ActDataload {

	private ListView listView;
	private List<Information> list;
	private AdaInfo adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_information);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_information);
	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.listview_info);
		list = new ArrayList<Information>();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadContent();
	}

	private void loadContent() {
		loadDataHttp(true, Method.QUERY_ANNOUNCEMENT, "?schoolId=1");
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (!apiName.equals(Method.QUERY_ANNOUNCEMENT))
			return;
		list.clear();
		try {
			Type collectionType = new TypeToken<List<Information>>() {
			}.getType();
			List<Information> newList = F.fromJson(content, collectionType);
			list.addAll(newList);
		} catch (Exception e) {
			showToast(R.string.error_data);
		}
		if (list.size() > 0) {
			setAdatper();
		}
	}

	private void setAdatper() {
		if (adapter == null) {
			adapter = new AdaInfo(this, list);
			listView.setAdapter(adapter);
			setListViewHeightBasedOnChildren(listView);
		} else {
			adapter.notifyDataSetChanged();
		}
	}

	private void setListViewHeightBasedOnChildren(ListView lv) {
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
