package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 使用注意事项
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActNotice extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_notice);
		init();
	}

	private void init() {
		initActionBar();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_notice);
	}

}
