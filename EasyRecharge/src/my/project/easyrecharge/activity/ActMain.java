package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.view.MyDialog.OnOKClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class ActMain extends ActBase implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
	}

	@Override
	protected View loadABCustomView() {
		View abView = LayoutInflater.from(this).inflate(R.layout.ab_main, null);
		return abView;
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onBackPressed() {
		showDialog(R.string.exit_dialog_title, R.string.exit_dialog_message,
				new OnOKClickListener() {

					@Override
					public void onOKClick() {
						exit();
					}
				});
	}

	private void exit() {
		finish();
		System.exit(0);
	}

}
