package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/**
 * Welcome Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActStart extends ActBase {

	private static final long DELAY_MILLIS = 3 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_start);
		init();
	}

	private void init() {
		setBackground();
		delayAndSwitch2Main();
	}

	private void setBackground() {
		View startPage = findViewById(R.id.start_page);
		int bgResId = F.isShowImage1 ? R.drawable.start_image_1
				: R.drawable.start_image_2;
		startPage.setBackgroundResource(bgResId);
		F.saveIsShowImage1();
	}

	private void delayAndSwitch2Main() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				switch2Main();
			}
		}, DELAY_MILLIS);
	}

	private void switch2Main() {
		switchActivityAndFinish(ActMain.class, null);
	}

	@Override
	public void onBackPressed() {

	}

}
