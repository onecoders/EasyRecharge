package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.Apart;
import my.project.easyrecharge.model.ElecDetail;
import my.project.easyrecharge.model.School;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Inquiry Result Page
 * 
 * 查询页面先进行查询操作（AsyncTask），查询成功后跳至此页面，不成功在原来页面提示
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActResultInquiry extends ActBase {

	private TextView txtSchool, txtApart, txtRoom, txtUsed, txtRemain,
			txtLastReadTime;
	private Button btnConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result_inquiry);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
		loadContent();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.result_inquiry);
	}

	private void initViews() {
		txtSchool = (TextView) findViewById(R.id.ri_school_txt);
		txtApart = (TextView) findViewById(R.id.ri_apart_txt);
		txtRoom = (TextView) findViewById(R.id.ri_room_txt);
		txtUsed = (TextView) findViewById(R.id.used_txt);
		txtRemain = (TextView) findViewById(R.id.remain_txt);
		txtLastReadTime = (TextView) findViewById(R.id.last_read_time_txt);
		btnConfirm = (Button) findViewById(R.id.ri_btn_confirm);
		btnConfirm.setOnClickListener(this);
	}

	private void loadContent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String schoolJson = bundle.getString(Key.SCHOOL_JSON);
			String apartJson = bundle.getString(Key.APART_JSON);
			String roomNum = bundle.getString(Key.ROOM_NUM);
			String elecJson = bundle.getString(Key.ELEC_JSON);
			setViewContent(schoolJson, apartJson, roomNum, elecJson);
		}
	}

	private void setViewContent(String schoolJson, String apartJson,
			String roomNum, String elecJson) {
		School school = F.fromJson(schoolJson, School.class);
		Apart apart = F.fromJson(apartJson, Apart.class);
		ElecDetail detail = F.fromJson(elecJson, ElecDetail.class);
		setText(txtSchool, school.getSchoolName());
		setText(txtApart, apart.getApartName());
		setText(txtRoom, roomNum);
		setText(txtUsed, detail.getUsedScore());
		setText(txtRemain, detail.getRemainScore());
		setText(txtLastReadTime, detail.getLastReadTime());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.ri_btn_confirm) {
			onBackPressed();
		}
	}

}
