package my.project.easyrecharge.activity;

import my.project.easyrecharge.F;
import my.project.easyrecharge.R;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.contants.RequestCode;
import my.project.easyrecharge.model.Apart;
import my.project.easyrecharge.model.School;
import my.project.easyrecharge.view.ClearEditText;
import android.content.Intent;
import android.os.AsyncTask;
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
	protected String roomNo;

	protected void initBasicInfoViews(View basicInfoView) {
		findView(basicInfoView);
		setListener();
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
				School selectSchool = F.fromJson(schoolJson, School.class);
				if (school != null
						&& selectSchool.getSchoolID() != school.getSchoolID()) {
					apart = null;
					apartTextView.setText("");
				}
				school = selectSchool;
				schoolTextView.setText(school.getSchoolName());
				break;
			case RequestCode.CHOOSE_BUILDING:
				String apartJson = data.getStringExtra(Key.APART_JSON);
				apart = F.fromJson(apartJson, Apart.class);
				apartTextView.setText(apart.getApartName());
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
		roomNo = roomEdit.getText().toString();
		return school == null || apart == null || isEmpty(roomNo);
	}

	protected abstract void resetButtonEnabled(boolean basicInfoNotEmpty);

	protected void checkFirst() {
		new CheckExistTask().execute();
	}

	class CheckExistTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showProgressHUD();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			return checkExist();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			dismissProgressHUD();
			if (result) {
				doAfterCheckOK();
			} else {
				showToast(R.string.bind_info_wrong);
			}
		}

	}

	private boolean checkExist() {
		// do real check
		return false;
	}

	protected abstract void doAfterCheckOK();

}
