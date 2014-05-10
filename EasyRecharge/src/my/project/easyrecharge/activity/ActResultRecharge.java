package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.Order;
import my.project.easyrecharge.model.OrderWithBind;
import my.project.easyrecharge.model.OrderWithoutBind;
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

public class ActResultRecharge extends ActDataload {

	private TextView payPrice;
	private Button btnBack;

	private Order order;

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
		payPrice = (TextView) findViewById(R.id.rr_pay_price);
		btnBack = (Button) findViewById(R.id.rr_btn_confirm);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String json = bundle.getString(Key.ORDER_JSON);
			order = fromJson(json, F.isBind() ? OrderWithBind.class
					: OrderWithoutBind.class);
			String price = order.getPrice() + getString(R.string.rmb);
			setText(payPrice, price);
		}
		btnBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.rr_btn_confirm) {
			onBackPressed();
		}
	}

}
