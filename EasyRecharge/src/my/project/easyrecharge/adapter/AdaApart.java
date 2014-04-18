package my.project.easyrecharge.adapter;

import java.util.List;

import my.project.easyrecharge.R;
import my.project.easyrecharge.model.Apart;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaApart extends ArrayAdapter<Apart> {

	public AdaApart(Context context, List<Apart> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_apart, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Apart item = getItem(position);
		holder.name.setText(item.getApartName());
		return convertView;
	}

	private final class ViewHolder {
		TextView name;

		public ViewHolder(View v) {
			name = (TextView) v.findViewById(R.id.name);
		}
	}

}
