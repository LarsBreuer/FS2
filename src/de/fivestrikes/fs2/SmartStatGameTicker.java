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

public class SmartStatGameTicker extends ListActivity {

	HelperSQL sqlHelper=null;
	Bundle args = null;
	Cursor model=null;
	HelperAdapterStatGameTicker adapter=null;
	View view;
	String game_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		view = findViewById(android.R.id.content);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		
/* Daten aus Activity laden */ 
	        
		args = getIntent().getExtras();
		game_id = args.getString("GameID");
		
/* Layout festlegen */
		
		sqlHelper=new HelperSQL(this);
		model=sqlHelper.getAllGameTickerEvent(game_id);
		startManagingCursor(model);
		adapter=new HelperAdapterStatGameTicker(SmartStatGameTicker.this, model, game_id);
		setListAdapter(adapter);
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();	  

	}
	
/* Tickerauswahl */
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {

		int ticker_event_id = (int) (long) id;
		String strInput = "EventText";
		
		args.putString("InputString", strInput);
		args.putInt("TickerEventID", ticker_event_id);
		
		Intent i = new Intent(SmartStatGameTicker.this, SmartTickerText.class);
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