package my.project.easyrecharge.activity;

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
		setAbTitle(R.string.activity_title_inquiry);
		showAbRightBtn();
		setAbRightBtnText(R.string.txt_recharge);
		setAbRightBtnClickListener(this);
		initViews();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.ab_right_btn) {
			switchActivityReorder2Front(ActRecharge.class);
		}
	}

	private void initViews() {
		View basicInfoView = findViewById(R.id.inquiry_basic_info);
		initBasicInfoViews(basicInfoView);
		// inquiry button
		btnInquiry = (Button) findViewById(R.id.btn_inquiry);
		btnInquiry.setOnClickListener(this);
	}

}
