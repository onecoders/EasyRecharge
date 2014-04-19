package my.project.easyrecharge.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.METHOD;
import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaInfo;
import my.project.easyrecharge.model.Information;
import android.os.Bundle;
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
		loadDataHttp(true, METHOD.QUERY_ANNOUNCEMENT, "?schoolId=1");
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (!apiName.equals(METHOD.QUERY_ANNOUNCEMENT))
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
		} else {
			adapter.notifyDataSetChanged();
		}
	}
}
