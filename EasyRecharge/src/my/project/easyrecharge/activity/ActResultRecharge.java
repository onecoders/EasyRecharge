package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 绑定结果显示
 * 
 * 充值页面先进行充值操作（AsyncTask，先验证充值信息，成功后充值），充值成功后跳至此页面
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActResultRecharge extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result_recharge);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.result_recharge);
	}

}
