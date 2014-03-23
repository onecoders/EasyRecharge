package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.util.MToast;
import my.project.easyrecharge.view.NewAlertDialog;
import my.project.easyrecharge.view.NewAlertDialog.OnLeftBtnClickListener;
import my.project.easyrecharge.view.ProgressHUD;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

/**
 * 基类
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActBase extends SherlockActivity implements OnClickListener,
		OnCancelListener {

	private TextView title;
	protected Button abLeftBtn, abRightBtn;

	private ProgressHUD mProgressHUD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		// Get custom view
		View customerView = loadABCustomView();
		// Now set custom view
		initActionBarAndSetCustomView(actionBar, customerView);
	}

	private View loadABCustomView() {
		View abView = LayoutInflater.from(this).inflate(R.layout.ab_main, null);
		abLeftBtn = (Button) abView.findViewById(R.id.ab_left_btn);
		abLeftBtn.setOnClickListener(this);
		title = (TextView) abView.findViewById(R.id.ab_title);
		abRightBtn = (Button) abView.findViewById(R.id.ab_right_btn);
		return abView;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.ab_left_btn) {
			onBackPressed();
		}
	}

	protected void setAbLeftBtnText(int resid) {
		abLeftBtn.setText(resid);
	}

	protected void setAbTitle(int resid) {
		title.setText(resid);
	}

	protected void showAbRightBtn() {
		abRightBtn.setVisibility(View.VISIBLE);
	}

	protected void setAbRightBtnText(int resid) {
		abRightBtn.setText(resid);
	}

	protected void setAbRightBtnClickListener(OnClickListener listener) {
		abRightBtn.setOnClickListener(listener);
	}

	private static void initActionBarAndSetCustomView(ActionBar actionBar,
			View customerView) {
		// set LayoutParams
		ActionBar.LayoutParams params = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
		// Set display to custom next
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// Do any other config to the action bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		// Now set custom view
		actionBar.setCustomView(customerView, params);
	}

	protected void showDialog(int titleId, int msgId, int leftBtnText,
			int rightBtnText, OnLeftBtnClickListener listener) {
		showDialog(titleId, getString(msgId), leftBtnText, false, rightBtnText,
				listener);
	}

	protected void showDialog(int titleId, String msg, int leftBtnText,
			boolean hideRightBtn, int rightBtnText,
			OnLeftBtnClickListener listener) {
		NewAlertDialog dialog = new NewAlertDialog(this);
		dialog.setTitle(titleId);
		dialog.setMessage(msg);
		dialog.setLeftBtnText(leftBtnText);
		dialog.setOnLeftBtnClickListener(listener);
		if (hideRightBtn) {
			dialog.hideRightBtn();
		} else {
			dialog.setRightBtnText(rightBtnText);
		}
		dialog.show();
	}

	protected void showProgressHUD() {
		mProgressHUD = ProgressHUD.show(this,
				getString(R.string.hint_when_loading), true, false, this);
	}

	protected void setMessage(String message) {
		mProgressHUD.setMessage(message);
	}

	protected void updateMessage(float progress) {
		setMessage(getString(R.string.downloading) + progress + "%");
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		dismissProgressHUD();
	}

	protected void dismissProgressHUD() {
		mProgressHUD.dismiss();
	}

	protected void showToast(int resId) {
		MToast.showText(this, resId);
	}

	protected void switchActivity(Class<?> cls) {
		startActivity(new Intent(this, cls));
		actDyncAnimate();
	}

	protected void switchActivityReorder2Front(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
		actDyncAnimate();
	}

	private void actDyncAnimate() {
		// 设置切换动画，从右边进入，左边退出,带动态效果
		overridePendingTransition(R.anim.new_dync_in_from_right,
				R.anim.new_dync_out_to_left);
	}

	protected void actFadeAnimate() {
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	protected void actZoomAnimate() {
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
	}

}
