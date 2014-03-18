package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

public class ActInquiry extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(layoutResId);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.title_inquiry);
	}

}
