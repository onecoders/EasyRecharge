package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 学校选择
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActChooseSchool extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(layoutResId);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.activity_title_school_chosen);
		loadContent();
	}

	private void loadContent() {

	}

}
