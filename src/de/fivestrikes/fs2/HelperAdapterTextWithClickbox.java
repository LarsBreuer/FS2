package de.fivestrikes.fs2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.content.res.Resources;

public class HelperAdapterTextWithClickbox extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	String game_id = null;
	String strInput = null;
	HelperSQL sqlHelper = null;
	HelperLayout lytHelper = null;
	int[] activityList = null;
	
	public HelperAdapterTextWithClickbox(Context context, String[] values, String id, Bundle args) {
		super(context, R.layout.row_simple_text, values);
		this.context = context;
		this.values = values;
		game_id = id;
		sqlHelper = new HelperSQL(context);
		lytHelper = new HelperLayout();
		activityList = sqlHelper.getActivityListFromActivityString();

		if (args != null) {

			strInput = args.getString("InputString");
			
		}
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		final CheckBox cbListCheck;
		
		//Layout definieren
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_checkbox, parent, false);
		cbListCheck = (CheckBox) rowView.findViewById(R.id.rowCheckbox);
		TextView textView = (TextView) rowView.findViewById(R.id.rowText);
		
		// Text einrichten
		textView.setText(values[position]);
		
		// Checkboxes einrichten
		
		if (strInput.equals("InputDepth")) {
			
			switch(position){
		    	
			case 0:
				
				if (sqlHelper.getGameInputPlayer(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;
				
			case 1:
				
				if (sqlHelper.getGameInputArea(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;
				
			case 2:
				
				if (sqlHelper.getGameInputThrowingTechnique(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;

			case 3:
				
				if (sqlHelper.getGameInputAssist(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;
				
			case 4:
				
				if (sqlHelper.getGameInputDefense(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;
				
			case 5:
				
				if (sqlHelper.getGameInputMark(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;
				
			case 6:
		
				if (sqlHelper.getGameInputTechFault(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;
			
			case 7:
			
				if (sqlHelper.getGameInputText(game_id).equals(1)) {
					cbListCheck.setChecked(true);
				} else {
					cbListCheck.setChecked(false);
				}
				break;
			
			}
		}
		
		if (strInput.equals("TickerActivity")) {
			
			if (activityList[position] == 1) {
				cbListCheck.setChecked(true);
			} else {
				cbListCheck.setChecked(false);
			}
			
		}
		
		cbListCheck.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				if (cbListCheck.isChecked()) {
					
					if (strInput.equals("InputDepth")) lytHelper.InputDepth(context, game_id, position, true);
					if (strInput.equals("TickerActivity")) {
						activityList[position] = 1;
						sqlHelper.updateStatGameActivities(activityList, null);
					}

				} else if (!cbListCheck.isChecked()) {

					if (strInput.equals("InputDepth")) lytHelper.InputDepth(context, game_id, position, false);
					if (strInput.equals("TickerActivity")) {
						activityList[position] = 0;
						sqlHelper.updateStatGameActivities(activityList, null);
					}

				}				
			}
		});

		return rowView;
	}
}