package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * 用户绑定
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 充值前，先去服务器验证，输入房间号是否存在，存在，则进行绑定
 */

public class ActBind extends ActEdittextFocus {

	private RelativeLayout roomContainer;
	private EditText roomEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_bind);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.activity_title_bind);
		if (!F.mBindInfo.isBind()) {
			showToast(R.string.no_bind);
		}
		initViews();
	}

	private void initViews() {
		roomContainer = (RelativeLayout) findViewById(R.id.bind_input_room_container);
		roomEdit = (EditText) findViewById(R.id.bind_edit_room);
		setEdittextFocus(roomContainer, roomEdit);
	}

}
