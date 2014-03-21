package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 用户绑定
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActBind extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_bind);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.title_bind);
	}

}
