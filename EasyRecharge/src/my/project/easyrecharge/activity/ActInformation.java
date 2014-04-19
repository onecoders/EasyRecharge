package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaInfo;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Information Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActInformation extends ActDataload {

	private ListView listView;
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
		if (F.infoList.size() > 0) {
			setAdatper();
		}
	}

	private void setAdatper() {
		if (adapter == null) {
			adapter = new AdaInfo(this, F.infoList);
			listView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
	}
}
