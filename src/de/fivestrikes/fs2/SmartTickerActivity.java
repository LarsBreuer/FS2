package de.fivestrikes.fs2;

import java.util.Date;
import android.app.ActionBar;
import android.app.TabActivity;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TabHost.TabSpec;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.database.Cursor;

public class SmartTickerActivity extends TabActivity {

	HelperLayout lytHelper = null;
	HelperSQL sqlHelper = null;
	View view = null;
	String game_id = null;
	public static Long elapsedTime=Long.parseLong("0");
	private TabHost mTabHost;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.smart_ticker);
		view = findViewById(android.R.id.content);
		
/* Helper generieren */
		
		lytHelper = new HelperLayout();
		sqlHelper = new HelperSQL(this);
		
/* Daten aus Activity laden */ 
	        
		game_id=getIntent().getStringExtra("GameID");
		
/* Layout einrichten */
		
		// Tabs einrichten
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		setupTab(new TextView(this), getString(R.string.actions));
		setupTab(new TextView(this), getString(R.string.ticker));
		mTabHost.setCurrentTab(0);
		
		// Layout Anzeigentafel festlegen
		
		lytHelper.lytTickerBoard(game_id, view, null, null);	

/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

	}
	
	@Override
	public void onResume() {

		super.onResume();
		refreshTickerButton();

	}
	
	public void startStop() {
		
		lytHelper.timerStartStop();
		
	}
	
	public void refreshTickerButton() {
		
		Integer possession = sqlHelper.getGamePossession(game_id);
		
/* Tore einstellen */
		((Button) view.findViewById(R.id.btn_goals_home)).setText(String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 1, null)));
		((Button) view.findViewById(R.id.btn_goals_away)).setText(String.valueOf(sqlHelper.count_ticker_goals(game_id, null, 0, null)));
		
/* Button Ballbesitz stellen */
		
	    	switch(possession){
	    	
			case 1:
				((Button) view.findViewById(R.id.btn_goals_home)).setBackgroundResource(R.drawable.button_result_active);
				((Button) view.findViewById(R.id.btn_goals_away)).setBackgroundResource(R.drawable.button_result_inactive);
				break;
				
			case 0:
				((Button) view.findViewById(R.id.btn_goals_away)).setBackgroundResource(R.drawable.button_result_active);
				((Button) view.findViewById(R.id.btn_goals_home)).setBackgroundResource(R.drawable.button_result_inactive);
				break;
				
			case 2:
				((Button) view.findViewById(R.id.btn_goals_home)).setBackgroundResource(R.drawable.button_result_inactive);
				((Button) view.findViewById(R.id.btn_goals_away)).setBackgroundResource(R.drawable.button_result_inactive);
				break;
				
	    	}
	    	
	    	lytHelper.setYellowCards();
		
	}
	
/*
 * 
 * Tabs einrichten
 *
 */
	
	private void setupTab(final View view, final String tag) {

		View tabview = createTabView(mTabHost.getContext(), tag);
		Resources res = getResources(); 
		Intent i;
		TabSpec setContent;

		if (tag.compareTo(res.getString(R.string.actions)) == 0) {
			
			i = new Intent().setClass(this, SmartTickerMenu.class);
			i.putExtra("GameID", game_id);
			setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(i);
			mTabHost.addTab(setContent);
			
		}

		if (tag.compareTo(res.getString(R.string.ticker)) == 0) {
			
			i = new Intent().setClass(this, SmartTickerList.class);
			i.putExtra("GameID", game_id);
			setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(i);
			mTabHost.addTab(setContent);
			
		}
	}

	private static View createTabView(final Context context, final String text) {
		
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		
		return view;
		
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