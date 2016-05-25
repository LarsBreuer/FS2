package de.fivestrikes.fs2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.res.Resources;
import android.graphics.Color;

public class HelperAdapterStatPlayer extends BaseAdapter {
	private final Context context;
	Resources res;
	private final ArrayList mData;
	HelperFunction fctHelper = null;
	
	public HelperAdapterStatPlayer(Context context, Map<String, String> map) {
		
		this.context = context;
		mData = new ArrayList();
		mData.addAll(map.entrySet());
		fctHelper=new HelperFunction();

	}
	
	@Override
	public int getCount() {
		return mData.size();
	}
	
	@Override
	public Map.Entry<String, String> getItem(int position) {
		return (Map.Entry) mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		res = context.getResources();
		Map.Entry<String, String> item = getItem(position);
		String label = item.getKey();
		String player_stat = item.getValue();
		
		// Layout der Reihe einrichten
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int xml_type = R.layout.row_stat_player;
		// Falls Überschrift, ändere das Layout der Tabellenzeile
		if (player_stat.equals("")) {
			xml_type = R.layout.row_stat_player_head;
		}
		View rowView = inflater.inflate(xml_type, parent, false);
		
		// Versuche und Quote umschreiben
		if (label.equals(res.getString(R.string.seven_attempts)) ||
				label.equals(res.getString(R.string.fb_attempts)) ||
				label.equals(res.getString(R.string.Sprungwurf_attempts)) ||
				label.equals(res.getString(R.string.Schlagwurf_attempts)) ||
				label.equals(res.getString(R.string.Laufwurf_attempts)) ||
				label.equals(res.getString(R.string.Fallwurf_attempts)) ||
				label.equals(res.getString(R.string.Hüftwurf_attempts)) ||
				label.equals(res.getString(R.string.kempa_attempts)) ||
				label.equals(res.getString(R.string.Dreher_attempts)) ||
				label.equals(res.getString(R.string.Heber_attempts)) ||
				label.equals(res.getString(R.string.Leger_attempts)) ||
				label.equals(res.getString(R.string.Abknickwurf_attempts))) {
			label = res.getString(R.string.attempts);
		}
		
		if (label.equals(res.getString(R.string.seven_percentage)) ||
				label.equals(res.getString(R.string.fb_percentage)) ||
				label.equals(res.getString(R.string.Sprungwurf_percentage)) ||
				label.equals(res.getString(R.string.Schlagwurf_percentage)) ||
				label.equals(res.getString(R.string.Laufwurf_percentage)) ||
				label.equals(res.getString(R.string.Fallwurf_percentage)) ||
				label.equals(res.getString(R.string.Hüftwurf_percentage)) ||
				label.equals(res.getString(R.string.kempa_percentage)) ||
				label.equals(res.getString(R.string.Dreher_percentage)) ||
				label.equals(res.getString(R.string.Heber_percentage)) ||
				label.equals(res.getString(R.string.Leger_percentage)) ||
				label.equals(res.getString(R.string.Abknickwurf_percentage))) {
			label = res.getString(R.string.percentage);
		}
		
		if (label.equals(res.getString(R.string.seven_throws_str)) ||
				label.equals(res.getString(R.string.fb_throws_str))) {
			label = res.getString(R.string.throws_str);
		}
		
		if (label.equals(res.getString(R.string.throws_percentage)) ||
				label.equals(res.getString(R.string.seven_throws_percentage)) ||
				label.equals(res.getString(R.string.fb_throws_percentage))) {
			label = res.getString(R.string.percentage);
		}
		
		// Inhalte der Tabellenzeilen eintragen
		if (xml_type == R.layout.row_stat_player_head) {
			
			TextView labelTextView = (TextView) rowView.findViewById(R.id.stat_player_label);
			labelTextView.setText(String.valueOf(label));
			
		} else {
			
			TextView labelTextView = (TextView) rowView.findViewById(R.id.stat_player_label);
			TextView playerTextView = (TextView) rowView.findViewById(R.id.stat_player_stat);
			labelTextView.setText(String.valueOf(label));
			// Spielzeit umschreiben
			
			if (label.equals(res.getString(R.string.playing_time))) {
				playerTextView.setText(fctHelper.updateTimer(Float.parseFloat(player_stat)));
			} else {
				playerTextView.setText(player_stat);
			}
			
			// Text einrücken, falls Versuch oder Quote
			if (label.equals(res.getString(R.string.attempts)) || label.equals(res.getString(R.string.percentage)) || label.equals(res.getString(R.string.throws_str))) {
				int leftPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, res.getDisplayMetrics());
				labelTextView.setPadding(leftPadding, 0, 0, 0);
			}
			
			if (label.equals(res.getString(R.string.percentage))) {
				playerTextView.setText(player_stat + "%");
			}
			
		}

		return rowView;
	}
}