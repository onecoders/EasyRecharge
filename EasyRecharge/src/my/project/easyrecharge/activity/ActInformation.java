package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * Information Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActInformation extends ActDataload {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_information);
		init();
	}

	private void init() {
		initActionBar();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_information);
	}

}
