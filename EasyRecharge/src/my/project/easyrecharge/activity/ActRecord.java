package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Records
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO listview显示充值记录，没有时显示empty view
 */

public class ActRecord extends ActBasicInfo {

	private Button btnInquiryRecord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_record);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_record);
	}

	private void initViews() {
		View basicInfoView = findViewById(R.id.inquiry_basic_info);
		initBasicInfoViews(basicInfoView);
	}

	@Override
	protected void findExtraView() {
		// inquiry button
		btnInquiryRecord = (Button) findViewById(R.id.btn_inquiry_record);
	}

	@Override
	protected void setExtraListener() {
		btnInquiryRecord.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_inquiry_record:
			go2Inquiry();
			break;
		default:
			break;
		}
	}

	private void go2Inquiry() {
		checkAvailable();
	}

	@Override
	protected void doAfterCheckOK(String content) {
		Bundle bundle = new Bundle();
		bundle.putString(Key.SCHOOL_ID, school.getSchoolID());
		bundle.putString(Key.APART_ID, apart.getApartID());
		bundle.putString(Key.ROOM_NUM, roomNum);
		switchActivityAndFinish(ActResultRecord.class, bundle);
	}

	@Override
	protected void refreshButtonStatus(boolean basicInfoNotEmpty) {
		// inquiry button enabled
		boolean enabled = !(basicInfoNotEmpty);
		btnInquiryRecord.setEnabled(enabled);
	}

}
