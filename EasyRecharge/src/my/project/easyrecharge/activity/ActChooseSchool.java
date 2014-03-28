package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 学校选择
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 只获取学校的id/name/icon信息，用于显示和选择
 * @TODO invoke setResult() after chosen before return
 */

public class ActChooseSchool extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_choose_school);
		init();
	}

	private void init() {
		initActionBar();
		loadContent();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_school_chosen);
	}

	private void loadContent() {

	}

}
