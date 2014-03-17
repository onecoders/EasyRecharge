package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.actionbarsherlock.app.SherlockActivity;

public class ActStart extends SherlockActivity {

	private static final int DELAY_MILLIS = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_start);
		init();
	}

	private void init() {
		delayAndSwitchToMain();
	}

	private void delayAndSwitchToMain() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(ActStart.this, ActMain.class);
				ActStart.this.startActivity(i);
				ActStart.this.finish();
			}
		}, DELAY_MILLIS);
	}

	@Override
	public void onBackPressed() {

	}

}
