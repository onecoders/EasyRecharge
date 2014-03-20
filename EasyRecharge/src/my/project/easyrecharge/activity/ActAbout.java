package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 关于
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActAbout extends ActBase {

	private TextView appInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_about);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.title_about);
		initAppInfo();
	}

	private void initAppInfo() {
		appInfo = (TextView) findViewById(R.id.app_info);
		String info = buildeAppInfo();
		appInfo.setText(info);
	}

	private String buildeAppInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(getString(R.string.about_version));
		sb.append(F.VERSION_NAME);
		return sb.toString();
	}

}
