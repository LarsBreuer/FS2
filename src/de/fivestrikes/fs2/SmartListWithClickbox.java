package de.fivestrikes.fs2;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SmartListWithClickbox extends ListActivity {
	
	HelperLayout lytHelper = null;
	HelperSQL sqlHelper = null;
	TextView text = null;
	String game_id = null;
	String strInput = null;
	Bundle args;
	String[] values = null;
	int[] activityList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_with_text);

/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
	        
		if (args != null) {
			
			game_id = args.getString("GameID");
			strInput = args.getString("InputString");
			
		}
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		lytHelper = new HelperLayout();
		
/* Text und Button definieren */
		
		TextView headline = (TextView) findViewById(R.id.headline);
		TextView text = (TextView) findViewById(R.id.text);
		LinearLayout lyt_button = (LinearLayout) findViewById(R.id.lyt_button);
		lyt_button.removeAllViews();
		Button btn_all_in = (Button) findViewById(R.id.all_in);
		Button btn_all_out = (Button) findViewById(R.id.all_out);
		Button btn_back = (Button) findViewById(R.id.back);
		
		if (strInput.equals("InputDepth")) {

			headline.setText(getString(R.string.input_depth)); 
			text.setText(getString(R.string.text_input_depth)); 
			
		}
		
		if (strInput.equals("TickerActivity")) {

			headline.setText(getString(R.string.input_activity)); 
			text.setText(getString(R.string.text_input_activity)); 
			
		}
		
/* Zelltexte anlegen */
		
		if (strInput.equals("InputDepth")) {
			
			values = new String[] { getResources().getString(R.string.player), 
					getResources().getString(R.string.area),
					getResources().getString(R.string.throwing_technique),
					getResources().getString(R.string.input_assist),
					getResources().getString(R.string.input_defense),
					getResources().getString(R.string.input_mark),
					getResources().getString(R.string.input_tech_fault),
					getResources().getString(R.string.input_text)};
			
		}
		
		if (strInput.equals("TickerActivity")) {
			
			values = new String[] { getResources().getString(R.string.possession), 
					getResources().getString(R.string.shot_on_goal),
					getResources().getString(R.string.seven_goal),
					getResources().getString(R.string.fb_goal),
					getResources().getString(R.string.miss),
					getResources().getString(R.string.seven_miss),
					getResources().getString(R.string.fb_miss),
					getResources().getString(R.string.gk_save),
					getResources().getString(R.string.seven_save),
					getResources().getString(R.string.fb_save),
					getResources().getString(R.string.goalkeeper_goal_against),
					getResources().getString(R.string.seven_goal_against),
					getResources().getString(R.string.fb_goal_against),
					getResources().getString(R.string.assists_goal),
					getResources().getString(R.string.assists),
					getResources().getString(R.string.defense_successful),
					getResources().getString(R.string.defensive_error),
					getResources().getString(R.string.block_successful),
					getResources().getString(R.string.block_error),
					getResources().getString(R.string.foul),
					getResources().getString(R.string.tech_fault),
					getResources().getString(R.string.Fehlpass),
					getResources().getString(R.string.steps),
					getResources().getString(R.string.three_seconds_rule),
					getResources().getString(R.string.Doppeldribbel),
					getResources().getString(R.string.Fuss),
					getResources().getString(R.string.Zeitspiel),
					getResources().getString(R.string.Kreis),
					getResources().getString(R.string.Stuermerfoul),
					getResources().getString(R.string.yellow_card),
					getResources().getString(R.string.two_minutes),
					getResources().getString(R.string.two_plus_two),
					getResources().getString(R.string.red_card),
					getResources().getString(R.string.sub_in),
					getResources().getString(R.string.sub_out),
					getResources().getString(R.string.timeout),
					getResources().getString(R.string.tactic_60),
					getResources().getString(R.string.tactic_51),
					getResources().getString(R.string.tactic_42),
					getResources().getString(R.string.tactic_321),
					getResources().getString(R.string.guarding)};
			
		}
		
		HelperAdapterTextWithClickbox adapter = new HelperAdapterTextWithClickbox(SmartListWithClickbox.this, values, game_id, args);
		setListAdapter(adapter);
		
		btn_all_in.setOnClickListener(new View.OnClickListener() {
			
			@Override
	            public void onClick(View v) {

				if (strInput.equals("InputDepth")) {
					for (int x = 0; x <= 7; x++) {
						lytHelper.InputDepth(SmartListWithClickbox.this, game_id, x, true);
					}
				}
				if (strInput.equals("TickerActivity")) {
					for (int x = 0; x <= 40; x++) {
						sqlHelper.updateStatGameActivities(null, 1);
					}
				}
				HelperAdapterTextWithClickbox adapter = new HelperAdapterTextWithClickbox(SmartListWithClickbox.this, values, game_id, args);
				setListAdapter(adapter);
				
			}
		});
		
		btn_all_out.setOnClickListener(new View.OnClickListener() {
			
			@Override
	            public void onClick(View v) {

				if (strInput.equals("InputDepth")) {
					for (int x = 0; x <= 7; x++) {
						lytHelper.InputDepth(SmartListWithClickbox.this, game_id, x, false);
					}
				}
				if (strInput.equals("TickerActivity")) {
					for (int x = 0; x <= 40; x++) {
						sqlHelper.updateStatGameActivities(null, 0);
					}
				}
				HelperAdapterTextWithClickbox adapter = new HelperAdapterTextWithClickbox(SmartListWithClickbox.this, values, game_id, args);
				setListAdapter(adapter);
				
			}
		});
		
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
	            public void onClick(View v) {

				SmartListWithClickbox.this.finish();
				
			}
		});
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	@Override
	public void onListItemClick(ListView list, View row, int position, long id) {

/** TODO -1- => Auswahl bei kostenloser App bei manchen Einstellungen nicht möglich. */
		Context ctxt = list.getContext();
		activityList = sqlHelper.getActivityListFromActivityString();
		
		CheckBox cbListCheck;
		cbListCheck = (CheckBox) row.findViewById(R.id.rowCheckbox);
			
		if (cbListCheck.isChecked()) {
			cbListCheck.setChecked(false);
			if (strInput.equals("InputDepth")) lytHelper.InputDepth(ctxt, game_id, position, false);
			if (strInput.equals("TickerActivity")) {
				activityList[position] = 0;
				sqlHelper.updateStatGameActivities(activityList, null);
			}
		} else {
			cbListCheck.setChecked(true);
			if (strInput.equals("InputDepth")) lytHelper.InputDepth(ctxt, game_id, position, true);
			if (strInput.equals("TickerActivity")) {
				activityList[position] = 1;
				sqlHelper.updateStatGameActivities(activityList, null);
			}
		}
	}
	
	@Override  
	public void onBackPressed() {
		
		super.onBackPressed();
		
		
	}
	
/* Action Bar einrichten */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
        
			case android.R.id.home: 
				onBackPressed();
				break;

			default:
				return super.onOptionsItemSelected(item);
      	  	
		}
        
		return true;
        
	}
	
	private void CreateMenu(Menu menu) {
		
	}
		
}