package my.project.easyrecharge.activity;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.alipay.Keys;
import my.project.easyrecharge.alipay.Result;
import my.project.easyrecharge.alipay.Rsa;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.Order;
import my.project.easyrecharge.model.OrderWithBind;
import my.project.easyrecharge.model.OrderWithoutBind;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.android.app.sdk.AliPay;

/**
 * Recharge Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @notice Keys中的值不固定，根据用户选择的学校，先从服务器端获取，后去执行支付操作
 * @TODO 充值前，先去服务器验证，输入房间号是否存在，存在，则进行充值
 */

public class ActRecharge extends ActBasicInfo implements
		OnCheckedChangeListener {

	private static final int RQF_PAY = 1;

	private RelativeLayout priceContainer;
	private TextView priceTextView, noticeTextView;
	private CheckBox noticeCheckbox;
	private Button btnRecharge;

	private String price;
	private int checkedItem;
	private String[] priceList;
	private int[] priceArr = new int[] { 5, 10, 20, 50, 100 };

	private Order order;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_recharge);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_recharge);
		showAbRightBtn();
		setAbRightBtnText(R.string.txt_inquiry);
		setAbRightBtnClickListener(this);
	}

	private void initViews() {
		// basic info
		View basicInfoView = findViewById(R.id.recharge_basic_info);
		initBasicInfoViews(basicInfoView);
	}

	@Override
	protected void findExtraView() {
		// price
		priceContainer = (RelativeLayout) findViewById(R.id.price_container);
		priceTextView = (TextView) findViewById(R.id.price_textview);
		// checkbox
		noticeCheckbox = (CheckBox) findViewById(R.id.notice_checkbox);
		// notice
		noticeTextView = (TextView) findViewById(R.id.notice_textview);
		// recharge button
		btnRecharge = (Button) findViewById(R.id.btn_recharge);
		// price string array
		priceList = getResources().getStringArray(R.array.recharge_price_list);
	}

	@Override
	protected void setExtraListener() {
		noticeCheckbox.setOnCheckedChangeListener(this);
		noticeTextView.setOnClickListener(this);
		priceContainer.setOnClickListener(this);
		btnRecharge.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ab_right_btn:
			switchActivityReorder2Front(ActInquiry.class);
			break;
		case R.id.price_container:
			showPriceChooseDialog();
			break;
		case R.id.notice_textview:
			switchActivity(ActNotice.class, null);
			break;
		case R.id.btn_recharge:
			doRecharge();
			break;
		default:
			break;
		}
	}

	private void showPriceChooseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.choose_price);
		builder.setSingleChoiceItems(priceList, checkedItem,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						checkedItem = which;
						priceTextView.setText(priceList[which]);
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

	private void doRecharge() {
		initOrder();
		// go2Pay();// must check first
		doPay();
	}

	private void initOrder() {
		if (F.isBind()) {
			order = new OrderWithBind(F.mBindInfo);
		} else {
			order = new OrderWithoutBind(school, apart, roomNum);
		}
		order.setPrice(priceArr[checkedItem]);
	}

	private void go2Pay() {
		if (F.isBind()) {
			doPay();
		} else {
			checkFirst();
		}
	}

	@Override
	protected void doAfterCheckOK() {
		doPay();
	}

	private void doPay() {
		try {
			String info = getNewOrderInfo();
			String sign = Rsa.sign(info, Keys.PRIVATE);
			sign = URLEncoder.encode(sign);
			info += "&sign=\"" + sign + "\"&" + getSignType();
			Log.i(F.TAG, "start pay");
			// start the pay.
			Log.i(F.TAG, "info = " + info);
			final String orderInfo = info;
			new Thread() {

				public void run() {
					AliPay alipay = new AliPay(ActRecharge.this, mHandler);

					// 设置为沙箱模式，不设置默认为线上环境
					// alipay.setSandBox(true);

					String result = alipay.pay(orderInfo);
					Log.i(F.TAG, "result = " + result);
					Message msg = new Message();
					msg.what = RQF_PAY;
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			}.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			Toast.makeText(this, R.string.remote_call_failed,
					Toast.LENGTH_SHORT).show();
		}
	}

	private String getNewOrderInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("partner=\"");
		sb.append(order.getPartnerId());
		sb.append("\"&out_trade_no=\"");
		sb.append(getOutTradeNo());
		sb.append("\"&subject=\"");
		sb.append(order.getSubject());
		sb.append("\"&body=\"");
		sb.append(order.getBody());
		sb.append("\"&total_fee=\"");
		sb.append(order.getPrice());
		sb.append("\"&notify_url=\"");

		// 网址需要做URL编码
		sb.append(URLEncoder.encode("http://notify.java.jpxx.org/index.jsp"));
		sb.append("\"&service=\"mobile.securitypay.pay");
		sb.append("\"&_input_charset=\"UTF-8");
		sb.append("\"&return_url=\"");
		sb.append(URLEncoder.encode("http://m.alipay.com"));
		sb.append("\"&payment_type=\"1");
		sb.append("\"&seller_id=\"");
		sb.append(order.getSellerAccount());

		// 如果show_url值为空，可不传
		// sb.append("\"&show_url=\"");
		sb.append("\"&it_b_pay=\"1m");
		sb.append("\"");

		return new String(sb);
	}

	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
		String key = format.format(date);

		java.util.Random r = new java.util.Random();
		key += r.nextInt();
		key = key.substring(0, 15);
		Log.d(F.TAG, "outTradeNo: " + key);
		return key;
	}

	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Result result = new Result((String) msg.obj);
			switch (msg.what) {
			case RQF_PAY:
				Toast.makeText(ActRecharge.this, result.getResult(),
						Toast.LENGTH_SHORT).show();
				boolean success = false;
				if (success) {
					Bundle bundle = new Bundle();
					bundle.putInt(Key.PAY_PRICE, order.getPrice());
					switchActivityAndFinish(ActResultRecharge.class, bundle);
				}
				break;
			default:
				break;
			}
		};
	};

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		resetButtonEnabled();
	}

	@Override
	protected void resetButtonEnabled(boolean isBasicInfoEmpty) {
		price = priceTextView.getText().toString();
		boolean isUnchecked = !noticeCheckbox.isChecked();
		// recharge button enabled
		boolean enabled = !(isBasicInfoEmpty || isEmpty(price) || isUnchecked);
		btnRecharge.setEnabled(enabled);
	}

}
