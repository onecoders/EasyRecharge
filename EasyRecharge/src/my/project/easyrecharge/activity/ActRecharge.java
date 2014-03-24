package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 支付充值
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @notice Keys中的值不固定，根据用户选择的学校，先从服务器端获取，后去执行支付操作
 */

public class ActRecharge extends ActBase {

	private TextView txtNotice;

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
		setAbRightBtnText(R.string.txt_inquiry);
		setAbRightBtnClickListener(this);
		initView();
	}

	private void initView() {
		txtNotice = (TextView) findViewById(R.id.txt_notice);
		txtNotice.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ab_right_btn:
			switchActivityReorder2Front(ActInquiry.class);
			break;
		case R.id.txt_notice:
			switchActivity(ActNotice.class);
			break;
		default:
			break;
		}
	}

}
