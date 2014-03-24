package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import android.os.Bundle;
import android.widget.Toast;

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
		setAbTitle(R.string.activity_title_bind);
		if (!F.mBindInfo.isBind()) {
			Toast.makeText(this, "用户未绑定相关信息", Toast.LENGTH_SHORT).show();
		}
	}

}
