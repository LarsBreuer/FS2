package de.fivestrikes.fs2;

import java.util.ArrayList;
import java.util.Calendar;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SmartStatGameActivity extends ListActivity {

	HelperLayout lytHelper = null;
	SmartStatGameActivity activity = null;
	Bundle args = null;
	View view;
	String strInput = null;
	Integer ticker_activity_id = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_with_button);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		view = findViewById(android.R.id.content);
		activity = this;
		
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		
/* Layout festlegen */
		
		lytHelper.lytStatTickerActicity(args, view, null, activity);
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();	  

	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		lytHelper.lytStatTickerActicity(args, view, null, activity);
	    	
	}
	
/* Tickerauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		
		ticker_activity_id = (int) (long) id;
		strInput = "ActivityText";
		
		args.putString("InputString", strInput);
		args.putInt("TickerActivityID", ticker_activity_id);
		
		Intent i = new Intent(SmartStatGameActivity.this, SmartTickerText.class);
		i.putExtras(args);
		startActivity(i);
		
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