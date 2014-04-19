package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Recharge Result Page
 * 
 * 充值页面先进行充值操作（AsyncTask，先验证充值信息，成功后充值），充值成功后跳至此页面
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActResultRecharge extends ActBase {

	private TextView payPrice;
	private Button btnInquiry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result_recharge);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.result_recharge);
	}

	private void initViews() {
		payPrice = (TextView) findViewById(R.id.pay_price);
		btnInquiry = (Button) findViewById(R.id.btn_inquiry);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String price = bundle.getString(Key.PAY_PRICE)
					+ getString(R.string.rmb);
			payPrice.setText(price);
		}
		btnInquiry.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.btn_inquiry) {
			switchActivityAndFinish(ActInquiry.class, null);
		}
	}

}
