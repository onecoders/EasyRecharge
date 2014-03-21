package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 问题反馈
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActFeedback extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_feedback);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.title_feedback);
	}

}
