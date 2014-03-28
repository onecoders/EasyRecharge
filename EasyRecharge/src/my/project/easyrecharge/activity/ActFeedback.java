package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 问题反馈
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActFeedback extends ActEdittextFocus {

	private LinearLayout container;
	private EditText mContent;
	private Button submit;
	private TextView mHasNum;
	private static final int LIMIT_SIZE = 200;

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
		findView();
		setListener();
	}

	private void findView() {
		container = (LinearLayout) findViewById(R.id.feedback_container);
		mContent = (EditText) findViewById(R.id.txt_feedback);
		mHasNum = (TextView) findViewById(R.id.txt_left_indicator);
		mHasNum.setText(String.valueOf(LIMIT_SIZE));
		submit = (Button) findViewById(R.id.btn_submit);
	}

	private void setListener() {
		setTextChangedListener();
		setEdittextFocus(container, mContent);
		submit.setOnClickListener(this);
	}

	private void setTextChangedListener() {
		mContent.addTextChangedListener(new TextWatcher() {

			private CharSequence mTemp;
			private int mSelectionStart;
			private int mSelectionEnd;

			@Override
			public void onTextChanged(CharSequence charSequence, int start,
					int before, int count) {
				mTemp = charSequence;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				int number = LIMIT_SIZE - editable.length();
				mHasNum.setText(String.valueOf(number));
				mSelectionStart = mContent.getSelectionStart();
				mSelectionEnd = mContent.getSelectionEnd();
				if (mTemp.length() > LIMIT_SIZE) {
					editable.delete(mSelectionStart - 1, mSelectionEnd);// 删掉多输入的文字
					int tempSelection = mSelectionEnd;
					mContent.setText(editable);
					mContent.setSelection(tempSelection);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.btn_submit) {
			checkInputAndSubmit();
		}
	}

	private void checkInputAndSubmit() {
		String input = mContent.getText().toString();
		boolean valid = checkInput(input);
		if (valid) {
			doSubmit(input);
		} else {
			showToast(R.string.hint_input_invalid);
		}
	}

	private boolean checkInput(String input) {
		return !isEmpty(input.trim());
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
		mContent.setText("");
	}

}
