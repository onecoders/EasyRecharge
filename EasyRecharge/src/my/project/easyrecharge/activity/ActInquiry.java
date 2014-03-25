package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * 电费查询
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActInquiry extends ActEdittextFocus {

	private RelativeLayout roomContainer;
	private EditText roomEdit;

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
		roomContainer = (RelativeLayout) findViewById(R.id.inquiry_input_room_container);
		roomEdit = (EditText) findViewById(R.id.inquiry_edit_room);
		setEdittextFocus(roomContainer, roomEdit);
	}

}
