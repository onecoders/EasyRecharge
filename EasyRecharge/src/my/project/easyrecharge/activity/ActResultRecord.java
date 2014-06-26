package my.project.easyrecharge.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import my.project.easyrecharge.F.Method;
import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaRecord;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.Record;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

public class ActResultRecord extends ActScrollEffectForListView {

	private ListView listView;
	private List<Record> list;
	private AdaRecord adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result_record);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_record);
	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.listview_record);
		list = new ArrayList<Record>();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadContent();
	}

	private void loadContent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String params = initParams(bundle);
			loadDataVolley(true, Method.QUERY_RECORD, params);
		}
	}

	private String initParams(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		sb.append("?schoolId=");
		sb.append(bundle.getString(Key.SCHOOL_ID));
		sb.append("&apartId=");
		sb.append(bundle.getString(Key.APART_ID));
		sb.append("&roomname=");
		sb.append(bundle.getString(Key.ROOM_NUM));
		return sb.toString();
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (!apiName.equals(Method.QUERY_RECORD))
			return;
		list.clear();
		try {
			Type collectionType = new TypeToken<List<Record>>() {
			}.getType();
			List<Record> newList = fromJson(content, collectionType);
			list.addAll(newList);
		} catch (Exception e) {
			e.printStackTrace();
			showToast(R.string.error_data);
		}
		if (list.size() > 0) {
			setAdatper();
		} else {
			showToast(R.string.no_record);
		}
	}

	private void setAdatper() {
		if (adapter == null) {
			adapter = new AdaRecord(this, list);
			listView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
		setListViewHeightBasedOnChildren(listView);
	}

}
