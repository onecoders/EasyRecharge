package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.Method;
import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.contants.RequestCode;
import my.project.easyrecharge.model.Apart;
import my.project.easyrecharge.model.School;
import my.project.easyrecharge.model.VersionServer;
import my.project.easyrecharge.view.ClearEditText;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Basic Information Choose Page, including School/Apart/Room
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 先获取school的id/name/icon信息，支付时，获取所选的alipay信息
 */

public abstract class ActBasicInfo extends ActEdittextFocus implements
		TextWatcher {

	protected RelativeLayout roomContainer, schoolContainer, apartContainer;
	protected TextView schoolTextView, apartTextView;
	protected ClearEditText roomEdit;

	protected School school;
	protected Apart apart;
	protected String roomNum;

	protected void initBasicInfoViews(View basicInfoView) {
		findView(basicInfoView);
		setListener();
		refreshViewsAndModels();
	}

	protected void findView(View basicInfoView) {
		// school
		schoolContainer = (RelativeLayout) basicInfoView
				.findViewById(R.id.school_container);
		schoolTextView = (TextView) basicInfoView
				.findViewById(R.id.school_textview);
		// apart
		apartContainer = (RelativeLayout) basicInfoView
				.findViewById(R.id.apart_container);
		apartTextView = (TextView) basicInfoView
				.findViewById(R.id.apart_textview);
		// room
		roomContainer = (RelativeLayout) basicInfoView
				.findViewById(R.id.room_container);
		roomEdit = (ClearEditText) basicInfoView
				.findViewById(R.id.room_edittext);
		findExtraView();
	}

	protected abstract void findExtraView();

	protected void setListener() {
		schoolContainer.setOnClickListener(this);
		apartContainer.setOnClickListener(this);
		setEdittextFocus(roomContainer, roomEdit);
		schoolTextView.addTextChangedListener(this);
		apartTextView.addTextChangedListener(this);
		roomEdit.addTextChangedListener(this);
		setExtraListener();
	}

	protected abstract void setExtraListener();

	protected void refreshViewsAndModels() {
		boolean isBind = F.isBind();
		school = isBind ? F.mBindInfo.getSchool() : null;
		apart = isBind ? F.mBindInfo.getApart() : null;
		String roomNumBind = isBind ? F.mBindInfo.getRoomNum() : "";

		setText(schoolTextView, isBind ? school.getSchoolName() : "");
		setText(apartTextView, isBind ? apart.getApartName() : "");
		setText(roomEdit, roomNumBind);
		showUnitPrice();

		schoolContainer.setEnabled(!isBind);
		apartContainer.setEnabled(!isBind);
		roomEdit.setEnabled(!isBind);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.school_container:
			switchActivityForResult(ActChooseSchool.class,
					RequestCode.CHOOSE_SCHOOL, null);
			break;
		case R.id.apart_container:
			if (school == null) {
				showToast(R.string.hint_choose_school_first);
				return;
			}
			Bundle extra = new Bundle();
			extra.putString(Key.SCHOOL_ID, school.getSchoolID());
			switchActivityForResult(ActChooseApart.class,
					RequestCode.CHOOSE_BUILDING, extra);
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
				String schoolJson = data.getStringExtra(Key.SCHOOL_JSON);
				School selectSchool = fromJson(schoolJson, School.class);
				boolean resetApart = school != null
						&& !selectSchool.getSchoolID().equals(
								school.getSchoolID());
				if (resetApart) {
					apart = null;
					setText(apartTextView, "");
				}
				school = selectSchool;
				setText(schoolTextView, school.getSchoolName());
				showUnitPrice();
				break;
			case RequestCode.CHOOSE_BUILDING:
				String apartJson = data.getStringExtra(Key.APART_JSON);
				apart = fromJson(apartJson, Apart.class);
				setText(apartTextView, apart.getApartName());
				break;
			default:
				break;
			}
		}
	}

	protected void showUnitPrice() {
		// override only in recharge activity
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
		refreshButtonStatus();
	}

	protected void refreshButtonStatus() {
		boolean isBasicInfoEmpty = isBasicInfoEmpty();
		refreshButtonStatus(isBasicInfoEmpty);
	}

	private boolean isBasicInfoEmpty() {
		roomNum = roomEdit.getText().toString();
		return school == null || apart == null || isEmpty(roomNum);
	}

	protected abstract void refreshButtonStatus(boolean basicInfoNotEmpty);

	protected void checkAvailable() {
		doCheckVersionStatus();
	}

	private void doCheckVersionStatus() {
		loadDataVolley(true, Method.QUERY_CAN_USE, "?versioncode="
				+ F.VERSION_CODE);
	}

	private void doCheckRoomExist() {
		String pSchoolID = school.getSchoolID();
		String pApartID = apart.getApartID();
		String pRoomNum = roomNum;
		loadDataXMLRPC(Method.QUERY_SCORE, pSchoolID, pApartID, pRoomNum);
	}

	@Override
	protected void disposeResult(String apiName, String content) {
		super.disposeResult(apiName, content);
		if (apiName.equals(Method.QUERY_CAN_USE)) {
			VersionServer version = fromJson(content, VersionServer.class);
			if (version.isCanUse()) {
				// if version can use,then check room exist
				doCheckRoomExist();
			} else {
				showToast(version.getDescription());
			}
		} else if (apiName.equals(Method.QUERY_SCORE)) {
			doAfterCheckOK(content);
		}
	}

	protected abstract void doAfterCheckOK(String content);

}
