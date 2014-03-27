package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 用户绑定
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 充值前，先去服务器验证，输入房间号是否存在，存在，则进行绑定
 */

public class ActBind extends ActBasicInfo {

	private Button btnBind;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_bind);
		init();
	}

	private void init() {
		initActionBar();
		if (!F.mBindInfo.isBind()) {
			showToast(R.string.no_bind);
		}
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_bind);
	}

	private void initViews() {
		// basic info
		View basicInfoView = findViewById(R.id.bind_basic_info);
		initBasicInfoViews(basicInfoView);
	}

	@Override
	protected void findExtraView() {
		// bind button
		btnBind = (Button) findViewById(R.id.btn_bind);
	}

	@Override
	protected void setExtraListener() {
		btnBind.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_bind:

			break;
		default:
			break;
		}
	}

	@Override
	protected void resetButtonEnabled(boolean basicInfoNotEmpty) {
		// bind button enabled
		boolean enabled = !(basicInfoNotEmpty);
		btnBind.setEnabled(enabled);
	}

}
