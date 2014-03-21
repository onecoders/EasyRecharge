package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * 问题反馈
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActFeedback extends ActBase {

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
		setAbTitle(R.string.title_feedback);
		initViews();
	}

	private void initViews() {
		container = (LinearLayout) findViewById(R.id.feedback_container);
		txtFeedback = (EditText) findViewById(R.id.txt_feedback);
		setEdittextFocus();
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

	private void setEdittextFocus() {
		container.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (txtFeedback.isFocused()) {
						Rect outRect = new Rect();
						txtFeedback.getGlobalVisibleRect(outRect);
						if (!outRect.contains((int) event.getRawX(),
								(int) event.getRawY())) {
							txtFeedback.clearFocus();
							InputMethodManager imm = (InputMethodManager) v
									.getContext().getSystemService(
											Context.INPUT_METHOD_SERVICE);
							imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
						}
					}
				}
				return false;
			}
		});
	}

}
