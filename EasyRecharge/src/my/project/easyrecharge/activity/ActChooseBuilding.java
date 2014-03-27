package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import android.os.Bundle;

/**
 * 楼号选择
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO invoke setResult() after chosen before return
 */

public class ActChooseBuilding extends ActBase {

	private int schoolId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_choose_building);
		init();
	}

	private void init() {
		initActionBar();
		loadContent();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_building_chosen);
	}

	private void loadContent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			schoolId = bundle.getInt(Key.SCHOOL_ID);
			loadBuildingInfo();
		}
	}

	private void loadBuildingInfo() {

	}

}
