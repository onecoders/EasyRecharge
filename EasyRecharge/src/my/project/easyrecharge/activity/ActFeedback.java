package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * 问题反馈
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActFeedback extends ActEdittextFocus {

	private LinearLayout container;
	private EditText txtFeedback;
	private Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_feedback);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_feedback);
	}

	private void initViews() {
		container = (LinearLayout) findViewById(R.id.feedback_container);
		txtFeedback = (EditText) findViewById(R.id.txt_feedback);
		setEdittextFocus(container, txtFeedback);
		submit = (Button) findViewById(R.id.btn_submit);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.btn_submit) {
			checkInputAndSubmit();
		}
	}

	private void checkInputAndSubmit() {
		String input = txtFeedback.getText().toString();
		boolean valid = checkInput(input);
		if (valid) {
			doSubmit(input);
		} else {
			showToast(R.string.hint_input_invalid);
		}
	}

	private boolean checkInput(String input) {
		return !TextUtils.isEmpty(input.trim());
	}

	private void doSubmit(String input) {
		new SubmitTask().execute(input);
	}

	class SubmitTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressHUD();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			// simulation
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// do real things
			submit(params[0]);
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			dismissProgressHUD();
			if (result) {
				clearEdittext();
				showToast(R.string.hint_feedback_succeed);
			} else {
				showToast(R.string.hint_feedback_failed);
			}
		}

	}

	private void submit(String input) {
		// do real submit
	}

	private void clearEdittext() {
		txtFeedback.setText("");
	}

}
