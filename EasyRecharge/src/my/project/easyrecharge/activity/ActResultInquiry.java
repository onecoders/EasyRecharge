package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import android.os.Bundle;

/**
 * 绑定结果显示
 * 
 * 查询页面先进行查询操作（AsyncTask），查询成功后跳至此页面，不成功在原来页面提示
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ActResultInquiry extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result_inquiry);
		init();
	}

	private void init() {
		initActionBar();
		setAbTitle(R.string.result_inquiry);
	}

}
