package de.fivestrikes.fs2;

import java.util.Date;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import java.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartGameEdit extends Activity {

	int year;
	int month;
	int day;
	static final int DATE_DIALOG_ID = 999;
	private static final int GET_CODE = 0;
	HelperSQL sqlHelper=null;
	HelperLayout lytHelper = null;
	View view = null;
	String game_id = null;
	String team_id = null;
	String str_game_date = null;
	Integer home_or_away = null;
	Date game_date = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_edit);
		view = findViewById(android.R.id.content);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		game_id=getIntent().getStringExtra("GameID");
		
/* Layout festlegen */
		
		lytHelper.lytGameEdit(game_id, view, null, null);
		Button btn_game_date = (Button) view.findViewById(R.id.game_date);
		
		// Button Datum
		btn_game_date.setOnClickListener(new View.OnClickListener() {
			
	            @Override
	            public void onClick(View v) {
	            	
	            		showDialog(DATE_DIALOG_ID);
	            	
	            }
	        });

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

	}
	
/*
 * 
 * Date Picker definieren
 *
 */

/** TODO -4- => DatePicker Dialog in Helper Layout bringen, Problem: onCreateDialog "must override or implement a supertype method"  
 *					Zurzeit funktioniert nicht das zweite Mal das Datum aufrufen nicht, wenn man also datum eingestellt hat und dann direkt noch einmal den Button klickt, öffnet sich das Fenster nicht. 
 **/
	
	@Override
	protected Dialog onCreateDialog(int id) {
			
		switch (id) {
			
			case DATE_DIALOG_ID:
				
				Button btn_game_date = (Button) findViewById(R.id.game_date);
				String str_date = btn_game_date.getText().toString();
				String[] parts = str_date.split("\\.");
				day = Integer.parseInt(parts[0]);
				month = Integer.parseInt(parts[1])-1;
				year = Integer.parseInt(parts[2]);
				// set date picker as current date
/** TODO -4- => Eigenes Layout für DatePickerDialog */  
				return new DatePickerDialog(this, datePickerListener, year, month,day);

		}
		
		return null;
	}
		
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
			
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker datePickerView, int selectedYear, int selectedMonth, int selectedDay) {
			
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
		
			// Beschriftung des Buttons ändern
			Button btn_game_date = (Button) findViewById(R.id.game_date);
			btn_game_date.setText(new StringBuilder()
				.append(day).append(".")
				.append(month + 1).append(".")
				.append(year));
			
			game_date=new Date(year-1900, month, day);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	    		str_game_date = dateFormat.format(game_date);
			sqlHelper.updateGame(game_id, null, null, null, null, null, str_game_date, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
			lytHelper.lytGameEdit(game_id, view, null, null);
			
		}
	};
	
/* Ergebnis der Sub-Activity "Clubauswahl" abfragen */

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
					
		if (requestCode == GET_CODE) {
			if (resultCode == RESULT_OK) {
				
				Bundle args = data.getExtras();
				home_or_away = args.getInt("HomeOrAway");
				game_id = args.getString("GameID");
				team_id = args.getString("TeamID");
				
				if (home_or_away.equals(1)) {
					
					sqlHelper.updateGame(game_id, Integer.parseInt(team_id), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
					lytHelper.lytGameEdit(game_id, view, null, null);
					
				} else {
					
					sqlHelper.updateGame(game_id, null, Integer.parseInt(team_id), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
					lytHelper.lytGameEdit(game_id, view, null, null);
					
				}
			}
		}
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