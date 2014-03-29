package my.project.easyrecharge.adapter;

import java.util.List;

import my.project.easyrecharge.R;
import my.project.easyrecharge.model.School;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaSchool extends ArrayAdapter<School> {

	public AdaSchool(Context context, List<School> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_school, null);
		}
		ImageView img = (ImageView) convertView.findViewById(R.id.school_img);
		//
		TextView name = (TextView) convertView.findViewById(R.id.school_name);
		//
		return convertView;
	}

}
