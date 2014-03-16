package my.project.easyrecharge.activity;

import my.project.easyrecharge.view.MyDialog;
import my.project.easyrecharge.view.MyDialog.OnOKClickListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public abstract class ActBase extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActionBar();
	}

	private void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		// Get custom view
		View customerView = loadABCustomView();
		// Now set custom view
		initActionBarAndSetCustomView(actionBar, customerView);
	}

	protected abstract View loadABCustomView();

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

}
