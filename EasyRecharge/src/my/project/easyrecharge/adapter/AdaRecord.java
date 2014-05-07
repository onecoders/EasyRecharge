package my.project.easyrecharge.adapter;

import java.util.List;

import my.project.easyrecharge.R;
import my.project.easyrecharge.model.Record;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaRecord extends ArrayAdapter<Record> {

	public AdaRecord(Context context, List<Record> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_record, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Record item = getItem(position);
		holder.setContent(item);
		return convertView;
	}

	private final class ViewHolder {
		TextView time;
		TextView record;

		public ViewHolder(View v) {
			time = (TextView) v.findViewById(R.id.time);
			record = (TextView) v.findViewById(R.id.message);
		}

		public void setContent(Record item) {
			time.setText(item.getPosTime());
			record.setText(item.getPosMoney());
		}
	}

}
