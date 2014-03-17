package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

public class ActFeedback extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(layoutResId);
		init();
	}

	private void init() {
		setAbTitle(R.string.title_feedback);
	}

}
