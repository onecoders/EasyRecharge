package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 电费查询
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActInquiry extends ActBasicInfo {

	private Button btnInquiry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_inquiry);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_inquiry);
		showAbRightBtn();
		setAbRightBtnText(R.string.txt_recharge);
		setAbRightBtnClickListener(this);
	}

	private void initViews() {
		View basicInfoView = findViewById(R.id.inquiry_basic_info);
		initBasicInfoViews(basicInfoView);
	}

	@Override
	protected void findExtraView() {
		// inquiry button
		btnInquiry = (Button) findViewById(R.id.btn_inquiry);
	}

	@Override
	protected void setExtraListener() {
		btnInquiry.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ab_right_btn:
			switchActivityReorder2Front(ActRecharge.class);
			break;
		case R.id.btn_inquiry:
			go2Inquiry();
			break;
		default:
			break;
		}
	}

	private void go2Inquiry() {
		if (F.mBindInfo.isBind()) {
			doInquiry();
		} else {
			checkFirst();
		}
	}

	@Override
	protected void doAfterCheckOK() {
		doInquiry();
	}

	private void doInquiry() {
		// do real inquiry
	}

	@Override
	protected void resetButtonEnabled(boolean basicInfoNotEmpty) {
		// inquiry button enabled
		boolean enabled = !(basicInfoNotEmpty);
		btnInquiry.setEnabled(enabled);
	}

}
