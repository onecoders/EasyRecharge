package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.BindInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Bind Result Page
 * 
 * 绑定页面先进行绑定操作（先验证绑定信息，成功后记录），绑定成功后跳至此页面
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActResultBind extends ActBase {

	private TextView txtSchool, txtApart, txtRoom;
	private Button btnConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result_bind);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.result_bind);
	}

	private void initViews() {
		txtSchool = (TextView) findViewById(R.id.rb_school_txt);
		txtApart = (TextView) findViewById(R.id.rb_apart_txt);
		txtRoom = (TextView) findViewById(R.id.rb_room_txt);
		btnConfirm = (Button) findViewById(R.id.rb_btn_confirm);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String bindJson = bundle.getString(Key.BIND_JSON);
			setViewContent(bindJson);
		}
		btnConfirm.setOnClickListener(this);
	}

	private void setViewContent(String bindJson) {
		BindInfo bindInfo = fromJson(bindJson, BindInfo.class);
		setText(txtSchool, bindInfo.getSchoolName());
		setText(txtApart, bindInfo.getApartName());
		setText(txtRoom, bindInfo.getRoomNum());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.rb_btn_confirm) {
			onBackPressed();
		}
	}

}
