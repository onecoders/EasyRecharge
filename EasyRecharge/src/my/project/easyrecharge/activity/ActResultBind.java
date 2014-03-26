package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 绑定结果显示
 * 
 * 绑定页面先进行绑定操作（先验证绑定信息，成功后记录），绑定成功后跳至此页面
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActResultBind extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result_bind);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.result_bind);
	}

}
