package my.project.easyrecharge.activity;

import java.util.List;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.School;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 学校选择
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 只获取学校的id/name/icon信息，用于显示和选择
 * @TODO invoke setResult() after chosen before return
 */

public class ActChooseSchool extends ActDataload implements OnItemClickListener {

	private ListView schoolListView;
	private List<School> schoolList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_choose_school);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
		loadContent();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_school_chosen);
	}

	private void initViews() {
		schoolListView = (ListView) findViewById(R.id.listview_school);
		schoolListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		School school = schoolList.get(arg2);
		Intent intent = new Intent();
		intent.putExtra(Key.SCHOOL_JSON, F.toJson(school));
		setResult(RESULT_OK, intent);
		finish();
	}

	private void loadContent() {
		// 请求数据
		// schoolList = newSchoolList
		// 刷新school listview
		// schoolListView.setAdapter(new AdaSchool(this, schoolList));
	}

}
