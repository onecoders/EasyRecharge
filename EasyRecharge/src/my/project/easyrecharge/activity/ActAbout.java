package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

public class ActAbout extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_about);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.title_about);
	}

}
