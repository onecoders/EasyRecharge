package my.project.easyrecharge.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.METHOD;
import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaApart;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.Apart;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

/**
 * Apart Choose Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 根据选择的school的id获取所有的楼号，用于显示和选择
 * @TODO invoke setResult() after chosen before return
 */

public class ActChooseApart extends ActDataload implements OnItemClickListener {

	private String schoolId;

	private ListView listView;
	private List<Apart> list;
	private AdaApart adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_choose_building);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_building_chosen);
	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.listview_building);
		listView.setOnItemClickListener(this);
		list = new ArrayList<Apart>();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Apart apart = list.get(position);
		Intent intent = new Intent();
		intent.putExtra(Key.APART_JSON, F.toJson(apart));
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadContent();
	}

	private void loadContent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			schoolId = bundle.getString(Key.SCHOOL_ID);
			loadBuildingInfo();
		}
	}

	private void loadBuildingInfo() {
		loadDataHttp(true, METHOD.QUERY_APART, "?schoolId=" + schoolId);
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (!apiName.equals(METHOD.QUERY_APART)) {
			return;
		}
		list.clear();
		try {
			Type collectionType = new TypeToken<List<Apart>>() {
			}.getType();
			List<Apart> newList = F.fromJson(content, collectionType);
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
			adapter = new AdaApart(this, list);
			listView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
	}

}
