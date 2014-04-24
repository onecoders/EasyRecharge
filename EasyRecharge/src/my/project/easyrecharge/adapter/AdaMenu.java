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
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.view_item_menu, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		IndexMenu item = getItem(position);
		holder.setContent(item);
		return convertView;
	}

	private final class ViewHolder {
		ImageView menuIcon;
		TextView menuTitle;

		public ViewHolder(View v) {
			menuIcon = (ImageView) v.findViewById(R.id.menu_icon);
			menuTitle = (TextView) v.findViewById(R.id.menu_title);
		}

		public void setContent(IndexMenu item) {
			menuIcon.setImageResource(item.getIconId());
			menuTitle.setText(item.getTitle());
		}
	}

}
