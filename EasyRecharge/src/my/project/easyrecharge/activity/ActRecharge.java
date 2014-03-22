package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;
import android.view.View;

/**
 * 支付充值
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @notice Keys中的值不固定，根据用户选择的学校，先从服务器端获取，后去执行支付操作
 */

public class ActRecharge extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_recharge);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.activity_title_recharge);
		showAbRightBtn();
		setAbRightBtnText(R.string.recharge_right_btn_text);
		setAbRightBtnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.ab_right_btn) {
			switchActivityReorder2Front(ActInquiry.class);
		}
	}

}
