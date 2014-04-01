package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;
import android.os.Handler;

/**
 * 起始欢迎页
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActStart extends ActUpdateApk {

	private static final long DELAY_MILLIS = 3 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_start);
		init();
	}

	private void init() {
		getNoticeAndUpdateInfo();
	}

	private void getNoticeAndUpdateInfo() {
		if (isNetworkConnected()) {
			checkUpdate();
		} else {
			delayAndSwitch2Main();
		}
		// 没有网络时提示没有网络，直接进应用
		// 有网络，获取通知信息和更新信息
		// 通知信息对话框，用户按确定后进入主界面
		// 更新信息，点击更新，下载更新，不进入主界面
		// 点击不更新，直接进入主界面
	}

	@Override
	protected void noNewVersion() {
		switch2Main();
	}

	@Override
	protected void notUpdateNow() {
		switch2Main();
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
		switchActivity(ActMain.class);
		finish();
	}

	@Override
	public void onBackPressed() {

	}

	@Override
	protected void initAbContent() {

	}

}
