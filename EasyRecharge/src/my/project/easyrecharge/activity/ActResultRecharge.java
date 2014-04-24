package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.Method;
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
	private Button btnInquiry;

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
		payPrice = (TextView) findViewById(R.id.pay_price);
		btnInquiry = (Button) findViewById(R.id.btn_inquiry);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String json = bundle.getString(Key.ORDER_JSON);
			order = F.fromJson(json, F.isBind() ? OrderWithBind.class
					: OrderWithoutBind.class);
			String price = order.getPrice() + getString(R.string.rmb);
			payPrice.setText(price);
		}
		btnInquiry.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.btn_inquiry) {
			String pSchoolID = order.getSchoolId();
			String pApartID = order.getApartId();
			String pRoomNum = order.getRoomNum();
			loadDataXMLRPC(Method.QUERY_SCORE, pSchoolID, pApartID, pRoomNum);
		}
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (!apiName.equals(Method.QUERY_SCORE))
			return;
		Bundle bundle = new Bundle();
		bundle.putString(Key.SCHOOL_JSON, F.toJson(order.getSchool()));
		bundle.putString(Key.APART_JSON, F.toJson(order.getApart()));
		bundle.putString(Key.ROOM_NUM, order.getRoomNum());
		bundle.putString(Key.ELEC_JSON, content);
		switchActivityAndFinish(ActResultInquiry.class, bundle);
	}

}
