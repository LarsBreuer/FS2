package de.fivestrikes.fs2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.Resources;

public class HelperAdapterMainSmart extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	
	public HelperAdapterMainSmart(Context context, String[] values) {
		super(context, R.layout.row_main_smart, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_main_smart, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.rowText);
		ImageView icon = icon = (ImageView) rowView.findViewById(R.id.rowIcon);
		
		textView.setText(values[position]);
		
		if (position == 0) icon.setImageResource(R.drawable.team);
		if (position == 1) icon.setImageResource(R.drawable.game);
		if (position == 2) icon.setImageResource(R.drawable.game);
		if (position == 3) icon.setImageResource(R.drawable.statistic);
		if (position == 4) icon.setImageResource(R.drawable.login);

		return rowView;
	}
}