package my.project.easyrecharge.adapter;

import java.util.List;

import my.project.easyrecharge.R;
import my.project.easyrecharge.model.Information;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaInfo extends ArrayAdapter<Information> {

	public AdaInfo(Context context, List<Information> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_info, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Information item = getItem(position);
		holder.time.setText(item.getUpdateTime());
		holder.message.setText(item.getMessage());
		return convertView;
	}

	private final class ViewHolder {
		TextView time;
		TextView message;

		public ViewHolder(View v) {
			time = (TextView) v.findViewById(R.id.time);
			message = (TextView) v.findViewById(R.id.message);
		}
	}

}
