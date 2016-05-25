package de.fivestrikes.fs2;

import android.app.ActionBar;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class SmartTickerPlayer extends ListActivity {

	HelperLayout lytHelper = null;
	HelperSQL sqlHelper=null;
	Cursor model=null;
	HelperAdapterTickerPlayer adapter = null;
	String team_id, game_id, player_id, realtime, activity_result, strInput;
	Integer activity_id, ticker_activity_id, ticker_event_id, ticker_activity_against_id, ticker_possession_id, home_or_away, tech_fault_id, goal_id, goal_7m_id, goal_fb_id, miss_id, miss_7m_id, miss_fb_id, turnover;
	View view;
	Bundle args;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
/* Helper generieren */
		
		sqlHelper=new HelperSQL(this);
		lytHelper = new HelperLayout();
		
/* Daten aus Activity laden */ 
		
		args = getIntent().getExtras();
		team_id = args.getString("TeamID");
		game_id = args.getString("GameID");
		activity_id = args.getInt("ActivityID");
		home_or_away = args.getInt("HomeOrAway");
		strInput = args.getString("InputString");
		ticker_activity_id = args.getInt("TickerActivityID");
		
		tech_fault_id = getResources().getInteger(R.integer.tech_fault_id);
		goal_id = getResources().getInteger(R.integer.goal_id);
		goal_7m_id = getResources().getInteger(R.integer.goal_7m_id);
		goal_fb_id = getResources().getInteger(R.integer.goal_fb_id);
		miss_id = getResources().getInteger(R.integer.miss_id);
		miss_7m_id = getResources().getInteger(R.integer.miss_7m_id);
		miss_fb_id = getResources().getInteger(R.integer.miss_fb_id);
		
		if (home_or_away.equals(1)) team_id = sqlHelper.getGameTeamHomeIDByID(game_id);
		if (home_or_away.equals(0)) team_id = sqlHelper.getGameTeamAwayIDByID(game_id);
		
		// Falls Abwehrspieler eingegeben werden soll und die vorhergehende Aktion ein Tor- oder Fehlwurf war, führe die Spieler der Gegnermannschaft auf
		if (strInput.equals("Defense")) {
			if (activity_id.equals(goal_id) || activity_id.equals(goal_7m_id) || activity_id.equals(goal_fb_id) ||
					activity_id.equals(miss_id) || activity_id.equals(miss_7m_id) || activity_id.equals(miss_fb_id)) {
				if (home_or_away.equals(1)) team_id = sqlHelper.getGameTeamAwayIDByID(game_id);
				if (home_or_away.equals(0)) team_id = sqlHelper.getGameTeamHomeIDByID(game_id);
			}
		}
		
		// Falls man sich im Editiermodus befindet, nimm die Mannschaft der Aktion
		if (strInput.length() > 3) { 
			if (strInput.substring(0,4).equals("Edit")) {
				home_or_away = sqlHelper.getTickerHomeOrAwayByID(String.valueOf(ticker_activity_id));
				if (home_or_away.equals(1)) team_id = sqlHelper.getGameTeamHomeIDByID(game_id);
				if (home_or_away.equals(0)) team_id = sqlHelper.getGameTeamAwayIDByID(game_id);
			}
		}
		
/* Grundlayout setzen */
		
		setContentView(R.layout.ticker_player);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	
		view = findViewById(android.R.id.content);
		SmartTickerPlayer activity = (SmartTickerPlayer) this;
		
		// Falls kein Fehlwurf eingegeben werden soll, entferne Ballverlust-Anzeige
		LinearLayout lyt_fault = (LinearLayout) view.findViewById(R.id.lyt_fault);
		if (!activity_id.equals(tech_fault_id) && !activity_id.equals(miss_id) && !activity_id.equals(miss_7m_id) && !activity_id.equals(miss_fb_id)) {
			lyt_fault.removeAllViews();
		}
		if (strInput.equals("Defense") || strInput.equals("Assist")) lyt_fault.removeAllViews();
		
/* Action Bar konfigurieren */
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
/* Layout einrichten */
		
		lytHelper.lytTickerPlayer(args, view, null, activity);	
		
/* Spielerliste einrichten */
		
		refreshContent(team_id, game_id, args);		
		
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();	  

	}
	
/* Inhalt neu laden, wenn Activity ernuet aufgerufen wird */
	
	@Override
	public void onResume() {

		super.onResume();
		refreshContent(team_id, game_id, args);
	    	
	}
	
	public void refreshContent(String team_id, String game_id, Bundle contentArgs) {
	
		args = contentArgs;
		sqlHelper = new HelperSQL(this);
		model = sqlHelper.getAllPlayerCursorByTeamID(team_id);
		startManagingCursor(model);
		adapter = new HelperAdapterTickerPlayer(SmartTickerPlayer.this, model, game_id, args);
		setListAdapter(adapter);
		        
	}
	
/*
 * 
 * Spielerauswahl 
 *
 */
	
	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
	
		// Player ID definnieren
		player_id = String.valueOf(id);
		args.putString("PlayerID", player_id);
		
		// Radiobutton abfragen, falls vorhanden (Hintergrund, ob die Aktion zu einem Ballverlust führte oder nicht)
		turnover = null;
		if ((RadioGroup) view.findViewById(R.id.radio_turnover) != null) {
			
			RadioGroup rdo_turnover = (RadioGroup) view.findViewById(R.id.radio_turnover);
			
			switch(rdo_turnover.getCheckedRadioButtonId()) {
  			
				case R.id.radio_yes:
  			
					/* Änderung des Ballbesitzes */
					turnover = 1;
					break;
  				
				case R.id.radio_no:

					break;
  				
			}
		}
		
		// Variablen übertragen
		lytHelper.updateTickerPlayer(args, view, null);
		finish();
		
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