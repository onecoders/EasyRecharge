package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.model.Order;
import my.project.easyrecharge.model.OrderWithBind;
import my.project.easyrecharge.model.OrderWithoutBind;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 支付充值
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @notice Keys中的值不固定，根据用户选择的学校，先从服务器端获取，后去执行支付操作
 * @TODO 充值前，先去服务器验证，输入房间号是否存在，存在，则进行充值
 */

public class ActRecharge extends ActBasicInfo implements
		OnCheckedChangeListener {

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
			switchActivity(ActNotice.class);
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
		go2Pay();
	}

	private void initOrder() {
		if (F.mBindInfo.isBind()) {
			order = new OrderWithBind();
		} else {
			order = new OrderWithoutBind();
			order.setSchool(school);
			order.setBuildingNo(buildingNo);
			order.setRoomNo(roomNo);
		}
		order.setPrice(priceArr[checkedItem]);
	}

	private void go2Pay() {
		if (F.mBindInfo.isBind()) {
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
		// invoke alipay method
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		resetButtonEnabled();
	}

	@Override
	protected void resetButtonEnabled(boolean isBasicInfoEmpty) {
		price = priceTextView.getText().toString();
		boolean isChecked = noticeCheckbox.isChecked();
		// recharge button enabled
		boolean enabled = !(isBasicInfoEmpty || isEmpty(price) || isChecked);
		btnRecharge.setEnabled(enabled);
	}

}
