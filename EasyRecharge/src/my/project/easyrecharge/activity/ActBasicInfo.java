package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.RequestCode;
import my.project.easyrecharge.view.ClearEditText;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ActBasicInfo extends ActEdittextFocus {

	protected RelativeLayout roomContainer, schoolContainer, buildingContainer;
	protected TextView schoolTextView, buildingTextView;
	protected ClearEditText roomEdit;

	protected void initBasicInfoViews(View basicInfoView) {
		// school
		schoolContainer = (RelativeLayout) basicInfoView
				.findViewById(R.id.school_container);
		schoolContainer.setOnClickListener(this);
		schoolTextView = (TextView) basicInfoView
				.findViewById(R.id.school_textview);
		// building
		buildingContainer = (RelativeLayout) basicInfoView
				.findViewById(R.id.building_container);
		buildingContainer.setOnClickListener(this);
		buildingTextView = (TextView) basicInfoView
				.findViewById(R.id.building_textview);
		// room
		roomContainer = (RelativeLayout) basicInfoView
				.findViewById(R.id.room_container);
		roomContainer.setOnClickListener(this);
		roomEdit = (ClearEditText) basicInfoView
				.findViewById(R.id.room_edittext);
		setEdittextFocus(roomContainer, roomEdit);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.school_container:
			switchActivity(ActChooseSchool.class);
			break;
		case R.id.building_container:
			switchActivity(ActChooseBuilding.class);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case RequestCode.CHOOSE_SCHOOL:

				break;
			case RequestCode.CHOOSE_BUILDING:

				break;
			default:
				break;
			}
		}
	}

}
