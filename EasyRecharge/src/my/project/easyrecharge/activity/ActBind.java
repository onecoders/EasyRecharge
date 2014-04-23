package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.BindInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Account Manager Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 充值前，先去服务器验证，输入房间号是否存在，存在，则进行绑定
 * @TODO 是否绑定，如果绑定，显示解绑；如果未绑定，显示绑定
 * @TODO 绑定成功，显示解绑界面；解绑成功，显示绑定页面
 */

public class ActBind extends ActBasicInfo {

	private Button btnBind, btnUnbind;
	private TextView bindStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_bind);
		init();
	}

	private void init() {
		initActionBar();
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
		// unbind button
		btnUnbind = (Button) findViewById(R.id.btn_unbind);
		// bind status
		bindStatus = (TextView) findViewById(R.id.bind_status);
		refreshStatus();
	}

	@Override
	protected void setExtraListener() {
		btnBind.setOnClickListener(this);
		btnUnbind.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_bind:
			doBind();
			break;
		case R.id.btn_unbind:
			doUnbind();
			break;
		default:
			break;
		}
	}

	private void doBind() {
		doCheckExist();
	}

	private void doUnbind() {
		F.unbind();
		refreshViews();
	}

	@Override
	protected void doAfterCheckOK(String content) {
		BindInfo bindInfo = initBindInfo();
		F.bind(bindInfo);
		// 显示绑定成功页面
		Bundle bundle = new Bundle();
		bundle.putString(Key.BIND_JSON, F.toJson(bindInfo));
		switchActivityAndFinish(ActResultBind.class, bundle);
	}

	private BindInfo initBindInfo() {
		BindInfo bindInfo = new BindInfo();
		bindInfo.setSchool(school);
		bindInfo.setApart(apart);
		bindInfo.setRoomNum(roomNum);
		bindInfo.setBind(true);
		return bindInfo;
	}

	private void refreshViews() {
		refreshViewsAndModels();
		refreshStatus();
	}

	private void refreshStatus() {
		btnUnbind.setVisibility(F.isBind() ? View.VISIBLE : View.GONE);
		btnBind.setVisibility(F.isBind() ? View.GONE : View.VISIBLE);
		bindStatus.setText(F.isBind() ? R.string.already_bind
				: R.string.not_bind);
	}

	@Override
	protected void resetButtonEnabled(boolean basicInfoNotEmpty) {
		// bind button enabled
		boolean enabled = !(basicInfoNotEmpty);
		btnBind.setEnabled(enabled);
	}

}
