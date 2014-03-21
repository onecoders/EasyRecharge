package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;
import android.view.View;

/**
 * 电费查询
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActInquiry extends ActBase {

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
		setAbRightBtnText(R.string.inquiry_right_btn_text);
		setAbRightBtnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.ab_right_btn) {
			switchActivityReorder2Front(ActRecharge.class);
		}
	}

}
