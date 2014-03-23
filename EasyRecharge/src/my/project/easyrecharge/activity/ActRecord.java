package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 充值记录
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActRecord extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_record);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.activity_title_record);
	}

}
