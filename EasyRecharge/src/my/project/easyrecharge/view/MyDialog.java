package my.project.easyrecharge.view;

import my.project.easyrecharge.R;
import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends Dialog implements OnClickListener {

	public interface OnOKClickListener {
		public void onOKClick();
	}

	private Button okBtn;
	private Button cancelBtn;
	private TextView mTitle;
	private TextView mMessage;
	private View v;

	private Context mContext;

	private OnOKClickListener listener;

	public MyDialog(Context context) {
		super(context);
		mContext = context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog);
		v = getWindow().getDecorView();
		v.setBackgroundResource(android.R.color.transparent);
		mTitle = (TextView) findViewById(R.id.dialogTitle);
		mMessage = (TextView) findViewById(R.id.dialogMessage);
		okBtn = (Button) findViewById(R.id.OkButton);
		okBtn.setOnClickListener(this);
		cancelBtn = (Button) findViewById(R.id.cancelButton);
		cancelBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.OkButton:
			listener.onOKClick();
			break;
		case R.id.cancelButton:
			dismiss();
			break;
		default:
			break;
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
		mTitle.setText(title);
	}

	@Override
	public void setTitle(int titleId) {
		super.setTitle(titleId);
		mTitle.setText(titleId);
	}

	public void setMessage(CharSequence message) {
		mMessage.setText(message);
		mMessage.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

	public void setMessage(int messageId) {
		mMessage.setText(mContext.getResources().getString(messageId));
		mMessage.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

	public void setOnOKClickListener(OnOKClickListener listener) {
		this.listener = listener;
	}

}
