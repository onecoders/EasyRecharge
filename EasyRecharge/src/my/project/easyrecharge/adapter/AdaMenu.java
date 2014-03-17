package my.project.easyrecharge.adapter;

import my.project.easyrecharge.R;
import my.project.easyrecharge.model.IndexMenu;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaMenu extends ArrayAdapter<IndexMenu> {

	public AdaMenu(Context context, IndexMenu[] objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		IndexMenu item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.view_item_menu, null);
		}
		ImageView menuIcon = (ImageView) convertView
				.findViewById(R.id.menu_icon);
		menuIcon.setImageResource(item.getIconId());
		TextView menuTitle = (TextView) convertView
				.findViewById(R.id.menu_title);
		menuTitle.setText(item.getTitle());
		return convertView;
	}

}
