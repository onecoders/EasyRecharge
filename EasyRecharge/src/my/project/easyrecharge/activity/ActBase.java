package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.view.MyDialog;
import my.project.easyrecharge.view.MyDialog.OnOKClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ActBase extends SherlockActivity implements OnClickListener {

	private TextView title;
	protected Button abLeftBtn, abRightBtn;

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

	protected void setAbTitle(int resid) {
		title.setText(resid);
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

	public void showDialog(int titleId, int msgId, OnOKClickListener listener) {
		MyDialog dialog = new MyDialog(this);
		dialog.setTitle(titleId);
		dialog.setMessage(msgId);
		dialog.setOnOKClickListener(listener);
		dialog.show();
	}

	public void showToast(int resId) {
		Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
	}

	public void startActivity(Class<?> cls) {
		startActivity(new Intent(this, cls));
	}

}
