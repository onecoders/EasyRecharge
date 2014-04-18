package my.project.easyrecharge.activity;

import java.lang.reflect.Type;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import my.project.easyrecharge.F;
import my.project.easyrecharge.F.METHOD;
import my.project.easyrecharge.R;
import my.project.easyrecharge.adapter.AdaSchool;
import my.project.easyrecharge.contants.Key;
import my.project.easyrecharge.model.School;
import my.project.easyrecharge.view.AlphaView;
import my.project.easyrecharge.view.AlphaView.OnAlphaChangedListener;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

/**
 * School Choose Page
 * 
 * @author roy
 * @email onecoders@gmail.com
 * 
 * @TODO 只获取学校的id/name/icon信息，用于显示和选择
 * @TODO invoke setResult() after chosen before return
 */

public class ActChooseSchool extends ActDataload implements
		OnItemClickListener, OnAlphaChangedListener {

	private ListView listView;
	private List<School> list;

	private AlphaView alphaView;
	private TextView overlay;

	private WindowManager windowManager;
	private AsyncQueryHandler queryHandler;
	private AdaSchool adapter;
	private HashMap<String, Integer> alphaIndexer;
	private OverlayThread overlayThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_choose_school);
		init();
	}

	private void init() {
		initActionBar();
		initViews();
	}

	@Override
	protected void initAbContent() {
		setAbTitle(R.string.activity_title_school_chosen);
	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.listview_school);
		listView.setOnItemClickListener(this);

		list = new ArrayList<School>();
		alphaIndexer = new HashMap<String, Integer>();
		overlayThread = new OverlayThread();
		alphaView = (AlphaView) findViewById(R.id.alphaView);
		alphaView.setOnAlphaChangedListener(this);
		initOverlay();
	}

	private void initOverlay() {
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		School school = list.get(position);
		Intent intent = new Intent();
		intent.putExtra(Key.SCHOOL_JSON, F.toJson(school));
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadContent();
	}

	private void loadContent() {
		loadDataHttp(true, METHOD.QUERY_SHCOOL, "");
	}

	@Override
	protected void disposeResult(String content) {
		super.disposeResult(content);
		list.clear();
		try {
			Type collectionType = new TypeToken<List<School>>() {
			}.getType();
			List<School> newList = F.fromJson(content, collectionType);
			list.addAll(newList);
		} catch (Exception e) {
			showToast(R.string.error_data);
		}
		if (list.size() > 0) {
			Collections.sort(list, comparator);
			initAlphaIndexer();
			setAdatper();
		}
	}

	private void initAlphaIndexer() {
		for (int i = 0; i < list.size(); i++) {
			String currentAlpha = list.get(i).getAlpha();
			String previewAlpha = (i - 1) >= 0 ? list.get(i - 1).getAlpha()
					: " ";
			if (!previewAlpha.equals(currentAlpha)) {
				String alpha = list.get(i).getAlpha();
				alphaIndexer.put(alpha, i);
			}
		}
	}

	private void setAdatper() {
		if (adapter == null) {
			adapter = new AdaSchool(this, list);
			listView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
	}

	private Handler handler = new Handler();

	private class OverlayThread implements Runnable {

		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}

	}

	@Override
	public void OnAlphaChanged(String s, int index) {
		if (s != null && s.trim().length() > 0) {
			overlay.setText(s);
			overlay.setVisibility(View.VISIBLE);
			handler.removeCallbacks(overlayThread);
			handler.postDelayed(overlayThread, 700);
			if (alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				listView.setSelection(position);
			}
		}
	}

	private Comparator<School> comparator = new Comparator<School>() {

		@Override
		public int compare(School arg0, School arg1) {
			return Collator.getInstance(Locale.CHINESE).compare(
					arg0.getSchoolName(), arg1.getSchoolName());
		}

	};

	@Override
	protected void onDestroy() {
		try {
			windowManager.removeViewImmediate(overlay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}

}
