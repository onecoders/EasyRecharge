package my.project.easyrecharge.adapter;

import my.project.easyrecharge.model.IndexMenu;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AdaMenu extends ArrayAdapter<IndexMenu> {

	public AdaMenu(Context context, int resource, IndexMenu[] objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {

		}
		return convertView;
	}

}
