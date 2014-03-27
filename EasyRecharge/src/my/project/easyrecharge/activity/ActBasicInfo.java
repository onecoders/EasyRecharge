package my.project.easyrecharge.activity;

import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.RequestCode;
import my.project.easyrecharge.view.ClearEditText;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class ActBasicInfo extends ActEdittextFocus implements
		TextWatcher {

	protected RelativeLayout roomContainer, schoolContainer, buildingContainer;
	protected TextView schoolTextView, buildingTextView;
	protected ClearEditText roomEdit;

	protected String school, building, room;

	protected void initBasicInfoViews(View basicInfoView) {
		findView(basicInfoView);
		setListener();
	}

	protected void findView(View basicInfoView) {
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
		findExtraView();
	}

	protected abstract void findExtraView();

	protected void setListener() {
		setEdittextFocus(roomContainer, roomEdit);
		schoolTextView.addTextChangedListener(this);
		buildingTextView.addTextChangedListener(this);
		roomEdit.addTextChangedListener(this);
		setExtraListener();
	}

	protected abstract void setExtraListener();

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.school_container:
			switchActivityForResult(ActChooseSchool.class,
					RequestCode.CHOOSE_SCHOOL, null);
			break;
		case R.id.building_container:
			switchActivityForResult(ActChooseBuilding.class,
					RequestCode.CHOOSE_BUILDING, null);
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

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		resetButtonEnabled();
	}

	protected void resetButtonEnabled() {
		boolean isBasicInfoEmpty = isBasicInfoEmpty();
		resetButtonEnabled(isBasicInfoEmpty);
	}

	private boolean isBasicInfoEmpty() {
		school = schoolTextView.getText().toString();
		building = buildingTextView.getText().toString();
		room = roomEdit.getText().toString();
		return isEmpty(school) || isEmpty(building) || isEmpty(room);
	}

	protected abstract void resetButtonEnabled(boolean basicInfoNotEmpty);

}
