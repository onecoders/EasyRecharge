package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 支付充值
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @notice Keys中的值不固定，根据用户选择的学校，先从服务器端获取，后去执行支付操作
 * @TODO 充值前，先去服务器验证，输入房间号是否存在，存在，则进行充值
 */

public class ActRecharge extends ActBasicInfo {

	private RelativeLayout priceContainer;
	private TextView priceTextView, noticeTextView;
	private CheckBox noticeCheckbox;
	private Button btnRecharge;

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
		initViews();
	}

	private void initViews() {
		// basic info
		View basicInfoView = findViewById(R.id.recharge_basic_info);
		initBasicInfoViews(basicInfoView);
		// price
		priceContainer = (RelativeLayout) findViewById(R.id.price_container);
		priceTextView = (TextView) findViewById(R.id.price_textview);
		// checkbox
		noticeCheckbox = (CheckBox) findViewById(R.id.notice_checkbox);
		// notice
		noticeTextView = (TextView) findViewById(R.id.notice_textview);
		noticeTextView.setOnClickListener(this);
		// recharge button
		btnRecharge = (Button) findViewById(R.id.btn_recharge);
		btnRecharge.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ab_right_btn:
			switchActivityReorder2Front(ActInquiry.class);
			break;
		case R.id.notice_textview:
			switchActivity(ActNotice.class);
			break;
		case R.id.btn_recharge:

			break;
		default:
			break;
		}
	}

}
