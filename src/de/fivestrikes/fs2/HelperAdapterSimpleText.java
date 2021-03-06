package de.fivestrikes.fs2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.res.Resources;

public class HelperAdapterSimpleText extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	
	public HelperAdapterSimpleText(Context context, String[] values) {
		super(context, R.layout.row_simple_text, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_simple_text, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.rowText);
		textView.setText(values[position]);

		return rowView;
	}
}